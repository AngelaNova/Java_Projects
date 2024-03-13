//Angela Novakovic 260727217

//importing useful methods from other classes
import java.util.Scanner;
import java.util.Arrays;
public class TicTacToe {
  
  
  public static void main(String[] args) { 
    play();
  }
  
  
  //This method created a 2D array of character based on the interger given
  //the integer is taken as the length of the array and the subarrays to create a n*n 2D array
  public static char[][] createBoard(int n) {
  char[][] board= new char[n][n];
  //for loop is used to go through the 2D array and initialize its elements
  for (int i=0; i<n; i++) {
    for (int j=0; j<n; j++) {
      board[i][j]=' ';
    }
  }
  return board;
  }
  
  
  
  //Void method that takes a 2D array of characters and based on that print the board in which the game can take place
  public static void displayBoard(char[][] dimension){
   //variable for the length of the array
    int l = dimension.length;
    //until when i/j goes
    int until=2*l+1; 
    //for loops used to go through the array and change the character of some of the elements
    for (int i=0; i<until;i++){
      for(int j=0; j<until;j++){
        if (i%2==0 && j%2==0) {
         System.out.print('+'); 
        }
        if (i%2==1 && j%2==0) {
         System.out.print('|'); 
        }
        if (i%2==0 && j%2==1) {
         System.out.print('-'); 
        }
        //this if conditional statement, allows us to populate the board at the right position 
        //(where ' ' character are until the user or the AI decides to play there
        if (i%2==1 && j%2==1){
          int x=(i-1)/2;
          int y=(j-1)/2;
         System.out.print(dimension[x][y]);     
        }
        
      }
      System.out.println();
    }
  }
  
  
  
  /*Method takes input 2D array, a character, the number of the row and the column 
   * in which the character should be written and it prints, thus it is void
   */
  public static void writeOnBoard(char[][] array,char c,int row, int column){
    //acquiring x and y coordinates from row and column
    int x=row;
    int y=column;
    int until=array.length;
    //Using if statements, we verify whether the integer obtained can be used or if they lead to an error 
    if (row>until || column>until){
      throw new IllegalArgumentException("Please provide new coordinates; these coordinates are out of the board."); 
    }
  char[][] a=array;
  if (array[x][y]!=' '){
    throw new IllegalArgumentException("Please provide new coordinates; this space has already been used.");
  } 
  else array[x][y]=c; 
  }
  
  
  
  
  //Method that allows the user to play the game;it requires an input, but does not return anny value
  //It also uses Scanner
  public static void getUserMove(char[][] a){
    int until=a.length;
    int row=0;
    int column=0;
    Scanner coordinates= new Scanner(System.in);
    System.out.println("Please provide two integers to indicate where you would like to play.  ");
    System.out.println ("The first one should be for the row and the second one for the column!");
    //the program will keep executing the while loop until the user gives two integer
    while( ! coordinates.hasNextInt()){
      System.out.println("Please provide two integers. ");
        coordinates.next();
    }
    row=coordinates.nextInt();
    column=coordinates.nextInt();
    //This loop verifies that the integer that the user has provided make sense,
    //it basically saves the user from seeing a IllegalArgumentException, and allows him to input new values 
    while ((row<0)|| (column<0)||  (row>=until) ||  (column>=until || a[row][column]!=' ')){
          System.out.println ("Please provide different integers for the coordinates");
          row=coordinates.nextInt();
          column=coordinates.nextInt();
    }
    //calling this method allows the user to add an 'x' on the board
    writeOnBoard(a,'x',row,column);
    
  }
  
  
  
  
  
