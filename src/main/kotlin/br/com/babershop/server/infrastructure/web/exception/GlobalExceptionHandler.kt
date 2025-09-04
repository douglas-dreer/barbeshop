package br.com.babershop.server.infrastructure.web.exception

import br.com.babershop.server.domain.exception.CampoObrigatorioException
import br.com.babershop.server.domain.exception.ServicoJaCadastradoException
import br.com.babershop.server.domain.exception.ServicoNaoEncontradoException
import br.com.babershop.server.infrastructure.client.viacep.exception.CepInvalidoException
import br.com.babershop.server.infrastructure.client.viacep.exception.EnderecoNaoEncontradoException
import br.com.babershop.server.infrastructure.client.viacep.exception.ErroServicoExternoException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Controlador centralizado para tratamento de exceções em toda a aplicação.
 * Utiliza a anotação @RestControllerAdvice para interceptar exceções lançadas pelos @Controllers
 * e transformá-las em respostas HTTP padronizadas e consistentes para o cliente.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    /**
     * Captura exceções de regras de negócio (ex: validações manuais, formato inválido).
     * Retorna um status HTTP 400 (Bad Request) com uma mensagem clara sobre o erro.
     */
    @ExceptionHandler(
        IllegalArgumentException::class,
        IllegalStateException::class,
        ServicoJaCadastradoException::class,
        CampoObrigatorioException::class,
        CepInvalidoException::class
    )
    fun handleRegraDeNegocio(
        ex: RuntimeException,
        request: HttpServletRequest
    ): ResponseEntity<ErroDto> {
        val erroDto = ErroDto(
            status = HttpStatus.BAD_REQUEST.value(),
            erro = "Requisição Inválida",
            mensagem = ex.message,
            caminho = request.requestURI
        )
        return ResponseEntity.badRequest().body(erroDto)
    }

    /**
     * Captura exceções de validação do Spring Validation (ex: @Valid nos DTOs).
     * Retorna um status HTTP 400 (Bad Request) com detalhes sobre os campos que falharam.
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidacao(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErroDto> {
        val erros = ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Mensagem de erro não disponível")
        }

        val erroDto = ErroDto(
            status = HttpStatus.BAD_REQUEST.value(),
            erro = "Erro de Validação",
            mensagem = "Um ou mais campos estão inválidos.",
            caminho = request.requestURI,
            errosDeValidacao = erros
        )
        return ResponseEntity.badRequest().body(erroDto)
    }

    /**
     * Captura exceções quando um recurso específico não é encontrado (ex: busca por ID).
     * Retorna um status HTTP 404 (Not Found) com a mensagem da exceção.
     */
    @ExceptionHandler(
        ServicoNaoEncontradoException::class,
        EnderecoNaoEncontradoException::class // <-- Adicionado!
    )
    fun handleNaoEncontrado(
        ex: RuntimeException, // Generalizado para aceitar ambas as exceções
        request: HttpServletRequest
    ): ResponseEntity<ErroDto> {
        val erroDto = ErroDto(
            status = HttpStatus.NOT_FOUND.value(),
            erro = "Recurso Não Encontrado",
            mensagem = ex.message,
            caminho = request.requestURI,
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroDto)
    }

    /**
     * Captura exceções quando um serviço externo do qual dependemos está indisponível ou falha.
     * Retorna um status HTTP 503 (Service Unavailable) para indicar que o erro não é nosso,
     * mas a operação não pôde ser concluída.
     */
    @ExceptionHandler(ErroServicoExternoException::class)
    fun handleServicoExterno(
        ex: ErroServicoExternoException,
        request: HttpServletRequest
    ): ResponseEntity<ErroDto> {
        logger.warn("Falha ao comunicar com serviço externo: ${ex.message}")
        val erroDto = ErroDto(
            status = HttpStatus.SERVICE_UNAVAILABLE.value(),
            erro = "Serviço Indisponível",
            mensagem = "Ocorreu um problema de comunicação com um serviço externo. Tente novamente mais tarde.",
            caminho = request.requestURI
        )
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(erroDto)
    }

    /**
     * Captura qualquer outra exceção não tratada como um "fallback" de segurança.
     * Loga o erro para análise e retorna um status HTTP 500 (Internal Server Error) com uma mensagem genérica.
     */
    @ExceptionHandler(Exception::class)
    fun handleErroGenerico(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ErroDto> {
        logger.error("Erro inesperado na requisição para '${request.requestURI}'", ex)

        val erroDto = ErroDto(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            erro = "Erro Interno no Servidor",
            mensagem = "Ocorreu um erro inesperado. Tente novamente mais tarde.",
            caminho = request.requestURI
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroDto)
    }
}