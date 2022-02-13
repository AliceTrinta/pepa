package com.example.pepa.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
class ClinicalBackground(
    @Id val id: UUID, val personId: UUID?, val value: String, val type: String, val createdAt: Instant
) {
    constructor(personId: UUID?, value: String, type: String, createdAt: Instant) : this(
        UUID.randomUUID(),
        personId,
        value,
        type,
        createdAt
    )
}