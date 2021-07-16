//
//  ViewController.swift
//  Destini-iOS13
//
//  Created by Angela Yu on 08/08/2019.
//  Copyright © 2019 The App Brewery. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    // Outlets in the controller
    @IBOutlet weak var storyLabel: UILabel!
    @IBOutlet weak var optionOneButton: UIButton!
    @IBOutlet weak var optionTwoButton: UIButton!
    
    // Story brain
    var storyBrain = StoryBrain()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        updateUi()
    }
    
    // Actions in controller
    //
    // Whenever anyone option is selected this action will be triggered
    @IBAction func storyLineOptionSelected(_ sender: UIButton) {
        storyBrain.updateStoryLine(option: sender.currentTitle ?? "")
        updateUi()
    }
    
    // Update the UI
    func updateUi() {
        storyLabel.text = storyBrain.getStoryTitle()
        
        let choices = storyBrain.getChoices()
        optionOneButton.setTitle(choices[0], for: .normal)
        optionTwoButton.setTitle(choices[1], for: .normal)
    }
}
