package br.com.babershop.server.application.usecase.servico

import br.com.babershop.server.domain.port.input.servico.BuscarPorIdServicoUseCase
import br.com.babershop.server.domain.port.output.ServicoRepositoryPort
import br.com.babershop.server.infrastructure.annotation.Logavel
import org.springframework.stereotype.Service

@Service
class BuscarPorIdServicoUseCaseImpl(
    private val repository: ServicoRepositoryPort,
) : BuscarPorIdServicoUseCase {
    @Logavel
    override fun executar(id: Int) = repository.buscarPorId(id)
}