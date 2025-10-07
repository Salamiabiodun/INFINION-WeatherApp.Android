Weatherly: Android Weather Application

A mobile developer assessment for INFINION.**

This repository contains the source code for Weatherly, a simple yet elegant iOS weather application. This project was developed as a technical assessment to showcase proficiency in Android development, API integration, and user interface design.

 Features

Real-time Weather:** Get the current temperature, weather conditions, and city name.
Location-Based: Automatically fetches weather data for the user's current location.
City Search: Manually search for the weather in any city worldwide.
Clean UI: A user-friendly and intuitive interface.

 Built With

The primary programming language for modern android development.
UIKit: Jetpack Kotlin For building the user interface.
CoreLocation: To access the device's current location.
URLSession:For making network requests to the weather API.
OpenWeatherMap API: To fetch weather data.

Getting Started

To get a local copy up and running, follow these simple steps.

Prerequisites

  Android Studio installed on your Pc.
  An API key from [OpenWeatherMap](https://openweathermap.org/api).

Installation

1.  Clone the repository:**
    ```sh
    git clone https://github.com/your_username/weather-ios-app.git
    ```
2.  **Open the project in Xcode:**
    ```sh
    cd weather-ios-app
    open WeatherApp.xcodeproj
    ```
3. Configure the API Key:**
   Navigate to the `WeatherManager.swift` file.
  Replace `"YOUR_API_KEY"` with your actual OpenWeatherMap API key.

4.Build and Run:**
    Select your target simulator or physical device.
   Press `Cmd + R` to build and run the application.

 Code Overview

The project follows a standard MVC (Model-View-Controller) architecture to ensure a clean separation of concerns.

*   Model: Contains the data structures for the weather information.
*   View: The user interface elements and layout defined in the Storyboard.
*   Controller: `ViewController.swift` manages the user input, API requests, and updates the UI.
*   Services: `WeatherManager.swift` handles the communication with the OpenWeatherMap API.

Acknowledgements

This project was created as part of the mobile developer interview process with INFINION. I appreciate the opportunity to demonstrate my skills.
