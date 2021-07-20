//
//  QuizBrain.swift
//  Quizzler-iOS13
//
//  Created by Pradyot Prakash on 16/07/21.
//  Copyright © 2021 The App Brewery. All rights reserved.
//

import UIKit
import Foundation

struct QuizBrain {
    // Questions
    let quiz = [
        Question(q: "Which is the largest organ in the human body?", a: ["Heart", "Skin", "Large Intestine"], correctAnswer: "Skin"),
        Question(q: "Five dollars is worth how many nickels?", a: ["25", "50", "100"], correctAnswer: "100"),
        Question(q: "What do the letters in the GMT time zone stand for?", a: ["Global Meridian Time", "Greenwich Mean Time", "General Median Time"], correctAnswer: "Greenwich Mean Time"),
        Question(q: "What is the French word for 'hat'?", a: ["Chapeau", "Écharpe", "Bonnet"], correctAnswer: "Chapeau"),
        Question(q: "In past times, what would a gentleman keep in his fob pocket?", a: ["Notebook", "Handkerchief", "Watch"], correctAnswer: "Watch"),
        Question(q: "How would one say goodbye in Spanish?", a: ["Au Revoir", "Adiós", "Salir"], correctAnswer: "Adiós"),
        Question(q: "Which of these colours is NOT featured in the logo for Google?", a: ["Green", "Orange", "Blue"], correctAnswer: "Orange"),
        Question(q: "What alcoholic drink is made from molasses?", a: ["Rum", "Whisky", "Gin"], correctAnswer: "Rum"),
        Question(q: "What type of animal was Harambe?", a: ["Panda", "Gorilla", "Crocodile"], correctAnswer: "Gorilla"),
        Question(q: "Where is Tasmania located?", a: ["Indonesia", "Australia", "Scotland"], correctAnswer: "Australia")
    ]
    
    // Current question number
    var currentQuestion = 0
    
    // Score
    var score = 0
    
    // Check the user selected answer
    mutating func checkAnswer(userAnswer: String) -> UIColor {
        if (userAnswer == quiz[currentQuestion].correctAnswer) {
            score += 1
            return .green
        } else {
            return .red
        }
    }
    
    // Get current question
    func getQuestion() -> String {
        return quiz[currentQuestion].text
    }
    
    // Get progress
    func getProgress() -> Float {
        return Float(currentQuestion + 1) / Float(quiz.count)
    }
    
    // Update question number
    mutating func updateQuestionNumber() {
        if currentQuestion + 1 < quiz.count {
            currentQuestion += 1
        } else {
            score = 0
            currentQuestion = 0
        }
    }
    
    // Get current options
    func getCurrentOptions() -> [String] {
        return quiz[currentQuestion].answer
    }
}
