# NeighborFit AI - Smart Neighborhood Recommendation App

## 1. Introduction
NeighborFit AI is a cloud-enabled Android application developed using Kotlin that helps users find the most suitable neighborhood based on their lifestyle preferences.

The application integrates:
*   Smart weighted recommendation algorithm
*   Firebase cloud services
*   AI-based personalized explanations (Google Gemini AI)
*   Advanced networking using Retrofit
*   Device sensors (Light, Accelerometer)
*   WebView integration (Google Maps)
*   Real-time database and authentication

The system aims to simplify decision-making for users who struggle with selecting the right residential area due to multiple complex factors like budget, safety, and lifestyle compatibility.

## 2. Problem Statement
Finding an appropriate neighborhood is challenging because:
*   Users have multiple preferences (budget, safety, amenities, lifestyle).
*   Data from multiple sources is overwhelming.
*   Most platforms provide listings but not personalized reasoning.
*   No intelligent explanation system is available.

NeighborFit AI addresses these issues using a combination of scoring algorithms and artificial intelligence.

## 3. Objectives of the Project
*   Build a cloud-enabled Android application using Kotlin.
*   Implement MVVM architecture for clean code separation.
*   Develop a smart weighted matching algorithm.
*   Integrate Google Gemini AI for intelligent explanations.
*   Implement Firebase Authentication and Firestore database.
*   Implement CRUD operations for neighborhood management.
*   Integrate Retrofit for advanced networking.
*   Implement device sensors and WebView features.
*   Deploy the application in Play Store compliant format.

## 4. Technology Stack
| Component | Technology |
| :--- | :--- |
| **Language** | Kotlin |
| **Architecture** | MVVM (Model-View-ViewModel) + Clean Architecture |
| **Database** | Firebase Firestore & Room Database (Local Storage) |
| **Authentication** | Firebase Auth (Email & Google Sign-In) |
| **Networking** | Retrofit & Coroutines |
| **AI Integration** | Google Gemini API |
| **Cloud Messaging** | Firebase Cloud Messaging (FCM) |
| **Hardware** | Accelerometer, Light Sensor, Bluetooth monitoring |
| **Web Content** | WebView (Maps & City Info) |

## 5. System Architecture Overview
The system follows a strict Clean Architecture pattern:
**Activity/Fragment → ViewModel → UseCase → Repository → Data Source (Room/Firebase)**

### 📁 Project Structure
```
app/src/main/java/com/example/neighborfitai/
├── data/               # Data Layer: Implementation of repositories and data sources
│   ├── dao/            # Room DAOs
│   ├── entity/         # Room Entities
│   ├── local/          # DataStore and Local DB setup
│   ├── mapper/         # Mappers (Entity ↔ Domain, DTO ↔ Domain)
│   ├── model/          # DTOs (Data Transfer Objects)
│   ├── remote/         # Retrofit API Services
│   └── repository/     # Repository Implementations
├── domain/             # Domain Layer: Business logic and abstractions
│   ├── model/          # Pure Kotlin Domain Models
│   ├── repository/     # Repository Interfaces
│   └── usecase/        # Business Logic (Use Cases)
├── ui/                 # Presentation Layer: UI and ViewModels
│   ├── admin/          # Admin Dashboard
│   ├── auth/           # Login, Register, Forgot Password
│   ├── detail/         # Neighborhood Details
│   ├── home/           # Main Dashboard
│   ├── map/            # Interactive Map View
│   ├── onboarding/     # ViewPager2 Onboarding flow
│   ├── preference/     # User Preference Input
│   ├── profile/        # User Profile & Settings
│   ├── result/         # AI Matching Results
│   ├── splash/         # Entry Splash Screen
│   └── state/          # UI State classes
├── utils/              # Helper classes and extensions
├── di/                 # Dependency Injection (Hilt)
└── MainActivity.kt
```

## 6. User Roles
### 6.1 Normal User
*   Register and login via Email or Google.
*   Enter lifestyle preferences (Budget, City, Priorities).
*   View recommended neighborhoods with AI-generated explanations.
*   Bookmark favorite neighborhoods (Offline support via Room).
*   Chat with an AI assistant for contextual advice.
*   Receive real-time notifications.

### 6.2 Admin
*   Manage the neighborhood dataset via Firestore.
*   Perform CRUD operations (Add, Update, Delete areas).
*   Monitor system analytics and API latency.
*   Trigger FCM alerts for system-wide updates.

## 7. Application Screens
*   **Splash Screen**: Initializes Firebase and checks session state.
*   **Onboarding**: Interactive ViewPager2 guide to app features.
*   **Login/Register**: Secure authentication via Firebase.
*   **Preference Input**: Detailed collection of user lifestyle data.
*   **Results Screen**: Ranked list of neighborhoods with match percentages.
*   **Detail Screen**: Comprehensive view of scores, metrics, and AI insights.
*   **Map View**: Google Maps integration with nearby insights.
*   **Profile**: User stats, saved neighborhoods, and account settings.
*   **Admin Dashboard**: Real-time system monitoring and dataset management.

## 8. Core Functional Modules
### 8.1 Smart Matching Engine
Implements weighted scoring to find the best residential fit:
`Final Score = (0.35 × Budget Match) + (0.25 × Safety Score) + (0.20 × Lifestyle Match) + (0.20 × Amenities Match)`

### 8.2 AI Integration Module
Uses Google Gemini API to provide personalized reasoning for matches and a contextual chat interface.

### 8.3 Sensor Module
*   **Light Sensor**: Auto-toggles UI themes based on ambient light.
*   **Accelerometer**: Detects device movement for interactive UI features.

## 9. Security Features
*   Secure Firebase authentication and Firestore rules.
*   BuildConfig usage for sensitive API keys.
*   Strict input validation and error handling.

## 10. Testing & Validation
*   Comprehensive UI flow testing (Splash → Results).
*   Algorithmic validation for matching accuracy.
*   Room + Firestore synchronization checks.
*   Stability testing across different device configurations.

## 11. Future Enhancements
*   Integration with live Real Estate APIs for up-to-date listings.
*   Multi-language support for AI insights.
*   Advanced predictive analytics for neighborhood price trends.

## 12. Conclusion
NeighborFit AI demonstrates modern Android development skills by combining Artificial Intelligence, Firebase services, and Clean Architecture to solve real-world urban living challenges.
