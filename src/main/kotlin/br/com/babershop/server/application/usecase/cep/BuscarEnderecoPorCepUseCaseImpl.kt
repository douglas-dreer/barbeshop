package br.com.babershop.server.application.usecase.cep

import br.com.babershop.server.domain.model.Endereco
import br.com.babershop.server.domain.port.input.cep.BuscarEnderecoPorCepUseCase
import br.com.babershop.server.domain.port.output.ViaCepPort
import br.com.babershop.server.infrastructure.annotation.Logavel
import org.springframework.stereotype.Service

@Service
class BuscarEnderecoPorCepUseCaseImpl(
    private val viaCepPort: ViaCepPort
): BuscarEnderecoPorCepUseCase {
    @Logavel
    override fun execute(
        cep: String,
        formatoCep: String
    ): Endereco {
        return viaCepPort.buscarPorCep(cep, formatoCep)
    }
}