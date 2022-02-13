package com.example.pepa.webApi.controllers

const val VALID_REQUEST =
    "{\n" + "   \"clinical_backgrounds\":[\n" + "      {\n" + "         \"type\":\"DISEASE\",\n" + "         \"value\":\"Diabetes\",\n" + "         \"createdAt\":\"2021-03-03T09:55:00Z\"\n" + "      },\n" + "      {\n" + "         \"type\":\"DISEASE\",\n" + "         \"value\":\"Diabetes\",\n" + "         \"createdAt\":\"2021-03-03T09:55:00Z\"\n" + "      }\n" + "   ]\n" + "}"

const val INVALID_BODY_REQUEST =
    "{\n" + "   \"clinical_backgrounds\":[\n" + "      {\n" + "         \"type\":\"DISEASE\",\n" + "         \"value\":\"dia\",\n" + "         \"createdAt\":\"2021-03-03T09:55:00Z\"\n" + "      },\n" + "      {\n" + "         \"type\":\"DISEASE\",\n" + "         \"value\":\"Diabetes\",\n" + "         \"createdAt\":\"2021-03-03T09:55:00Z\"\n" + "      }\n" + "   ]\n" + "}"