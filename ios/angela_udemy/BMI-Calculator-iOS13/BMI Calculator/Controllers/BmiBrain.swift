//
//  BmiBrain.swift
//  BMI Calculator
//
//  Created by Pradyot Prakash on 16/07/21.
//  Copyright © 2021 Angela Yu. All rights reserved.
//

import UIKit

struct BmiBrain {
    // BMI result
    var bmi: BMI? = nil
    
    // Calculate BMI
    mutating func calculateBmi(height: Float, weight: Float) {
        let value = (weight / pow(height, 2))
        
        switch value {
        case ..<18.5:
            bmi = BMI(value: value, advice: "Eat more pies", color: #colorLiteral(red: 0.2392156869, green: 0.6745098233, blue: 0.9686274529, alpha: 1))
        case 18.5...24.9:
            bmi = BMI(value: value, advice: "Fit as a fiddle", color: #colorLiteral(red: 0.4666666687, green: 0.7647058964, blue: 0.2666666806, alpha: 1))
        default:
            bmi = BMI(value: value, advice: "Eat less pies", color: #colorLiteral(red: 0.8078431487, green: 0.02745098062, blue: 0.3333333433, alpha: 1))
        }
    }
    
    // Get BMI value
    func getBmi() -> String {
        return String(format: "%.1f", bmi?.value ?? 0.0)
    }
    
    // Get advide
    func getAdvice() -> String {
        return bmi?.advice ?? "No advice for you"
    }
    
    // Get color
    func getColor() -> UIColor {
        return bmi?.color ?? .black
    }
}
