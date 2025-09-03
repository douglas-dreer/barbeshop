package br.com.babershop.server.infrastructure.persistence.mapper

import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.infrastructure.persistence.entity.ServicoEntity
import java.time.LocalDateTime

fun Servico.toEntity(): ServicoEntity {
    return ServicoEntity(
        id = id,
        nome = nome,
        descricao = descricao,
        valor = valor,
        dataCadastro = LocalDateTime.now(),
        dataAtualizacao = LocalDateTime.now(),
        status = status,
    )
}

fun ServicoEntity.toModel(): Servico {
    return Servico(
        id = id,
        nome = nome,
        valor = valor,
        descricao = descricao,
        dataCadastro = dataCadastro,
        dataAtualizacao = dataAtualizacao,
        status = status,
    )
}