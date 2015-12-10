This program consists of four files.

First of all, Dictionary.java. This will hold the dictionary for the program. It allows the other programs to store the dictionary, do a binary search of the dictionary, add words into the dictionary such that the dictionary remains sorted, and update the dictionary file. 

Second, Report.java. This program will take care of the output files. It takes a file name and an ArrayList of words, and creates a file with the given file name containing the given words.

Third, SpellCheck.java. This program will use Dictionary to hold the dictionary. It provides functionality for updating the dictionary (both the file and the ArrayList), reading the user's input file, checking to see if a given string is a word, and removing unwanted characters from an input string.

Lastly, the main file, SpellCheckerGui.java. This is the program that you want to run. It is the interface between the user and the program. It will let the user input a file name, then run the input file through SpellCheck.java to get a list of words that are not spelled correctly. It will then display the misspelled words to the user, one at a time, to let the user decide if the word is actually misspelled or not. After the user has checked all the misspelled words, SpellCheckerGui.java will send two lists of words to Report.java: a list of words that were added to the dictionary, and a list of words that were not added to the dictionary. Report, as noted above, will create a file for each of those.