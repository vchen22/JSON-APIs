## Summary
1. By having a test in which I know what the result should be, I have 
an envision of what the logic of the code should already be. Having 
tests helped me keep track of my progress and what I should fix.
I can focus on one part of the program at a time, fixing little by
little and then piecing the whole program together.
	
2. ParseJSON.java takes in a set of data and formats the data in a 
specific way in by placing them in between curly brackets in a list. 
Within those data, it can look for a keyword in the data
and extract the value associated with that keyword. Main.java is used
to test if the funtionality of ParseJSON.java is working. In other words,
its checking if it is extracting the values correctly. It also takes
in weather report data from metaweather.com and prints out a report by
using PaseJSON.java to extract the data.

## ArrayLists & JSON & APIs
1. Arraylists doesn't have a fixed length while arrays have a fixed length
data, so the data structure of an arraylist can always be resized.
Arraylist can only hold objects whereas arrays can hold objects and
primitive types.

2. We use array.size() arraylist's memory capacity length is resizeable and
its length may not be the same as the number of objects inside the list.
array.size() would count the number of objects.

3. JSON uses the data structure arrays and objects. Arrays are an ordered
list of values within square brackets while objects are an unordered set
of name-value pairs in curly braces.

4. GET requests askes the server to send data to the browser while
POST requests askes to send data to the server and store the data.

## Commands
Questions about Vim:
1. In command mode, to undo you would type u and to redo you would type
Ctrl r.

2. Place cursor on top of the line you want to start deleting, then type
7dd in the command line and it will delete the line your cursor is on and
the following 6 lines.

3. The four direction navagation keys are h, j, k, and l

4. Be on command mode and place cursor on the line you want to copy. Then
press shift + V to select whole line and press y to copy the line. Place
cursor where you want to paste and press P to paste before cursor or p 
for after the cursor.

Questions about Linux:
1. cp -r *.class ..

2. rm -r Tester

3. ls -al displays the directory's listing in a format with the hidden files
ls -r dislay files and directories in reverse order
ls -s list file size

4. Use the command mv bar.b foo.f

5. Use the command cd .. to go to parent directory 

