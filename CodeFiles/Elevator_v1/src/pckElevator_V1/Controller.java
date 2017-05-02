package pckElevator_V1;

import java.util.ArrayList;

public class Controller {
    //data
    private WindowMainApp window = null;      // need the window
    private boolean isSimulationRunning = false;
    private IScenario myScenario;
    //private ElevatorBank elevatorBank;  // need elevator bank

    //constructors
    public Controller() {}
    
    // This is use when calling Pre-set Scenario class
    public void setScenario(int scenarioOption){
        switch(scenarioOption) {
            case 0: myScenario = new ScenarioOne();
            ElevatorBank.GetInstance().updateConfiguration(
                myScenario.getNumberOfFloors(), 
                myScenario.getNumberOfElevators(),
                myScenario.getNumberOfVisitors()
            );
                break;
//            case 1: myScenario = new ScenarioTwo();   //Needs to be Created
//                break;
//            case 2: myScenario = new ScenarioThree(); //Needs to be Created
//                break;
//            case 3: myScenario = new ScenarioFour();  //Needs to be Created
//                break;
        }   
    }
    
    // This is use when calling custom Scenario class
     //public void saveScenario(int numberOfFloors, int numberOfElevators, int numbOfVisitors){
    public void customScenario(int numberOfFloors, int numberOfElevators, int numberOfVisitors ) {
        // update the CUSTOM scenario object with current user data
        myScenario = new ScenarioCustom(); 
        myScenario.setNumberOfElevators(numberOfElevators);
        myScenario.setNumberOfFloors(numberOfFloors);
        myScenario.setNumberOfVisitors(numberOfVisitors);

        // Config Elevator Bank with floor and elevators:
        ElevatorBank.GetInstance().updateConfiguration(
               myScenario.getNumberOfFloors(), 
               myScenario.getNumberOfElevators(),
               myScenario.getNumberOfVisitors() 
        );
    }//saveScenario   

    public IScenario getScenario() {
        return myScenario;
    }//getScenario

    public void animate() {
        if (isSimulationRunning == false) {
            return;
        }
        // update business model
        // I Think this is where the Scenario class should be loaded
        ArrayList<Elevator> elevators = ElevatorBank.GetInstance().getElevators();
        for (Elevator elevator : elevators) {
            elevator.move();
        }
        //update the view
        updateWindow();         // new 

    }// animate

    public void startAnimation() {
        isSimulationRunning = true;
    }//startAnimation
        
    void updateWindow() {
        // Make sure that te actual update happens on
        // the EDT thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                window.update();
            }
        });
    }// updateWindow

    public void runSimulation() {
        isSimulationRunning = true;
    }//runSimulation                                                

    public void stopSimulation() {
        isSimulationRunning = false;
    }//stopSimulation

    //Getters and Setters
    public WindowMainApp getWindow() {
        return window;
    }

    public void setWindow(WindowMainApp window) {
        this.window = window;
    }

}//class ControllerDemo

/**
* public void initUseCase() {
*         //not used anymore... could be deleted
*    }
*/
