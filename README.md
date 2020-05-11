<p align="center">
  <img src="https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/app/src/main/res/drawable/accessible_memory_game_logo_gray_2.png">
</p>

# Accessible Memory Game

This game was passionately developed for Shopify for the Mobile Developer Intern (Android) - Fall 2020.

## Table of Contents

- [About this Project](#about-this-project)
- [Build Status](#build-status)
- [Project Requirements](#project-requirements)
  * [Basic Requirements](#basic-requirements)
  * [Bonus Requirements](#bonus-requirements)
  * [Additional Features](#additional-features)
- [Screen Shots](#screen-shots)
- [Libraries](#libraries)
  * [Android Libraries](#android-libraries)
  * [Third Party](#third-party)    
- [Author](#author)
- [License](#license)

## About this project

Thanks for taking time to read this. Some important consideration about this project:

1. I tried to show case my skills and passion for Android by going above and beyond the original scope.
2. I added a special flavour to this project by making it **Accessible**.
3. I also included some other cool stuff such as automated tests and build, log Interceptor, and a Settings Screen.
4. I tried to follow as much as I could the [Shopify POS](https://engineering.shopify.com/blogs/engineering/building-shopify-pos-android-using-mvvm) Architecture. The only thing I purposefully didn't add was the `Contracts` between the `View` and the `ViewModel`. Although I am familiar with this Design Pattern, as I have worked with MVP, I decided to use the `ViewModelProvider` to make the `View` `observe` the `LiveData`.

## Build Status

[![Build status](https://badge.buildkite.com/fae4d188e43aa2339505c96c4c7c0c0cc506f018abd3c6c949.svg)](https://buildkite.com/minamidaniel/accessible-memory-game)

## Project Requirements


### `Basic` Requirements

<img align="right" width="100" height="100" src="https://media.giphy.com/media/IdU8QouHMzMdseSEUG/giphy.gif">

- [x] Minimal requirement asked 
```
List of items in section "Requirements:"
```
---

|Requirement|Status|
|---|---|
|The user should have to find a minimum of 10 pairs to win|:heavy_check_mark:|
|Keep track of how many pairs the user has found|:heavy_check_mark:|
|When the user wins, display a message to let them know!|:heavy_check_mark:|
|Make sure it compiles successfully.|:heavy_check_mark:|

---

### `Bonus` Requirements

<img align="right" width="100" height="100" src="https://media.indiedb.com/cache/images/games/1/35/34365/thumb_300x150/0_animation_Character_LevelUp.gif">

- [x] Bonuses requirements 
```
List of items in section "Bonuses!"
```
---

|Requirement|Status|
|---|---|
|Make the game configurable to match 3 or 4 of the same products instead of 2|:heavy_check_mark:|
|Make the grid size configurable. (The player needs to match more than 10 sets of the same product).|:heavy_check_mark:|
|Build a slick screen that keeps track of the user’s score.|:heavy_check_mark:|
|Make a button that shuffles the game.|:heavy_check_mark:|
|Feel free to make the app beautiful and add anything else you think would be cool!|:heavy_check_mark:|

---

### Additional Features

<p align="center">
  <img src="https://media.giphy.com/media/13HgwGsXF0aiGY/giphy.gif">
</p>

---

- [x] Accessibility focused game
    * As stated at the [Shopify's Accessibility Guide Lines](https://polaris.shopify.com/foundations/accessibility#navigation) around 20% of the North Americans has some sort of disability. Accessible Memory Game was developed with special consideration to make it usable for everyone. Four main accessibility considerations are in place in this project: 
        1. All UI elements have `Content Description` added to it. Content dynamically bind also includes appropriate descriptions.
        2. `Focus Order` is consistent. UI was designed to allow the natural flow of the Screen Reader.
        3. More than one `Feedback Mechanism` was implemented to serve users with different kids of disability.
        4. `UI Colors` were chosen respecting the [Shopify's Color Guide Line](https://polaris.shopify.com/design/colors#navigation) having consideration about contrast and readability.

- [x] Game configuration for impaired and non-impaired users
    * At Settings Page, users can switch Accessibility on/off. Switch off accessibility will enable features such as animations and sounds.

- [x] Custom Retrofit Class
    * `CustomRetrofit` implementation enhances code reusability and allows modularized addition of network `Interceptors`.

- [x] Automated tests and build
    * Scheduled builds ran at [Buildkite](http://www.buildkite.com) to ensure code passes tests and compiles successfully. Tests are written in **Kotlin**.

- [x] Settings screen
    * Settings page allows users to change the difficulty level an also set the user experience for Accessibility.

- [x] Custom Fragment Class
    * This extended Fragment Class logs the `Fragment` lifecycle, so we know what is going on under the hood. This is specially handy for debugging, as the application grows.
    
- [x] Winner message
    * When Accessibility is disabled, we celebrate the user's victory with fireworks, we also give extra motivation by playing a winning sound!

- [ ] More Stuff...
    * Oh yeah!!! I wanted to do more stuff. Given the time constraint, I just listed the enhancements at [Issues](https://github.com/danielminami/accessible_memory_game_shopify/issues).

## Screenshots

|Accessibility Enabled|Accessibility Disabled|
|---|---|
|![AccessibleMainScreen](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_enabled_main.png)|![MainScreen](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_disabled_main.png)|
|![AccessibleSettingsMain](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_enabled_settings_main.png)|![SettingsMain](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_disabled_settings_main.png)|
|![AccessibleSettingsNumOfPairs](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_enabled_settings_number_of_pairs.png)|![SettingsNumOfPairs](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_disabled_settings_number_of_pairs.png)|
|![AccessibleGameTimeToExplore](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_enabled_game_time_revealed.png)|![GameTimeToExplore](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_disabled_game_cards_revealed.png)|
|![AccessibleGameNoMatch](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_enabled_game_no_match.png)|![GameNoMatch](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_disabled_game_no_match.png)|
|![AccessibleGameSomeMatches](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_enabled_game_some_matches.png)|![GameSomeMatches](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_disabled_game_some_matches.png)|
|![AccessibleGameWinner](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_enabled_game_winner.png)|![GameWinner](https://github.com/danielminami/accessible_memory_game_shopify/blob/trunk/screenShots/Accessibility_disabled_game_winner.png)|



## Libraries

### Android Libraries

|What|Why|
|---|---|
|Live Data|To make the App survive against configuration changes|
|ViewModel|To implement MVVM Archtecture, as ViewModels are Lifecycle Aware|
|ConstraintLayouts|To make it look good with different screen sizes|
|Preferences|To handle user's preferences|


### Third Party
|What|Why|
|---|---|
|Butter Knife|To get layout resources and get rid of some boiler plate code|
|Retrofit|To handle Service Calls|
|Glide|To bind the images bind in the Custom `ImageView`|
|Lottie|To play Animations|
|Junit|To write some Unit Tests|
|Roboeletric|For some kinda Instrumentation test, once the context is needed for some of them :-)|
|GSON|To convert JSON into POJO|


## Author

* **Daniel Minami** 
* minamidaniel@gmail.com

## Credits

* Game Sounds from Zapsplat.com
* Animation by Omar Mahmoud

## License

This project is licensed under the Apache License 2.0 License.