class Arithmetic extends Thread
{
	double num1,num2 ;
	Arithmetic(double a,double b )
	{
		num1=a;
		num2=b ;
	}
	public void run()
	{
		System.out.println("Sum of 2 numbers : "+(num1+num2)) ;
		System.out.println("Difference of 2 numbers : "+(num1-num2)) ;
		System.out.println("Multiplication of 2 numbers : "+num1*num2) ;
		System.out.println("Division of 2 numbers : "+(num1/num2)) ;



		
		
	}
}
public class Singlethread 
{
	public static void main(String args[])
	{
		Arithmetic ob=new Arithmetic(2.7,3.6);
		ob.start();
	}
	

}
