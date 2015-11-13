#Adam Sinck
#Spell Checker

#This program reads an input dictionary and does some spell checking

imports = [
    "from Tkinter import *",
    "import tkMessageBox",
    "import string"
]
#failedPackages will keep a record of the names of the packages that
#failed to import, so that the program can go through the entire list
#of packages that it wants to import. This will allow the program to
#give the user a complete list of packages that they need to install,
#instead of only telling the user one at a time.
failedPackages = ''
for i in imports:
    try:
        exec(i)
    except ImportError as error:
        failedPackages += str(error) + '\n'
#if there were any errors in the imports, tell the users what packages
#didn't import, and exit.
if len(failedPackages) > 0:
    print "Some packages could not be imported:"
    print failedPackages
    exit()



#yes, a global variable.
misspelledWords = []

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
def readFile(inputFileName, mode):
    userFile = open(inputFileName)
    punctuation = string.punctuation.translate(None, "'-")
    words = None
    if (mode == "array"):
        words = []
        for line in userFile:
            #strip the line of whitespace, remove punctuation, and split into words
            for word in line.strip().translate(None, punctuation).split():
                words.append(word)
    elif (mode == "str"):
        words = ""
        for line in userFile:
            words += line
    return words

#this tries to open a file to see if it exists
def checkForFile(fileName):
    try:
        myFile = open(fileName)
        myFile.close()
        return True
    except:
        return False

def processFile(text):
    punctuation = string.punctuation.translate(None, "'-")
    return text.strip().translate(None, punctuation).split()

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
def getFixWords(words, mode):
    fix = []
    if (mode == "text"):
        for word in words:
            prompt = "Misspelled word: %s. Add? (y/n)  " %word
            option = getOption("yn", prompt)
            if option == 'y':
                fix.append(word)
    else:
        #first make sure nothing's packed
        misspelledWordLabel.pack_forget()
        misspelledWord.pack_forget()
        addButton.pack_forget()
        ignoreButton.pack_forget()
        
        #ok, now pack
        misspelledWordLabel.pack(side=TOP)
        global misspelledWords
        misspelledWord.config(text=misspelledWords[0])
        misspelledWord.pack(side=TOP)
        addIgnoreFrame.pack(side=TOP)
        addButton.pack(side=RIGHT)
        ignoreButton.pack(side=LEFT)
        
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

#this adds one word to the dictionary
def addWord():
    words = []
    global misspelledWords
    word = misspelledWords[0]
    words.append(word)
    addWords(words)
    misspelledWords.remove(word)
    updateMisspelled()

#this ignores the current misspelled word
def ignoreWord():
    global misspelledWords
    word = misspelledWords[0]
    misspelledWords.remove(word)
    updateMisspelled()
    
def updateMisspelled():
    global misspelledWords
    if (len(misspelledWords) > 0):
        misspelledWord.config(text=misspelledWords[0])
        misspelledWordLabel.config(text="The current misspelled word:")
    else:
        messageLabel.config(text="Done checking.")
        messageLabel.pack(side=TOP)
        misspelledWordLabel.pack_forget()
        misspelledWord.pack_forget()
        addButton.pack_forget()
        ignoreButton.pack_forget()

# the main controller of the text-only version of the program
def main():
    #read the dictionary
    dictionary = readDictionary()
    
    #do the following as many times as the user wants
    go = 'y'
    while (go == 'y'):
        #get the file name
        fileName = getFileName()
        #get the words from the file that the user wants checked
        fileWords = readFile(fileName, "array")
        #get the misspelled words
        misspelled = getMisspelled(dictionary, fileWords)
        #work with user about the misspelled words
        fixWords = getFixWords(misspelled, "text")
        #add words to the dictionary
        if (len(fixWords) > 0):
            for word in fixWords:
                dictionary.append(word)
            addWords(fixWords)
        go = getOption("yn", "Run program again? (y/n)  ")

# the main controller of the text-only version of the program
def check(fileName):
    if (not checkForFile(fileName)):
        messageLabel.config(text = "File Not Found.")
        messageLabel.pack(side=TOP)
    else:
        messageLabel.pack_forget()
        
        #read the dictionary
        dictionary = readDictionary()
        fileContents = readFile(fileName, "str")
        document.delete("0.0", END)
        document.insert(END, fileContents)
        #get the words from the file that the user wants checked
        fileWords = processFile(fileContents)
        #get the misspelled words
        global misspelledWords
        misspelledWords = getMisspelled(dictionary, fileWords)
        if (len(misspelledWords) > 0):
            #let the program work with the user about the misspelled words
            getFixWords(misspelledWords, "gui")
        
        

#this is for the text-only version
#if __name__ == "__main__":
#    main()



#the root of the gui
root = Tk()
root.title("Spell Checker")

#top level frame
mainFrame = Frame(root)

#left and right frames
leftFrame = Frame(mainFrame)
rightFrame = Frame(mainFrame)

#the document goes on the left
documentFrame = Frame(leftFrame)
document = Text(documentFrame, height=28, width=80)

#options on the right
interactionFrame = Frame(rightFrame)

messageFrame = Frame(interactionFrame)
messageLabel = Label(interactionFrame, text = "")

docNameFrame = Frame(interactionFrame)
docNameEntry = Entry(docNameFrame, bd=5, width = 10)
docNameSubmit = Button(docNameFrame, text = "Open", command = lambda: check(docNameEntry.get()))
docNameEntry.bind("<Return>", lambda(none): check(docNameEntry.get()))
docNameEntry.focus_set()

misspelledWordLabel = Label(interactionFrame, text = "The current misspelled word:")
misspelledWord = Label(interactionFrame, text = "")

addIgnoreFrame = Frame(interactionFrame)
addButton = Button(addIgnoreFrame, text = "Add", command = lambda: addWord())
ignoreButton = Button(addIgnoreFrame, text = "Ignore", command = lambda: ignoreWord())


#pack up like it has the One Ring of Power and the Nazgul are coming
mainFrame.pack()

leftFrame.pack(side=LEFT)
rightFrame.pack(side=RIGHT)

documentFrame.pack()
document.pack()

interactionFrame.pack()
messageFrame.pack(side=TOP)
docNameFrame.pack(side=TOP)
docNameEntry.pack(side=LEFT)
docNameSubmit.pack(side=RIGHT)

root.mainloop()

