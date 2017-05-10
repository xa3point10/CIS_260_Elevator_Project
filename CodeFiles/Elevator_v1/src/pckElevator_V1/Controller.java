package pckElevator_V1;

import java.util.ArrayList;

public class Controller {

    //data
    private WindowMainApp window = null;      // need the window
    private boolean isSimulationRunning = false;
    private IScenario myScenario;
    private ArrayList< Integer > floorCalls;


    //constructors
    public Controller() {
    }

    // Set the Custom Scenario and if is used, called bellow
    public void customScenario(int numberOfFloors, int numberOfElevators, int numberOfVisitors) {
        this.floorCalls = new ArrayList<>();
        // tell myScenario it will be ScenarioCustom()!
        myScenario = new ScenarioCustom();
        myScenario.setNumberOfElevators(numberOfElevators);
        myScenario.setNumberOfFloors(numberOfFloors);
        myScenario.setNumberOfVisitors(numberOfVisitors);
        System.out.println("DEBUG: CONTROLLER customScenario() ");
    }//saveScenario   
    
    public void setScenario(int scenarioOption) {
        switch (scenarioOption) {
            case 0: 
                //customScenario already told myScenario it is a ScenarioCustom!
                updateElevatorBankConfig();
                myScenario.populateVisitorsArray();
                break;
            case 1:
                myScenario = new ScenarioOne();   //Needs to be Created
                updateElevatorBankConfig();
                myScenario.populateVisitorsArray();
                break;
            case 2:
                myScenario = new ScenarioTwo();   //Needs to be Created
                updateElevatorBankConfig();
                myScenario.populateVisitorsArray();
                
                break;
            case 3:
                myScenario = new ScenarioThree(); //Needs to be Created
                updateElevatorBankConfig();
                myScenario.populateVisitorsArray();
                break;
        }
    }
    
    public void updateElevatorBankConfig() {
    ElevatorBank.GetInstance().updateConfiguration(
                        myScenario.getNumberOfFloors(),
                        myScenario.getNumberOfElevators() );
    // update the Scenario IElements array
    }

    public IScenario getScenario() {
        return myScenario;
    }//getScenario

    public void animate() {
        if (isSimulationRunning == false) {return;}
        // update business model
        ElevatorBank.GetInstance().animate();
        myScenario.wakeUpScenario();
        updateWindow();         
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
