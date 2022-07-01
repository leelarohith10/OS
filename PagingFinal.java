import java.util.*;
import java.lang.*;
class PagingFinal
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
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no of bits in logical address : ");
        int m=sc.nextInt();
        System.out.print("Enter the page size in bytes : ");
        int p=sc.nextInt();
        System.out.print("Enter no of bits in physical address : ");
        int n=sc.nextInt();
        int laddrspace=(int)Math.pow(2,m); 
        
        int paddrspace=(int)Math.pow(2,n);
        int pageoffset=exponentof2(p);
        int pages=laddrspace/p;
        int page[]=new int[pages];
        table(page,pages);
        System.out.print("Enter the logical address in binary format : ");
        String l=sc.next();
        String offsetbits=l.substring(l.length()-pageoffset);
        String frameno=physical(l,page,pages,pageoffset);
        String physicaladdress=frameno+offsetbits;
        System.out.println("Physical address: "+physicaladdress);
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
    static void table(int page[],int pages)
    {
        int f,i;
        Scanner sc = new Scanner(System.in);
        for(i=0;i<pages;i++)
        {
            System.out.print("Enter frame number for page - "+i+":");
            f=sc.nextInt();
            page=linearprobe(page,f);
            System.out.println();
        }
        for(i=0;i<pages;i++)
        {
            System.out.println(i+"\t"+page[i]);
        }
    }
    static String physical(String l,int page[],int pages,int pageoffset)
    {
    	String pageno=l.substring(0,l.length()-pageoffset);
    	int decimal_pageno=binarytodecimal(pageno);
    	int frameno=page[decimal_pageno];
    	String binary_frameno=decimaltobinary(frameno);
    	return binary_frameno;
    	
    }
}