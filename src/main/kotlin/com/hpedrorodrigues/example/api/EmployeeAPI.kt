package com.hpedrorodrigues.example.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class EmployeeAPI {

  @Bean
  fun employeeRouter(handler: EmployeeHandler) = coRouter {
    path("/api/v1/employee")
      .and(accept(MediaType.APPLICATION_JSON))
      .nest { POST("", handler::save) }
  }
}
