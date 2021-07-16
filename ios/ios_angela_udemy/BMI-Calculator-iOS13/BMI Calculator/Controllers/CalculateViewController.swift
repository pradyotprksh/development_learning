//
//  ViewController.swift
//  BMI Calculator
//
//  Created by Angela Yu on 21/08/2019.
//  Copyright © 2019 Angela Yu. All rights reserved.
//

import UIKit

class CalculateViewController: UIViewController {
    // Outlets in the controller
    @IBOutlet weak var heightLabel: UILabel!
    @IBOutlet weak var weightLabel: UILabel!
    @IBOutlet weak var heightSlider: UISlider!
    @IBOutlet weak var weightSlider: UISlider!
    
    // BMI brain
    var bmiBrain = BmiBrain()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    // Actions in the controller
    //
    // An action which will be triggered whenever there is a change in slider
    @IBAction func sliderUpdateAction(_ sender: UISlider) {
        if sender.tag == 0 {
            heightLabel.text = "\(String(format: "%.2f", sender.value))m"
        } else {
            weightLabel.text = "\(Int(sender.value))Kg"
        }
    }
    //
    // An action which will be triggered when the calculate button is pressed
    @IBAction func calculateBMIAction(_ sender: UIButton) {
        let height = heightSlider.value
        let weigth = weightSlider.value
        
        bmiBrain.calculateBmi(height: height, weight: weigth)
        
        self.performSegue(withIdentifier: "goToResults", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "goToResults" {
            let destinationVC = segue.destination as! ResultsViewController
            destinationVC.bmiValue = bmiBrain.getBmi()
            destinationVC.bmiAdvide = bmiBrain.getAdvice()
            destinationVC.color = bmiBrain.getColor()
        }
    }
}
