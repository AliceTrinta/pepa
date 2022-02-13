package com.example.pepa.usecases

import com.example.pepa.domain.ClinicalBackground
import com.example.pepa.infra.repositories.ClinicalBackgroundRepository
import com.example.pepa.webApi.controllers.clinicalBackground.dataContracts.request.ClinicalBackgroundRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class ClinicalBackgroundService {

    @Autowired
    private lateinit var repository: ClinicalBackgroundRepository

    private val validDisease: List<String> = listOf("Diabetes", "Alzheimer", "Hypertension", "Asthma", "Parkinson")
    private val validSurgical: List<String> =
        listOf("Mammoplasty", "Liposuction", "Blepharoplasty", "Rhinoplasty", "Abdominoplasty")
    private val validVaccine: List<String> = listOf("BCG", "HPV", "Hepatitis A", "Hepatitis B", "Influenza")
    private val validMedicine: List<String> = listOf("Aradois", "Paroxetine", "Addera D3", "Xarelto", "Glifage XR")
    private val validMap: Map<String, List<String>> = mapOf(
        "DISEASE" to validDisease, "SURGICAL" to validSurgical, "VACCINE" to validVaccine, "MEDICINE" to validMedicine
    )

    fun saveNewClinicalBackground(
        personId: String?, backgrounds: List<ClinicalBackgroundRequest>
    ): List<ClinicalBackground> {
        val list = mutableListOf<ClinicalBackground>()
        var mutablePersonId = personId
        var createdAt: Instant

        verifyClinicalBackgrounds(backgrounds)
        if (mutablePersonId == null) {
            mutablePersonId = UUID.randomUUID().toString()
        } else {
            if (!(repository.existsByPersonId(UUID.fromString(mutablePersonId)))) {
                throw Exception("PersonID not found")
            }
        }
        for (background in backgrounds) {
            createdAt = Instant.parse(background.createdAt)
            list.add(
                repository.save(
                    ClinicalBackground(
                        UUID.fromString(mutablePersonId), background.value, background.type, createdAt
                    )
                )
            )
        }

        return list
    }

    fun getAllBackgrounds(personId: String): List<ClinicalBackground> {
        val list = repository.findAllByPersonId(UUID.fromString(personId))
        if(list.isEmpty()){
            throw Exception("No records were found for id: $personId")
        }
        return list
    }

    private fun verifyClinicalBackgrounds(backgrounds: List<ClinicalBackgroundRequest>) {
        for (background in backgrounds) {
            if (!(validMap.containsKey(background.type) && validMap[background.type]!!.contains(background.value))) {
                throw Exception("Invalid background data")
            }
        }
    }
}