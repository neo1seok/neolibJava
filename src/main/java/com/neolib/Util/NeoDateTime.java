/**
 * 
 */
package com.neolib.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 *
 */
public class NeoDateTime extends GregorianCalendar
{
	//String m_custr = "";
	public NeoDateTime(int year,int month1to12,int dayofmonth,int hour,int min,int sec){
		super(year, month1to12-1, dayofmonth, hour, min, sec);
		
		init();

	}
	public NeoDateTime(long datetime){
		//super(year, month1to12-1, dayofmonth, hour, min, sec);
		if(datetime >=0)
			this.setTimeInMillis(datetime);
		init();

	}
	public NeoDateTime(){
		init();

	}
	void init(){
		//m_custr = toString( );
		
	}
	public NeoDateTime getDate()
	{
		NeoDateTime date = new NeoDateTime();
		date.setTimeInMillis(this.getTimeInMillis());
		
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		
		
		return date;

	}
	
	public NeoDateTime addDays(int day)
	{
		NeoDateTime today = new NeoDateTime();
		
		today.setTimeInMillis(this.getTimeInMillis());
		today.add ( java.util.Calendar.DATE,day );
		return today;
	}
	public long getmilisec(){
		return this.getTimeInMillis();
	}
	public long getsec(){
		return this.getTimeInMillis()/1000;
	}
	
	
	public String toString( String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat ( format );
		return  sdf.format (this.getTime() );
	}
	public String toString( )
	{
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy/MM/dd HH:mm:ss" );
		return  sdf.format (this.getTime() );
	}
	
	public static long GetDatelong(long datetime){
		Calendar today = new GregorianCalendar();
		today.setTime(new Date(datetime));
		
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		return today.getTimeInMillis();
	}
	/**
	 * @param ��¥ ��
	 * @return 
	 * @author neo1seok
	 * @since 2014.01.17
	 */
	public static NeoDateTime getToday()
	{
		NeoDateTime today = new NeoDateTime();
		return today.getDate();
	}
	public static NeoDateTime getNow()
	{
		return new NeoDateTime();
	}
	public static long getTodaymsec()
	{
		return getToday().getTimeInMillis();
	}
	public static long getNowMsec()
	{
		return getNow().getTimeInMillis();
	}
	
	
}