  //Check for obvious move method
  //This method takes as input the board (2D array of char) and return a boolean value
  //THE HARDEST METHOD
  public static boolean checkForObviousMove(char[][] board){
    boolean value=false;
    //If conditional statement are there to not allow the AI to play more than one time
    //When it enters a if block, the variable value will obtain a "true" value,
    //thus the program will skip all the other if statements
    //obvious move in a column through a method
    boolean value1=columnMove(board);
    value=value1;
    if (value==false){
    //obvious move in a row through a method
    boolean value2=rowMove(board);
    value=value2;
    }
    if (value==false){
    //obvious move in a diagonal(up-left to down-right)through a method
    boolean value3=diagonalMove(board);
    value=value3;
    }
    if (value==false){
    //obvious move for the second diagonal(down-left to up-right)through a method
    boolean value4=diagonalMove2(board);
    value=value4;
    }
   return value; }
  
  
  
  
  public static void getAIMove(char[][] board){
    char[][] copyBoard=board;
    //initializing i and j using Math.random()
    int i=(int)(Math.random()*copyBoard.length+1)-1;
    int j=(int)(Math.random()*copyBoard.length+1)-1;
    boolean obviousMove=false;
    //IT SHOULD ENTER THE LOOP IF BOARD[I][J]!=' ', THIS IS A WAY OF AVOIDING THE AI TO REACH A ILLEGAL_ARGUMENT_EXCEPTION
    while (copyBoard[i][j]!=' '){
    i=(int)(Math.random()*copyBoard.length+1)-1;
    j=(int)(Math.random()*copyBoard.length+1)-1;  
    }
    //check if there is an "obvious" move
    obviousMove=checkForObviousMove(copyBoard); 
    if (obviousMove==false){ 
      writeOnBoard(copyBoard,'o',i,j);
    }
  }
  
  
  //check whether either the user or the AI have won the game
  //method that takes 2D array as input and returns a char
  public static char getWinner(char[][] board){
    char c=' ';
    int count=0;
    int point=0;
    int i=0;
    int j=0;
    //check if there is a winner in a column
    for (i=0;i<board.length;i++){
      point=0;
      count=0;
      for (j=0; j<board.length; j++){
        if (board[i][j]=='o'){
          point=point+1;
        }
        if (board[i][j]=='x'){
         count=count+ 1;
        }
          if (point==board.length){
              c='o';
            }
          if (count==board.length){
             c='x'; 
            }
          }
      
    }
    //check if there is a winner in a row
  for (j=0;j<board.length;j++){
    point=0;
    count=0;
    for (i=0; i<board.length; i++){
        if (board[i][j]=='o'){
          point=point+1;
        }
        if (board[i][j]=='x'){
          count=count+ 1;
        }
        if (point==board.length){

          c='o';
        }
        if (count==board.length){
          c='x'; 
        }
    }
  }
    //Check if there is a winner in a diagonal (UpRight - DownLeft)
    
    point=0;
    count=0;
      for (j=0; j<board.length; j++){
        if (board[j][j]=='o'){
          point=point+1;
        }
        if (board[j][j]=='x'){
         count=count+ 1;
        }
          if (point==board.length){
              c='o';
            }
          if (count==board.length){
             c='x'; 
            }
          }
    
     //Check if there is a winner in a diagonal (DownRight - UpLeft)
      i=0;
      point=0;
      count=0;
      for (j=board.length-1; j>=0; j--,i++){
        if (board[i][j]=='o'){
          point=point+1;
        }
        if (board[i][j]=='x'){
         count=count+ 1;
        }
          if (point==board.length){
              c='o';
            }
          if (count==board.length){
             c='x'; 
            }
          }
    return c;
  }
   
  
  
  
  //method that takes no input and does not return any value
  //Uses Scanner
  public static void play(){
    String name=" ";
    int dimension=0;
    Scanner s=new Scanner(System.in);
    System.out.println("Welcome to TicTacToe! What is your name?");
    name = s.nextLine();
    System.out.println("Awesome " + name + "! Let's play!" + "\n In which dimension would you like to play? ");
    //The program will stay in the while loop until the the user provides an integer
    while( ! s.hasNextInt()){
      System.out.println("Please provide an integer, dear " + name);
        s.next();
    }
    dimension=s.nextInt();
    //if the integer that the user has provided is equal to zero, it will ask for a new integer
    while (dimension<1){
      System.out.println("Please provide an integer greater than 0 for your dimension");
     dimension=s.nextInt(); 
    }
    
    char[][] board=createBoard(dimension);
    
    //Flip the coin to determine whether the AI or the user should play first
    int coin;
    coin= ((int)(Math.random()*10+1))%2;
    System.out.println("The result of the coin toss is: " + coin);
    //Conditional statement that checks whether the coin is equal to zero or one.
    //If the coin is equal to zero, the user plays first
    //If the coin is equal to one, the AI plays first
    if (coin==0){
     System.out.println("You will play first, " + name); 
     getUserMove(board);
     displayBoard(board);
     System.out.println();
    }
    else {
      System.out.println("AI will start the game this time");
      getAIMove(board);
      System.out.println("AI has made its move:");
      displayBoard(board);
      System.out.println();
    }
    int numOfTurns=1; //can maximally be nxn
    
    //Checks who played first; if coin==0 then the user played first, thus the AI should play first in the loop
    //Otherwise the while loop will start with the user playing first and then the AI
    if (coin==0){
      //Verifies if the game can still be played
      while (getWinner(board)==' ' && numOfTurns<dimension*dimension){
       System.out.println("AI has made a move:");
       getAIMove(board);
       displayBoard(board);
       System.out.println();
       numOfTurns++;
       //Verifies if the game can still be played
       if (getWinner(board)!=' ' || numOfTurns==dimension*dimension){
         break;
       }
       System.out.println("It's your turn, " + name + ".");
       getUserMove(board);
       displayBoard(board);
       System.out.println();
       numOfTurns++;
      }
    }
    else {
     {
       //Verifies if the game can still be played
      while (getWinner(board)==' ' && numOfTurns<dimension*dimension){
       System.out.println("It's your turn, " + name + ".");
       getUserMove(board);
       displayBoard(board); 
       System.out.println();
       numOfTurns++;
       //Verifies if the game can still be played
       if (getWinner(board)!=' '|| numOfTurns==dimension*dimension ){
         break;
       }
       System.out.println("AI has made a move:");
       getAIMove(board);
       displayBoard(board);
       System.out.println();
       numOfTurns++;
      } 
    }
    
  }
    //Tell the user about the score. Whether the user won, lost or if it was a tie.
    if (getWinner(board)=='o'){
     System.out.println("GAME OVER! \n You lost."); 
    }
    else { if (getWinner(board)=='x'){
     System.out.println("Bravo! \n" + name+ ", you have won the game!");
    }
    else {
     System.out.println("Tie!"); 
    }
    }
  }
  
  
  
  
  
  
  
  
  
