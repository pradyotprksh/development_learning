This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.

# Results

![Screenshot_20250412-222927](https://github.com/user-attachments/assets/99225979-404f-457b-8e15-c05fd00138df)
![Screenshot_20250412-222916](https://github.com/user-attachments/assets/6a7eccee-3416-42ad-85fa-1b5885f50066)
![Screenshot_20250412-222853](https://github.com/user-attachments/assets/6d6ded1b-3a52-457b-8690-d5bab9fd7390)
![Screenshot_20250412-222839](https://github.com/user-attachments/assets/0aec7b82-da0e-412c-914e-a58643fe57ab)
![Screenshot_20250412-222835](https://github.com/user-attachments/assets/4e7ed6fc-928b-43b3-b8b2-28010f86cc82)
![Screenshot_20250412-222832](https://github.com/user-attachments/assets/709aaed0-e0c4-4be2-b9b9-9484c10ba3d0)
