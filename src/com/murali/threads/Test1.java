package com.murali.threads;


class Printt {	
	public void printieven() {
		synchronized (this) {				
			for(int i=0;i<10;i+=2) {
				System.out.println("thread am printing even "+i);
				try {
					Thread.sleep(500);					
				}catch(InterruptedException ie	) {
					ie.printStackTrace();
				}
			}
			}
	}
	public void printiodd() {
		synchronized (this) {				
			for(int i=1;i<10;i+=2) {
				System.out.println("thread am printing odd "+i);
				try {				
					Thread.sleep(500);
				}catch(InterruptedException ie	) {
					ie.printStackTrace();
				}
			}
			}
	}
}
class Even implements Runnable {
	Printt p ;
	
	public Even(Printt p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		p.printieven();
	}
	
}
class Odd implements Runnable {
	Printt p;	
	public Odd(Printt p) {
		super();
		this.p = p;
	}
	public void run() {
		p.printiodd();
	}
}
public class Test1 {
public static void main(String[] args) throws InterruptedException {
	Printt p = new Printt();
	
	//t1.join();
	Thread t2 = new Thread(new Odd(p));
	t2.start();
	Thread t1 = new Thread(new Even(p));
	t1.start();
}
}
