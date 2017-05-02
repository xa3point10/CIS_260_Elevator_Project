package pckElevator_V1;

public abstract class /*interface*/ IVisitor {
    private static final int CALLING = 1;
    private static final int RIDING = 2;
    private static final int VISITING = 3;
    // Visitors can visit floors and Elevators

    public IVisitor (){}
    
    public int state (int state){
        return state;
    } 
    

    public void visit( Floor floor ) {
        System.out.println(
                "Visitor:"
                + this.toString()
                + " visiting Floor:"+floor.hashCode()
                );
    }//visit

    public void visit( Elevator elevator ) {
        System.out.println(
                "Visitor:"
                + this.toString()
                + " visiting Elevator:"
                + elevator.hashCode()
                );
    }//visit
        
    
}// interface IVisitor
