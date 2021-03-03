# Subtonomy

A client/server application for word count in Java. Each of the components have following roles.
## Server
•	Read the file islands_in_the_stream.txt from disk. \
•	Send it to the client when the client requests to have it
## Client
•	Connect to the server and request to get the file. \
•	Create a top ten lists over the most common words in the file and the number of occurrences for these words.

<br/><br/>
# Steps to run the program
**1. Start the server**\
```java -jar Server.jar```

**2. Start the client and pass the name of file as an argument**\
```java -jar Client.jar islands_in_the_stream.txt```

---
## NOTES
* Note that the file islands_in_the_stream.txt should be available on the path where Server.jar is running.
* The source code can be found in the WordCount directory
---
