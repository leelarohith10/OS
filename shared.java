import java.util.concurrent.Semaphore;
public class shared {
   static Semaphore readlock=new Semaphore(1);
   static Semaphore writelock=new Semaphore(1);
   static int readcount=0;
   static class Read implements Runnable
   {
	   public void run()
	   {
		 try
		 {
			 readlock.acquire();
			 readcount++;
			 if(readcount==1)
				 writelock.acquire();
			 readlock.release();
			 System.out.println(Thread.currentThread().getName()+"is Reading");
			 Thread.sleep(1500);
			 System.out.println(Thread.currentThread().getName()+"has finished Reading");
			 readlock.acquire();
			 readcount--;
			 if(readcount==0)
				 writelock.release();
			 readlock.release();
		 }
		 catch(InterruptedException e)
		 {
			 System.out.println(e.getMessage());
		 }
	   }
   }
   static class Write implements Runnable
   {
	   public void run()
	   {
		   try
		   {
			   writelock.acquire();
			   System.out.println(Thread.currentThread().getName()+"is writing");
			   Thread.sleep(2500);
			   System.out.println(Thread.currentThread().getName()+"has finished writing");
			   writelock.release();
		   }
		   catch(InterruptedException e)
		   {
			   System.out.println(e.getMessage());
		   }
	   }
   }
   public static void main(String[] args)
   {
	   Read r=new Read();
	   Write w=new Write();
	   Thread t1=new Thread(r);
	   t1.setName("Reader 1");
	   Thread t2=new Thread(r);
	   t2.setName("Reader 2");
	   Thread t3=new Thread(w);
	   t3.setName("Writer 3");
	   Thread t4=new Thread(w);
	   t4.setName("Writer 4");
	   t1.start();
	   t2.start();
	   t3.start();
	   t4.start();
	   
   }
}
