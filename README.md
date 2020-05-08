# Term 3 - Java Pool Project (Daniel Norris)

Project 2 - The java pool game

## How to Play
Make sure you're with a friend and run the game.
Choose who's going to take the first shot and then start playing using the rules of Pool outlined [here](http://www.epa.org.uk/wrules.php)

## How to compile
Below you will find a section for how to compile the project on each of the three main operating systems.
All the below are to be executed via command line and require no extra installations.
If you already have gradle installed then open the terminal in the directory and run `gradle clean shadowJar`
Otherwise, find the relevant section below for your OS and follow the instructions found there.

### Mac


### Linux
Start by right-clicking in your directory of choice (one you have administrator access to) and clicking to open the terminal.
Clone the repository into the folder using the `git clone https://codefeedback.lancaster.ac.uk/git110/Term3-norrisd/project.git` command
or extract the compressed file downloaded from the website.

Then using the `cd` command enter the directory to which you cloned the repository.

Once in the correct directory ensure the `gradlew` file exists and the `gradle` directory exists.
If they do not exist then there was an issue in cloning the repository!

To compile and run the project run the following command: `sh run.sh`

If that doesn't work then there was an issue in cloning the repository!

You'll know it was successful as the program will open once the compiler has finished.
Upon the first compile it may take longer than the typical compile time (i.e. anywhere from 2 minutes depending
on how fast your computer is).

### Windows
Start by opening the command prompt, or if you have Git bash then go to the directory and open it there.
Clone the repository into a folder using the `git clone https://codefeedback.lancaster.ac.uk/git110/Term3-norrisd/project.git` command
or extract the compressed file downloaded from the website.

Then using the `cd` command enter the directory to which you cloned the repository.

Once in the correct directory ensure the `gradlew` and `gradlew.bat` files exists and the `gradle` directory exists.
If they do not exist then there was an issue in cloning the repository!

To compile and run the project run the following command: `run.bat`

If that doesn't work then there was an issue in cloning the repository!

You'll know it was successful as the program will open once the compiler has finished.
Upon the first compile it may take longer than the typical compile time (i.e. anywhere from 2 minutes depending
on how fast your computer is).

## Development
If you want to use the GameArena implementation for development and have any questions then visit the [wiki](https://codefeedback.lancaster.ac.uk/git110/Term3-norrisd/project/wiki/Developer+API/)


## Contribution
If you want to contribute to this implementation of the GameArena follow the guide on the [wiki](https://codefeedback.lancaster.ac.uk/git110/Term3-norrisd/project/wiki/Contribution)