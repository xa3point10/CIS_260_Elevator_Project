package pckElevator_V1;

import java.util.ArrayList;

public class ElevatorBank {
    // Use collection because dont know how many elevators
    private ArrayList< Elevator > elevatorCollection;

    //constructors
    public ElevatorBank(){
        elevatorCollection = new ArrayList< Elevator >();
    }
    //Operations 
    public void addElevator( Elevator elevator ){
        elevatorCollection.add( elevator );
    }
    public Elevator getElevator( int elevatorSeqNumber ){
        return elevatorCollection.get(elevatorSeqNumber);
    }// Elevator getElevator
    public ArrayList<Elevator> getElevatorCollection() {
        return elevatorCollection;
    }
}//class ElevatorBank
