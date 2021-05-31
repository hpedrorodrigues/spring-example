package com.hpedrorodrigues.example.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Employee(
  @Id val id: Long? = null,

  val name: String
)
