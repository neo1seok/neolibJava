package com.neolib.Util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ManualResetEvent implements EventWaitHandle{
    private volatile CountDownLatch event;
    private final Integer mutex;
    
    public ManualResetEvent(boolean signalled) {
            mutex = new Integer(-1);
            if (signalled) {
                    event = new CountDownLatch(0);
            } else {
                    event = new CountDownLatch(1);
            }
    }
    
    public void set() {
            event.countDown();
    }
    
    public void reset() {
            synchronized (mutex) {
                    if (event.getCount() == 0) {
                            event = new CountDownLatch(1);
                    }
            }
    }
    
    public void waitOne() throws InterruptedException {
            event.await();
    }
    
    public boolean waitOne(int timeout, TimeUnit unit) throws InterruptedException {
            return event.await(timeout, unit);
    }
    
    public boolean isSignalled() {
            return event.getCount() == 0;
    }
}
//
//public class ManualResetEvent implements EventWaitHandle {
//	protected boolean m_isManual;
//	  private final Object monitor = new Object();
//	  private volatile boolean open = false;
//
//	  public ManualResetEvent(boolean open) {
//	    this.open = open;
//	    m_isManual = true;
//	  }
//
//	  public void waitOne() throws InterruptedException {
//	    synchronized (monitor) {
//	      while (open==false) {
//	          monitor.wait();
//	      }
//	      if(!m_isManual)  open = false;
//	    }
//	  }
//
//	  public void set() {//open start
//	    synchronized (monitor) {
//	      open = true;
//	      monitor.notifyAll();
//	    }
//	  }
//
//	  public void reset() {//close stop
//	    open = false;
//	  }
//	}