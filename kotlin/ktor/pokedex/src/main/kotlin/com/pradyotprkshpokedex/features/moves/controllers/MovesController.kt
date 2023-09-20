package com.pradyotprkshpokedex.features.moves.controllers

class MovesController(
    private val moveAilmentsController: MoveAilmentsController,
    private val moveBattleStylesController: MoveBattleStylesController,
    private val moveCategoriesController: MoveCategoriesController,
    private val moveController: MoveController,
    private val moveDamageClassesController: MoveDamageClassesController,
    private val moveLearnMethodsController: MoveLearnMethodsController,
    private val moveTargetsController: MoveTargetsController
) : MoveAilmentsController by moveAilmentsController,
 MoveBattleStylesController by moveBattleStylesController,
  MoveCategoriesController by moveCategoriesController,
  MoveController by moveController,
  MoveDamageClassesController by moveDamageClassesController,
    MoveLearnMethodsController by moveLearnMethodsController,
  MoveTargetsController by moveTargetsController {
}