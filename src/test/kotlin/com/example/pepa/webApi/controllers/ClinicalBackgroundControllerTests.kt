package com.example.pepa.webApi.controllers

import com.example.pepa.domain.ClinicalBackground
import com.example.pepa.infra.repositories.ClinicalBackgroundRepository
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Instant
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class ClinicalBackgroundControllerTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repository: ClinicalBackgroundRepository

    private val type = "DISEASE"
    private val value = "Diabetes"
    private val createdAt = "2021-03-03T09:55:00Z"
    private val createdAtInstant = Instant.parse(createdAt)

    @Nested
    inner class SaveClinicalBackground {
        @Test
        fun `passing valid data`() {
            mockMvc.perform(
                MockMvcRequestBuilders.post(("/${saveValidClinicalBackground()}/clinical_backgrounds"))
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(VALID_REQUEST)
            ).andExpect(MockMvcResultMatchers.status().isOk)
        }

        @Test
        fun `passing invalid personId`() {
            mockMvc.perform(
                MockMvcRequestBuilders.post(("/${UUID.randomUUID()}/clinical_backgrounds"))
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                    .content(INVALID_BODY_REQUEST)
            ).andExpect(MockMvcResultMatchers.status().isBadRequest)
        }

        @Test
        fun `passing invalid body`() {
            mockMvc.perform(
                MockMvcRequestBuilders.post(("/${saveValidClinicalBackground()}/clinical_backgrounds"))
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                    .content(INVALID_BODY_REQUEST)
            ).andExpect(MockMvcResultMatchers.status().isBadRequest)

        }
    }

    @Nested
    inner class NewClinicalBackground {
        @Test
        fun `passing valid data`() {
            mockMvc.perform(
                MockMvcRequestBuilders.post(("/clinical_backgrounds")).accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON).content(VALID_REQUEST)
            ).andExpect(MockMvcResultMatchers.status().isOk)
        }

        @Test
        fun `passing valid body`() {
            mockMvc.perform(
                MockMvcRequestBuilders.post(("/clinical_backgrounds")).accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON).content(INVALID_BODY_REQUEST)
            ).andExpect(MockMvcResultMatchers.status().isBadRequest)
        }
    }

    private fun saveValidClinicalBackground(): String {
        val personId = UUID.randomUUID()
        repository.save(ClinicalBackground(personId, value, type, createdAtInstant))
        return personId.toString()
    }
}