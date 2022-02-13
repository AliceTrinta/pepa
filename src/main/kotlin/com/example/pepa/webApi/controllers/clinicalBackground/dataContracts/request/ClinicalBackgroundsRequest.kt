package com.example.pepa.webApi.controllers.clinicalBackground.dataContracts.request

import com.fasterxml.jackson.annotation.JsonProperty


class ClinicalBackgroundsRequest(
    @JsonProperty("clinical_backgrounds") val clinicalBackgrounds: List<ClinicalBackgroundRequest>
)