package com.neolib;

import java.text.MessageFormat;

public class OnInfo {
	 public static void main( String[] args )
	    {
			String osarch = System.getProperty("os.arch");
	    	String osname = System.getProperty("os.name");
	        System.out.println( MessageFormat.format("os.arch:{0}\nos.name:{1}", osarch,osname) );
	    }
}
