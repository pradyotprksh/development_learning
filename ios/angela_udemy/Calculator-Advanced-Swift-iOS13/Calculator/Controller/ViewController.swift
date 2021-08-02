//
//  ViewController.swift
//  Calculator
//
//  Created by Angela Yu on 10/09/2019.
//  Copyright © 2019 London App Brewery. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    // Outlets in the controller
    @IBOutlet weak var displayLabel: UILabel!
    
    // This will be used to check when if number button is pressed then how to update the label
    private var isFinishTypingNumber: Bool = true
    
    private var calculator = CalculatorLogic()
    
    // Double value of the entered values
    private var displayValue: Double {
        get {
            guard let num = Double(displayLabel.text!) else {
                fatalError("Cannot convert display lable text to Double")
            }
            return num
        }
        set {
            displayLabel.text = String(newValue)
        }
    }
    
    // Actions in the controller
    //
    // An action which is triggered when any calculate button is pressed
    @IBAction func calcButtonPressed(_ sender: UIButton) {
        isFinishTypingNumber = true
        
        calculator.setNumber(displayValue)
        
        if let calcMethod = sender.currentTitle {
            if let result = calculator.calculate(calcMethod: calcMethod) {
                displayValue = result
            }
        }
    }
    //
    // An action which is triggered when any number button is pressed
    @IBAction func numButtonPressed(_ sender: UIButton) {
        if sender.currentTitle == "." {
            if let currentText = displayLabel.text {
                if currentText.contains(".") {
                    return
                }
            }
        }
        
        if let numValue = sender.currentTitle {
            if isFinishTypingNumber {
                displayLabel.text = numValue
                isFinishTypingNumber = false
            } else {
                displayLabel.text?.append(numValue)
            }
        }
    }
}
