package com.hpedrorodrigues.example.repository

import com.hpedrorodrigues.example.entity.Employee
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface EmployeeRepository : R2dbcRepository<Employee, Long> {

  fun existsByName(name: String): Mono<Boolean>
}
