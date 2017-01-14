package com.neolib.Util;

import org.apache.log4j.Level;

public interface INLoger {
	
	void debugH(String message,Object ...args );
	void infoH(String message,Object ...args );
	void errorH(String message);
	
}
