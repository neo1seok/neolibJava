package com.neolib.Util;

public interface EventWaitHandle {
	void set();
	void reset();
	void waitOne()throws InterruptedException;

}
