package dsl

fun HtmlTag.toHtmlString(): String {
    return "<${name()}>${children().joinToString("") { it.toHtmlString() }}${if (content() != null) content() else ""}</${name()}>"
}

interface HtmlTag {

    fun name(): String

    fun children(): List<HtmlTag>

    fun content(): String?
}

class Html : HtmlTag {

    private val body: Body = Body()
    private val head: Head = Head()

    fun body(buildContent: Body.() -> Unit) = body.buildContent()

    fun head(buildContent: Head.() -> Unit) = head.buildContent()

    override fun name(): String = "dsl.html"

    override fun children(): List<HtmlTag> = listOf(head, body)

    override fun content(): String? = null
}

abstract class HtmlContainer : HtmlTag {

    private val children: MutableList<HtmlTag> = mutableListOf()

    fun div(buildContent: Div.() -> Unit) = buildContent(Div().also { children.add(it) })

    fun p(buildContent: P.() -> Unit) = buildContent(P().also { children.add(it) })

    override fun children(): List<HtmlTag> = children

    override fun content(): String? = null
}

abstract class HtmlText : HtmlTag {

    var text: String = ""

    override fun children(): List<HtmlTag> = emptyList()

    override fun content(): String = text
}

class Body : HtmlContainer() {

    override fun name(): String = "body"
}

class Div : HtmlContainer() {

    override fun name(): String = "div"
}

class Head : HtmlTag {

    override fun name(): String = "head"

    override fun children(): List<HtmlTag> = emptyList()

    override fun content(): String? = null
}

class P : HtmlText() {

    override fun name(): String = "p"
}


fun html(buildContent: Html.() -> Unit): Html = Html().also { it.buildContent() }


fun main() {
    println(
        html {
            head {
            }
            body {
                div {

                }
                div {
                    p {
                        text = "Parágrafo 1"
                    }
                    div {
                        p {
                            text = "Parágrafo 2"
                        }
                    }
                }
            }
        }.toHtmlString()
    )
}