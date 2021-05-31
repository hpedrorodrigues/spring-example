package com.hpedrorodrigues.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@EnableR2dbcRepositories
@SpringBootApplication
class ExampleApplication

fun main(args: Array<String>) {
  runApplication<ExampleApplication>(*args)
}
