package pckElevator_V1;

public interface IVisitor {
    
    public void visit( Floor floor );
    public void visit( Elevator elevator );
    
    public void configVisitorRoutine(IElement visitables);
    public void configVisitorRoutine();
    public void unboard (Floor floor);          // arrived at floor
    public void arrived (Elevator elevator);    // boarding elevator
    
}// interface IVisitor
