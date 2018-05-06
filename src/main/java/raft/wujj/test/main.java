package raft.wujj.test;

public class main {

	public static void main(String[] args) {
		
		TestThread t1 = new TestThread();
		t1.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+ "   interrupte ");
		t1.interrupt();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+ "   interrupte ");
		t1.interrupt();
		

	}

}
