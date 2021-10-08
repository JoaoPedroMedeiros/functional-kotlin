# Kotlin Funcional

O objetivo desse projeto é resumir e exemplificar os recursos do Kotlin para a programação funcional.
Está sendo construído através do curso da Alura `Kotlin: recursos do paradigma funcional`.
Explicarei os recursos aqui no README conforme meu entendimento e farei o link com as classes que contém os exemplos.

## O que é a programação funcional

Programação funcional se resume a programar de forma que todas as funções sejam matemáticas, ou seja, os mesmos parâmetros
geram SEMPRE o mesmo resultado, independente do que esteja salvo em qualquer lugar que guarde estado. O paradigma funcional
também respeita imutabilidade, ou seja, uma função NUNCA altera uma variável global ou o valor de um parâmetro que ela recebeu.
Portanto, na programação funcional TODAS as variáveis serão imutáveis (claro que existem exceções, mas tentamos
seguir a regra ao máximo). 

## Funções são first-class values (Type Functions)

É muito importante entender esse conceito para compreender como a programação funcional se aplica em Kotlin.
Isso significa que assinaturas de funções são tipos dentro do Kotlin. Com isso, é possível que uma assinatura
de função seja o tipo de uma variável ou até que uma classe implemente uma assinatura de função. Isso é chamado
de `function type`, no português `tipo de função`.

Dessa forma, variáveis cujo tipo são assinaturas de função são ponteiros para uma função, portanto podem ser invocadas.
No fim das contas, funções declaradas da forma tradicional e variáveis com function type são exatamente a mesma coisa 
para a JVM, são ponteiros.

Quando uma classe implementa uma function type, é como se ela fosse uma classe implementando uma interface que possui
apenas um método com a assinatura da fuction type. Isso não é lindo?

[Exemplos com type functions](src/main/kotlin/TypeFunctions.kt)

### Higher Order Functions

Essa nomenclatura representa indica que uma função receberá outra por parâmetro ou retornará uma outra função. O Kotlin
tem algumas simplificações na sintaxe quando se está recebendo funções por parâmetro, por exemplo, ele não obriga o uso
de parenteses para lambdas quando elas são o último parâmetro da função. Isso deixa o código mais fluído.

É interessante também que funções recebidas por argumento podem aproveitar recursos como extension functions. É o caso
do `also`.

