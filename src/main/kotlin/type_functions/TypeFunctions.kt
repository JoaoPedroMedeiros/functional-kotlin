package type_functions

fun safeDivide(numerator: Int, denominator: Int) =
    if (denominator == 0) 0.0 else numerator.toDouble() / denominator

fun main() {
    // Essa variável é uma referência para a função safe divide
    val f = ::safeDivide

    // Variáveis com type functions também podem receber lambdas,
    // que no fim das contas também são referência para funções,
    // só que no caso são funções anônimas.
    val l: (Int, Int) -> Double = { n, d -> safeDivide(n, d) }

    // Outra coisa muito legal é que uma classe também pode implementar
    // uma type function. Ela, então, terá o método invoke que implementará
    // o que acontece na chamada da função.
    // No caso, quando a gente instanciar a classe que implementa a type
    // function, ela será uma referência para a função.
    val c = SafeDivisor()

    // Portanto, podemos chamar a mesma função através da variável
    // ou da própria função.
    // O último print mostra que no fim das contas é tudo a mesma coisa.
    println("Função chamada por variável de type function..............: ${f(5, 2)}" )
    println("Função chamada pela referência original da função.........: ${safeDivide(5, 2)}" )
    println("Função chamada por uma variável que recebeu lambda........: ${l(5, 2)}" )
    println("Função chamada por uma classe que implementa type function: ${c(5, 2)}" )
    println(f == ::safeDivide)

    // Uma coisa interessante é que type functions podem ter o nome
    // do parâmetro na assinatura. Isso deixa mais claro qual a regra de negócio
    // quando estiver trabalhando com lambdas ou anonymous functions.
    val calcularBonificacao: (salario: Double) -> Double = { it + 200 }
    println(calcularBonificacao(2000.00))
}

class SafeDivisor : (Int, Int) -> Double {

    override fun invoke(numerator: Int, denominator: Int): Double =
        safeDivide(numerator, denominator)
}