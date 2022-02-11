package dsl

import java.math.BigDecimal
import java.time.LocalDate

data class Cliente(
    val cnpj: String,
    val regimeTributario: String,
    val comercio: Boolean,
)

fun main() {
    efetuarPagamento("12345",
        faltouSaldo = {
            println("Faltou saldo. Saldo atual $it")
        },
        boletoVencido = {
            println("Boleto venceu em $it")
        },
        codigoInvalido = { de, ate ->
            println("Código inválido no trecho $de até $ate")
        },
        riscoPequenoFraude = {
            println("Pagamento realizado mas deve ser avaliado por alguém")
        },
        riscoAltoFraude = {
            println("Pagamento não realizado devido a risco grave de fraude")
        },
        pagamentoEfetuadoSucesso = {
            println("Pagamento realizado com sucesso")
        }
    )
}

fun efetuarPagamento(
    linhaDigitavel: String,
    faltouSaldo: (saldoAtual: BigDecimal) -> Unit,
    boletoVencido: (vencimento: LocalDate) -> Unit,
    codigoInvalido: (Int, Int) -> Unit,
    riscoPequenoFraude: (motivo: String) -> Unit,
    riscoAltoFraude: (motivo: String) -> Unit,
    pagamentoEfetuadoSucesso: () -> Unit) {

    if (possivelFraude(linhaDigitavel)) {
        riscoPequenoFraude("Foi identificada uma possível fraude com o pagamento. O processo não será interrompido")
    }

    when (linhaDigitavel) {
        "1" -> pagar()
        "2" -> faltouSaldo(BigDecimal("2000"))
        else -> codigoInvalido(1, 3)
    }
}

fun pagar() {
    println("Pagou")
}

fun possivelFraude(linhaDigitavel: String): Boolean = true

