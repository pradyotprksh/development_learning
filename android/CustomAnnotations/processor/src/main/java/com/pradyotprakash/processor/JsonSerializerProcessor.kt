package com.pradyotprakash.processor

import com.google.devtools.ksp.isAbstract
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import com.pradyotprakash.annotations.JsonSerializableCompiler
import java.io.OutputStream
import kotlin.text.ifEmpty

class JsonSerializerProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation(JsonSerializableCompiler::class.qualifiedName!!)
        val unableToProcess = symbols.filterNot { it.validate() }

        val dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray())

        symbols.filter { it is KSClassDeclaration && it.validate() }
            .forEach { it.accept(JsonSerializerKClassVisitor(dependencies), Unit) }

        return unableToProcess.toList()
    }

    private inner class JsonSerializerKClassVisitor(val dependencies: Dependencies) :
        KSVisitorVoid() {
        private val packageName = "com.pradyotprakash"

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            if (classDeclaration.isAbstract()) {
                logger.error(
                    "||Class Annotated with JsonSerializableCompiler should kotlin data class",
                    classDeclaration
                )
            }

            if (classDeclaration.classKind != ClassKind.CLASS) {
                logger.error(
                    "||Class Annotated with Projections should kotlin data class", classDeclaration
                )
            }

            val className = classDeclaration.simpleName.getShortName()
            val classPackage = classDeclaration.packageName.asString() + "." + className

            val properties = classDeclaration.primaryConstructor?.parameters ?: emptyList()

            if (properties.isEmpty())
                logger.error("No variables found in class", classDeclaration)

            val toGenerateFileName = "${classDeclaration.simpleName.getShortName()}JsonSerializer"

            val outputStream: OutputStream = codeGenerator.createNewFile(
                dependencies = dependencies,
                packageName,
                fileName = toGenerateFileName
            )

            outputStream.write(
                """
    |package $packageName
    
    |import $classPackage
    |import com.pradyotprakash.customannotations.utils.JsonUtil
    
    |fun $className.toJson() = JsonUtil.toJson(this)
    """.trimMargin().toByteArray()
            )
        }
    }
}
