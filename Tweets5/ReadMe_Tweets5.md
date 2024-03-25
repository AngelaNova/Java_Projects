# Tweet and Twitter Java Programs

This repository contains two Java programs: **Tweet** and **Twitter**, which are designed to handle tweets and Twitter-like functionalities. Below you'll find a brief overview of each program along with instructions on how to use them.

## Tweet

The **Tweet** class represents individual tweets with attributes such as user account, date, time, and message. It includes methods for checking the validity of messages, comparing tweet posting times, and loading stop words from a file. Here's a summary of its functionality:

- **Attributes:**
  - `userAccount`: The user account associated with the tweet.
  - `date`: The date when the tweet was posted (YYYY-MM-DD format).
  - `time`: The time when the tweet was posted (HH:MM:SS format).
  - `message`: The content of the tweet.
  - `stopWords`: A set of stop words loaded from a file.

- **Constructor:** Initializes a tweet with the provided user account, date, time, and message.

- **checkMessage():** Validates the message content of the tweet, ensuring it does not contain too few or too many words and does not consist only of stop words.

- **Getters:** Provides methods to retrieve the user account, date, time, and message of the tweet.

- **isBefore(Tweet t):** Compares the posting time of the current tweet with another tweet.

- **loadStopWords(String name):** Loads stop words from a file and initializes the `stopWords` set.

## Twitter

The **Twitter** class manages a collection of tweets, allowing users to load tweets from a file, sort them, retrieve specific tweets, and perform operations such as printing the database and finding trending topics. Here's a summary of its functionality:

- **Attributes:**
  - `tweets`: An ArrayList containing Tweet objects.

- **Constructor:** Initializes an empty ArrayList of tweets.

- **loadDB(String name):** Loads tweets from a file, checks their validity, and populates the `tweets` ArrayList.

- **sortTwitter():** Sorts the tweets based on their posting times.

- **getSizeTwitter():** Returns the number of tweets in the database.

- **getTweet(int index):** Retrieves a tweet from the database at the specified index.

- **printDB():** Prints all tweets in the database with each tweet's attributes separated by tabs.

- **rangeTweets(Tweet t1, Tweet t2):** Returns a sublist of tweets between two given tweets inclusively.

- **saveDB(String nameOfFile):** Writes the tweets in the database to a file.

- **trendingTopic():** Identifies the most frequently used word among all tweets, excluding stop words.

## Usage

1. **Compile:** Compile both Java files using a Java compiler. For example:
   ```bash
   javac Tweet.java
   javac Twitter.java
   ```

2. **Execute:** Run the main method of the Twitter class to load and process tweets. Ensure that the required input files are in the same directory as the compiled Java files. For example:
   ```bash
   java Twitter
   ```

3. **Follow Prompts:** Follow the prompts to interact with the program, such as providing file names and observing the output.

4. **Extend Functionality:** Feel free to extend the functionality by adding new methods or modifying existing ones based on your requirements. Make sure to follow good coding practices and maintain code readability.

That's it! You can now use the Tweet and Twitter classes to manage and analyze tweet data efficiently. Enjoy exploring and experimenting with the programs!
