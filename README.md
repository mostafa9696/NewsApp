
## App Architecture

The Application is split into a three layer architecture:
- Presentation
- Domain
- Data

## Tech Stack

- Unit testing with mockito and mock webserver
- Hilt - Used to provide dependency injection
- Retrofit 2 - OkHttp3 - request/response API
- Glide - for image loading.
- Coroutines - to handle network requests
- LiveData - use LiveData to see UI update with data changes.
- Data Binding - bind UI components in layouts to data sources

## Overview of app arch.

- follow the rules from Architecture guidelines recommended by Google.
- keep Activity only responsible for UI related code
- ViewModel provides data required by the UI class
- Repository layer provides data to ViewModel classes. (single source of truth)