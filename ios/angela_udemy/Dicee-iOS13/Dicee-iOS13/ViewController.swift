//
//  ViewController.swift
//  Dicee-iOS13
//
//  Created by Angela Yu on 11/06/2019.
//  Copyright © 2019 London App Brewery. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    // Views for controller
    @IBOutlet weak var diceImageView1: UIImageView!
    @IBOutlet weak var diceImageView2: UIImageView!
    
    // Dice number for left and right image
    var leftDiceNumber = 1;
    var rightDiceNumber = 1;
    
    // Dice images array
    let diceImages = [#imageLiteral(resourceName: "DiceOne"), #imageLiteral(resourceName: "DiceTwo"), #imageLiteral(resourceName: "DiceThree"), #imageLiteral(resourceName: "DiceFour"), #imageLiteral(resourceName: "DiceFive"), #imageLiteral(resourceName: "DiceSix"),];
    
    override func viewDidLoad() {
        super.viewDidLoad();
        
        updateImages();
    }
    
    // Actions for controller
    //
    // When the roll button is clicked.
    @IBAction func rollButtonPressed(_ sender: UIButton) {
        updateImages();
    }
    
    // Update the image of the dices
    func updateImages() {
        leftDiceNumber = Int.random(in: 0...5);
        rightDiceNumber = Int.random(in: 0...5);
        
        diceImageView1.image = diceImages[leftDiceNumber];
        diceImageView2.image = diceImages[rightDiceNumber];
    }
}
