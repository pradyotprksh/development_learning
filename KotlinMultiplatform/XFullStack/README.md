This is a Kotlin Multiplatform project targeting Android, iOS, Desktop, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the
  platform-specific folders here too.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

Links:

* [Project updates](https://x.com/pradyotprksh4/status/1799002745414004997)

Articles:

* [Kotlin Multiplatform — Details](https://medium.com/@pradyotprksh4/kotlin-multiplatform-details-524a3baf75d9)
* [Kotlin Multiplatform — Registration (Server Side)](https://medium.com/@pradyotprksh4/kotlin-multiplatform-authentication-74d0adbbea06)
* [Kotlin Multiplatform — Login|Authenticate|User Info (Server Side)](https://medium.com/@pradyotprksh4/kotlin-multiplatform-login-authenticate-user-info-server-side-1dd565b2309b)