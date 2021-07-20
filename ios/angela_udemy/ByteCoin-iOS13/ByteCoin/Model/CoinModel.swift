//
//  CoinModel.swift
//  ByteCoin
//
//  Created by Pradyot Prakash on 20/07/21.
//  Copyright © 2021 The App Brewery. All rights reserved.
//

import Foundation

struct CoinModel {
    let base: String
    let quotes: String
    let value: Double
    
    var valueString: String {
        return String(format: "%.2f", value)
    }
}
