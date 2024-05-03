package core.utils

enum class LoggerLevel {
    Debug,
    Warn,
    Info,
    Error,
}

object Logger {
    fun log(
        loggerLevel: LoggerLevel,
        message: String,
    ) {
        when (loggerLevel) {
            LoggerLevel.Debug -> debug(message)
            LoggerLevel.Warn -> warn(message)
            LoggerLevel.Info -> info(message)
            LoggerLevel.Error -> error(message)
        }
    }

    private fun debug(
        message: String,
    ) {
        println("${LoggerLevel.Debug.name}: $message")
    }

    private fun warn(
        message: String,
    ) {
        println("${LoggerLevel.Warn.name}: $message")
    }

    private fun info(
        message: String,
    ) {
        println("${LoggerLevel.Info.name}: $message")
    }

    private fun error(
        message: String,
    ) {
        println("${LoggerLevel.Error.name}: $message")
    }
}