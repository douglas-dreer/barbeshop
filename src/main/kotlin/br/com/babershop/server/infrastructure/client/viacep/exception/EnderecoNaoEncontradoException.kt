package br.com.babershop.server.infrastructure.client.viacep.exception

import java.lang.RuntimeException

class EnderecoNaoEncontradoException(mensagem: String): RuntimeException(mensagem) {
}