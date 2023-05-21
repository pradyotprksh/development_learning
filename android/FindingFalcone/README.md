# FindingFalcone

A native android application build using Jetpack Compose.

Libraries Used:
1. Jetpack Compose
2. Hilt - For dependency injection
3. Retrofit - For remote API calls

## Run Application

To run the application, open the project in Android Studio, after the build is finish click on the Run button at the top. No extra configuration is required.

## Tasks

1. `updateTranslations`
This tasks simply copies the json localization file and convert it to TR.kt file for easy use. Have used a Python script for performing this operation. Script can be found in `script` folder.

## Architecture

Have used clean architecture for this project. Below are the layers

- app: Contains the UI elements and view model.
- core: Contains the core logics like navigation, response details, etc.
- domain: Contains repositories and usecases.
  - data: Connects to the remote API and return the results.

Basically, the data flow is like below

App <--Usecases-- Domain <--Repositores-- Data layer

And it returns the result back to the App layer.

