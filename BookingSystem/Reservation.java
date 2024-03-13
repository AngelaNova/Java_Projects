//Angela Novakovic 260727217


//Reservation is a immutable object as it has only get methods
public class Reservation {
  private String name;
  private Room roomReserved;
  
  //constructor
  public Reservation(Room room, String name ){
    this.name=name;
    this.roomReserved=room;
  }
  
  //get method
  public String getName(){
    return this.name;
  }
  
  //get method
  public Room getRoom(){
    return this.roomReserved; 
  }
  
  
  public static void main(String[] args) { 
    
  }
  
  
  
}
