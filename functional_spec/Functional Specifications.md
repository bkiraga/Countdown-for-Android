#

# CA326

# Functional Specification





Project: Countdown for Android

Team: Gheorghe-Vasile Burac and Bartlomiej Kiraga

Project supervisor: Dr. Darragh O&#39;Brien











Countdown for Android is an Android OS application which implements the game &quot;Countdown&quot;, presented in the popular TV show &quot;Countdown&quot;. The app allows the user to play all three rounds of the game in a single player and multiplayer mode. The app aims to provide a great user experience and an accessible user interface. Also, the app offers the user the option to see the optimum solution to the game challenge.



## 1. Introduction

1.1 Overview

Countdown for Android is a multiplayer game designed for Android OS. It is  inspired by the TV show Countdown. It implements the Letters round, the Numbers round and the Conundrum round in the same way as portrayed in the show.

1. **Letters** ​is the first round of the game. The player must select between vowels and consonants nine times. Every selection returns a random vowel or consonant, respectively. The player must find the longest possible word using the available letters.
2. **Numbers** ​is the second round. It consists of the user receiving a random number and a set of six numbers. The user must use some or all of the following mathematical operations: addition, subtraction, multiplication and division, in order to obtain a number close or equal to the target number.
3. **Conundrum** ​is the third round of the game. The player is presented with a set of nine letters and must use them all in order to create a word present in the English dictionary.

The player has thirty second to complete each round. A timer function will ensure the accuracy of the allowed time. The score is calculated based on the length of the word produced in Letters round, how close the player is from obtaining the target number in the Numbers round and on whether or not the player is able to complete the Conundrum round by finding the nine letter word.

There are a number of implementations of the game available on the internet, however all these implementations are quite poor. These implementations lack certain elements of functionality such as some of the rounds of the game, the solver (or a way to observe the optimum solution to the game challenge), or have poor implementations of the user interface, offer poor user experience or lack the multiplayer experience. The number of alternative implementations available for Android OS users is very small and fall in the same category, lacking functionality and offering poor user experience.

In order to offer a great user experience, there is a need for an implementation of the game that includes features similar to the experience provided by the TV show called Countdown. The TV show offers the audience the possibility to play the game (all three rounds) against other people (who are present on the set) and offers them the optimum solution to the challenge, thus providing them with the opportunity to compare their results. This is an important part of the entertainment element as it involves the competitive side of the individual.

There is a need for an implementation of the game available on Android OS that includes all this functionality presented in the TV show and meets all the needs of the users who wish to play the game.

Countdown for Android will provide the user with a solution to this problem by implementing all three rounds of the game, a multiplayer experience, the solver for each challenge which will offer the optimum solution to the challenge, an accessible user interface and a great user experience by following the best practices and industry standards regarding app development.

Countdown for Android will function on Android OS. It&#39;s interactions with the operating system consist of system calls to obtain user input such as credentials and game related choices, access to gyroscope functionality in order to provide the best user experience depending on the device&#39;s orientation and access to networking functionality in order to allow for multiplayer experience.

The application will interact with a number of Google Firebase products in order to provide functionality for all of its features. Such Firebase products are:

- --Firebase Cloud Functions in order to validate the game state
- --Firebase Authentication in order to provide authentication to the users who wish to use the multiplayer features
- --Firebase Cloud Storage in order to offer the multiplayer experience by storing user generated data
- --Firebase Realtime Database in order to offer a low latency multiplayer experience

The list of interactions is open to change as functionality is added.

1.3 Glossary

_Android OS - Operating system for mobile devices (phones, tablets, TV, etc)_

_Firebase Realtime database - Real time database system hosted in the cloud_

_Kotlin - Cross Platform Programming Language (IOS &amp; Android)_

_Java - General Purpose Programming Language (runs on Android OS)_

_Android Studio - Integrated Development Environment for Android OS_

_System - The Android Application and its associated components or services_

_Firebase Cloud Functions - Code that is executed on the server side_

_Firebase Authentication - Authentication service provided by Google Firebase_

_Firebase Cloud Storage - Memory storage service provided by Google Firebase in the cloud_

