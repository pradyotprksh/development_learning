package com.pradyotprakash.annotations

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class JsonElementCompiler(val key: String = "")
