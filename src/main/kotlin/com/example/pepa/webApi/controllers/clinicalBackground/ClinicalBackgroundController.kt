package com.example.pepa.webApi.controllers.clinicalBackground

import com.example.pepa.usecases.ClinicalBackgroundService
import com.example.pepa.webApi.controllers.clinicalBackground.dataContracts.request.ClinicalBackgroundsRequest
import com.example.pepa.webApi.controllers.clinicalBackground.dataContracts.response.ClinicalBackgroundsResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ClinicalBackgroundController {

    @Autowired
    private lateinit var service: ClinicalBackgroundService

    @PostMapping("/{person_id}/clinical_backgrounds")
    fun save(
        @PathVariable("person_id") id: String, @RequestBody background: ClinicalBackgroundsRequest
    ): ResponseEntity<ClinicalBackgroundsResponse> {
        val responseList = service.saveNewClinicalBackground(id, background.clinicalBackgrounds)
        return ResponseEntity.ok(ClinicalBackgroundsResponse(responseList))
    }

    @PostMapping("/clinical_backgrounds")
    fun new(@RequestBody background: ClinicalBackgroundsRequest): ResponseEntity<ClinicalBackgroundsResponse> {
        val responseList = service.saveNewClinicalBackground(null, background.clinicalBackgrounds)
        return ResponseEntity.ok(ClinicalBackgroundsResponse(responseList))
    }

    @GetMapping("/{person_id}/clinical_backgrounds")
    fun getAll(@PathVariable("person_id") id: String): ResponseEntity<ClinicalBackgroundsResponse> {
        val responseList = service.getAllBackgrounds(id)
        return ResponseEntity.ok(ClinicalBackgroundsResponse(responseList))
    }
}