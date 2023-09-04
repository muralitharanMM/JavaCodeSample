package com.murali.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Disp implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i=1;
		while(true) {
			System.out.println("print "+(i++));
			try {
				Thread.sleep(200);
				if(i==3) {
					i=i/0;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		}
	}
	
}
public class MultiThreadss {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ScheduledExecutorService ex = Executors.newScheduledThreadPool(1);
		ScheduledFuture<?> future =    ex.scheduleWithFixedDelay(new Disp(), 0, 1, TimeUnit.MINUTES);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		future.cancel(true);
		ex.shutdown();
		

	}

}
