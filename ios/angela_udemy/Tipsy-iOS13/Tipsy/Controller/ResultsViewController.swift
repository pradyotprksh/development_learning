//
//  ResultsViewController.swift
//  Tipsy
//
//  Created by Pradyot Prakash on 16/07/21.
//  Copyright © 2021 The App Brewery. All rights reserved.
//

import UIKit

class ResultsViewController: UIViewController {
    
    // Outlets in the controller
    @IBOutlet weak var tipAmountLabel: UILabel!
    @IBOutlet weak var tipDetailsLabel: UILabel!
    
    // Tip details
    var tipAmount: String?
    var tipValue: String?
    var splitBetween: String?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        tipAmountLabel.text = tipAmount
        let split = splitBetween ?? "2"
        let value = tipValue ?? "10"
        tipDetailsLabel.text = "Split between \(split) people, with \(value)% tip."
    }
    
    // Actions in the controller
    //
    // An action which will be triggered when recalculate button is pressed
    @IBAction func recalculateTip(_ sender: UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
}
