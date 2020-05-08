# Accessible Memory Game

This memory game focus Android users with vision impairment.

## Table of Contents

- [About this Project](#about-this-project)
- [Build Status](#build-status)
- [Project Requirements](#project-requirements)
  * [Basic Requirements](#basic-requirements)
  * [Bonus Requirements](#bonus-requirements)
  * [Additional Features](#additional-features)
- [Screen Shots](#screen-shots)
- [Author](#author)
- [License](#license)

## About this project

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

link for accessibility reference from Google

## Build Status

[![Build status](https://badge.buildkite.com/fae4d188e43aa2339505c96c4c7c0c0cc506f018abd3c6c949.svg)](https://buildkite.com/minamidaniel/accessible-memory-game)

## Project Requirements


### `Basic` Requirements

<img align="left" width="100" height="100" src="https://media.giphy.com/media/IdU8QouHMzMdseSEUG/giphy.gif">

- [x] Minimal requirement asked 
```
* Comments about how it was completed
```
---

- [x] The user should have to find a minimum of 10 pairs to win.
* User can choose at the `SettingsFragment` page a different number of pairs to win.

- [x] Keep track of how many pairs the user has found. 
* The `playerMoves` and `remainingPairs` LiveData objects keep track of it.

- [x] When the user wins, display a message to let them know!
* The `isWinnerLiveData` is observed by the View and displays a dialog when condition is true.

- [x] Make sure it compiles successfully.
* An automated build is daily scheduled in [Buildkite](#http://www.buildkite.com) to make sure project compiles and passes all the tests successfully.

---

### `Bonus` Requirements

<img align="right" width="100" height="100" src="https://media.indiedb.com/cache/images/games/1/35/34365/thumb_300x150/0_animation_Character_LevelUp.gif">

- [x] Bonuses requirements 
```
* Comments about how it was completed
```
---

- [x] Make the game configurable to match 3 or 4 of the same products instead of 2.
* `SettingsFragment` page allows users to change the match size.

- [x] Make the grid size configurable. (The player needs to match more than 10 sets of the same product).
* Users can choose both, `Number of Pairs to Win` and `Number of Cards to Make a Match`. This will determine the grid size automatically. The `Utils` class was created to make it look good in different devices and screen sizes.

- [x] Build a slick screen that keeps track of the user’s score.
* A `LinearLayout` was placed in the Game page bottom to keep user posted. This observes the `playerMoves` and `remainingPairs` LiveData objects.

- [x] Make a button that shuffles the game.
* Players are able to reset the game at almost any time. The exception comes when a `Runnable` is being executed.

- [x] Feel free to make the app beautiful and add anything else you think would be cool!
* Please check out the topic [Additional Features](#additional-features) to learn more about some more cool stuff.

---

### Additional Features

<p align="center">
  <img src="https://media.giphy.com/media/13HgwGsXF0aiGY/giphy.gif">
</p>

---

- [x] Vision impaired focused game
* As stated at the [Shopify's Accessibility Guide Lines](#https://polaris.shopify.com/foundations/accessibility#navigation) around 25% of the North Americans has some sort of disability. Accessible Memory Game was developed with special consideration to make it usable for everyone. Three main accessibility considerations are in place in this project: 
1. All UI elements have `Content Description` added to it. Content bound dynamically also includes appropriated content descriptions.
2. `Focus Order` is consistent. UI was designed to allow the natural flow of the Screen Reader.
3. More than one `Feedback Mechanism` was implemented to serve users with different kids of disability.
4. `UI Colors` were chosen respecting the [Shopify's Color Guide Line](#https://polaris.shopify.com/design/colors#navigation) having consideration about contrast and readability.

- [x] Game configuration for impaired and non-impaired users
* At Settings Page, users can switch on/off `Accessibility Enabled`. Switch off accessibility will enable features such as slight animations and sounds.

- [x] Custom Retrofit Class
* `CustomRetrofit` implementation enhances code reusability and allows easy switch on/off network `Interceptors`.

- [x] Automated tests and build
* Daily builds are ran at [Buildkite](#http://www.buildkite.com) to ensure code passes the tests and compiles successfully. Tests are written in Kotlin.

- [x] Settings screen
* Settings page allows users to change the difficulty level an also set the user experience.

- [x] Custom Fragment Class
* This extended Fragment Class helps to keep track of the `Fragment` lifecycle, so we know what is going on under the hood. This is specially handy for debugging, as the application grows.

- [x] Landing Fragment
    * A presentation landing fragment helps blind users to 

- [ ] More Stuff?
* Oh yeah!!! I wanted to do more stuff. Given the time constraint, I just listed it in the [Issues](#https://github.com/danielminami/accessible_memory_game_shopify/issues)

## Screen Shots

| ------------ | ------------- |
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column

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
|Junit|To write some Unit Tests|
|Roboeletric|Kinda Instrumentation test, once the context is need for some of them :-)|
|GSON|To convert JSON into POJO|


## Author

* **Daniel Minami** 
* minamidaniel@gmail.com

## Credits

* Game Sounds from Zapsplat.com

## License

This project is licensed under the Apache License 2.0 License.
