package br.com.babershop.server.infrastructure.annotation

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * Implementação de [ObjectLogFormatter] que usa reflection para inspecionar
 * data classes e mascarar campos anotados com @NaoLogar.
 */
@Component
class ReflectionLogFormatter : ObjectLogFormatter {

    private val MASCARA = "[DADO SENSÍVEL]"

    override fun formatarArgumentos(joinPoint: ProceedingJoinPoint): String {
        val signature = joinPoint.signature as MethodSignature
        val parameterAnnotations = signature.method.parameterAnnotations

        return joinPoint.args.mapIndexed { index, arg ->
            val annotations = parameterAnnotations[index]
            if (annotations.any { it is NaoLogar }) {
                MASCARA
            } else {
                formatarResultado(arg)
            }
        }.joinToString()
    }

    override fun formatarResultado(obj: Any?): String {
        if (obj == null) return "null"
        if (obj::class.isData.not()) return obj.toString()

        return obj::class.simpleName + "(" + obj::class.memberProperties.joinToString(", ") { prop ->
            val valor = if (prop.findAnnotation<NaoLogar>() != null) {
                MASCARA
            } else {
                prop.getter.call(obj)
            }
            "${prop.name}=$valor"
        } + ")"
    }
}