package com.pradyotprkshpokedex.features.games.controllers

class GamesController(
    private val gameGenerationsController: GameGenerationsController,
    private val gamePokedexesController: GamePokedexesController,
    private val gameVersionController: GameVersionController,
    private val gameVersionGroupController: GameVersionGroupController,
): GameGenerationsController by gameGenerationsController, GamePokedexesController by gamePokedexesController, GameVersionController by gameVersionController, GameVersionGroupController by gameVersionGroupController {
}