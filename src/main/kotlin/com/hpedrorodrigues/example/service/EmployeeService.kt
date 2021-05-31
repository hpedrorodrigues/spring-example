package com.hpedrorodrigues.example.service

import com.hpedrorodrigues.example.entity.Employee
import com.hpedrorodrigues.example.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.flux
import kotlinx.coroutines.reactor.mono
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EmployeeService(private val repository: EmployeeRepository) {

  fun existsByName(name: String): Mono<Boolean> = repository.existsByName(name)

  @Transactional
  fun saveMonoDSL(name: String): Mono<Employee> = mono {
    repository.save(Employee(name = name)).awaitSingle()
    throw IllegalStateException()
  }

  @Transactional
  fun saveFluxDSL(name: String): Flux<Employee> = flux {
    repository.save(Employee(name = name)).awaitSingle()
    throw IllegalStateException()
  }

  @Transactional
  fun saveFlowDSL(name: String): Flow<Employee> = flow {
    repository.save(Employee(name = name)).awaitSingle()
    throw IllegalStateException()
  }

  @Transactional
  suspend fun saveSuspendMono(name: String): Mono<Employee> {
    repository.save(Employee(name = name)).awaitSingle()
    throw IllegalStateException()
  }

  @Transactional
  suspend fun saveSuspendFlux(name: String): Flux<Employee> {
    repository.save(Employee(name = name)).awaitSingle()
    throw IllegalStateException()
  }

  @Transactional
  suspend fun saveSuspendFlow(name: String): Flow<Employee> {
    repository.save(Employee(name = name)).awaitSingle()
    throw IllegalStateException()
  }
}
