package pckElevator_V1;

public class ControllerDemo {

    //data
    private jFrameMainView window;      // need the window
    private ElevatorBank elevatorBank;  // need elevator bank

    //constructors
    public ControllerDemo() {
    }

    //Operators
    public void initUseCase() {
        // Business Objects to be initialized 
        elevatorBank = new ElevatorBank();
        elevatorBank.addElevator(new Elevator(Elevator.maxFloor));  //creates an Elevator
        elevatorBank.addElevator(new Elevator(Elevator.minFloor));  //creates another Elevator
    }

    //Getters and Setters
    public jFrameMainView getWindow() {
        return window;
    }

    public void setWindow(jFrameMainView window) {
        this.window = window;
    }

    public void startAnimation() {
        ThreadAnimation thp = new ThreadAnimation( this );
        Thread th = new Thread(thp);
        th.start();
    }

    public void animate() {
        // update business model
        elevatorBank.getElevator(0).move();
        elevatorBank.getElevator(1).move();
        //update the view
        displayElevators();

    }

    void displayElevators() {
        //add string: " " to concatinate with the integer
        StringBuilder displayGrid = new StringBuilder();
        // for each floor
        for (int floorNumber = Elevator.maxFloor; floorNumber >= Elevator.minFloor; --floorNumber) {
            //for each elevator
            for (Elevator elevator : elevatorBank.getElevatorCollection()) {
                if (floorNumber == elevator.getFloor()) {
                    displayGrid.append(" " + floorNumber + '\t');
                } else {
                    displayGrid.append("\t");
                }//else if
            }//for
            displayGrid.append("\n");
        }//for

        // ENFORCE: Must occur on the EDT!!
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // this must be done on evant dispatch thread 
                window.getAreaDisplayElevators().setText(displayGrid.toString());
            }//void run()
        });// java.awt.EventQueue

    }//displayElevators()

}//class ControllerDemo

