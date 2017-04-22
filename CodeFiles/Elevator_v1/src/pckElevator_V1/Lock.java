package pckElevator_V1;

public class Lock {
    // data  
    private Mutex mutex;
       
    // operations
    public Lock ( Mutex mut) {
        // pull in the mutex
        mutex = mut;
        mutex.acquire();
    }// Lock
    
    public void release(){
        // placeholder code to release the mutex
        mutex.release();
        
    }// release
    
} // class lock
