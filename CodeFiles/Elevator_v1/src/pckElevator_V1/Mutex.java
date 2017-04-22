package pckElevator_V1;

public class Mutex {
    //------------------------------
    // data
    //------------------------------
    static private java.util.concurrent.locks.Lock mLock
            = new java.util.concurrent.locks.ReentrantLock();
    
    //------------------------------
    // operations
    //------------------------------
    public void acquire() {
        mLock.lock();
    }//acquire
    
    public void release() {
        mLock.unlock();
    }//release

    
}// class Mutex
