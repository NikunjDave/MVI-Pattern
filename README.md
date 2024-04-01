# Android-MVI-SAMPLE

Card app built with jetpack Compose and Clean + MVI architecture.

# Main Features
- Demonstrate the MVI pattern following clean architecture pattern

# App Screen
- Login Screen [LoginScreen](screenshot/login.png)
  - show login screen, upon login we fetched the user detail along with user cards
  - on login navigated to card list screen
- CardList Screen [CardList](screenshot/card_list.png)
  - show all cards available for users.
- Checkout Screen [Checkout](screenshot/checkout.png)
  - show selected cards

## Architecture 🏗️
- Clean Architecture (data - domain- ui)
- MVI Architecture
- Repository pattern
- Hilt - dependency injection
- Jetpack Compose
- Datastore prefs
