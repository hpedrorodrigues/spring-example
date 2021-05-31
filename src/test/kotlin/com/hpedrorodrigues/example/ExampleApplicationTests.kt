package com.hpedrorodrigues.example

import com.hpedrorodrigues.example.service.EmployeeService
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExampleApplicationTests(@Autowired private val service: EmployeeService) {

  @Test
  fun `kotlin coroutines DSLs for mono's project reactor`() {
    val name = "saveMonoDSL"
    StepVerifier
      .create(service.saveMonoDSL(name))
      .expectError()
      .verify()

    StepVerifier
      .create(service.existsByName(name))
      .expectNext(false)
      .expectComplete()
      .verify()
  }

  @Test
  fun `kotlin coroutines DSLs for flux's project reactor`() {
    val name = "saveFluxDSL"
    StepVerifier
      .create(service.saveFluxDSL(name))
      .expectError()
      .verify()

    StepVerifier
      .create(service.existsByName(name))
      .expectNext(false)
      .expectComplete()
      .verify()
  }

  @Test
  fun `suspend function for mono's project reactor`() = runBlocking {
    val name = "saveSuspendMono"

    try {
      service.saveSuspendMono(name).awaitSingle()
      Assertions.fail()
    } catch (e: Exception) {
      // Ignore error
    }

    Assertions.assertEquals(false, service.existsByName(name).awaitSingle())
  }

  @Test
  fun `suspend function for flux's project reactor`() = runBlocking {
    val name = "saveSuspendFlux"

    try {
      service.saveSuspendFlux(name).collectList().awaitSingle()
      Assertions.fail()
    } catch (e: Exception) {
      // Ignore error
    }

    Assertions.assertEquals(false, service.existsByName(name).awaitSingle())
  }

  @Test
  fun `suspend function for mono's project reactor returning another mono with error`() = runBlocking {
    val name = "saveSuspendMonoReturnAnotherMono"

    try {
      service.saveSuspendMonoReturnAnotherMono(name).awaitSingle()
      Assertions.fail()
    } catch (e: Exception) {
      // Ignore error
    }

    Assertions.assertEquals(false, service.existsByName(name).awaitSingle())
  }

  @Test
  fun `suspend function for mono's project reactor returning a flow with error`() = runBlocking {
    val name = "saveSuspendMonoReturnFlow"

    try {
      service.saveSuspendMonoReturnFlow(name).toList()
      Assertions.fail()
    } catch (e: Exception) {
      // Ignore error
    }

    Assertions.assertEquals(false, service.existsByName(name).awaitSingle())
  }

  @Test
  fun `suspend function for flux's project reactor returning another flux with error`() = runBlocking {
    val name = "saveSuspendFluxReturnAnotherFlux"

    try {
      service.saveSuspendFluxReturnAnotherFlux(name).collectList().awaitSingle()
      Assertions.fail()
    } catch (e: Exception) {
      // Ignore error
    }

    Assertions.assertEquals(false, service.existsByName(name).awaitSingle())
  }

  @Test
  fun `suspend function for flux's project reactor returning a flow with error`() = runBlocking {
    val name = "saveSuspendFluxReturnFlow"

    try {
      service.saveSuspendFluxReturnFlow(name).toList()
      Assertions.fail()
    } catch (e: Exception) {
      // Ignore error
    }

    Assertions.assertEquals(false, service.existsByName(name).awaitSingle())
  }

  @Test
  fun `flow's DSL`() = runBlocking {
    val name = "saveFlowDSL"

    try {
      service.saveFlowDSL(name).toList()
      Assertions.fail()
    } catch (e: Exception) {
      // Ignore error
    }

    Assertions.assertEquals(false, service.existsByName(name).awaitSingle())
  }

  @Test
  fun `flow's suspend function`() = runBlocking {
    val name = "saveSuspendFlow"

    try {
      service.saveSuspendFlow(name).toList()
      Assertions.fail()
    } catch (e: Exception) {
      // Ignore error
    }

    Assertions.assertEquals(false, service.existsByName(name).awaitSingle())
  }
}
