//
//  CalculatorLogic.swift
//  Calculator
//
//  Created by Pradyot Prakash on 23/07/21.
//  Copyright © 2021 London App Brewery. All rights reserved.
//

import Foundation

struct CalculatorLogic {
    var num: Double
    
    init(num: Double) {
        self.num = num
    }
    
    // Calculate the values
    mutating func calculate(calcMethod: String) -> Double {
        switch calcMethod {
        case "+/-":
            num *= -1
        case "%":
            num /= 100
        default:
            num = 0
        }
        return num
    }
}
