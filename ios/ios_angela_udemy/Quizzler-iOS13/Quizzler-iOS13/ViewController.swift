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
    
    // Questions
    let quiz = [
        Question(q: "A slug's blood is green.", a: "True"),
        Question(q: "Approximately one quarter of human bones are in the feet.", a: "True"),
        Question(q: "The total surface area of two human lungs is approximately 70 square metres.", a: "True"),
        Question(q: "In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.", a: "True"),
        Question(q: "In London, UK, if you happen to die in the House of Parliament, you are technically entitled to a state funeral, because the building is considered too sacred a place.", a: "False"),
        Question(q: "It is illegal to pee in the Ocean in Portugal.", a: "True"),
        Question(q: "You can lead a cow down stairs but not up stairs.", a: "False"),
        Question(q: "Google was originally called 'Backrub'.", a: "True"),
        Question(q: "Buzz Aldrin's mother's maiden name was 'Moon'.", a: "True"),
        Question(q: "The loudest sound produced by any animal is 188 decibels. That animal is the African Elephant.", a: "False"),
        Question(q: "No piece of square dry paper can be folded in half more than 7 times.", a: "False"),
        Question(q: "Chocolate affects a dog's heart and nervous system; a few ounces are enough to kill a small dog.", a: "True")
    ]
    
    // Current question number
    var currentQuestion = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        updateUi()
    }
    
    // Actions in the controller
    //
    // An action which will be triggered when any one of the answer button is pressed.
    @IBAction func answerButtonPressed(_ sender: UIButton) {
        let selectedAnswer = sender.currentTitle
        if (selectedAnswer == quiz[currentQuestion].answer) {
            sender.backgroundColor = .green
        } else {
            sender.backgroundColor = .red
        }
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.2) {
            if self.currentQuestion + 1 < self.quiz.count {
                self.currentQuestion += 1
            } else {
                self.currentQuestion = 0
            }
            self.updateUi()
        }
    }
    
    // Update the ui with the new question and also update the progress bar
    func updateUi() {
        questionLabel.text = quiz[currentQuestion].text
        trueButton.backgroundColor = .clear
        falseButton.backgroundColor = .clear
        progressView.progress = Float(currentQuestion + 1) / Float(quiz.count)
    }
}
