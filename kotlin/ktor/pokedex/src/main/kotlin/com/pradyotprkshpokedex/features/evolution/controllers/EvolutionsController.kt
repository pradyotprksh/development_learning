package com.pradyotprkshpokedex.features.evolution.controllers

class EvolutionsController(
    private val evolutionChainController: EvolutionChainController,
    private val evolutionTriggerController: EvolutionTriggerController,
) : EvolutionChainController by evolutionChainController, EvolutionTriggerController by evolutionTriggerController