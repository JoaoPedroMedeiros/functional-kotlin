/**
 * Sobrescrevendo as higher order functions tradicionais do Kotlin como o let e o
 * also de um jeitinho especial.
 */
fun main() {
    delay(3000) {
        val pureText = "joÃO Paixão"
        describeSomeone(pureText.firstWord(), pureText.lastWord())
    }

    "joÃo Lindão".letDelayed(3000) {
        describeSomeone(
            who = it.firstWord(),
            somethingAboutThem = it.lastWord()
        )
    }

    "JOÃO Doidão".alsoDelayed(3000) {
        describeSomeone(
            who = firstWord(),
            somethingAboutThem = lastWord()
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
    return block()
}

fun String.firstWord() = substringBefore(" ")

fun String.lastWord() = substringAfterLast(" ")

fun String.camelCase() = "${substring(0, 1).uppercase()}${substring(1).lowercase()}"

fun describeSomeone(who: String, somethingAboutThem: String) = println("O ${who.camelCase()} é ${somethingAboutThem.lowercase()}")