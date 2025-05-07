package com.pradyotprakash.customannotations.annotations

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonElementRuntime(val key: String = "")