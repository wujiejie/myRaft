package raft.wujj.test;

public class TestThread extends Thread{
	
	
	public void run() {
		while(true) {
			
			try {
				System.out.println("111111");
				Thread.sleep(3000);
				System.out.println("222222");
			} catch (InterruptedException e) {
				System.err.println("error ");
			}
			
		}
	}

}
