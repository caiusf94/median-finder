# Median finder
Java program for finding the median value from a file with one number per line.

## How to build
This project is intended to be built using Maven. Make sure you have [Maven installed](https://maven.apache.org). 

Then open a terminal in the folder *../median-finder* and execute
`mvn clean package`

## How to run program

With the terminal still open in *../median-finder*, run the script **run.sh**:
`./run.sh [filename]`.

You can run the program using the mock files in the test resources:

`./run.sh src/test/resources/file_with_random_numbers.txt`

Or place your own .txt file either in the same folder, or somewhere else. Make sure the file's path is given relative to the folder `median-finder`. E.g. if you place your file **largedataset.txt** in the folder `median-finder`, run

`./run.sh largedataset.txt`

## Running from an IDE

I used IntelliJ to develop this program. For this IDE, go to `Main` class. On the top menu, go to *Run > Edit configurations* and enter the test file under *Program arguments*.
![Screenshot](/docs/img/edit_config.png)
