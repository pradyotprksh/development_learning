//
//  ViewController.swift
//  Clima
//
//  Created by Angela Yu on 01/09/2019.
//  Copyright © 2019 App Brewery. All rights reserved.
//

import UIKit

class WeatherViewController: UIViewController, UITextFieldDelegate, WeatherManagerDelegate {
    // Outlets in the controller
    @IBOutlet weak var conditionImageView: UIImageView!
    @IBOutlet weak var temperatureLabel: UILabel!
    @IBOutlet weak var cityLabel: UILabel!
    @IBOutlet weak var searchTextField: UITextField!
    
    var weatherManager = WeatherManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        weatherManager.weatherDelegate = self
        searchTextField.delegate = self
    }
    
    // Actions in the controller
    //
    // An action which will be triggered whenever the search button is pressed
    @IBAction func searchPressed(_ sender: UIButton) {
        updateTextFieldUI()
    }
    
    // Whenever the return button of the keyboard is pressed then this method is called
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        updateTextFieldUI()
        return true
    }
    
    // Whenever the texxt field in the controller is done editing
    func textFieldDidEndEditing(_ textField: UITextField) {
        if let city = textField.text {
            weatherManager.fetchWeather(cityName: city)
        }
        updateTextFieldUI()
    }
    
    // Whenever the text field is done editing this method will be called.
    // This will be used to check if the text field is empty or not, to hide the keyboard.
    func textFieldShouldEndEditing(_ textField: UITextField) -> Bool {
        if textField.text != "" {
            return true
        } else {
            textField.placeholder = "Type Something"
            return false
        }
    }
    
    // Update text field UI
    func updateTextFieldUI() {
        searchTextField.endEditing(true)
        searchTextField.text = ""
    }
    
    func didUpdateWeather(_ weatherManager: WeatherManager, _ weather: WeatherModel) {
        DispatchQueue.main.async {
            self.temperatureLabel.text = weather.temperatureString
            self.cityLabel.text = weather.cityName
            self.conditionImageView.image = UIImage(systemName: weather.conditionName)
        }
    }
    
    func didFailWithError(_ weatherManager: WeatherManager, _ error: Error) {
        print(error)
    }
}
