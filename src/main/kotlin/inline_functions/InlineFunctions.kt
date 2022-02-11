class Cliente {

}



/**
 * Verifique o bytecode gerado na pasta build para ver a diferença das duas coisas =)
 * Dica: ctrl + shift + a e digite Show Kotlin bytecode e depois clique em decompile.
 *       Perceba a diferença entre a função inline e a não inline.
 */
fun main() {
    println("INICIO INLINE")
    "Criado como INLINE higher order function".letDelayedInline(2000) {
        println(it)
    }
    println("FIM INLINE")

    println("INICIO NOT INLINE")
    "Criado como NOT INLINE higher order function".letDelayedNotInline(2000) {
        println(it)
    }
    println("FIM NOT INLINE")
}

private inline fun <T, R> T.letDelayedInline(delay: Long, block: (T) -> R): R {
    Thread.sleep(delay)
    return block(this)
}

private fun <T, R> T.letDelayedNotInline(delay: Long, block: (T) -> R): R {
    Thread.sleep(delay)
    return block(this)
}
