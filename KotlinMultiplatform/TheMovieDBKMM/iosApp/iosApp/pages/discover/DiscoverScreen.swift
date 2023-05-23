//
//  DiscoverScreen.swift
//  iosApp
//
//  Created by Pradyot Prakash on 23/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DiscoverScreen: View {
    @ObservedObject private(set) var discoverViewModel: DiscoverViewModel
    
    var body: some View {
        if discoverViewModel.loading {
            Text("Loading...")
        }
        
        if discoverViewModel.movieError != nil {
            Text(discoverViewModel.movieError ?? "")
        }
        
        if discoverViewModel.tvError != nil {
            Text(discoverViewModel.tvError ?? "")
        }
        
        if discoverViewModel.movies != nil || discoverViewModel.tvs != nil {
            List {
                if discoverViewModel.movies != nil {
                    Section() {
                        Text("Movies")
                    }
                    
                    ForEach(discoverViewModel.movies?.results ?? [], id: \.self) { movie in
                        DiscoverDetails(title: movie.title, imageUrl: movie.imageUrl)
                    }
                }
                
                if discoverViewModel.tvs != nil {
                    Section() {
                        Text("TVs")
                    }
                    
                    ForEach(discoverViewModel.tvs?.results ?? [], id: \.self) { tv in
                        DiscoverDetails(title: tv.name, imageUrl: tv.imageUrl)
                    }
                }
            }
        }
    }
}
