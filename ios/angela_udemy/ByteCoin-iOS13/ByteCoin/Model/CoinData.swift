//
//  CoinData.swift
//  ByteCoin
//
//  Created by Pradyot Prakash on 20/07/21.
//  Copyright © 2021 The App Brewery. All rights reserved.
//

import Foundation

struct CointData: Decodable {
    let asset_id_base: String
    let asset_id_quote: String
    let rate: Double
}
