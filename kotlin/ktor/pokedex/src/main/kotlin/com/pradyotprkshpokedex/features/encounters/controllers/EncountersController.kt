package com.pradyotprkshpokedex.features.encounters.controllers

class EncountersController(
    private val encounterMethodsController: EncounterMethodsController,
    private val encounterConditionsController: EncounterConditionsController,
    private val encounterConditionValuesController: EncounterConditionValuesController,
) : EncounterMethodsController by encounterMethodsController,
    EncounterConditionsController by encounterConditionsController,
    EncounterConditionValuesController by encounterConditionValuesController {
}