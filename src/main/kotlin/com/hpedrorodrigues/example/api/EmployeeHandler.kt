package com.hpedrorodrigues.example.api

import com.hpedrorodrigues.example.service.EmployeeService
import kotlinx.coroutines.reactive.asFlow
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait

@Component
class EmployeeHandler(private val service: EmployeeService) {

  suspend fun save(request: ServerRequest): ServerResponse =
    ServerResponse
      .status(HttpStatus.CREATED)
      .bodyAndAwait(service.saveMonoDSL("test").asFlow())
}
