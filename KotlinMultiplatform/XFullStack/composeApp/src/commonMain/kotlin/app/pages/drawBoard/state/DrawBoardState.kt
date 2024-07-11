package app.pages.drawBoard.state

data class DrawBoardState(
    val components: List<UiComponents> = listOf(
        UiComponents.BoxComponent,
        UiComponents.OvalComponent,
        UiComponents.LineComponent,
    ),
)