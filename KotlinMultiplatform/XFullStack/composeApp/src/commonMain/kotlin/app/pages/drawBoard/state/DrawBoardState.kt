package app.pages.drawBoard.state

data class DrawBoardState(
    val components: List<UiComponent> = listOf(
        UiComponent.BoxComponent,
        UiComponent.OvalComponent,
    ),
    val isCurrentlyDraggable: Boolean = false,
    val addedUiComponents: MutableList<SelectedUiComponents> = mutableListOf(),
    val selectedUiComponentIndex: Int = -1,
)