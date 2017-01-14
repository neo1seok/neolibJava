package com.neolib.Util;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;


import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("restriction")
public class PerformCheck {
    //RuntimeMXBean runbean = ManagementFactory.getRuntimeMXBean( );
    long bfprocesstime ;//= osbean.getProcessCpuTime( );
    
    long bfuptime;// = runbean.getUptime( );
    long prevUpTime = 0;//runtimeMXBean.getUptime();
    long prevProcessCpuTime = 0;//operatingSystemMXBean.getProcessCpuTime();

    public void func(){
    	double cpuUsage = getCpu(null,null);
    	System.out.println("Java CPU: " + cpuUsage);
    	NeoUtil.Sleep(500);
    	
    }
    public double  getCpu(RefParam<Float> rfusage , RefParam<Float> rfcal )
    {
    	
    	OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
        double cpuUsage;
      

        operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long upTime = runtimeMXBean.getUptime();
        long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
        long elapsedCpu = processCpuTime - prevProcessCpuTime;
        if(prevUpTime ==0) {
        	 prevUpTime = upTime;//runtimeMXBean.getUptime();
             prevProcessCpuTime = processCpuTime;//operatingSystemMXBean.getProcessCpuTime();
        	return 0f;
        }
        long elapsedTime = upTime - prevUpTime;
        prevUpTime = upTime;//runtimeMXBean.getUptime();
        prevProcessCpuTime = processCpuTime;//operatingSystemMXBean.getProcessCpuTime();
        
        
      

        cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
        //System.out.println("Java CPU: " + cpuUsage);
        return cpuUsage;
    	
    }
    public float  getCpuOld(RefParam<Float> rfusage , RefParam<Float> rfcal )
    {
    	//System.out.println( "*showCPU" );
    	
        OperatingSystemMXBean osbean = ( OperatingSystemMXBean ) ManagementFactory.getOperatingSystemMXBean( );
        
        
        
        RuntimeMXBean runbean = ManagementFactory.getRuntimeMXBean( );
//        long bfprocesstime = osbean.getProcessCpuTime( );
//        
//        long bfuptime = runbean.getUptime( );
        
        long ncpus = osbean.getAvailableProcessors( );
        
//        for ( int i = 0 ; i < 1000000 ; ++i )
//        {
//            ncpus = osbean.getAvailableProcessors( );
//        }
        
        long afprocesstime = osbean.getProcessCpuTime( );
        
        long afuptime = runbean.getUptime( );
        
        float cal = ( afprocesstime - bfprocesstime ) / ( ( afuptime - bfuptime ) * 10000f );
        NeoUtil.Sleep(500);
        
        bfprocesstime = afprocesstime;
        bfuptime = afuptime;

        
        float usage = Math.min( 99f , cal );
//        System.out.println( "Calculation: " + cal /ncpus);
//        System.out.println( "CPU Usage: " + usage/ncpus );
//        System.out.println( "" );
        if(rfusage != null) rfusage.value = usage;
        if(rfcal != null) rfcal.value = cal;
        
        return usage;
    }
    public void showRuntime( )
    {
    	System.out.println( "*showRuntime" );
        RuntimeMXBean runbean = ManagementFactory.getRuntimeMXBean( );
        System.out.println( "" );
    }
    public void GetUsageMemory(){
    	MemoryMXBean membean = ManagementFactory.getMemoryMXBean( );
    	MemoryUsage heap = membean.getHeapMemoryUsage( );
    	MemoryUsage nonheap = membean.getNonHeapMemoryUsage( );
    	
    	
    }

    /*
     * 메모리 사용량
     */
    public void showMemory( )
    {
    	System.out.println( "*showMemory" );
        MemoryMXBean membean = ManagementFactory.getMemoryMXBean( );
        MemoryUsage heap = membean.getHeapMemoryUsage( );
        System.out.println( "Heap Memory: " + heap.toString( ) );
        MemoryUsage nonheap = membean.getNonHeapMemoryUsage( );
        System.out.println( "NonHeap Memory: " + nonheap.toString( ) );
        System.out.println( "" );
    }

    public void showClassLoading( )
    {
    	System.out.println( "*showClassLoading" );
        ClassLoadingMXBean classbean = ManagementFactory.getClassLoadingMXBean( );
        System.out.println( "TotalLoadedClassCount: " + classbean.getTotalLoadedClassCount( ) );
        System.out.println( "LoadedClassCount: " + classbean.getLoadedClassCount( ) );
        System.out.println( "UnloadedClassCount: " + classbean.getUnloadedClassCount( ) );
        System.out.println( "" );
    }

    public void showThreadBean( )
    {
    	System.out.println( "*showThreadBean" );
        ThreadMXBean tbean = ManagementFactory.getThreadMXBean( );
        long[] ids = tbean.getAllThreadIds( );
        System.out.println( "Thread Count: " + tbean.getThreadCount( ) );
        for ( long id : ids )
        {
            System.out.println( "Thread CPU Time(" + id + ")" + tbean.getThreadCpuTime( id ) );
            System.out.println( "Thread User Time(" + id + ")" + tbean.getThreadCpuTime( id ) );
        }
        System.out.println( "" );
    }

    /**
     * OS 정보
     */
    public void showOSBean( )
    {
    	System.out.println( "*showOSBean" );
        OperatingSystemMXBean osbean = ( OperatingSystemMXBean ) ManagementFactory.getOperatingSystemMXBean( );
        System.out.println( "OS Name: " + osbean.getName( ) );
        System.out.println( "OS Arch: " + osbean.getArch( ) );
        System.out.println( "Available Processors: " + osbean.getAvailableProcessors( ) );
        System.out.println( "TotalPhysicalMemorySize: " + osbean.getTotalPhysicalMemorySize( ) );
        System.out.println( "FreePhysicalMemorySize: " + osbean.getFreePhysicalMemorySize( ) );
        System.out.println( "TotalSwapSpaceSize: " + osbean.getTotalSwapSpaceSize( ) );
        System.out.println( "FreeSwapSpaceSize: " + osbean.getFreeSwapSpaceSize( ) );
        System.out.println( "CommittedVirtualMemorySize: " + osbean.getCommittedVirtualMemorySize( ) );
        System.out.println( "SystemLoadAverage: " + osbean.getSystemLoadAverage( ) );
        System.out.println( "" );
    }
}