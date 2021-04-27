Implement English to Estonian dictionary console program (that is just a normal Java program, which I will run in IntelliJ), such that:
Upon starting the program, it should first ask me what I want to do:

Create a new dictionary: When the dictionary is created, it should be created as a .txt file, and it should be permanently
stored on a local disk (so that after restarting program, client will be able to "load the existing dictionary").
Everything that you'll enter into a dictionary will be stored in a file;

Load the existing Dictionary: loading the dictionary should be possible by file name -> user is prompted to enter a file name to be loaded, and corresponding file is loaded into Java application's memory (you read the data from file and store it somewhere).. 

Notice, that multiple files can exist;
When the user creates OR loads the dictionary, better to display a informative dialogue message, like: "Dictionary X has been created" or "Dictionary X has been successfully loaded", after which, user should have available two options:

Display entire dictionary;

Lookup the word (user enters the word and translation is returned.. of course, user should be able to enter another word, and etc.. hint: use while);

Enter a new entry - user should be able to add word(s);

User should always be able to exit program gracefully (without just stopping IntelliJ). Think of implementing some functionality, to interactively ask the user something like this: "If you want to quit the program, please type "Q" and press enter".
This all may seem a bit too much complicated, but it is not, and you have all the knowledge to implement such a program.
Hint: start small, try to implement each little feature, piece by piece.. step by step.. and you will be surprised where you'll reach.
