package br.com.babershop.server.infrastructure.annotation

/**
 * Anotação para ser usada em propriedades de data classes ou parâmetros de métodos
 * para indicar que seu valor não deve ser incluído nos logs.
 * O LoggingAspect substituirá o valor por uma máscara.
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class NaoLogar