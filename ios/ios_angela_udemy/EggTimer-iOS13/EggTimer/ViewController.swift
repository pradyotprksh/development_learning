//
//  ViewController.swift
//  EggTimer
//
//  Created by Angela Yu on 08/07/2019.
//  Copyright © 2019 The App Brewery. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController {
    // Outlets in the controller
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var progressView: UIProgressView!
    
    // Time to boil eggs
    let eggTime = [
        "Soft": 5,
        "Medium": 7,
        "Hard": 12,
    ]
    
    // Timer
    var countdownTimer: Timer?
    var totalTime = 300
    var secondsPassed = 0
    
    // An audio player
    var player: AVAudioPlayer!;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        updateProgress(value: 0)
    }
    
    // Actions for the controller
    //
    // Called when any egg is pressed
    @IBAction func hardnessSelected(_ sender: UIButton) {
        let hardness = sender.currentTitle ?? "Soft"
        totalTime = eggTime[hardness] ?? 0
        updateTitle(title: "Please wait for \(totalTime) mins for \(hardness) egg...")
        
        // Stop the sound when new egg process has started
        player?.stop()
        
        // End the timer if an already timer is going on
        endTimer()
        
        // Start a new timer
        startTimer()
    }
    
    // Start timer
    func startTimer() {
        countdownTimer = Timer.scheduledTimer(
            timeInterval: 1,
            target: self,
            selector: #selector(updateTime),
            userInfo: nil,
            repeats: true)
    }
    
    // Update the time
    @objc func updateTime() {
        if secondsPassed < totalTime {
            secondsPassed += 1
            updateProgress(value: Float(secondsPassed) / Float(totalTime))
        } else {
            playSound(soundName: "alarm_sound")
            updateTitle(title: "Done")
            endTimer()
        }
    }
    
    // End the time when the time runs out
    func endTimer() {
        secondsPassed = 0
        updateProgress(value: 0)
        countdownTimer?.invalidate()
    }
    
    // Update title label
    func updateTitle(title: String) {
        titleLabel.text = title;
    }
    
    // Update progress
    func updateProgress(value: Float) {
        progressView.progress = value
    }
    
    // Play sound using player
    func playSound(soundName: String) {
        let url = Bundle.main.url(forResource: soundName, withExtension: "mp3");
        player = try! AVAudioPlayer(contentsOf: url!);
        player.play();
    }
}
