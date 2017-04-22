package pckElevator_V1;

import java.util.ArrayList;

public class ControllerDemo {

    //data
    private WindowMainApp window = null;      // need the window
    private boolean isSimulationRunning = false;
    private Scenario scenario = new Scenario();
    //private ElevatorBank elevatorBank;  // need elevator bank

    //constructors
    public ControllerDemo() {
    }

    //Operators
    public void initUseCase() {
        // construct business objects as neccessary
        // Business Objects to be initialized 
//        elevatorBank = new ElevatorBank();
//        elevatorBank.addElevator(new Elevator(Elevator.maxFloor));  //creates an Elevator
//        elevatorBank.addElevator(new Elevator(Elevator.minFloor));  //creates another Elevator
//        elevatorBank.addElevator(new Elevator(Elevator.maxFloor));
    }

    public Scenario getScenario() {
        return scenario;
    }//getScenario

    //Getters and Setters
    public WindowMainApp getWindow() {
        return window;
    }

    public void setWindow(WindowMainApp window) {
        this.window = window;
    }

    public void startAnimation() {
//        ThreadAnimation thp = new ThreadAnimation( this );
//        Thread th = new Thread(thp);
//        th.start();
        isSimulationRunning = true;
    }//startAnimation

    public void animate() {
        if (isSimulationRunning == false) {
            return;
        }
        // update business model
        ArrayList<Elevator> elevators = ElevatorBank.GetInstance().getElevators();
        for (Elevator elevator : elevators) {
            elevator.move();
        }
//        elevatorBank.getElevator(0).move(); // old left side
//        elevatorBank.getElevator(1).move(); // old left side
//        elevatorBank.getElevator(2).move(); // old left side

        //update the view
        updateWindow();         // new 

    }// animate

    void updateWindow() {
        // Make sure that te actual update happens on
        // the EDT thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                window.update();
            }
        });

    }// updateWindow

    public void saveScenario(int numberOfFloors, int numberOfElevators) {
        // update the scenario object with current user data
        scenario.setNumberOfElevators(numberOfElevators);
        scenario.setNumberOfFloors(numberOfFloors);

        // tell the elevator bank to configure
        // floor and elevators:
        ElevatorBank.GetInstance().updateConfiguration(
                numberOfFloors, numberOfElevators
        );
    }//saveScenario   

    public void runSimulation() {
        isSimulationRunning = true;
    }//runSimulation                                                

    public void stopSimulation() {
        isSimulationRunning = false;
    }//stopSimulation

    

}//class ControllerDemo

