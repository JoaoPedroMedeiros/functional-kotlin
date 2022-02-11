package dsl


class Emitiu() {

    private val clausulas: MutableList<String> = mutableListOf()

    fun maisDe(notas: Int): Emitiu {
        clausulas.add("notas > $notas")
        return this
    }

    fun menosQue(notas: Int): Emitiu {
        clausulas.add("notas < $notas")
        return this
    }

    fun somenteParaPessoaFisica(): Emitiu {
        clausulas.add("notas.clientes.sohTemPf")
        return this
    }

    fun clausulas(): List<String> {
        return clausulas
    }
}


class ObterEmpresas {

    private val clausulas: MutableList<String> = mutableListOf()

    fun comCertificadoDigitalValido(): ObterEmpresas {
        clausulas.add("(certificado_digital IS NOT NULL OR certificado_digital_vencimento > CURRENT_DATE())")
        return this
    }

    fun somenteCidadesComEmissor(): ObterEmpresas {
        clausulas.add("cidade IN ('cwb', 'bh', 'rj', 'sp')")
        return this
    }

    fun sendoQue(emitiu: Emitiu): ObterEmpresas {
        clausulas.addAll(emitiu.clausulas())
        return this
    }

    fun toSqlQuery() = """
        >SELECT *
        >FROM empresa
        >WHERE ${clausulas.joinToString("AND ") { "$it\n" }}
    """.trimIndent().trimMargin(">")
}

fun main() {
    println(
        ObterEmpresas()
            .comCertificadoDigitalValido()
            .somenteCidadesComEmissor()
            .sendoQue(
                Emitiu().menosQue(1).maisDe(5).somenteParaPessoaFisica(),
            ).toSqlQuery()
    )
}