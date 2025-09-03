package br.com.babershop.server.application.usecase.servico

import br.com.babershop.server.domain.model.Servico
import br.com.babershop.server.domain.port.input.servico.CriarServicoUseCase
import br.com.babershop.server.domain.port.output.ServicoRepositoryPort
import br.com.babershop.server.domain.service.servico.ServicoValidator
import br.com.babershop.server.infrastructure.annotation.Logavel
import org.springframework.stereotype.Service

@Service
class CriarServicoUseCaseImpl(
    private val repository: ServicoRepositoryPort,
    private val validador: ServicoValidator
) : CriarServicoUseCase {
    @Logavel
    override fun executar(servico: Servico): Servico {
        validador.validarAntesDeInserir(servico)
        return repository.salvar(servico)
    }
}