package com.murali.threads;
class Printerr {
	public void print(String whois) throws InterruptedException {				
		for(int i=1;i<=5;i++) {
			System.out.println(whois+"printing...");
			Thread.sleep(1000);
		}		
	}
}
class Scannerr {
	public void scan(String whois) throws InterruptedException {							
		for(int i=1;i<=5;i++) {
			System.out.println(whois+"scanning...");
			Thread.sleep(1000);
		}		
	}
}
class User1 implements Runnable {
	Printerr pp;
	Scannerr ss;
	
	public User1(Printerr pp, Scannerr ss) {
		super();
		this.pp = pp;
		this.ss = ss;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//while(true) {
				synchronized (ss) {								
			ss.scan("user1 ");
				
			Thread.sleep(1000);
			synchronized (pp) {				
			pp.print("user1 ");
			}
				}
			//}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}
class User2 implements Runnable {
	Printerr pp;
	Scannerr ss;
	
	public User2(Printerr pp, Scannerr ss) {
		super();
		this.pp = pp;
		this.ss = ss;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//while(true) {
				
				synchronized (ss) {				
					ss.scan("user2 ");
					
				Thread.sleep(1000);
				synchronized (pp) {								
					pp.print("user2 ");
					}}
				//}			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}
public class DeadLock {
public static void main(String[] args) {
	Printerr pp = new Printerr();
	Scannerr ss = new Scannerr();
	Thread t1 = new Thread(new User1(pp,ss));
	Thread t2 = new Thread(new User2(pp,ss));
	t2.start();t1.start();
}
}
