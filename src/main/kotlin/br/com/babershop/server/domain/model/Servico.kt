package br.com.babershop.server.domain.model

import br.com.babershop.server.domain.enums.StatusServico
import java.time.LocalDateTime

data class Servico(
    val id: Int,
    val nome: String,
    val valor: Double,
    val descricao: String,
    val dataCadastro: LocalDateTime,
    val dataAtualizacao: LocalDateTime,
    val status: StatusServico = StatusServico.ATIVO,
)
