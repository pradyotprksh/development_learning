//
//  WeatherManager.swift
//  Clima
//
//  Created by Pradyot Prakash on 19/07/21.
//  Copyright © 2021 App Brewery. All rights reserved.
//

import Foundation

// Protocol for handling the weather details when the api gives success
protocol WeatherManagerDelegate {
    
    // Update the weather when got the response
    func didUpdateWeather(_ weatherManager: WeatherManager, _ weather: WeatherModel)
    
    // Called when there is an error
    func didFailWithError(_ weatherManager: WeatherManager, _ error: Error)
}

struct WeatherManager {
    let weatherUrl = "https://api.openweathermap.org/data/2.5/weather?appid=933a1fd1c99b94daba062ae1668aeda6&units=metric"
    
    var weatherDelegate: WeatherManagerDelegate?
    
    // Give the cityName get the waether from the weatherUrl
    func fetchWeather(cityName: String) {
        let urlString = "\(weatherUrl)&q=\(cityName)"
        performRequest(with: urlString)
    }
    
    // Perfomr the request for getting the weather details
    func performRequest(with urlString: String) {
        if let url = URL(string: urlString) {
            let session = URLSession(configuration: .default)
            let task = session.dataTask(with: url) { data, response, error in
                if error != nil {
                    weatherDelegate?.didFailWithError(self, error!)
                    return
                }
                
                if let safeData = data {
                    if let weather = self.parseJSON(safeData) {
                        self.weatherDelegate?.didUpdateWeather(self, weather)
                    }
                }
            }
            task.resume()
        }
    }
    
    // Parse JSON
    func parseJSON(_ data: Data) -> WeatherModel? {
        let decoder = JSONDecoder()
        do {
            let jsonData = try decoder.decode(WeatherData.self, from: data)
            let id = jsonData.weather.first?.id ?? 800
            let temp = jsonData.main.temp
            let name = jsonData.name
            
            return WeatherModel(id: id, cityName: name, temperature: temp)
        } catch {
            weatherDelegate?.didFailWithError(self, error)
            return nil
        }
    }
}
