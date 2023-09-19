package com.pradyotprkshpokedex.features.contests.controllers

class ContestsController(
    private val contestEffectController: ContestEffectController,
    private val contestTypeController: ContestTypeController,
    private val superContestEffectController: SuperContestEffectController,
) : ContestEffectController by contestEffectController, ContestTypeController by contestTypeController,
    SuperContestEffectController by superContestEffectController {
}