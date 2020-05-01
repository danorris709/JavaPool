# Term 3 - Java Pool Project (Daniel Norris)

Project 2 - The java pool game

## How to compile
Below you will find a section for how to compile the project on each of the three main operating systems.
All the below are to be executed via command line and require no extra installations.
If you already have gradle installed then open the terminal in the directory and run `gradle clean shadowJar`
Otherwise, find the relevant section below for your OS and follow the instructions found there.

### Mac


### Linux


### Windows
Start by opening the command prompt, or if you have Git bash then go to the directory and open it there.
Clone the repository into a folder using the `git clone https://codefeedback.lancaster.ac.uk/git110/Term3-norrisd/project.git` command
or extract the compressed file downloaded from the website.

Then using the `cd` command enter the directory to which you cloned the repository.

Once in the correct directory ensure the `gradlew` and `gradlew.bat` files exist.
If they do not exist then there was an issue in cloning the repository!

To compile the project run: `gradlew clean shadowJar`.

If that doesn't work then try: `./gradlew.bat clean shadowJar`.

Once the following output has been received:
![Image of build success](https://i.gyazo.com/fa56fb41b65b401cfbfaa1863e981e41.png)
then you should find a `build` directory has been generated.

In the build directory you should find a `libs` directory which will contain the `PoolProject.jar` file.

Double click the jar file to start the game.

## How to Play
Description of the game here