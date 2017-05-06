package pckElevator_V1;

public class VisitorShopper  implements /*extends*/ IVisitor {
    private static final int CALLING = 1;
    private static final int RIDING = 2;
    private static final int VISITING = 3;
    
    // internal floor index
    private int floor = 0; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing
    private String myFloorLabel;   // from ElevatorBank updateConfiguration()
    
    // ******still need These
    // unboard( IElements floor){}    // unboarding
    // Arrived( IElements Elevator){} // boarding
    
    
    public int state (int state){
        return state;
    } 
    
    @Override
    public void visit( Floor floor ) {
        System.out.println(
                "Visitor:"
                + this.toString()
                + " visiting Floor:"+floor.hashCode()
                );
    }//visit
    
    @Override
    public void visit( Elevator elevator ) {
        System.out.println(
                "Visitor:"
                + this.toString()
                + " visiting Elevator:"
                + elevator.hashCode()
                );
    }//visit

    public String getMyFloorLabel() {
        return myFloorLabel;
    }
        
}// class VisitorShopper
