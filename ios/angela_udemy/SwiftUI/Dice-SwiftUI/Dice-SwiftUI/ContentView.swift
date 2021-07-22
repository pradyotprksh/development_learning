//
//  ContentView.swift
//  Dice-SwiftUI
//
//  Created by Pradyot Prakash on 22/07/21.
//

import SwiftUI

struct ContentView: View {
    @State var leftDiceNumber = 1
    @State var rightDiceNumber = 1
    
    var body: some View {
        ZStack {
            Image("background")
                .resizable()
                .edgesIgnoringSafeArea(.all)
            VStack {
                Image("diceeLogo")
                Spacer()
                HStack {
                    DiceImage(diceNum: leftDiceNumber)
                    DiceImage(diceNum: rightDiceNumber)
                }.padding(.horizontal)
                Spacer()
                Button(
                    action: {
                        self.leftDiceNumber = Int.random(in: 1...6)
                        self.rightDiceNumber = Int.random(in: 1...6)
                    }
                ) {
                    Text("Roll")
                        .font(.system(size: 50))
                        .fontWeight(.heavy)
                        .foregroundColor(.white)
                        .padding()
                }.background(Color.red)
            }
        }
    }
}

struct DiceImage: View {
    let diceNum: Int
    
    var body: some View {
        Image("dice\(diceNum)")
            .resizable()
            .aspectRatio(1, contentMode: .fit)
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
