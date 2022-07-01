import java.util.Scanner;
import java.util.ArrayList;
public class MemoryManagement 
{

	public static void main(String[] args)
	{
		int i,j,k,x;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no of processes : ");
		int n=sc.nextInt();
		System.out.println("Enter"+n+" of processes : ");
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(i=0;i<n;i++)
		{
			x=sc.nextInt();
			p.add(x);
		}
		System.out.println("Enter no of blocks : ");
		int m=sc.nextInt();
		System.out.println("Enter"+m+" of blocks : ");
		ArrayList<Integer> b = new ArrayList<Integer>();
		for(i=0;i<m;i++)
		{
			x=sc.nextInt();
			b.add(x);
		}
		System.out.println("Enter your option : 1.FirstFit 2.BestFit 3.WorstFit");
		int op=sc.nextInt();
		System.out.println("Process no\t"+"Process size\t"+"Block no");
		if(op==1)
		{
			boolean allocate=false;
			for(i=0;i<n;i++)
			{
				for(j=0;j<m;j++)
				{
					if(p.get(i)<b.get(j) && b.get(j)!=-1)
					{
						System.out.println((i+1)+"\t"+p.get(i)+"\t"+(j+1));
						allocate=true;
						b.set(j,-1);
						break;
					}
				}
				if(allocate==false)
				{
					System.out.println((i+1)+"\t"+p.get(i)+"\t"+"Not allocated");
				}
				allocate=false;
			}
		}
		if(op==2)
		{
			boolean allocate=false;
			for(i=0;i<n;i++)
			{
				int min=9999;
				for(j=0;j<m;j++)
				{
					if(p.get(i)<b.get(j) && b.get(j)!=-1 && b.get(j)<min)
					{
							min=b.get(j);
							allocate=true;
							
					}
				}
				if(allocate==true)
				{
				System.out.println((i+1)+"\t"+p.get(i)+"\t"+(b.indexOf(min)+1));
				b.set(b.indexOf(min),-1);
				}
				if(min==9999)
				{
					System.out.println((i+1)+"\t"+p.get(i)+"\t"+"Not allocated");
				}
				allocate=false;
			}
		}
		if(op==3)
		{
			boolean allocate=false;
			for(i=0;i<n;i++)
			{ 
				int max=-9999;
				for(j=0;j<m;j++)
				{
					if(p.get(i)<b.get(j) && b.get(j)!=-1 && b.get(j)>max)
					{
							max=b.get(j);
							allocate=true;
							
					}
				}
				if(allocate==true)
				{
				System.out.println((i+1)+"\t"+p.get(i)+"\t"+(b.indexOf(max)+1));
				b.set(b.indexOf(max),-1);
				}
				if(max==-9999)
				{
					System.out.println((i+1)+"\t"+p.get(i)+"\t"+"Not allocated");
				}
				allocate=false;
			}
		}
		
		
		

	}

}
