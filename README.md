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

## About this project

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

link for accessibility reference from Google

## Build Status

[![Build status](https://badge.buildkite.com/fae4d188e43aa2339505c96c4c7c0c0cc506f018abd3c6c949.svg)](https://buildkite.com/minamidaniel/accessible-memory-game)

## Project Requirements


### `Basic` Requirements

<img align="left" width="100" height="100" src="https://media.giphy.com/media/IdU8QouHMzMdseSEUG/giphy.gif">

[x] Minimal requirement asked 
```
* Comments about how it was completed
```
---

\[x] The user should have to find a minimum of 10 pairs to win.
* User can choose at the `SettingsFragment` page a different number of pairs to win.

\[x] Keep track of how many pairs the user has found. 
* The `playerMoves` and `remainingPairs` LiveData objects keep track of it.

\[x] When the user wins, display a message to let them know!
* The `isWinnerLiveData` is observed by the View and displays a dialog when condition is true.

\[x] Make sure it compiles successfully.
* An automated build is daily scheduled in [Buildkite] to make sure project compiles and passes all the tests successfully.

---

### `Bonus` Requirements

<img align="right" width="100" height="100" src="https://media.indiedb.com/cache/images/games/1/35/34365/thumb_300x150/0_animation_Character_LevelUp.gif">

[x] Bonuses requirements 
```
* Comments about how it was completed
```
---

\[x] Make the game configurable to match 3 or 4 of the same products instead of 2.
* `SettingsFragment` page allows users to change the match size.

\[x] Make the grid size configurable. (The player needs to match more than 10 sets of the same product).
* Users can choose both, `Number of Pairs to Win` and `Number of Cards to Make a Match`. This will determine the grid size automatically. The `Utils` class was created to make it look good in different devices and screen sizes.

\[x] Build a slick screen that keeps track of the user’s score.
* A `LinearLayout` was placed in the Game page bottom to keep user posted. This observes the `playerMoves` and `remainingPairs` LiveData objects.

\[x] Make a button that shuffles the game.
* Players are able to reset the game at almost any time. The exception comes when a `Runnable` is being executed.

\[x] Feel free to make the app beautiful and add anything else you think would be cool!
* Please check out the Aditionl

---

### Additional Features

<img align="center" src="https://media.giphy.com/media/13HgwGsXF0aiGY/giphy.gif">

---

[x] Vision impaired focused game
    * 

[x] Custom Retrofit Class
    * `SettingsFragment` page allows users to change the match size.

[x] Automated tests and build
    * 

[x] Settings screen
    * 

[x] Custom Fragment Class
    * This customized class helps to keep track of the `Fragments` lifecycle. As the application grows, this becomes very handy.

[x] Landing Fragment
    * A presentation landing fragment helps blind users to 


## Screen Shots

------------ | -------------
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column

## Authors

* **Daniel Minami** 

## License

This project is licensed under the Apache License 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
