package br.com.babershop.server.infrastructure.adapter

import br.com.babershop.server.domain.model.Endereco
import br.com.babershop.server.domain.port.output.ViaCepPort
import br.com.babershop.server.infrastructure.annotation.Logavel
import br.com.babershop.server.infrastructure.client.viacep.ViaCepFeignClient
import br.com.babershop.server.infrastructure.client.viacep.dto.ViaCepResponse
import br.com.babershop.server.infrastructure.client.viacep.dto.mapper.toEndereco
import br.com.babershop.server.infrastructure.client.viacep.exception.EnderecoNaoEncontradoException
import org.springframework.stereotype.Component
import java.util.Locale.getDefault

@Component
class ViaCepAdapter(
    private val client: ViaCepFeignClient
): ViaCepPort {

    @Logavel
    override fun buscarPorCep(cep: String, formato: String): Endereco {
        return client.buscarCep(cep, formato.lowercase(getDefault()))
            .also { it.lancaErroSeNecessario(cep) }
            .toEndereco()
    }

    /**
     * Função de extensão para validar a resposta do ViaCEP.
     * Deixa nosso código no adapter mais limpo e legível.
     */
    private fun ViaCepResponse.lancaErroSeNecessario(cepOriginal: String) {
        if (this.erro == true) {
            throw EnderecoNaoEncontradoException(
                "Não foi possível encontrar nenhum endereço com o CEP: $cepOriginal"
            )
        }
    }
}