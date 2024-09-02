package utils

enum class LoggerLevel {
    Debug,
    Warn,
    Info,
    Error,
}

object Logger {
    fun log(
        loggerLevel: LoggerLevel,
        message: Any,
    ) {
        when (loggerLevel) {
            LoggerLevel.Debug -> debug(message.toString())
            LoggerLevel.Warn -> warn(message.toString())
            LoggerLevel.Info -> info(message.toString())
            LoggerLevel.Error -> error(message.toString())
        }
    }

    private fun debug(
        message: String,
    ) {
        println("PRADYOT_PRAKASH_X_FULLSTACK ${LoggerLevel.Debug.name}: $message")
    }

    private fun warn(
        message: String,
    ) {
        println("PRADYOT_PRAKASH_X_FULLSTACK ${LoggerLevel.Warn.name}: $message")
    }

    private fun info(
        message: String,
    ) {
        println("PRADYOT_PRAKASH_X_FULLSTACK ${LoggerLevel.Info.name}: $message")
    }

    private fun error(
        message: String,
    ) {
        println("PRADYOT_PRAKASH_X_FULLSTACK ${LoggerLevel.Error.name}: $message")
    }
}