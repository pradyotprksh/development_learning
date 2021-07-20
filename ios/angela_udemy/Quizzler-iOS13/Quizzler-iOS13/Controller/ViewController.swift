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
    @IBOutlet weak var trueButton: UIButton!
    @IBOutlet weak var falseButton: UIButton!
    @IBOutlet weak var scoreLabel: UILabel!
    
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
        sender.backgroundColor = quizBrain.checkAnswer(userAnswer: selectedAnswer ?? "FALSE")
        quizBrain.updateQuestionNumber()
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.2) {
            self.updateUi()
        }
    }
    
    // Update the ui with the new question and also update the progress bar
    func updateUi() {
        questionLabel.text = quizBrain.getQuestion()
        scoreLabel.text = "Score: \(quizBrain.score)"
        trueButton.backgroundColor = .clear
        falseButton.backgroundColor = .clear
        progressView.progress = quizBrain.getProgress()
    }
}
