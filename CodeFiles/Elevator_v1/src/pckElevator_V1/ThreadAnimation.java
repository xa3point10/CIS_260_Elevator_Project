package pckElevator_V1;

public class ThreadAnimation implements Runnable {
    //data
    private ControllerDemo controller;
    
    
    //constructors
    public ThreadAnimation( ControllerDemo controller ){
        this.controller = controller;
    }
    
    //operations
    @Override
    public void run(){
       //must be done on EDT thread
       controller.animate();
    }
    
}// class ThreadAnimation
