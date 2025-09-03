package br.com.babershop.server.util

import br.com.babershop.server.domain.enums.StatusServico
import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.infrastructure.persistence.entity.ServicoEntity
import br.com.babershop.server.infrastructure.web.request.AtualizarServicoRequest
import br.com.babershop.server.infrastructure.web.request.CriarServicoRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.LocalDateTime

open class ServicoFactory {
    companion object {
        fun criarServicoEntity(
            id: Int = 1,
            nome: String = "Corte de Cabelo",
            valor: Double = 30.0,
            descricao: String = "Corte de cabelo masculino tradicional",
            dataCadastro: LocalDateTime = LocalDateTime.now().minusDays(1),
            dataAtualizacao: LocalDateTime = LocalDateTime.now(),
            status: StatusServico = StatusServico.ATIVO,
        ): ServicoEntity = ServicoEntity(
            id = id,
            nome = nome,
            valor = valor,
            descricao = descricao,
            dataCadastro = dataCadastro,
            dataAtualizacao = dataAtualizacao,
            status = status,
        )
    }

    fun criarServico(
        id: Int = 1,
        nome: String = "Corte de Cabelo",
        valor: Double = 30.0,
        descricao: String = "Corte de cabelo masculino tradicional",
        dataCadastro: LocalDateTime = LocalDateTime.now().minusDays(1),
        dataAtualizacao: LocalDateTime = LocalDateTime.now(),
        status: StatusServico = StatusServico.ATIVO,
    ): Servico = Servico(
        id = id,
        nome = nome,
        valor = valor,
        descricao = descricao,
        dataCadastro = dataCadastro,
        dataAtualizacao = dataAtualizacao,
        status = status,
    )

    fun criarServicos(quantidade: Int): List<Servico> = (1..quantidade).map { criarServico() }
    fun criarServicosEntity(quantidade: Int): List<ServicoEntity> = (1..quantidade).map { criarServicoEntity() }
    fun criarCriarServicoRequest(
        nome: String = "Corte de Cabelo",
        valor: Double = 30.0,
        descricao: String = "Corte de cabelo masculino tradicional",
    ) = CriarServicoRequest(nome, valor, descricao)

    fun criarAtualizarServicoRequest(
        id: Int = 1,
        nome: String = "Corte de Cabelo",
        valor: Double = 30.0,
        descricao: String = "Corte de cabelo masculino tradicional",
        status: StatusServico = StatusServico.ATIVO,
    ) = AtualizarServicoRequest(id, nome, valor, descricao, status)

    /**
     * Cria um objet JSON de um objeto CriarServicoRequest.
     */
    fun criarJsonCriarServicoRequest(
        nome: String = "Corte de Cabelo",
        valor: Double = 30.0,
    ): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(criarCriarServicoRequest(nome, valor))
    }


}