_Android Application - a program that is developed to be run specifically on the Android OS_

## 2. General Description

2.1 Product / System Functions

The system functions are:

- Login
- Play against live opponents (Play Letters, Numbers, Conundrum Rounds)
- Practice Play (locally, against a bot)
- View score
- View Optimal Solutions

The above enumeration is subject to change as more functionality could be added.

2.2 User Characteristics and Objectives

The user community is represented by the people that use mobile devices such as phones and tablets that run the Android OS (Operating System) version 4.4 (KitKat) or later. This is to make the application available to approximately 96.2% of the Android OS users. This is becoming an industry standard.

Objectives from the user&#39;s perspective may include being able to play the Countdown game against other people. There may be significant support towards this claim as there are multiple TV shows that include the three round game. One of the TV shows, &quot;Countdown&quot; enjoys significant popularity as now has been running for more than twenty seven years and is on its sixty nineth season.

The requirements for the game implementation, from the users&#39; point of view, may include:

- have a good user experience
- have an accessible user interface
- have good graphics
- offer fast response from the device regarding their attempted action.



## Operational Scenarios

**2.3.1 User creates a local account**

When the user runs the application for the first time, he is asked to provide a username. This provides the user personalised access to the application features. This functionality is available to the user who wish to play the game offline.

**2.3.2 User registers an online account**

On the main menu the user is given the option to create an online account. When this option is selected the user is asked to provide a username and their email. After the registration the user is able to play against other users. This requires internet access as it uses Firebase Authentication.

**2.3.3 User plays against a bot**

This option creates a lobby that contains the player and a bot. The user has the option to select the bot&#39;s difficulty level and the option to start the game.

**2.3.4 User plays against other player(s)**

This option allows the user to play against others option a lobby is created that contains the user and up to three other players. After at least one player has joined the lobby the user is given the option to start the game.

**User plays the letters round**

After the game is started all the players are directed to the letter round.

**User plays the numbers round**

**User plays the conundrum round**

**User requests a solution**

After completing a round the user is presented with the option to view solutions for the round. When the user selects this option the best scoring solutions available are displayed to the user.

**User views personal scoreboard**

At the main menu the user is given the option to view their scoreboard. After they select this option their top five scores are displayed for both the bot and the player matches.

**Users views global scoreboard**

Users that registered with the online account have the option to view and compare the scores of all the players.

2.4 Constraints

**Hardware constraints**

The application requires the following minimum hardware specification:

- CPU (Central Processing Unit) - Intel Atom® Processor Z2520 1.2 GHz, or faster processor
- Storage - between 1.0 GB and 1.4 GB depending on the language version
- RAM (Random Access Memory)  - 512 MB
- Network - 3G

**Time constraints**

Time constraints applied in the process of development of this application as the developers had six modules to complete in the first semester (the equivalent of 30 credits). This allowed

for the scenario where the students had to prepare for five exams in January 2020. The deadline for completion of this project is the 6th of March 2020. This provides a short time for research, development, documentation and testing.

It is hoped that this scenario will have a minimum impact on the quality of development carried out by the two people team, given the planning work carried out.

In order to provide satisfactory work the development team has decided to implement the core functionality first, then the less critical functionality with possibility to add later features if time permits.

The application was developed by following an Agile methodology. The core functionality is detailed in the next section.

**User constraints**

The user constraints are minimal as the expertise required to use the application is similar to the experience required to use any other Android OS application. At the moment, there are approximately 2.5 billion Android OS users worldwide, this means that the user population is generally well educated regarding the use of such systems and applications.

In order to ensure that our application is accessible to our users we will follow the best practices in terms of app development and the app will include explanations and information to educate the users regarding the functionality available to them.

The design of the user interface will follow principles such as:

- -- &quot;recognition rather than recall&quot;, in order to allow the user to intuit the use of each button or function
- --&quot;consistency and standards&quot;, in order to allow the user to get familiar with the application in a more easier fashion
- --&quot;error prevention&quot;, to allow the user&#39;s flow not to be disturbed by misunderstanding regarding the functionality of the system.





**Cost constraints**

