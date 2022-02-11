package higher_order_functions

import Cliente

fun Cliente.algumaCoisa(): Cliente {
    println(this)
    return this
}

fun Cliente.outraCoisa(): Cliente {
    println(this)
    return this
}

/**
 * Sobrescrevendo as higher order functions tradicionais do Kotlin como o let e o
 * also de um jeitinho especial.
 */
fun main() {
    delay(3000) {
        val pureText = "joÃO Paixão"
        describeSomeone(pureText.whoWeAreTalkingAbout(), pureText.somethingAbout())
    }

    "joÃo Maroto".letDelayed(3000) { a ->
        describeSomeone(
            who = a.whoWeAreTalkingAbout(),
            somethingAboutThem = a.somethingAbout(),
            howMuch = HowMuch.MEIO
        )
    }

    "JOÃO Doidão".alsoDelayed(3000) {
        describeSomeone(
            who = this.whoWeAreTalkingAbout(),
            somethingAboutThem = this.somethingAbout(),
            howMuch = HowMuch.MUITO
        )
    }
}

/**
 * Recebe uma função por argumento que será o corpo da execução depois que o delay
 * for executado.
 */
fun delay(delay: Long, block: () -> Unit) {
    Thread.sleep(delay)
    block()
}

/**
 * Esse método faz a mesma coisa do que o let só que com um delay.
 * A ideia é brincar com high order function recebendo uma função
 * que contém um argumento.
 */
fun <T, R> T.letDelayed(delay: Long, block: (T) -> R): R {
    Thread.sleep(delay)
    return block(this)
}

/**
 * Esse método faz a mesma coisa do que o also só que com um delay.
 * A ideia é brincar com high order function recebendo uma função
 * que é uma extension function. O mais legal é ela ser uma extension
 * function de um objeto que já está numa extension function.
 * O Kotlin é lindo.
 */
fun <T, R> T.alsoDelayed(delay: Long, block: T.() -> R): R {
    Thread.sleep(delay)
    return this.block()
}

fun String.whoWeAreTalkingAbout() = substringBefore(" ")

fun String.somethingAbout() = substringAfterLast(" ")

fun String.camelCase() = "${substring(0, 1).uppercase()}${substring(1).lowercase()}"

fun <T> T?.letOrEmpty(block: T.() -> String): String = if (this != null) block() else ""

fun describeSomeone(who: String, somethingAboutThem: String, howMuch: HowMuch? = null) =
    println("${who.camelCase()} é ${howMuch.letOrEmpty { "${name.lowercase()} " }}${somethingAboutThem.lowercase()}")

enum class HowMuch {
    MUITO,
    MEIO
}