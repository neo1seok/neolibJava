package com.neolib.Util;





import java.text.MessageFormat;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class NLoger implements INLoger {
	public static Logger loger = Logger.getLogger(NLoger.class); // 로거 선언. 맨 뒤에 클래스 이름은 해당 클래스 이름 적으면 됨.
	
	private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NLoger.class.getName());
	public static boolean isSystemConsole = false;
	String hostName ="";
	//Logger logger;
	public NLoger(String hostName) {
		this.hostName =hostName;

		// TODO Auto-generated constructor stub
	}
	public static void debug(String message,Object ...args ){
		log(Level.DEBUG,message,args );
	}
	public static void clog(String message,Object ...args ){
		if(message == null) {
			logger.log(java.util.logging.Level.INFO,"NULL");
			return ;
		}
		
		String msg = MessageFormat.format(message, args);
		if(isSystemConsole) {
			System.out.println(msg);
			return ;
		}
		logger.log(java.util.logging.Level.INFO,msg);
		
		//System.out.println();
	}
	
	public static void info(String message,Object ...args ){
		log(Level.INFO,message,args );
	}
	
	public static void error(String message){
		log(Level.ERROR,message );
	}
	public static void debugD(Object message){
		logD(Level.DEBUG,message );
	}
	public static void infoD(Object message){
		logD(Level.INFO,message );
	}
	
	public static void errorD(Object message){
		logD(Level.ERROR,message );
	}
	
	public static void log(Level leve,String message,Object ...args) {
		loger.log(NLoger.class.getCanonicalName(), leve, MessageFormat.format(message, args), null);
	}
	
	public static void logD(Level leve,Object message) {
		loger.log(NLoger.class.getCanonicalName(), leve, message, null);
	}
	public static void log(Level leve,String hostName,String message,Object ...args) {
		if(hostName == null || hostName.isEmpty()){
			log(leve,message,args);
			return;	
		}
		
		loger.log(NLoger.class.getCanonicalName(), leve,"["+ hostName+"] "+MessageFormat.format(message, args), null);
	}

	public static void logNotWorking(String message) {
		loger.info(message);
		//loger.fatal(message)
		
	} 

	public void test(){
		//Throwable 
		Throwable t = new Throwable();
		StackTraceElement[] fadafa = t.getStackTrace();
		StackTraceElement[] nstack = new StackTraceElement[fadafa.length-1] ;
		
		
		for(int i = 1; i <  fadafa.length ; i++ ){
			StackTraceElement  tmp = fadafa[i];
			nstack[i-1] = tmp;
			
		}
		t.setStackTrace(nstack);
		Throwable t1 =  t.getCause();
		
	//	StackTraceElement[] fadafa2 = t1.getStackTrace();
		
		//this.logger.debug("test");
	}
//	public static void fatal(RuntimeException ex) {
//		// TODO Auto-generated method stub
//		
//	}
	public void debugH(String message, Object... args) {
		// TODO Auto-generated method stub
		log(Level.DEBUG,hostName,message,args );
	}
	public void infoH(String message, Object... args) {
		// TODO Auto-generated method stub
		log(Level.INFO,hostName,message,args );
	}
	public void errorH(String message) {
		// TODO Auto-generated method stub
		log(Level.ERROR,hostName,message );
	}
	

}
