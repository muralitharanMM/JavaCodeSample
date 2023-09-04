package com.murali.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * Factory has both produce and consume , and list is common for both.
 * There are 2 threads having instance of Factory, one is calling produce method of Factory, another is consume.
 */

class Factory {
	List<String> lop ;
	
	public Factory(List<String> lop) {
		super();
		this.lop = lop;
	}
	public void produce() throws InterruptedException {		
		while(true) {
			synchronized (this) {					
			if(lop.size()==15) {				
				System.out.println("********full product******");
				wait();
			}		
			int i = new Random().nextInt(15);
			System.out.println("adding product....product"+i);			
			lop.add("product"+i);							
			Thread.sleep(2000);			
			notify();		
			}
		}
	}
	public void consume() throws InterruptedException {		
		while(true) {
			synchronized (this) {			
			if(lop.size()==0) {
				System.out.println("********no product******");				
				wait();
			}						
				System.out.println("consuming product..."+lop.remove(0));						
				Thread.sleep(2000);
				System.out.println("SIZE "+lop.size());
				notify();							
		}
		}
	}
}
class Consumer implements Runnable {
	Factory f;
	
	public Consumer(Factory f) {
		super();
		this.f = f;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			f.consume();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
class Producer implements Runnable {
	Factory f;
	
	public Producer(Factory f) {
		super();
		this.f = f;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			f.produce();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}

public class ProdCons {
public static void main(String[] args) {
	List<String> lop = new ArrayList<>();
	Factory ff = new Factory(lop);
	Thread t1 = new Thread(new Producer(ff));
	Thread t2 = new Thread(new Consumer(ff));
	t1.start();
	t2.start();
}
}
