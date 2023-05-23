import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
			DiscoverScreen(discoverViewModel: DiscoverScreen.DiscoverViewModel())
		}
	}
}
