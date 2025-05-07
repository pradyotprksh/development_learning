package com.pradyotprakash.customannotations.annotations

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class JsonElementCompiler(val key: String = "")