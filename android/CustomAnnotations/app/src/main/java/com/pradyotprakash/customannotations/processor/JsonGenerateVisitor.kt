package com.pradyotprakash.customannotations.processor

import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid

class JsonGenerateVisitor(
    private val codeGenerator: CodeGenerator,
) : KSVisitorVoid() {
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        val packageName = classDeclaration.packageName.asString()
        val properties = classDeclaration.getDeclaredProperties()
        properties.forEach { ksPropertyDeclaration ->

        }
    }

    private fun generateJsonClass(property: KSPropertyDeclaration, packageName: String) {

    }
}