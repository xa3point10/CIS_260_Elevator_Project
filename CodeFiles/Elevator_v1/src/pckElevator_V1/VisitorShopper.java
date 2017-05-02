package pckElevator_V1;

public class VisitorShopper  /*implements*/ extends IVisitor {
    private static final int CALLING = 1;
    private static final int RIDING = 2;
    private static final int VISITING = 3;
    
    
    // ******still need These
    // unboard( IElements floor){}    // unboarding
    // Arrived( IElements Elevator){} // boarding
    
    
    @Override
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
        
}// class VisitorShopper