The cost constraints must be considered given that we are using Firebase, which is a cloud service provided by Google. The project is currently using the free tier price plan, however if the use increases this option will become unavailable.

## 3. Functional Requirements

**Find players**

Description:

Our system must allow users to have the option of finding other users to play against. Once the user finds his opponents our system needs to create a lobby of players ready for the match. Both will be managed by Firebase realtime database.

Criticality:

This a critical feature as the end goal of our app is a multiplayer game.

Technical issues:

We need to manage and store data between users in real time. We will be using the Firebase realtime database to solve this issue.

Dependencies:

Player choosing to play against other users, Firebase



**Add bots**

Description:

Our system gives users an alternative to playing against other people by allowing them to play against bots. The user will have the option to either play against human opponents or practice their skills against a bot. The bot will have variable difficulty level which will be implemented by handicapping its capabilities.

Criticality:

This is a critical feature as it allows our game to be played regardless of availability of opponents or user&#39;s connection to the internet.

Technical issues:

There is an issue of making bots that can solve the challenges presented in the game without being too efficient at it. Bots that cannot finish the rounds are useless while bots that do it beyond human capability are not fun to play against.

Dependencies:

Player choosing to play against bots.

**Letter round**

Description:

The first round of our game is the letter round in which the player selects a random vowel or consonant 9 times and then attempts to create the longest word possible from the available letters.

Criticality:

Critical feature due to the fact that this round is a core part of the game rules

Technical issues:

There is an issue of checking the validity of words given by the players which is solved by using a database containing a file with a list of all english words.

To improve performance we plan to scan the contents of zipped files without having to extract it.

Dependencies:

Database (Firebase), English dictionary file

**Letter round solver**

Description:

The game implements a feature for finding the best possible solutions for the letters round using an algorithm that compares the longest possible combinations of letters given to the player with the list of all english words.

Criticality:

Low priority feature

Technical issues:

Finding the best answers in an efficient manner.

Dependencies:

Participation in the letter round, database, English dictionary file.



**Number round**

Description:

The second round of our game which is the numbers round in which the players pick 6 numbers from 24 possible numbers. A random number is generated and the contestants try to manipulate their numbers and arithmetic operators in order to get a number that is as close as possible to the number generated.

Criticality:

Critical feature due to the fact that this round is a core part of the game rules

Technical issues:

None

Dependencies:

Player finishing the letter round.

**Number round solver**

Description:

Our game implements a feature of finding the best possible solutions for the number round using our algorithm that searches a tree made of permutations of possible operations of the numbers given to the player.

Criticality:

Low priority feature

Technical issues:

Finding the best answers in an efficient manner

Dependencies:

Participation in the number round

**Conundrum round**

Description:

The third round in our game is the conundrum round in which the player gets 9 random letters and has to use all of them to create an english word.

Criticality:

Critical feature due to the fact that this round is a core part of the game rules

Technical issues:

Same as letters round

Dependencies:

Player finished the number round, database, english dictionary file

**Conundrum round solver**

Description:

Modified letters round solver

Criticality:

Low priority feature

Technical issues:

Finding the best answers in an efficient manner

Dependencies:

Participation in the conundrum round, database, english dictionary file

**Scoreboard**

Description:

Displays the winner as well as the scores of all the players in the match.

Criticality:

Medium priority feature.

Technical issues:

None.

Dependencies:

All rounds completed, database.

**3.2 Interface requirements**

The interface must be designed such that it is accessible to people that need to use a screen reader or other accessibility tools.

**3.3 Performance requirements**

The system must be responsive such that it doesn&#39;t take more than two seconds to receive a response to any of the attempted actions supported by the app.

**3.4 Reliability requirements**

The system must be up 99.999% of the time.























##

## 4. System Architecture

The architecture of the system is illustrated below. The Android OS application consists of a number of components such as a User Interface, Game logic and other components which interact with Firebase Cloud Storage, Firebase Cloud Functions, Firebase Authentication and Firebase Realtime Database.

The Game state functionality uses Firebase Cloud Functions to validate the game state, thus preventing the user from exhibiting unwanted behavior on the app.

