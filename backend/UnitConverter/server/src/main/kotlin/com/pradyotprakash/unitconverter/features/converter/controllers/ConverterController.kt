package com.pradyotprakash.unitconverter.features.converter.controllers

import com.pradyotprakash.unitconverter.features.converter.controllers.length.LengthController
import com.pradyotprakash.unitconverter.features.converter.controllers.temperature.TemperatureController
import com.pradyotprakash.unitconverter.features.converter.controllers.weight.WeightController

class ConverterController(
    private val lengthController: LengthController,
    private val weightController: WeightController,
    private val temperatureController: TemperatureController,
) : LengthController by lengthController, WeightController by weightController,
    TemperatureController by temperatureController