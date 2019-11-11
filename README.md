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

## Tests

In order to run the unit tests, make sure the project is built. Then open a terminal in `median-finder` folder and run `mvn clean test`.

## Technical aspects

The program was designed and tested with `.txt` files containing up to 5GB of data, each line representing a real number. I've assumed the values won't be less than `LONG.MIN_VALUE` and greater than `LONG.MAX_VALUE`.

The file may contain integers or decimal numbers. Those can be represented by a **,** (COMMA) or by a **.** (POINT).

Since the program expects files very large in size (> 1TB), there is no way the content of the file could all be loaded in memory at the same time. That's why I tried making this program memory efficient, by using min-max heaps.

Instead of sorting the numbers and finding the middle index to determine the median, the program uses a **minHeap** and a **maxHeap** to determine the median *on-the-run*. That is, every time a new number is read from the file, the current median will be updated. The numbers found are stored into two separate priority queues: one for numbers smaller than the current median, called `maxHeap`, with the highest element at the root of the queue. A separate queue, called `minHeap` stores the numbers greater than the current median, with the lowest element at the root of the queue. Therefore, it is very efficient to check the median by just checking the top-most elements. The two heaps are not allowed to have a size difference greater than 1. If one of them breaks this rule, the two of them will be rebalanced, by moving the root element from one queue to the other, depending on the current median value.

## Example

Considering the file `test.txt` containg:
```
4
7,8
3.22
1.0002
788,544
-8
-8.21
140.1
78888888895,777
```

The output would be `4`.