The online authentication uses Firebase Authentication in order to offer efficient and reliable authentication services to users who wish to play the game online.
 ![](https://gitlab.computing.dcu.ie/buracg2/2020-ca326-gburac-countdownforandroid/raw/master/Functional%20Specification%20images/O.PNG)

The user generated game data is stored on the cloud using Firebase Cloud Storage functionality.

Firebase Realtime Database ensures a low latency multiplayer experience by dealing with the client requests in realtime and providing responses. The data is later moved to Firebase Cloud Storage.

The use of all these Google Firebase components allow for fault tolerance and high availability to be features of the app service. The application will be fault tolerant as it will have the Firebase Realtime Database and Firebase Cloud Functions use multiple instances of the service. This allows for continued use even in the unlikely case of a component failing.

The functionality of the system will be monitored and in the event of a component failure, other instances of the service will be ready to replace the failed component. This means that there will be at least one instance of each component running in standby mode such that is ready to allow the system to function even if there is a component failure.







## 5. High-Level Design

**5.1 Online Game Play**

The following diagram details the user interacting with the system in order to play the game online. The user signs up/logs in, then the user joins a lobby, plays the game views the optimal solutions and obtains his score. The user can view the leaderboard as well.
![](https://gitlab.computing.dcu.ie/buracg2/2020-ca326-gburac-countdownforandroid/raw/master/Functional%20Specification%20images/Project%20architecture%20-%20Core%20functionality.png)












**5.2 Local Game Play**

The following diagram illustrates the user interacting with the system in order to play the game offline. The user can play against a bot in order to create a more entertaining game experience.

The app, generally, attempts to allow for the same functionality offline. The user signs up/logs in, joins a lobby to play a game against a bot, views the optimal solution, views their score and ranking against the bot.

##
 ![](https://gitlab.computing.dcu.ie/buracg2/2020-ca326-gburac-countdownforandroid/raw/master/Functional%20Specification%20images/Project%20architecture%20-%20LOCAL%20functionality.png)
##





##

## 6. Preliminary Schedule

The following Gantt diagram illustrates in broad terms the project management element of developing Countdown for Android.

The project is divided in the following tasks:

1. Research
2. Submit Proposal
3. Functional Specification
4. Firebase back-end
5. User interface
6. Letters Round
7. Numbers round
8. Conundrum round
9. Documentation
10. Testing
11. Numbers Solver
12. Letters Solver
13. Conundrum Solver
14. Set up CI/CD pipelines
15. User Manual
16. Technical Specification
17.
17.Video Walkthrough
 ![](https://gitlab.computing.dcu.ie/buracg2/2020-ca326-gburac-countdownforandroid/raw/master/Functional%20Specification%20images/Schedule.PNG)


Each task will be accomplished by following the principle of &quot;pair programming&quot;. Both team members will contribute to the completion of each task. This is to allow for an improved learning experience, as outlined in the research associated with the subject.

## 7. Appendices

Accessibility best practices -[https://material.io/design/usability/accessibility.html#hierarchy](https://material.io/design/usability/accessibility.html#hierarchy)

[https://developers.google.com/appmaker/accessibility/make-accessible-apps](https://developers.google.com/appmaker/accessibility/make-accessible-apps)

App development best practices -

[https://www.smashingmagazine.com/2018/02/comprehensive-guide-to-mobile-app-design/](https://www.smashingmagazine.com/2018/02/comprehensive-guide-to-mobile-app-design/)

Minimum hardware requirements for devices to run Android OS version 4.4 (KitKat) - [https://source.android.com/compatibility/4.4/android-4.4-cdd.pdf](https://source.android.com/compatibility/4.4/android-4.4-cdd.pdf)

The use of mobile smartphones - [https://www.pewresearch.org/global/2019/02/05/smartphone-ownership-is-growing-rapidly-around-the-world-but-not-always-equally/](https://www.pewresearch.org/global/2019/02/05/smartphone-ownership-is-growing-rapidly-around-the-world-but-not-always-equally/)

User interface design heuristics - [https://www.nngroup.com/articles/ten-usability-heuristics/](https://www.nngroup.com/articles/ten-usability-heuristics/)