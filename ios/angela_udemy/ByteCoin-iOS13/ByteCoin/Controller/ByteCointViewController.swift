//
//  ViewController.swift
//  ByteCoin
//
//  Created by Angela Yu on 11/09/2019.
//  Copyright © 2019 The App Brewery. All rights reserved.
//

import UIKit

class ByteCointViewController: UIViewController {
    // Outlets in the controller
    @IBOutlet weak var currencyValueLabel: UILabel!
    @IBOutlet weak var currencySymbolLabel: UILabel!
    @IBOutlet weak var currencyPickerView: UIPickerView!
    
    // Coin manager
    var coinManager = CoinManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        coinManager.coinManagerDelegate = self
        currencyPickerView.delegate = self
        currencyPickerView.dataSource = self
    }
}

//MARK:- UIPickerViewDataSource

extension ByteCointViewController: UIPickerViewDataSource {
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return coinManager.currencyArray.count
    }
}

//MARK:- UIPickerViewDelegate

extension ByteCointViewController: UIPickerViewDelegate {
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return coinManager.currencyArray[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        coinManager.createUrl(currency: coinManager.currencyArray[row])
    }
}

//MARK:- CoinManagerDelegate

extension ByteCointViewController: CoinManagerDelegate {
    func didUpdateCoinDetails(_ coinManager: CoinManager, _ coin: CoinModel) {
        DispatchQueue.main.async {
            self.currencyValueLabel.text = coin.valueString
            self.currencySymbolLabel.text = coin.quotes
        }
    }
    
    func didFailWithError(_ coinManager: CoinManager, _ error: Error) {
        print(error)
    }
}
