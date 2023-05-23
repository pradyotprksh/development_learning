//
//  DiscoverViewModel.swift
//  iosApp
//
//  Created by Pradyot Prakash on 23/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension DiscoverScreen {
    class DiscoverViewModel: ObservableObject {
        lazy var discoverPresenter: DiscoverPresenter = DiFactory.shared.discoverPresenter
        @Published var movies: DiscoverMovies?
        @Published var movieError: String?
        @Published var tvs: DiscoverTv?
        @Published var tvError: String?
        @Published var loading: Bool = false
        
        init() {
            getDiscoverDetails()
        }
        
        func getDiscoverDetails() {
            getMovies()
            getTvs()
        }
        
        func getMovies() {
            loading = true
            discoverPresenter.getMovies { movies, error in
                DispatchQueue.main.async {
                    if let movies = movies {
                        self.movies = movies
                    } else {
                        self.movieError = error?.localizedDescription ?? "error"
                    }
                }
            }
        }
        
        func getTvs() {
            discoverPresenter.getTvs { tvs, error in
                DispatchQueue.main.async {
                    if let tvs = tvs {
                        self.tvs = tvs
                    } else {
                        self.tvError = error?.localizedDescription ?? "error"
                    }
                    self.loading = false
                }
            }
        }
    }
}
