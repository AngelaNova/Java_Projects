

import java.util.Arrays;

public class Room {
  private final String type;
  private final double price; //must be initialized in the constructor
  private boolean availability;
  
  //constructor
  public Room(String room){
    this.type=room;
    if (room=="double"){
      this.price=90; 
    }
    else {
      if (room=="queen"){
        this.price=110; 
      }
      else {
        if (room=="king"){
          this.price=115;
        }
        else{
          throw new IllegalArgumentException("This type of room is not available in our hotel. \n Please choose between one of the following types: \n double \n queen \n king");   
        }
      }
    }
    this.availability=true; 
  }
  
  
  
  public static void main(String[] args) { 
    
  }
  
  //get method
  public String getType(){
    String roomType=this.type;
    return roomType;
  }
  
  //get method
  public double getPrice(){
    double price=this.price;
    return price;
  }
  
  //non-static method that checks whether a specific room is available
  public boolean getAvailability() {
    boolean available=this.availability;
    return available;
  }
  
  //non-static method that changes the availability of a specific room
  public  void changeAvailability(){
    if (this.availability==true){
      this.availability=false ;
    }
    else {
      this.availability=true; 
    }
  }
  
  // returns the first available (TRUE) room of the indicated TYPE if available
  //static method as it takes inputs, and must check all the rooms to find the first avaible room in the Room array
  public static Room findAvailableRoom(Room[] rooms, String type){
    //default value if no room is available
    Room availableRoom=null;
    //copy array
    Room[] copyRooms= new Room[rooms.length];
    for (int i=0; i<copyRooms.length;i++){
      copyRooms[i]=rooms[i];
    }
    
    for (int i=0; i<copyRooms.length;i++){
      //Check if the type is the same and if the room is available
      if ( copyRooms[i].type.equals(type)){
        if ( copyRooms[i].availability==true){
          //stores the Room object in the variable
          availableRoom= copyRooms[i];
          break; //escape the loop
        }
      }
    }
    //returns a Room object if a free room of the requested type was found
    return availableRoom;
  }
  
  
  
  
  
}
