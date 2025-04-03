package com.abbosidev

import com.fasterxml.uuid.Generators
import java.util.UUID

private val generator = Generators.timeBasedEpochGenerator()

fun randomUUIDv7(): UUID = generator.generate()