package br.com.babershop.server.application.usecase.servico

import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.domain.port.input.servico.ListarServicoUseCase
import br.com.babershop.server.domain.port.output.ServicoRepositoryPort
import br.com.babershop.server.infrastructure.annotation.Logavel
import org.springframework.stereotype.Service

@Service
class ListarServicoUseCaseImpl(
    private val repository: ServicoRepositoryPort,
) : ListarServicoUseCase {
    @Logavel
    override fun executar(): List<Servico> {
        return repository.listar()
    }
}