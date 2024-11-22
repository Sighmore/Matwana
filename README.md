# Matwana App

**Matwana** is an Android application for booking long-distance matatu (shared minibus) travel across various routes in Kenya. This app allows users to create an account, browse vehicle schedules, book seats, and make payments conveniently. 

## Features
- **User Authentication**: Sign up, log in, and log out using email/password or third-party providers (Google, Facebook).
- **Route Management**: View routes such as Nairobi to Mombasa, Kakamega, Bungoma, Turkana, and more.
- **Seat Booking**: Choose available seats and confirm bookings for selected routes and times.
- **Payment Integration**: Securely pay for tickets directly within the app.
- **User Profile**: Manage personal information like name, email, and phone number.
- **Offline Support**: Access trip history and user profile offline using Room database.

---

## Technologies Used
### Frontend
- **Kotlin**: Primary language for Android development.
- **Jetpack Compose**: Modern declarative UI framework for building UIs.
- **Navigation Component**: For seamless screen transitions.

### Backend
- **Firebase Authentication**: Manage user authentication.
- **Firebase Firestore**: Store and retrieve booking and route data.
- **Room Database**: Store user profile and booking data for offline access.

### Additional Libraries
- **Coroutines**: For asynchronous programming.
- **StateFlow**: To handle UI state and authentication updates.
- **Retrofit**: For network requests (optional if external APIs are used).

---

## Project Structure
```
app/
├── view/
│   ├── pages/              # Individual screens (Login, SignUp, HomePage, etc.)
│   ├── MatwanaNavigation   # Centralized navigation graph
├── viewmodel/              # ViewModels for state management
├── repository/             # Handles data interactions (Firebase, Room, etc.)
├── model/                  # Data models (e.g., User, Route, Booking)
```

---

## Setup Instructions
### Prerequisites
1. **Android Studio** (Arctic Fox or later).
2. **Firebase Project**:
   - Create a Firebase project [here](https://console.firebase.google.com/).
   - Enable Firebase Authentication (email/password, Google, Facebook as required).
   - Set up Firestore database.
3. **API Keys**: Add your `google-services.json` file to the `app/` directory.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/matwana.git
   cd matwana
   ```
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the app on an emulator or physical device.

---

## Usage
1. **Sign Up or Login**:
   - New users can sign up with their email and password.
   - Existing users can log in to access their bookings.
2. **Explore Routes**:
   - Browse available routes and view details.
3. **Book a Seat**:
   - Select a route, date, time, and seat to confirm your booking.
4. **Payment**:
   - Complete the payment through the integrated payment gateway (e.g., M-Pesa, card).
5. **Profile Management**:
   - Update your profile information (name, email, phone).

---

## Contributing
We welcome contributions to enhance the Matwana app! Here's how you can contribute:
1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m "Add feature"`.
4. Push to the branch: `git push origin feature-name`.
5. Open a pull request.

---

## License
This project is licensed under the [MIT License](LICENSE).

---

## Screenshots
Add screenshots here for better visualization, e.g., Login Screen, Home Page, Booking Screen.

---

## Contact
For questions, feature requests, or issues, feel free to reach out:
- **Email**: simon.matwana@app.com
- **GitHub Issues**: [Create an issue](https://github.com/your-username/matwana/issues)

Let’s make matatu travel seamless! 🚐
