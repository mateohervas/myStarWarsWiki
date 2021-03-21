# myStarWarsWiki

## Introduction:

This is a small but scalable app which interacts with the open [Star Wars API](https://swapi.dev/). The app has 3 main screens:
- Star Wars Character Search
- Star Wars Character Detail
- Favorite Star Wars Character 

### Expected User Flow

On app start, the user lands on the character search screen. The user can search for characters from the Star Wars universe. The result of the search should display a character list. When tapping on a character, details are displayed in the Character Detail Screen.
Also, on the character search screen there is a floating action button that takes the user to the favorite characters screen, which will fetch the characters saved by the user.

## The Implementation

### Language
This app is fully developed in Kotlin and uses the amazing power of Extension functions, Scope Functions, High Order functions, Null Safety and so much more!

### Architecture
This app was developed using a Clean - MVVM approach. This means that the app is divided in two main modules: App which has all the Android Dependencies and Core which has all the operations that do not depend on Android.
This secures that, the app will be more testable and scalable as each module has its own operations and libraries and is tested separately. 

### Dependency Injection
This projects uses the [Dagger Hilt](https://dagger.dev/hilt/) framework for dependency injection. This decision was chosen because Dagger Hilt is simpler to implement than plain Dagger and it builds the dependency graph on build time (contrary to Koin); this will reassure the developers that the graph is correctly created before the app even runs.

### Asynchronous Work
Kotlin Coroutines with flow is used in this project to create lightweight threads that handle all the asynchronous work such as all the IO Operations (API and Database).

### Api Communication 
 - The Core module uses Retrofit to fetch all the information from the API

### Jetpack

- Room to save favorite characters in a SQLite Database. It is also compatible with coroutines for asynchronous work which is amazing.
- LiveData
- LiveCycle
- View Model

### Animations

All the animations are brought to you thanks to the amazing [Lottie Library](https://lottiefiles.com/)

### Testing
Unit Tests are an essential part of this project. This project has unit test coverage for the mappers, the repositories and the view models and it uses the following libraries:
- Junit
- Mockito + Mockk
- Roboelectric 
- Jraska Live Data Testing

## Next Steps
In order to keep improving the codebase and the project, the following points are being taken into consideration:
- Define Dependencies in a unique module so the update of dependencies is simpler.
- CI (probably with github actions due to its simplicity). Also, CD is not being considered as this app is not meant to be deployed. 
- Instrumentation Testing
- Memory Leaks monitoring (probably with Canary Library)



