package utils

import androidx.compose.ui.input.pointer.PointerIcon
import org.jetbrains.skiko.Cursor

actual fun getStartResizePointerIcon(): PointerIcon? {
    return PointerIcon(Cursor(Cursor.W_RESIZE_CURSOR))
}

actual fun getEndResizePointerIcon(): PointerIcon? {
    return PointerIcon(Cursor(Cursor.E_RESIZE_CURSOR))
}

actual fun getTopResizePointerIcon(): PointerIcon? {
    return PointerIcon(Cursor(Cursor.N_RESIZE_CURSOR))
}

actual fun getBottomResizePointerIcon(): PointerIcon? {
    return PointerIcon(Cursor(Cursor.S_RESIZE_CURSOR))
}

actual fun getSelectComponentPointerIcon(): PointerIcon? {
    return PointerIcon(Cursor(Cursor.CROSSHAIR_CURSOR))
}