package com.murali.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Disp implements Runnable{

	@Override
	public void run() {
		System.out.println("my task is , i will print 5 times INDIA in 1 sec delay "
				+ "then i will take 20 seonds REST :-) ");
		for(int i=1;i<=5;i++) {
			System.out.println("INDIA");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("waiting to complete 20seconds");
		int second = 20;
		while(true) {
			System.out.println("please wait "+second+" seconds...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			second--;
			if(second<=0) {
				break;
			}
		}
	}
	
}
public class MultiThreadss {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ScheduledExecutorService ex = Executors.newScheduledThreadPool(1);
		//ScheduledFuture<?> future =    ex.scheduleWithFixedDelay(new Disp(), 0, 1, TimeUnit.MINUTES);
		//ex.scheduleWithFixedDelay(new Disp(), 0, 20, TimeUnit.SECONDS);  //should call Disp every 20 seconds
		ex.scheduleAtFixedRate(new Disp(), 0, 20, TimeUnit.SECONDS); //should call Disp every 20 seconds
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//future.cancel(true);
		//ex.shutdown();
		

	}

}
