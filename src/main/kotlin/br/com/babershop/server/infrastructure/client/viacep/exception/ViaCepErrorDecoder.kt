package br.com.babershop.server.infrastructure.client.viacep.exception

import feign.Response
import feign.codec.ErrorDecoder

class ViaCepErrorDecoder : ErrorDecoder {
    companion object {
        val MSG_ENDERECO_NAO_ENCONTRADO = "Não foi possível encontrar nenhum endereco para o CEP solicitado"
        val MSG_ENDERECO_CAMPO_INVALIDO = "Não foi possível realizar a operação pois o campo CEP e/ou formato são inválidos"
        val MSG_ENDERECO_ERRO_INTERNO = "Ocorreu um erro interno no serviço de CEP"
    }

    private val defaultErrorDecoder = ErrorDecoder.Default()

    override fun decode(methodKey: String, response: Response): Exception {
        when (response.status()) {
            404 -> throw EnderecoNaoEncontradoException(MSG_ENDERECO_NAO_ENCONTRADO)
            400 -> throw CepInvalidoException(MSG_ENDERECO_CAMPO_INVALIDO)
            in 500..599 -> ErroServicoExternoException(MSG_ENDERECO_ERRO_INTERNO)
            else -> defaultErrorDecoder.decode(methodKey, response)
        }
        return defaultErrorDecoder.decode(methodKey, response)
    }
}