fun main() {
    exemplo(
        nome = "Exemplo 1",
        descricao = """
            >Funções que serão usadas para decidir por qual caminho o código vai.
            >uma função que valida se o usuário está autenticado.
        """.beautify()
    ) { exemplo1() }

    
}

private fun exemplo1() {
    listOf("123456789", "a", "b").forEach { token ->

        println("TOKEN [$token]: Iniciando operação de pagamento")

        verificarAutenticacao(token,
            autenticado = {
                "✔ Pagamento realizado com sucesso"
            },
            naoAutenticado = {
                "✖ Pagamento não realizado porque o usuário não está autenticado"
            }
        ).let(::println)

        println()
    }
}

fun <T> verificarAutenticacao(token: String, autenticado: (username: String) -> T, naoAutenticado: () -> T): T =
    if (token == "123456789") {
        autenticado("joao_pedro")
    } else naoAutenticado()

private fun exemplo(nome: String, descricao: String, block: () -> Unit) {
    println("$nome --------------------------------------")
    println(descricao)
    println("--------------------------------------------")
    println()
    block()
    println()
    println()
}

private fun String.beautify() =
    trimIndent().trimMargin(">")