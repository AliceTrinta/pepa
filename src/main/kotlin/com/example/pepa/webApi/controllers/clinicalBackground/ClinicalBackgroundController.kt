package com.example.pepa.webApi.controllers.clinicalBackground

import com.example.pepa.usecases.ClinicalBackgroundService
import com.example.pepa.webApi.controllers.clinicalBackground.dataContracts.request.ClinicalBackgroundsRequest
import com.example.pepa.webApi.controllers.clinicalBackground.dataContracts.response.ClinicalBackgroundsResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ClinicalBackgroundController {

    @Autowired
    private lateinit var service: ClinicalBackgroundService

    @PostMapping("/{person_id}/clinical_backgrounds")
    fun saveClinicalBackground(
        @PathVariable("person_id") id: String, @RequestBody background: ClinicalBackgroundsRequest
    ): ResponseEntity<ClinicalBackgroundsResponse> {
        val list = service.saveNewClinicalBackground(id, background.clinicalBackgrounds)
        return ResponseEntity.ok(ClinicalBackgroundsResponse(list))
    }

    @PostMapping("/clinical_backgrounds")
    fun newClinicalBackground(@RequestBody background: ClinicalBackgroundsRequest): ResponseEntity<ClinicalBackgroundsResponse> {
        val list = service.saveNewClinicalBackground(null, background.clinicalBackgrounds)
        return ResponseEntity.ok(ClinicalBackgroundsResponse(list))
    }
}