#Adam Sinck
#Spell Checker

#This program reads an input dictionary and does some spell checking

import string

# this reads the dictionary and returns an array of words
def readDictionary():
    dictionary = None
    try:
        dictionary = open("dict.txt")
    except:
        print("Dictionary not found. Initializing new dictionary.")
        dictionary = open("dict.txt", "w+")
        dictionary.close()
        #only open in read mode
        dictionary = open("dict.txt")
    words = []
    for line in dictionary:
        words.append(line.strip().lower())
    dictionary.close()
    return words

#this gets the file name from the user
def getFileName():
    inputFileName = raw_input("Filename?  ")
    while (not checkForFile(inputFileName)):
        print("File not found.")
        inputFileName = raw_input("Enter the filename again.  ")
    return inputFileName

# this reads the file that the user specifies
def readFile(inputFileName):
    userFile = open(inputFileName)
    punctuation = string.punctuation.translate(None, "'-")
    words = []
    for line in userFile:
        #strip the line of whitespace, remove punctuation, and split into words
        for word in line.strip().translate(None, punctuation).split():
            words.append(word)
    return words

#this tries to open a file to see if it exists
def checkForFile(fileName):
    try:
        myFile = open(fileName)
        myFile.close()
        return True
    except:
        return False

#this gets the misspelled words from the input file
def getMisspelled(dictionary, words):
    bad = []
    for word in words:
        lowerCased = word.lower()
        #see if a) the word is misspelled, and b) it hasn't been noted already
        if (lowerCased not in dictionary and lowerCased not in bad):
            bad.append(lowerCased)
    return bad

#this goes through the misspelled words, asking the user if they want
#to add or ignore the word
def getFixWords(words):
    fix = []
    for word in words:
        prompt = "Misspelled word: %s. Add? (y/n)  " %word
        option = getOption("yn", prompt)
        if option == 'y':
            fix.append(word)
    return fix

#this ensures the user gives a valid option
def getOption(options, prompt):
    option = raw_input(prompt).lower()
    while (option not in options or len(option) > 1):
        option = raw_input("Enter a valid option.\n%s" %prompt)
    return option
        
#this adds words to the dictionary
def addWords(words):
    dictionary = open("dict.txt", 'a')
    for word in words:
        dictionary.write(word + '\n')
    dictionary.close()


# the main controller of the program
def main():
    #read the dictionary
    dictionary = readDictionary()
    
    #do the following as many times as the user wants
    go = 'y'
    while (go == 'y'):
        #get the file name
        fileName = getFileName()
        #get the words from the file that the user wants checked
        fileWords = readFile(fileName)
        #get the misspelled words
        misspelled = getMisspelled(dictionary, fileWords)
        #work with user about the misspelled words
        fixWords = getFixWords(misspelled)
        #add words to the dictionary
        if (len(fixWords) > 0):
            for word in fixWords:
                dictionary.append(word)
            addWords(fixWords)
        go = getOption("yn", "Run program again? (y/n)  ")

#3 2 1 go
if __name__ == "__main__":
    main()
