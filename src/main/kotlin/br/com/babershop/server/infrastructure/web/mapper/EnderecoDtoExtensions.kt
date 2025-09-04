package br.com.babershop.server.infrastructure.web.mapper

import br.com.babershop.server.domain.model.Endereco
import br.com.babershop.server.infrastructure.web.response.EnderecoResponse

fun Endereco.toResponse(): EnderecoResponse {
    return EnderecoResponse(
        cep =  cep,
        logradouro =  logradouro,
        complemento =  complemento,
        unidade =  unidade,
        bairro =  bairro,
        localidade =  localidade,
        uf =  uf,
        estado =  estado,
        regiao =  regiao,
        ibge =  ibge,
        gia =  gia,
        ddd =  ddd,
        siafi =  siafi
    )
}