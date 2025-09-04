package br.com.babershop.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ServerApplication
    fun main(args: Array<String>) {
        runApplication<ServerApplication>(*args)
    }

