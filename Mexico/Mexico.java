
public class Mexico {
  
  
  public static void main(String[] args) {
    //args take String as input, thus we must convert to doubles 
     double buyIn=Double.parseDouble(args[0]);
     double bet=Double.parseDouble(args[1]);
     //Calling a method in the main method
     playMexico(buyIn,bet);   
  }
  
  
  
  //Method for obtaining a number on the dice
  //Method does not take input, but returns an int
  public static int diceRoll() {
    /*multiplying by 6 as Math.random() goes from 0(included) to 1(excluded); 
     * also adding 1 as the result on a dice can never be 0, but can sometimes be 6
     */
    int numberOnDice= (int)( 6*Math.random() + 1);
    return numberOnDice;
  }
  
  
  
  //Method to calculate the score obtained by rolling two dice
  //Method takes two int inputs and returns an int
  public static int getScore( int a, int b) {
    //Conditional statement where we verify which int is bigger to be able to calculate the score
    if(a < b){
      int score=( a + 10*b);
      return score;
    }
    else {
      //If a=b then it's not important whether a. or be is used to be in the ten-column
      int score=( a*10 +b);
      return score;}  
  }
  
  
  
  //Method to tell the digits and the score obtained by the player
  //Method takes a string and returns an int
  public static int playOneRound(String player) {
    //initiallization of variables where 
    int firstDigitObtained=diceRoll();
    int secondDigitObtained=diceRoll();
    int theScore=getScore(firstDigitObtained,secondDigitObtained);
    //The print statement do not store any value, but they show/print what is between the brakets
    //Only theScore is store as a value, and can be obtained by calling this method
    System.out.print(player + " rolled: ");
    System.out.println(firstDigitObtained+ " " +secondDigitObtained);
    System.out.print( player + "'s score is: ");
    System.out.println(theScore);
    return theScore;
  } 
  
  
  
  //Method to determine who is the winner by following the rules in Mexico
  //Method takes two int inputs and returns a string
  public static String getWinner(int g, int d){ //where g and d are the scores obtained by Giulia and David
    //Conditional statement
    //Checking whether Giulia and David have the same scores
    if (g==d) {
      return "tie";
    }
    //If they don't have same scores then if one of them has the number 21, he/she is the winner
    if (g==21) {
      return "Giulia";
    }
    if (d==21) {
      return "David";
    }
    
    //If they don't have 21 as score, we verify the second highest score; same digit number (ex.22,33,66...)
    /*To do that, we can simply divide the number by 10 to get the digit in the ten-column and then we do
     * modulo of 10 to get the digit in the first position. If they are equal, we know that the number is 
     *a same digit number (double number)*/
    if ( d/10==d%10) { 
      int digitDavid=d/10;
      //We must verify if Giulia has also an double number and compare it to David's digit to see who is the winner
      if (g/10==g%10) {
        int digitGiulia=g/10;
        if (digitDavid > digitGiulia) {
          return "David";
        }
        else
          return "Giulia";
      }
    return "David";
    }
    if (g/10 == g%10) {
     return "Giulia"; 
    }
    
    /*Finally, if the scores Giulia and David have are not equal, not 21 and not an double, 
    *we verify which number is the largest */
    if ( g < d) {
      return "David";
    }
    else 
      return "Giulia"; 
    }
 
  
  
  /*This method check if the players can play at all and when they can by comparing 
  *the buy in and the money that they must give when they lose
  *Method that takes two doubles and returns a boolean value*/
  public static boolean canPlay( double buyIn, double bet){
    
    //check if the third statement must be in int or should stay in double!!!!!!!!!
    
    //Using while loop because they play UNTIL they can't anymore
    while (( bet <= buyIn) && (bet>=0) && ((buyIn)%(bet)==0.0)){
      buyIn=buyIn-bet;
      return true;}
    return false;
  }
  
  
  
  //Method simulates the game Mexico
  //Method takes two doubles as inputs and do not return anything
  public static void playMexico(double buyIn, double bet) {
    //declaring two variables, a buy in for both of the players as it will be different as the game continues.
    double moneyGiulia=buyIn;
    double moneyDavid=buyIn;
    /*declaring variable n; it will be used to give the number of the round, 
     * as it can differ from game to game since it depends on the bet and the buy in
    */
    int n=1;
    //While loop again, the game can be played until one of the players has no money left
    //Call the method canPlay in the condition of the while loop
    while ((canPlay(moneyGiulia,bet)==true) && (canPlay(moneyDavid,bet)==true)){
     System.out.println("Round" + n + "\n");
     n=n+1;
     //declaring variables that will be used to check who had the highest score
      int giulia=playOneRound("Giulia");
      int david=playOneRound("David");
      //Conditional statement
      if (giulia < david) {
        moneyGiulia=moneyGiulia-bet;
        System.out.println("David is the winner of this round \n");}
      else 
      { moneyDavid=moneyDavid-bet;
        System.out.println("Giulia is the winner of this round \n");}
    }//end of while loop
    //conditional statement that gets print if and only if while loop cannot be entered
    if ((moneyDavid<bet) && (moneyGiulia<bet)) {
    System.out.println("The fund is insufficient in order to play the game");
    }
    else {
      if (moneyDavid<bet) {
        System.out.println("Giulia won!");}
      else System.out.println("David won!");}
   
    
      }
  
      
  }

  
  
  
  
    
    
    
    
    
