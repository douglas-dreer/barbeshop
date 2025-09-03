package br.com.babershop.server.infrastructure.web.mapper

import br.com.babershop.server.domain.enums.StatusServico
import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.infrastructure.web.request.AtualizarServicoRequest
import br.com.babershop.server.infrastructure.web.request.CriarServicoRequest
import br.com.babershop.server.infrastructure.web.response.ServicoResponse
import java.time.LocalDateTime

fun ServicoResponse.toDto(): ServicoResponse = ServicoResponse(
    id = id,
    nome = nome,
    valor = valor,
    descricao = descricao,
    dataCadastro = dataCadastro,
    dataAtualizacao = dataAtualizacao,
    status = status,
)

fun Servico.toResponse(): ServicoResponse {
    return ServicoResponse(
        id = id,
        nome = nome,
        valor = valor,
        descricao = descricao,
        dataCadastro = dataCadastro,
        dataAtualizacao = dataAtualizacao,
        status = status
    )
}

fun CriarServicoRequest.toModel(): Servico {
    return Servico(
        id = 0,
        nome = nome,
        valor = valor,
        descricao = descricao,
        dataCadastro = LocalDateTime.now(),
        dataAtualizacao = LocalDateTime.now(),
        status = StatusServico.ATIVO,
    )
}

fun AtualizarServicoRequest.toModel(): Servico {
    return Servico(
        id = id,
        nome = nome,
        valor = valor,
        descricao = descricao,
        dataCadastro = LocalDateTime.now(),
        dataAtualizacao = LocalDateTime.now(),
        status = status,
    )
}