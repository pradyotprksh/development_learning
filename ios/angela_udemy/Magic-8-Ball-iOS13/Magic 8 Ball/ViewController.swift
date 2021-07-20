//
//  ViewController.swift
//  Magic 8 Ball
//
//  Created by Angela Yu on 14/06/2019.
//  Copyright © 2019 The App Brewery. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    // Views in controller
    @IBOutlet weak var ballImageView: UIImageView!
    
    // Ball number
    var ballNumber = 0;
    
    // Ball list
    let ballArray = [#imageLiteral(resourceName: "ball1.png"), #imageLiteral(resourceName: "ball2.png"), #imageLiteral(resourceName: "ball3.png"), #imageLiteral(resourceName: "ball4.png"), #imageLiteral(resourceName: "ball5.png"),];
    
    override func viewDidLoad() {
        super.viewDidLoad();
        updateBallimage();
    }
    
    // Action in controller
    //
    // Update the image when ASK button is tapped
    @IBAction func askAction(_ sender: UIButton) {
        updateBallimage();
    }
    
    // Update the ball image
    func updateBallimage() {
        ballNumber = Int.random(in: 0...4);
        ballImageView.image = ballArray[ballNumber];
    }
}
