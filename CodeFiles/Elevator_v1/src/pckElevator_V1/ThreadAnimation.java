package pckElevator_V1;

public class ThreadAnimation implements Runnable {
    //------------------------------
    // data
    //------------------------------
    private static final int ANIMATION_TIMEOUT = 500; //one second
    private ControllerDemo controller;

    
    //------------------------------
    // constructors
    //------------------------------
    public ThreadAnimation(ControllerDemo controller) {
        this.controller = controller;
    }// ThreadAnimation

    
    //------------------------------
    // operations
    //------------------------------
    @Override
    public void run()
    {
        for (;;) {
            try {
                Thread.sleep( ANIMATION_TIMEOUT );
            } catch ( InterruptedException ex ) {
                System.out.println( ex.getMessage() );
                ex.printStackTrace();
                return;
            }
            //System.out.println( "Thread woke up!" );
            controller.animate();
        }
    }//run    }// run()

}// class ThreadAnimation
