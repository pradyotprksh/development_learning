//
//  PostData.swift
//  H4XOR News
//
//  Created by Pradyot Prakash on 22/07/21.
//

import Foundation

struct Results: Decodable {
    let hits: [Post]
}

struct Post: Decodable, Identifiable {
    let points: Int
    let title: String
    let url: String?
    let objectID: String
    
    var id: String {
        return objectID
    }
}
