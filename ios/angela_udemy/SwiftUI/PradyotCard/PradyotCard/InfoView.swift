//
//  InfoView.swift
//  PradyotCard
//
//  Created by Pradyot Prakash on 22/07/21.
//

import SwiftUI

struct InfoView: View {
    let icon: String
    let text: String
    
    var body: some View {
        RoundedRectangle(cornerRadius: 25)
            .fill(Color.white)
            .frame(height: 50, alignment: .center)
            .overlay(
                HStack {
                    Image(systemName: icon)
                        .foregroundColor(.green)
                    Text(text)
                        .foregroundColor(.black)
                }
            ).padding()
    }
}

struct InfoView_Previews: PreviewProvider {
    static var previews: some View {
        InfoView(icon: "phone.fill", text: "+91 9663522579")
            .previewLayout(.sizeThatFits)
    }
}
