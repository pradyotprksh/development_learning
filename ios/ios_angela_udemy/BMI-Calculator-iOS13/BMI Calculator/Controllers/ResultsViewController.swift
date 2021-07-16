//
//  ResultsViewController.swift
//  BMI Calculator
//
//  Created by Pradyot Prakash on 16/07/21.
//  Copyright © 2021 Angela Yu. All rights reserved.
//

import UIKit

class ResultsViewController: UIViewController {
    // Outlets in the controller
    @IBOutlet weak var resultLabel: UILabel!
    @IBOutlet weak var adviceLabel: UILabel!
    
    var bmiValue: String?
    var bmiAdvide: String?
    var color: UIColor?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        resultLabel.text = bmiValue
        adviceLabel.text = bmiAdvide
        view.backgroundColor = color ?? .black
    }
    
    // Actions in the controller
    //
    // An action which will be called when the recalculate button is clicked
    @IBAction func recalculateAction(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
}
