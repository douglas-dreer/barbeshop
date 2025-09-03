package br.com.babershop.server.application.usecase.servico

import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.domain.port.input.servico.AtualizarPorIdServicoUseCase
import br.com.babershop.server.domain.port.output.ServicoRepositoryPort
import br.com.babershop.server.domain.service.servico.ServicoValidator
import br.com.babershop.server.infrastructure.annotation.Logavel
import org.springframework.stereotype.Service

@Service
class AtualizarPorIdServicoUseCaseImpl(
    private val repository: ServicoRepositoryPort,
    private val validador: ServicoValidator
) : AtualizarPorIdServicoUseCase {
    @Logavel
    override fun executar(id: Int, servico: Servico): Servico {
        validador.validarAntesDeEditar(servico)
        return repository.atualizar(servico)
    }
}