# Welcome to my Images Test App (an implementation of SerpApi's Google Search API).

### 1. The concept:

##### The application allows a search query for Google images to be sent to SerpApi.
##### The response from that query is then parsed and the image items are displayed in a list.
##### The displayed items are also simultaneously cached, so that they can be displayed again upon
##### relaunching the app without making the same query to SerpApi.
##### The user is also able to click on an item and see a detailed overview.

### 2. Technical overview:

##### - Architectural patterns: MVVM, SOLID, CLEAN
##### - Technologies/libraries: Koin, Room, Retrofit, Glide, Coroutines