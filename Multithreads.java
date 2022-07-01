class Ticketcounter extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		{
			System.out.println("Issued Ticket - "+i) ;
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				
			}
		}
		
		
	}
}
class Customer extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		{
			System.out.println("Customer "+i+" "+"Collected Ticket") ;
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
}
public class Multithreads
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated me.java
		Ticketcounter ob1 = new Ticketcounter();
		Customer ob2 = new Customer() ;
		ob1.start();
		ob2.start();

	}

}
