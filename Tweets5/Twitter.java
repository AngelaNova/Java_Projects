import java.util.ArrayList;
import java.io.*;
import java.util.HashSet;
import java.util.HashMap;

public class Twitter {
  private ArrayList<Tweet> tweets; 
  
  public Twitter(){
    ArrayList<Tweet> tweets= new ArrayList<Tweet>();
    
  }
  
  //check what kind of method it should be
  public void loadDB(String name){
    ArrayList<Tweet> tweetsToBeInitiallized = new ArrayList<Tweet>();
    try{
      FileReader fr = new FileReader(name);
      BufferedReader br = new BufferedReader(fr);
      String userAccount="";
      String date="";
      String time="";
      String message="";
      String currentLine =br.readLine();
      while(currentLine != null) {
        String line= currentLine;
        String[] information=line.split("\t");
        userAccount=information[0];
        date=information[1];
        time=information[2];
        message=information[3];
        Tweet t= new Tweet(userAccount, date, time, message);
        boolean value= t.checkmessage();
        if (value==true){
          tweetsToBeInitiallized.add(t);
        }
        currentLine = br.readLine();
      }
      br.close();
      fr.close();
    }
    catch (IOException e){
      System.out.println("The file does not contain anything"); 
    }
    this.tweets= tweetsToBeInitiallized; 
    sortTwitter();
  }
  
  
  //sorts the attribute array of Tweets 
  public void sortTwitter(){
    int count=0;
    ArrayList<Tweet> orderedTweets = new ArrayList<Tweet>() ;
    Tweet temp=this.tweets.get(0);
    //while loop used to be sure that the array has been sorted till the last element
    while (count < tweets.size()){
      //sorts the unsorted part of the array
      //by finding the earliest tweet that has been posted from the unsorted part of the array
      for (int i=count+1; i<tweets.size();i++){
        if (! (temp.isBefore(tweets.get(i)))){ 
          temp=tweets.get(i); //min tweet
        }
      }
      if (!orderedTweets.contains(temp)){
        orderedTweets.add(temp);
        int index = tweets.indexOf(temp);
        //keeping Tweet at index del in memory while the temp Tweet and the Tweet at index del will be swapped
        Tweet swap =tweets.get(count); 
        tweets.set(count,temp); //this tweet is now in order
        tweets.set(index,swap); // this tweet has still to be compared with the others to obtain a position 
        count++; 
        //this conditional statement is true only if the array has been sorted,
        //and thus count-1 elements have been sorted
        if (count==(tweets.size())){ 
          count++;
        }
        else {
          temp=tweets.get(count);
        }
      }
      
    }
    this.tweets=orderedTweets;
  }
  
  //get method
  public int getSizeTwitter(){
    int size = this.tweets.size();
    return size;
  }
  
  //set method
  public Tweet getTweet(int index){
    Tweet t= this.tweets.get(index);
    return t;
  }
  
  //prints the tweets each in one row, the attributes separated by tabs
  public String printDB(){
    String  s= "";
    for (int i=0; i< this.tweets.size(); i++){
      Tweet t=this.tweets.get(i);
      s= s + t.toString() + "\n" ;
      
    }
    return s;
  }
  
  
  //return an ArrayList that is between the two inputs
  public ArrayList<Tweet> rangeTweets(Tweet t1, Tweet t2){
    ArrayList<Tweet> newTweets = new ArrayList<Tweet>();
    int index1= this.tweets.indexOf(t1);
    int index2=this.tweets.indexOf(t2);
    if (index1>index2){
      //swapping of indexes to start adding tweets from the tweets that has been posted before the other
      int temp= index1;
      index1=index2;
      index2=temp;
    }
    for (int i=index1; i<index2+1; i++){
      newTweets.add(tweets.get(i));
    }
    return newTweets;
  }  
  
  
  //method that allows us to write/create a file in which the tweets will be saved
  public void saveDB(String nameOfFile){
    try{
      FileWriter fw = new FileWriter( nameOfFile );
      BufferedWriter bw = new BufferedWriter(fw);
      String message = printDB();
      bw.write(message);
      bw.close();
      fw.close();
    }
    catch(IOException e){
      System.out.println("Please try giving a new name to your file."); 
    }
  }
  
  
  //method that find which word has been used the most
  public String trendingTopic(){
    String word="";
    
    HashMap<String, Integer> numberOfWords= new HashMap<String,Integer>();
    //first loop allows us to access one tweet at a time
    for (int i=0; i<this.tweets.size(); i++){
      String message= this.tweets.get(i).getMessage();
      //splitting the message to get each word
      String[] words = message.split(" ");
      //second loop allows us to use one word at a time
      for (int j=0; j<words.length; j++){
        boolean value=false;        
        //for each loop allows us to loop through a HashSet and check whether a word from a tweet is a stop word or not
        if(!isStopWord(words[j])) {
          value = true;
        }
        if (value==true){
          
        //make a word to not have . , : ;
          String newWord= "" ;
          char lastLetter= words[j].charAt(words[j].length()-1);
          if (lastLetter=='.' || lastLetter==',' || lastLetter==':' || lastLetter==';'){
            for (int k=0; k<words[j].length()-1;k++){
              char letter= words[j].charAt(k);
              newWord= newWord + letter;
              
            }
            words[j]=newWord;//modifies the word such that "cats;" becomes "cats" -> new string is created 
          }
          
          
          
          //Checks whether the key has already been created and if it hasn't, it created a new key
          boolean wordExistsAsKey =numberOfWords.containsKey(words[j]);
          if (wordExistsAsKey==false){
            numberOfWords.put(words[j], 1); 
          }
          else {
            //updates the value associated with the key by verifying what value is already associated to that key 
            //the value is then updated by adding 1 to the previous value
            //this way the key with the greatest value is the one that appears the most often
            int count= numberOfWords.get(words[j]);
            count++;
            numberOfWords.put(words[j],count);
          }
        }
      }
      //find which word is used the most often by using a for each loop
      int max=0;
      for (String key : numberOfWords.keySet()){
        int valueOfKey = numberOfWords.get(key);
        if (valueOfKey > max){
          max=valueOfKey; 
          word=key;
        }
      }
    }
    
    return word;
  }
  

  //helper method
  private static boolean isStopWord(String s) {
  
        for (String stopWord: Tweet.stopWords){
          
          if(s.equalsIgnoreCase(stopWord)) {
            return true;
          } else if(s.equalsIgnoreCase(stopWord + "." )){
            return true;
          } else if (s.equalsIgnoreCase(stopWord + ",")) {
            return true;
          } else if(s.equalsIgnoreCase(stopWord + ":")) {
            return true;
          } else if(s.equalsIgnoreCase(stopWord + ";")) {
            return true;
          }
        }
  return false;
  
}
  
  
  
  public static void main(String[] args) { 
    Twitter example = new Twitter();
    Tweet.loadStopWords("stopWords.txt");
    example.loadDB("tweets.txt");
    System.out.println(example.trendingTopic());
  }
  
  
}
