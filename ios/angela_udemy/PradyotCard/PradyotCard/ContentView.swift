//
//  ContentView.swift
//  PradyotCard
//
//  Created by Pradyot Prakash on 22/07/21.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        ZStack {
            Color(red: 0.91, green: 0.30, blue: 0.24)
                .ignoresSafeArea(.all)
            VStack {
                Image("diamond")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 150, height: 150, alignment: .center)
                    .clipShape(Circle())
                    .overlay(
                        Circle()
                            .stroke(Color.white, lineWidth: 5)
                    )
                Text("Pradyot Prakash")
                    .font(Font.custom("Pacifico-Regular", size: 40))
                    .bold()
                    .foregroundColor(.white)
                Text("Mobile Developer")
                    .font(.system(size: 25))
                    .foregroundColor(.white)
                Divider()
                InfoView(icon: "phone.fill", text: "+91 9663522579")
                InfoView(icon: "envelope.fill", text: "pradyotprksh4@gmail.com")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
