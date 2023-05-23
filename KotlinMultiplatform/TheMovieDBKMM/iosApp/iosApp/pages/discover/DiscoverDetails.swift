//
//  DiscoverDetails.swift
//  iosApp
//
//  Created by Pradyot Prakash on 23/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DiscoverDetails: View {
    let title: String
    let imageUrl: String
    
    var body: some View {
        if #available(iOS 16.0, *) {
            Grid {
                GridRow(alignment: VerticalAlignment.center) {
                    AsyncImage(url: URL(string:imageUrl)) { image in
                        image.resizable()
                    } placeholder: {
                        ProgressView()
                    }
                    .frame(width: 90, height: 130)
                    .cornerRadius(20)
                    .overlay(
                        RoundedRectangle(
                            cornerRadius: 20
                        ).stroke(
                            .purple,
                            lineWidth: 1
                        )
                    )
                    Text(title)
                }
            }
        } else {
            Text(title)
        }
    }
}
