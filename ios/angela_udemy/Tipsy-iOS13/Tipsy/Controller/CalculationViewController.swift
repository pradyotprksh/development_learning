//
//  ViewController.swift
//  Tipsy
//
//  Created by Angela Yu on 09/09/2019.
//  Copyright © 2019 The App Brewery. All rights reserved.
//

import UIKit

class CalculationViewController: UIViewController {
    // Outlets of the controller
    @IBOutlet weak var amountTextField: UITextField!
    @IBOutlet weak var zeroPercentageButton: UIButton!
    @IBOutlet weak var tenPercentageButton: UIButton!
    @IBOutlet weak var twentyPercentButton: UIButton!
    @IBOutlet weak var splitsLabel: UILabel!
    
    // Tip brain
    var tipBrain = TipBrain()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        updateUi()
    }

    // Actions of the controller
    //
    // An action which will check any event for split change
    @IBAction func splitBetweenAction(_ sender: UIStepper) {
        tipBrain.updateSplit(value: Int(sender.value))
        updateUi()
    }
    //
    // An action which will be triggered when the amount is entered
    @IBAction func amountAction(_ sender: UITextField) {
        tipBrain.updateBillAmount(value: sender.text ?? "0.0")
    }
    //
    // An action which will be triggered whenever a tip value is selected
    @IBAction func tipAmountAction(_ sender: UIButton) {
        tipBrain.updateTipPercentage(value: sender.currentTitle ?? "0.10")
        zeroPercentageButton.isSelected = false
        tenPercentageButton.isSelected = false
        twentyPercentButton.isSelected = false
        sender.isSelected = true
    }
    //
    // An action which will be triggered when the calulate button is pressed
    @IBAction func calculateAmount(_ sender: UIButton) {
        tipBrain.calculateTip()
        self.performSegue(withIdentifier: "goToResult", sender: self)
    }
    
    // Update UI
    func updateUi() {
        splitsLabel.text = tipBrain.getSplitValue()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "goToResult" {
            let destinationVC = segue.destination as! ResultsViewController
            
            destinationVC.splitBetween = tipBrain.getSplitValue()
            destinationVC.tipAmount = tipBrain.getTipAmount()
            destinationVC.tipValue = tipBrain.getTipValue()
        }
    }
}

