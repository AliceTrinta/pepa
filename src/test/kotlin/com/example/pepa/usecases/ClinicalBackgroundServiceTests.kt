package com.example.pepa.usecases

import com.example.pepa.domain.ClinicalBackground
import com.example.pepa.infra.repositories.ClinicalBackgroundRepository
import com.example.pepa.webApi.controllers.clinicalBackground.dataContracts.request.ClinicalBackgroundRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*

@SpringBootTest
class ClinicalBackgroundServiceTests {

    @Autowired
    private lateinit var service: ClinicalBackgroundService

    @Autowired
    private lateinit var repository: ClinicalBackgroundRepository

    private val type1 = "DISEASE"
    private val type2 = "None"
    private val value1 = "Diabetes"
    private val value2 = "None"
    private val createdAt = "2021-03-03T09:55:00Z"
    private val createdAtInstant = Instant.parse(createdAt)

    @Nested
    inner class SaveClinicalBackground {
        @Test
        fun `valid clinicalBackground`() {
            val personId = saveValidClinicalBackground()
            val list = service.saveNewClinicalBackground(personId, buildValidClinicalBackgroundList())
            val validClinicalBackground = list.first()
            Assertions.assertNotNull(validClinicalBackground.id)
            Assertions.assertEquals(validClinicalBackground.personId.toString(), personId)
            Assertions.assertEquals(validClinicalBackground.value, value1)
            Assertions.assertEquals(validClinicalBackground.type, type1)
            Assertions.assertEquals(validClinicalBackground.createdAt, createdAtInstant)
        }

        @Test
        fun `throw exception invalid personId`() {
            val exception = assertThrows<Exception> {
                service.saveNewClinicalBackground(UUID.randomUUID().toString(), buildValidClinicalBackgroundList())
            }
            Assertions.assertEquals(exception.message, "PersonID not found")
        }

        @Test
        fun `throw exception invalid uuid`() {
            assertThrows<Exception> {
                service.saveNewClinicalBackground("123", buildValidClinicalBackgroundList())
            }
        }

        @Test
        fun `throw exception invalid type background`() {
            val exception = assertThrows<Exception> {
                service.saveNewClinicalBackground(
                    saveValidClinicalBackground(),
                    buildInvalidTypeClinicalBackgroundList()
                )
            }
            Assertions.assertEquals(exception.message, "Invalid background data")
        }

        @Test
        fun `throw exception invalid value background`() {
            val exception = assertThrows<Exception> {
                service.saveNewClinicalBackground(
                    saveValidClinicalBackground(),
                    buildInvalidValueClinicalBackgroundList()
                )
            }
            Assertions.assertEquals(exception.message, "Invalid background data")
        }

        @Test
        fun `throw exception invalid createdAt`() {
            assertThrows<Exception> {
                service.saveNewClinicalBackground(
                    saveValidClinicalBackground(),
                    buildInvalidCreatedAtClinicalBackgroundList()
                )
            }
        }
    }
    @Nested
    inner class GetAllBackgrounds {
        @Test
        fun `Clinical background exists`() {
            val personId = saveValidClinicalBackground()
            val list = service.getAllBackgrounds(personId)
            val validClinicalBackground = list.first()
            Assertions.assertNotNull(validClinicalBackground.id)
            Assertions.assertEquals(validClinicalBackground.personId.toString(), personId)
            Assertions.assertEquals(validClinicalBackground.value, value1)
            Assertions.assertEquals(validClinicalBackground.type, type1)
            Assertions.assertEquals(validClinicalBackground.createdAt, createdAtInstant)
        }

        @Test
        fun `Clinical background does not exists`() {
            val personId = UUID.randomUUID().toString()
            val exception = assertThrows<Exception> {
                service.getAllBackgrounds(personId)
            }
            Assertions.assertEquals(exception.message, "No records were found for id: $personId")
        }
    }

    private fun buildValidClinicalBackgroundList(): List<ClinicalBackgroundRequest> {
        return listOf(ClinicalBackgroundRequest(type1, value1, createdAt))
    }

    private fun buildInvalidTypeClinicalBackgroundList(): List<ClinicalBackgroundRequest> {
        return listOf(ClinicalBackgroundRequest(type2, value1, createdAt))
    }

    private fun buildInvalidValueClinicalBackgroundList(): List<ClinicalBackgroundRequest> {
        return listOf(ClinicalBackgroundRequest(type1, value2, createdAt))
    }

    private fun buildInvalidCreatedAtClinicalBackgroundList(): List<ClinicalBackgroundRequest> {
        return listOf(ClinicalBackgroundRequest(type1, value1, "createdAt"))
    }

    private fun saveValidClinicalBackground(): String {
        val personId = UUID.randomUUID()
        repository.save(ClinicalBackground(personId, value1, type1, createdAtInstant))
        return personId.toString()
    }

}
