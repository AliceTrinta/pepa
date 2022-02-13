package com.example.pepa.webApi.controllers.clinicalBackground.dataContracts.response

import com.example.pepa.domain.ClinicalBackground
import com.fasterxml.jackson.annotation.JsonProperty

class ClinicalBackgroundsResponse(
    @JsonProperty("clinical_backgrounds") val clinicalBackgrounds: List<ClinicalBackground>?
)