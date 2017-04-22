package pckElevator_V1;

public class Scenario {
    // data
    private int numberOfFloors = 0;
    private int numberOfElevators = 0;

    // operations
    public int getNumberOfFloors()
    {
        return numberOfFloors;
    }//getNumberOfFloors

    public void setNumberOfFloors( int numberOfFloors )
    {
        Elevator.maxFloor = numberOfFloors;
        this.numberOfFloors = numberOfFloors;
    }//setNumberOfFloors

    public int getNumberOfElevators()
    {
        return numberOfElevators;
    }//getNumberOfElevators

    public void setNumberOfElevators( int numberOfElevators ) {
        this.numberOfElevators = numberOfElevators;
    }//setNumberOfElevators
    
} // class Scenario 
