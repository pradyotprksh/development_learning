package com.pradyotprakash.unitconverter.config

import com.pradyotprakash.unitconverter.features.converter.controllers.ConverterController
import com.pradyotprakash.unitconverter.features.converter.controllers.length.LengthController
import com.pradyotprakash.unitconverter.features.converter.controllers.length.LengthControllerImplementation
import com.pradyotprakash.unitconverter.features.converter.controllers.temperature.TemperatureController
import com.pradyotprakash.unitconverter.features.converter.controllers.temperature.TemperatureControllerImplementation
import com.pradyotprakash.unitconverter.features.converter.controllers.weight.WeightController
import com.pradyotprakash.unitconverter.features.converter.controllers.weight.WeightControllerImplementation
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object ModulesConfig {
    private val controllersModule = DI.Module("CONTROLLERS") {
        bindProvider<LengthController> { LengthControllerImplementation() }
        bindProvider<WeightController> { WeightControllerImplementation() }
        bindProvider<TemperatureController> { TemperatureControllerImplementation() }
    }

    private val featuresModule = DI.Module("FEATURES") {
        bindProvider { ConverterController(instance(), instance(), instance()) }
    }

    val di = DI {
        importAll(
            controllersModule,
            featuresModule,
        )
    }
}