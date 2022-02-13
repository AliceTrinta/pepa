package com.example.pepa.infra.repositories

import com.example.pepa.domain.ClinicalBackground
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ClinicalBackgroundRepository : MongoRepository<ClinicalBackground, String> {
    fun existsByPersonId(personId: UUID): Boolean
}