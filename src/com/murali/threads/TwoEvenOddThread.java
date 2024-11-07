package com.murali.threads;

class PrintEO {

	public synchronized void printEven() {
		int i = 1;
		while (i <= 10) {
			if (i % 2 == 0) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notifyAll();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		}
	}
	public synchronized void printOdd() {
		int i = 1;
		while (i <= 10) {
			if (i % 2 != 0) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notifyAll();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		}
	}

}
class Threads implements Runnable {

	PrintEO p = new PrintEO();
	String opr ;
	
	Threads(PrintEO p, String opr)	{
		this.p = p;
		this.opr = opr;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(opr.equals("even")) {
			p.printEven();
		}
		if(opr.equals("odd")) {
			p.printOdd();
		}
	}
	
}
public class TwoEvenOddThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintEO p = new PrintEO(); 
		new Thread(new Threads(p,"even")).start();
		new Thread(new Threads(p,"odd")).start();
	}

}
