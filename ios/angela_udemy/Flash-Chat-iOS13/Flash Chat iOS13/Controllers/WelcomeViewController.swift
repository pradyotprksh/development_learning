//
//  WelcomeViewController.swift
//  Flash Chat iOS13
//
//  Created by Angela Yu on 21/10/2019.
//  Copyright © 2019 Angela Yu. All rights reserved.
//

import UIKit

class WelcomeViewController: UIViewController {
    // Outlets in the welcome view controller
    @IBOutlet weak var titleLabel: UILabel!
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.isNavigationBarHidden = true
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        navigationController?.isNavigationBarHidden = false
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        titleLabel.text = ""
        var characterIndex = 0.0
        
        let titleString = Constants.appName
        for letter in titleString {
            Timer.scheduledTimer(withTimeInterval: 0.1 * characterIndex, repeats: false, block: {
                _ in self.titleLabel.text?.append(letter)
            })
            characterIndex += 1
        }
    }
}
