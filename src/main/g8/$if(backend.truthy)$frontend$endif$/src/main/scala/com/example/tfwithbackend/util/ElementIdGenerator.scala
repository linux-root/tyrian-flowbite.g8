package com.example.tfwithbackend.util

import java.util.UUID

object ElementIdGenerator:
  /**
   * Flowbite interactive data API
   */
  def generate(prefix: String): String =
    s"\$prefix-\${UUID.randomUUID().toString()}"