  //                                 HELPER METHODS!
 
    
  //helper method that checks whether the same character is 'o' or 'x'
  //Check whether AI should play to win (if possible) or play to block the user
  //Boolean method that changes the board through another method while also checking for the value 
  //of the variable "value" through another method.
  public static boolean shouldAIPlay(int point, int count, char[][] board, int i, int j, boolean value){
    if (point>=count){
      if (value==true){
        //playing to win
      //verifies if the row/column entered are valid coordinates to put a character in that position 
      writeOnBoard(board,'o',i,j);
      }
    }
    else{
      if (value==true){
        //playing to block the user from winning
      //verifies if the row/column entered are valid coordinates to put a character in that position
      writeOnBoard(board,'o',i,j); 
      }
  }
    return value;
  }
  
  
  
  
  //helper method that returns a boolean value and that checks whether a obvious move can be done in a column 
  public static boolean columnMove(char[][] board) {
    int count=0;
    int point=0;
    boolean value=false;
    for (int i=0; i<board.length; i++){
      count=0;
      point=0;
      for (int j=0; j<board.length-1; j++){
     //EXCEPTION WHEN ARRAY IS OF DIMENSION 2
          if (board[i][j]=='x'){
          count=count+1;
          }
          else {
            if (board[i][j]=='o'){
           point=point+1;
            }
          }
          if(count==board.length-1 || point==board.length-1){
            for (j=0; j<board.length; j++){
              if(board[i][j]==' ' ){
                value=shouldAIPlay(point,count,board,i,j,true);
              }
            }
          }
          if (value==true) {
           break; 
          }
      }
    }
  return value;}
  
  
  
  
  //helper method
  //obvious move in a row
  public static boolean rowMove(char[][] board){
    int count=0;
    int point=0;
    boolean value=false;
    for (int j=0; j<board.length; j++){
      point=0;
      count=0;
      for(int i=0; i<board.length; i++){
        if (board[i][j]=='x'){
          count=count+1;
        }
        else {
          if (board[i][j]=='o'){
           point=point+1;
            }
        }
        if(count==board.length-1 || point==board.length-1){
          for(i=0; i<board.length; i++){
            if(board[i][j]==' '){
              value=shouldAIPlay(point,count,board,i,j,true);
            }
          }
        }
        if (value==true){
         break; 
        }
    } 
    }
  return value;}
  
  
  
  
  //helper method
   //obvious move in a diagonal(up-left - down-right)
  public static boolean diagonalMove(char[][] board){
    int count=0;
    int point=0;
    boolean value=false;
      for(int j=0; j<board.length;j++){
        if (board[j][j]=='x'){
          count=count+1;
        }
        else if (board[j][j]=='o'){
            point=point+1;
          }
      }
    
    if(count==board.length-1 || point==board.length-1){
      for(int j=0; j<board.length;j++){
        if(board[j][j]== ' '){
          value=shouldAIPlay(point,count,board,j,j,true);
          System.out.println("value is " + value);
        }
      }  
    }
    return value;}
      
  
  
  
  
  //helper method to check for moves
  //obvious move for the second diagonal(down-left - up-right)
  public static boolean diagonalMove2(char[][] board){
    int count=0;
    int point=0;
    boolean value=false;
    for(int i=0; i<board.length;){
      for (int j=board.length-1;j>=0;j--,i++){
          if (board[i][j]=='x'){
            count=count+1;
          }
          else {
            if (board[i][j]=='o'){
           point=point+1;
            }
          }
          if (count==board.length-1 || point==board.length-1){
            i=0;
            for (j=board.length-1;j>=0;j--,i++){
         if(board[i][j]==' '){
          value=shouldAIPlay(point,count,board,i,j, true);
         }
         }
          }
        }
      
      }
    
    return value;   }
        

          
 
       
    
    
}
