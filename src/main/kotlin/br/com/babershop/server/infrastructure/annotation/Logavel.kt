package br.com.babershop.server.infrastructure.annotation

/**
 * Anotação para ser usada em métodos cuja execução deve ser logada.
 * Quando um método é anotado com @Logavel, o LoggingAspect interceptará sua execução
 * para registrar a entrada, saída, argumentos, resultado e tempo de execução.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Logavel