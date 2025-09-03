package br.com.babershop.server.infrastructure.annotation

import org.aspectj.lang.ProceedingJoinPoint

/**
 * Define o contrato para classes que formatam objetos e argumentos de método para logging.
 * Permite desacoplar a lógica de formatação do aspect de logging.
 */
interface ObjectLogFormatter {
    fun formatarArgumentos(joinPoint: ProceedingJoinPoint): String
    fun formatarResultado(obj: Any?): String
}