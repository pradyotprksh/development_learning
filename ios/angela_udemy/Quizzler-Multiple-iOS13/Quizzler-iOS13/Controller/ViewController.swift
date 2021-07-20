//
//  ViewController.swift
//  Quizzler-iOS13
//
//  Created by Angela Yu on 12/07/2019.
//  Copyright © 2019 The App Brewery. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    // Outlets in the controller
    @IBOutlet weak var questionLabel: UILabel!
    @IBOutlet weak var progressView: UIProgressView!
    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet weak var optionOneButton: UIButton!
    @IBOutlet weak var optionTwoButton: UIButton!
    @IBOutlet weak var optionThreeButton: UIButton!
    
    // Quiz brain
    var quizBrain = QuizBrain()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        updateUi()
    }
    
    // Actions in the controller
    //
    // An action which will be triggered when any one of the answer button is pressed.
    @IBAction func answerButtonPressed(_ sender: UIButton) {
        let selectedAnswer = sender.currentTitle
        sender.backgroundColor = quizBrain.checkAnswer(userAnswer: selectedAnswer ?? "")
        quizBrain.updateQuestionNumber()
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.2) {
            self.updateUi()
        }
    }
    
    // Update the ui with the new question, options and also update the progress bar
    func updateUi() {
        questionLabel.text = quizBrain.getQuestion()
        
        let options = quizBrain.getCurrentOptions()
        optionOneButton.setTitle(options[0], for: .normal)
        optionTwoButton.setTitle(options[1], for: .normal)
        optionThreeButton.setTitle(options[2], for: .normal)
        
        optionOneButton.backgroundColor = .clear
        optionTwoButton.backgroundColor = .clear
        optionThreeButton.backgroundColor = .clear
        
        scoreLabel.text = "Score: \(quizBrain.score)"
        progressView.progress = quizBrain.getProgress()
    }
}
