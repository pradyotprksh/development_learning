//
//  ViewController.swift
//  Xylophone
//
//  Created by Angela Yu on 28/06/2019.
//  Copyright © 2019 The App Brewery. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController {
    
    // An audio player
    var player: AVAudioPlayer!;

    override func viewDidLoad() {
        super.viewDidLoad();
    }

    // Actions in controller
    //
    // When any button is pressed keyPressed will be called.
    @IBAction func keyPressed(_ sender: UIButton) {
        playSound(soundName: sender.currentTitle ?? "C");
        //Reduces the sender's (the button that got pressed) opacity to half.
        sender.alpha = 0.5;
        
        //Code should execute after 0.2 second delay.
         DispatchQueue.main.asyncAfter(deadline: .now() + 0.2) {
             //Bring's sender's opacity back up to fully opaque.
            sender.alpha = 1.0;
         }
    }
    
    // Play sound using player
    func playSound(soundName: String) {
        let url = Bundle.main.url(forResource: soundName, withExtension: "wav");
        player = try! AVAudioPlayer(contentsOf: url!);
        player.play();
    }
}
