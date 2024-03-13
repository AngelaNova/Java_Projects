// Angela Novakovic 260727217

import java.io.* ;
import java.util.HashSet;

public class Tweet{ 
  private String userAccount;
  private String date;
  private String time;
  private String message;
  public static HashSet<String> stopWords; 
  
  //String dateOfPost has the following format YYYY-MM-DD
  //String timeOfPost has the following format HH:MM:SS
  public Tweet(String userAccount,String dateOfPost, String timeOfPost , String message){
    HashSet<String> stopWords= new HashSet<String>(); 
    this.userAccount=userAccount;
    this.date=dateOfPost;
    this.time=timeOfPost;
    this.message=message;
  }
  
  //check if the message is valid
  public boolean checkmessage(){
    boolean value=false;
    HashSet<String> stopWords=this.stopWords;
    if (stopWords==null){
      throw new NullPointerException("HashSet (stopWords) has not been initiallized!"); 
    }
    String[] message = this.message.split(" ");
   // System.out.println(Arrays.toString(message));  //*********************************************
    int count=0;
    for (int i=0; i<message.length;i++){
      count++;
      //check if there are any stop words in the message, if yes, do not count as a word
      for ( String s : stopWords ){
        if (message[i].equalsIgnoreCase(s)){
          count--;
        }
        else {
          if((message[i]).equalsIgnoreCase((s + ".") )){
            count--;
          } 
          else {
            if((message[i]).equalsIgnoreCase((s + ","))){
              count--;
            }
            else{
              if((message[i]).equalsIgnoreCase((s + ":"))){
                count--;
              }
              else {
                if((message[i]).equalsIgnoreCase((s + ";"))){
                  count--;
                }
              } 
            }     
          }
        }
      }
    }
    
    if (count>0 && count<16){
      value=true;
    } 
    return value;
  }
  
  
  //get method
  public String getDate(){  
    return this.date; 
  }
  
  
  
  
  
  //get method
  public String getTime(){
    return this.time; 
  }
  
  
  //get method
  public String getMessage(){
    return this.message; 
  }
  
  
  //get method
  public String getUserAccount(){
    return this.userAccount; 
  }
  
  
  //toString method
  public String toString(){
    String print= this.userAccount + "\t" + this.date + "\t" + this.time + "\t" + this.message;
    return print;
  }
  
  
  //method that check if the attribute Tweet has been posted before the Tweet that was received as input
  public boolean isBefore(Tweet t){
    boolean value=false;
    //returns true when this.tweet was posted before the one given in input
    //splitting 
    String[] date = this.date.split("-"); //where date[0]=year, date[1]=month, date[2]=days
    int year= Integer.parseInt(date[0]);
    int month= Integer.parseInt(date[1]);
    int day= Integer.parseInt(date[2]);
    //splitting
    String[] dateInput=t.date.split("-");
    int yearInput= Integer.parseInt(dateInput[0]);
    int monthInput= Integer.parseInt(dateInput[1]);
    int dayInput= Integer.parseInt(dateInput[2]);
    
    //nested conditions to verify which Tweet has been posted before the other
    if (year<yearInput){
      value=true;
    }
    else{
      if (year==yearInput){
        if (month<monthInput){
          value=true;
        }
        else { 
          if (month==monthInput){
            if(day<dayInput){
              value=true;
            }
            else {
              if(day==dayInput){
                String[] time = this.time.split(":"); //where time[0]=hour time[]=minute time[]=second
                int h= Integer.parseInt(time[0]);
                int m= Integer.parseInt(time[1]);
                int s= Integer.parseInt(time[2]);
                String[] timeInput=t.time.split(":");
                int hInput= Integer.parseInt(timeInput[0]);
                int mInput= Integer.parseInt(timeInput[1]);
                int sInput= Integer.parseInt(timeInput[2]);
                
                if (h<hInput){
                  value=true; 
                }
                else {
                  if (h==hInput){
                    if (m<mInput){
                      value=true; 
                    }
                    else {
                      if(m==mInput){
                        if (s<sInput){
                          value=true; 
                        }
                        //no need to check if s==sInput 
                        //'cuz it would mean that they were posted at the same time,
                        //thus the method should return false
                      }
                    }
                  }
                } 
              }
            }
          }
        }
      }
    }
    
    return value;
  }
  
  
  //initializes the stop words
  public static void loadStopWords(String name){
    HashSet<String> stopWordsSet= new HashSet<String>() ;
    try{
    FileReader fr = new FileReader(name);
    BufferedReader br = new BufferedReader(fr);
    String currentLine =br.readLine();
    
    while(currentLine != null) {
      stopWordsSet.add(currentLine);
      currentLine = br.readLine();
    }
    br.close();
    fr.close();
    }
    catch (IOException e){
     System.out.println("The file does not contain anything"); 
    }
    stopWords = stopWordsSet;
  }
  
  
  public static void main(String[] args) { 
    
  }
  

  
}
