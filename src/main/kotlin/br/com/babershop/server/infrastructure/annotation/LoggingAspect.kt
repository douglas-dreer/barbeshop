package br.com.babershop.server.infrastructure.annotation

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Aspecto do Spring AOP responsável por interceptar e logar a execução de métodos
 * anotados com @Logavel.
 *
 * Sua única responsabilidade é orquestrar o processo de logging: medir o tempo,
 * delegar a formatação dos dados para um [ObjectLogFormatter] e registrar as mensagens.
 */
@Aspect
@Component
class LoggingAspect(
    private val formatter: ObjectLogFormatter
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Around("@annotation(br.com.babershop.server.infrastructure.annotation.Logavel)")
    fun logarExecucao(joinPoint: ProceedingJoinPoint): Any? {
        val nomeMetodo = joinPoint.signature.toShortString()
        val args = formatter.formatarArgumentos(joinPoint)

        log.info(">> Entrando: {} com argumentos: {}", nomeMetodo, args)

        val inicio = System.nanoTime()

        try {
            val resultado = joinPoint.proceed()
            val tempoTotalMs = (System.nanoTime() - inicio) / 1_000_000.0
            val resultadoFormatado = formatter.formatarResultado(resultado)
            log.info(
                "<< Saindo: {} | Resultado: {} | Executado em {:.3f} ms",
                nomeMetodo,
                resultadoFormatado,
                tempoTotalMs
            )
            return resultado
        } catch (e: Throwable) {
            val tempoTotalMs = (System.nanoTime() - inicio) / 1_000_000.0
            log.error("!! Exceção em {} | Executado em {:.3f} ms", nomeMetodo, tempoTotalMs, e)
            throw e
        }
    }
}