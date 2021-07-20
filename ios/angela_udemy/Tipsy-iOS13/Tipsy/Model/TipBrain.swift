//
//  TipBrain.swift
//  Tipsy
//
//  Created by Pradyot Prakash on 16/07/21.
//  Copyright © 2021 The App Brewery. All rights reserved.
//

import Foundation

struct TipBrain {
    var tip = Tip(amount: 0.0, tipPercentage: 0.10, tipSplit: 2, tipAmount: 0.0)
    
    // Update the split value
    mutating func updateSplit(value: Int) {
        tip = Tip(amount: tip.amount, tipPercentage: tip.tipPercentage, tipSplit: value, tipAmount: tip.tipAmount)
    }
    
    // Get split value
    func getSplitValue() -> String {
        return "\(tip.tipSplit)"
    }
    
    // Get tip percentage
    func getTipPercentage() -> Float {
        return tip.tipPercentage
    }
    
    // Update tip percentage
    mutating func updateTipPercentage(value: String) {
        switch value {
        case "0%":
            tip = Tip(amount: tip.amount, tipPercentage: 0.0, tipSplit: tip.tipSplit, tipAmount: tip.tipAmount)
        case "10%":
            tip = Tip(amount: tip.amount, tipPercentage: 0.10, tipSplit: tip.tipSplit, tipAmount: tip.tipAmount)
        default:
            tip = Tip(amount: tip.amount, tipPercentage: 0.20, tipSplit: tip.tipSplit, tipAmount: tip.tipAmount)
        }
    }
    
    // Update bill amount
    mutating func updateBillAmount(value: String) {
        let amount = Float(value) ?? 0.0
        tip = Tip(amount: amount, tipPercentage: tip.tipPercentage, tipSplit: tip.tipSplit, tipAmount: tip.tipAmount)
    }
    
    // Calculate tip
    mutating func calculateTip() {
        let amount = tip.amount
        let tipPercentage = tip.tipPercentage
        let split = tip.tipSplit
        
        let tipAmount = (amount * (1 + tipPercentage)) / Float(split)
        
        tip = Tip(amount: tip.amount, tipPercentage: tip.tipPercentage, tipSplit: tip.tipSplit, tipAmount: tipAmount)
    }
    
    // Get tip amount
    func getTipAmount() -> String {
        return "\(String(format: "%.2f", tip.tipAmount))"
    }
    
    // Get tip split
    func getTipSplit() -> String {
        return "\(tip.tipSplit)"
    }
    
    // Get tip value
    func getTipValue() -> String {
        return "\(tip.tipPercentage * 100)"
    }
}
