S-Cubed--VideoStore
===================

Create a virtual 'Blockbuster', with a membership list and video inventory.

Inluded in this repository are the Java files necessary to compile and run the application. I used NetBeans to create this program. Also included are two CSV files, one for video store members and the other for video inventory, that can be used as arguments. These text files can be edited as you see fit, or you may use your own. They will be included in the root of this repository.

If you are to edit or use your own file, there must not be any space between each segment of the line. For example, if I want to add another person to the file I must write it as "X,Y,123456" (without quotation marks). The format for a new customer is first name, last name, and a 10-digit numerical code. There are two formats that are acceptable in the film text file: (1) film name, year, 6-digit numerical code, or (2) film name, year, 6-digit numerical code, number of tapes.

The first argument must be the file for video store members, and the second must be for the video inventory. The VideoStore.java file is where the main function can be found.

In order to run in the command prompt (Windows), Java SDK must first be installed on your computer. Assumming that you already have it installed run this command: java -jar [insert directory here]\S-Cubed--VideoStore\Video Store\dist\Video_Store.jar [insert directory here]\S-Cubed--VideoStore\us-500.csv [insert directory here]\S-Cubed--VideoStore\movies.csv
