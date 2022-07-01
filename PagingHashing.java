import java.util.*;
import java.lang.*;
class PagingHashing
{
    static int exponentof2(int a)
    {
        int c=0;
        while(a!=1)
        {
            a=a/2;
            c=c+1;
        }
        return c ;
    }
    static String decimaltobinary(int a)
    {
    	String s="";
        String remainder="";
    	while(a>0)
    	{
    		remainder=String.valueOf(a%2);
    		s=remainder+s;
    		a=a/2;
    		
    	}
    	return s ;
    }
    static int binarytodecimal(String a)
    {
    int sum=0;
    int power=a.length()-1;
    int t;
    for(int i=0;i<a.length();i++)
    {
    t=a.charAt(i)-48;
    sum=(int) (sum+t*Math.pow(2, power));
    power--;
    }
    return sum;
    }
    static int[] linearprobe(int page[],int f)
    {
        int loc=f%4;
        if(page[loc]==-1)
        page[loc]=f;
        else
        {
            int flag=1,i=0;
            while(flag==1)
            {
                i=i+1;
                int j=(loc+i)%4;
                if(page[j]==-1)
                {
                   page[j]=f;
                   flag=0;
                }
           
            }
        }
        return page;
       
       
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of bits in logical address : ");
        int m=sc.nextInt();
        System.out.println("Enter the page size in bytes : ");
        int p=sc.nextInt();
        int pageoffset=exponentof2(p);
        System.out.println("Enter no of bits in physical address : ");
        int n=sc.nextInt();
        int laddrspace=(int)Math.pow(2,m);
        int paddrspace=(int)Math.pow(2,n);
        int pages=laddrspace/p;
        int page[]=new int[pages];
        for(int i=0;i<page.length;i++)
        page[i]=-1;
        table(page,pages);
        System.out.println("Enter the logical address in binary format : ");
        String l=sc.next();
        int laddr=binarytodecimal(l);
        int paddr=physical(laddr,page,pages,p);
        String res=decimaltobinary(paddr);
        System.out.println("Physical address:"+res);
    }
    static void table(int page[],int pages)
    {
        int f,i;
        Scanner sc = new Scanner(System.in);
        for(i=0;i<pages;i++)
        {
            System.out.print("Enter frame number for page - "+i+":");
            f=sc.nextInt();
            page=linearprobe(page,f);
            //System.out.println();
        }
        System.out.println("Pageno\tFrameno");
        for(i=0;i<pages;i++)
        {
            System.out.println(i+"\t"+page[i]);
        }
    }
    static int physical(int laddr,int page[],int pages,int p)
    {
        int pageno=laddr/p;
        int d=laddr%p;
        int paddrr=page[pageno]*p+d;
        return paddrr;
    }
}