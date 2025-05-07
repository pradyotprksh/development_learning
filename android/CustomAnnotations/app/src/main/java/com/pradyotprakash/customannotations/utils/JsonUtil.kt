package com.pradyotprakash.customannotations.utils

import com.pradyotprakash.customannotations.annotations.JsonElementRuntime
import com.pradyotprakash.customannotations.annotations.JsonSerializableRuntime
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

object JsonUtil {
    fun toJson(obj: Any): String {
        return when (obj) {
            is String, is Number, is Boolean -> "\"$obj\""
            is List<*> -> listToJson(obj)
            else -> objectToJson(obj)
        }
    }

    private fun objectToJson(obj: Any): String {
        val kClass = obj::class
        if (!kClass.annotations.any { it is JsonSerializableRuntime }) {
            throw RuntimeException("The class ${kClass.simpleName} is not annotated with JsonSerializable")
        }

        val jsonBuilder = StringBuilder("{")
        kClass.memberProperties.forEach { property ->
            val annotation = property.findAnnotation<JsonElementRuntime>()
            if (annotation != null) {
                val key = annotation.key.ifEmpty { property.name }
                val value = property.call(obj)
                if (value != null) {
                    jsonBuilder.append("\"$key\":${toJson(value)},")
                }
            }
        }

        if (jsonBuilder.endsWith(",")) {
            jsonBuilder.deleteCharAt(jsonBuilder.length - 1)
        }
        jsonBuilder.append("}")

        return jsonBuilder.toString()
    }

    private fun listToJson(list: List<*>): String {
        val jsonBuilder = StringBuilder("[")
        list.filterNotNull().forEach { element ->
            jsonBuilder.append("${toJson(element)},")
        }
        if (jsonBuilder.endsWith(",")) {
            jsonBuilder.deleteCharAt(jsonBuilder.length - 1)
        }
        jsonBuilder.append("]")
        return jsonBuilder.toString()
    }
}