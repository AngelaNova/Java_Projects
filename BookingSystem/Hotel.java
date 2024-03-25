

import java.util.NoSuchElementException;

public class Hotel {
  private String name;
  private Room[] rooms;
  private Reservation[] reservations; 
  
  //constructor that creates an object of type Hotel with the String input for a name and an Room array
  public Hotel(String name, Room[] rooms){
    //initilizing the private attribute name
    this.name=name;
    //copy REFERENCES from room array to the array in class hotel
    //the address of Room[] that was given from the user and this.rooms Room[] are not stored at the same address
    Room[] privateRooms= new Room[rooms.length];
    for (int i=0; i<rooms.length; i++){
      privateRooms[i]=rooms[i];
    }
    //initializing the private attribute rooms with the copy of array that was given by the user
    this.rooms=privateRooms;
    
  }
  
  
  //This is method is a private method thus can only be used in this class, unless used through a public method
  //this method initializes the array of Reservation 
  //and adds more elements to the array by creating a new one each time it is called 
  private void addReservation(Reservation newReservation){
    Reservation[] newRes;
    //try/catch block to prevent an exception
    try{
      //could create an exception if the attribute "reservations" has not been initilized
      newRes= new Reservation[this.reservations.length + 1];
      //Copy the array reservations to the new array of reservation, "newRes" that can have one extra element
      for (int i=0; i<reservations.length; i++){
        newRes[i]=this.reservations[i];
      }
      //Add the new reservation
      newRes[this.reservations.length]=newReservation;
    }
    //this block of code occurs only the first time a reservation is added to the array "reservations" as it hasn't been initialised
    catch (NullPointerException e){
      newRes= new Reservation[1];
      newRes[0]=newReservation;
    }
    //makes the address of newRes to be the address of the attribute reservations
    this.reservations=newRes;
    
  }
  
  //private method, thus can only be used directly in this class
  //it removes a reservation from the attribute array reservations
  //it takes the name of the user and the type of the room that was reserved in order to cancel the reservation
  private void removeReservation(String name, String type){
    boolean value=false;
    int i=0;
    //check if the name corresponds to one of the people who have made a reservation
    //if yes, it also checks whether that person has reserved a room of the type that was inputted by the user
    //if yes, the reservation exists and can then be removed
    for (i=0; i<this.reservations.length;i++){
      if ((reservations[i].getName()).equals(name)){
        Room roomToRemove= reservations[i].getRoom();
        if ((roomToRemove.getType()).equals(type)){
          roomToRemove.changeAvailability();
          value=true;
          break;
        }
      }
    }
    //throws an error if the reservation does not exist
    if (value!=true){
      throw new NoSuchElementException("This reservation does not exist.");
    }
    
    //remove the reservation if it exits
    Reservation[] updatedReservations = new Reservation[reservations.length-1];
    //copying the attribute Reservation array until j=i, 
    //the reservation at index i is skipped, "thus erased"
    //The new array will contain all the reservation except for the one at index i
    for (int j=0; j<updatedReservations.length;j++){
      if(j<i){
        updatedReservations[j]=reservations[j];
      }
      else {
        updatedReservations[j]=reservations[j+1]; 
      }
    }
    
    //updating the array
    this.reservations=updatedReservations;
    
  }
  
  //public method that used addReservation to create reservation in the array reservations
  public void createReservation(String name, String type){
    //storing a Room in the variable room1
    Room room1=Room.findAvailableRoom(this.rooms, type);
    if (room1==null){
      System.out.println("The hotel has no available rooms of the requested type."); 
    }
    else {
      //the room requested is available, thus can be reserved
      Reservation r1= new Reservation(room1,name);  
      room1.changeAvailability(); //the room is now not available
      addReservation(r1); //adding an element in the array reservations
      System.out.println("The reservation has been successfully completed." );
    }  
  }
  
  
  //public method to cancel reservation,thus to remove reservation from the array
  public void cancelReservation(String name, String type){
    boolean reservationExist=true;
    //Try/catch block to not obtain an exception
    try{
      removeReservation(name, type); //if possible
    }
    catch(NoSuchElementException e) {
      //the reservation does not exist or the user has made a mistake
      System.out.println("No reservation under such name has been made for the given type of room.");
      reservationExist=false;
    }
    if (reservationExist==true){
      System.out.println("The reservation has been canceled.");
    }
  }
  
  
  //takes as input the name and print how much that person owes to the hotel
  public void printInvoice(String name){
    double price=0.0;
    for (int i=0; i<this.reservations.length;i++){
      if ((this.reservations[i].getName()).equals(name)){
        //acquiring a single reservation through reservation[i]
        //then acquiring a single room with reservation[i].getRoom()
        //finally acquiring the price of the room through reservations[i].getRoom().getPrice()
        price = price + reservations[i].getRoom().getPrice();
      }
    }
    System.out.println("The total is: " +  price);
  }
  
  //allows he user to see the elements of arrays, or simple to see objects by simply using print/println
  public String toString(){
    String print="";
    //counts how many AVAILABLE!! type(d,q,k) of rooms the hotel has
    int d=0; //double
    int q=0; //queen
    int k=0; //king
    for (int i=0; i<rooms.length;i++){
      if(rooms[i].getAvailability()==true){
        if (rooms[i].getType()=="double"){
          d++;
        }
        else
        {if (rooms[i].getType()=="queen"){
          q++;
        }
        else {
          k++;
        }
        }
      }
      
    }
    //prints information about the hotel
    print= "Hotel: " + this.name + "\n Available rooms of type double: " + d + "\n Available rooms of type queen: " + q + "\n Available rooms of type king: " + k; 
    return print ;
  }
  
  public  void main(String[] args) { 
    
  }
  
  
  
}
