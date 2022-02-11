package scope_functions

const val tableFormat = "%-12s%-20s%-18s%-30s"

private data class ScopeFunction(
    val name: String,
    var objectReference: String? = null,
    var returnValue: String? = null,
    var isExtensionFunction: String? = null) {

    fun toTableLine(): String =
        tableFormat.format(name, objectReference, returnValue, isExtensionFunction)

    companion object {

        fun printHeader() {
            println(tableFormat.format("Function", "Object reference", "Return value", "Is extension function"))
        }
    }
}

fun main() {
    ScopeFunction.printHeader()
    println()

    printLet()
    printRunExtensionFunction()
    printRunNoExtensionFunction()
    printWith()
    printApply()
    printAlso()
}

private fun printLet() {
    println(
        "let".let {
            ScopeFunction(
                name = it,
                objectReference = "it",
                returnValue = "Lambda result",
                isExtensionFunction = "Yes"
            ).toTableLine()
        }
    )
}

private fun printRunExtensionFunction() {
    println(
        "run".run {
            ScopeFunction(
                name = this,
                objectReference = "this",
                returnValue = "Lambda result",
                isExtensionFunction = "Yes"
            ).toTableLine()
        }
    )
}

private fun printRunNoExtensionFunction() {
    println(
        run {
            ScopeFunction(
                name = "run",
                objectReference = "-",
                returnValue = "Lambda result",
                isExtensionFunction = "No: Called without context object"
            ).toTableLine()
        }
    )
}

private fun printWith() {
    println(
        with("with") {
            ScopeFunction(
                name = this,
                objectReference = "this",
                returnValue = "Lambda result",
                isExtensionFunction = "No: Takes the context object as an argument"
            )
        }.toTableLine()
    )
}

private fun printApply() {
    println(
        ScopeFunction("apply").apply {
            objectReference = "this"
            returnValue = "Context object"
            isExtensionFunction = "Yes"
        }.toTableLine()
    )
}

private fun printAlso() {
    ScopeFunction(
        name = "also",
        objectReference = "it",
        returnValue = "Context object",
        isExtensionFunction = "Yes")
        .also { print(it.toTableLine().substringBefore("object")) }
        .also { println(it.toTableLine().substringAfter("Context ")) }
}

