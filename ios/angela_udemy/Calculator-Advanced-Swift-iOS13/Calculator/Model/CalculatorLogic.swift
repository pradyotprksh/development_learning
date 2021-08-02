//
//  CalculatorLogic.swift
//  Calculator
//
//  Created by Pradyot Prakash on 23/07/21.
//  Copyright © 2021 London App Brewery. All rights reserved.
//

import Foundation

struct CalculatorLogic {
    private var num: Double?
    
    private var intermediateCalculation: (number: Double, symbol: String)?
    
    // Update the num value
    mutating func setNumber(_ number: Double) {
        self.num = number
    }
    
    // Calculate the values
    mutating func calculate(calcMethod: String) -> Double? {
        if let n = num {
            switch calcMethod {
            case "+/-":
                return n * -1
            case "AC":
                return 0
            case "%":
                return n * 0.01
            case "=":
                return performTwoNumCalculation(num1: n)
            default:
                intermediateCalculation = (number: n, symbol: calcMethod)
            }
        }
        return nil
    }
    
    // Perform calculation
    private func performTwoNumCalculation(num1: Double) -> Double? {
        if let n1 = intermediateCalculation?.number, let operation = intermediateCalculation?.symbol {
            switch operation {
            case "+":
                return n1 + num1
            case "-":
                return n1 - num1
            case "×":
                return n1 * num1
            case "÷":
                return n1 / num1
            default:
                fatalError("Operation passed in does not match any symbol")
            }
        }
        return nil
    }
}
