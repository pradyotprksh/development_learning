//
//  CoinManager.swift
//  ByteCoin
//
//  Created by Angela Yu on 11/09/2019.
//  Copyright © 2019 The App Brewery. All rights reserved.
//

import Foundation

protocol CoinManagerDelegate {
    // Update the coin details
    func didUpdateCoinDetails(_ coinManager: CoinManager, _ coin: CoinModel)
    
    // Called when there is an error
    func didFailWithError(_ coinManager: CoinManager, _ error: Error)
}

struct CoinManager {
    
    let baseURL = "https://rest.coinapi.io/v1/exchangerate/BTC"
    let apiKey = "862894D8-FB6E-4318-AD4B-267920D65AAE"
    
    var coinManagerDelegate: CoinManagerDelegate?
    
    let currencyArray = ["AUD", "BRL","CAD","CNY","EUR","GBP","HKD","IDR","ILS","INR","JPY","MXN","NOK","NZD","PLN","RON","RUB","SEK","SGD","USD","ZAR"]
    
    // Create an URL
    func createUrl(currency: String) {
        let urlString = "\(baseURL)/\(currency)?apikey=\(apiKey)"
        performRequest(with: urlString)
    }
    
    // Perfomr the request for getting the weather details
    func performRequest(with urlString: String) {
        if let url = URL(string: urlString) {
            let session = URLSession(configuration: .default)
            let task = session.dataTask(with: url) { data, response, error in
                if error != nil {
                    coinManagerDelegate?.didFailWithError(self, error!)
                    return
                }
                
                if let safeData = data {
                    if let coin = self.parseJSON(safeData) {
                        coinManagerDelegate?.didUpdateCoinDetails(self, coin)
                    }
                }
            }
            task.resume()
        }
    }
    
    // Parse JSON
    func parseJSON(_ data: Data) -> CoinModel? {
        let decoder = JSONDecoder()
        do {
            let jsonData = try decoder.decode(CointData.self, from: data)
            return CoinModel(base: jsonData.asset_id_base, quotes: jsonData.asset_id_quote, value: jsonData.rate)
        } catch {
            coinManagerDelegate?.didFailWithError(self, error)
            return nil
        }
    }
}
