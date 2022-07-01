import java.util.*;
public class bankeralgo
{
	public static void main(String[] args) 
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the total number of Resources: ");
		int resno = sc.nextInt();  //Number of resources
		System.out.println("Enter number of processes: ");
		int proc=sc.nextInt();
		int res[] = new int[resno];
		int alloc[][] = new int[proc][resno];
		int max[][] = new int[proc][resno];
		int avail[] = new int[resno];
		int need[][] = new int[proc][resno];
		int sequence[] = new int[proc];
		for(int i = 0; i < resno; i++)
		{
		      System.out.println("Enter total number of instances for Resource"+ (i+1) +":");
		      res[i] = sc.nextInt();
		      avail[i] = res[i];
		}
	
	    System.out.print("Enter the maximum matrix : " );
		for(int i = 0; i<proc; i++)
		{
		     
		      for(int j = 0; j < resno; j++)
		      {
		           max[i][j] = sc.nextInt() ;
		      }
		}
	    System.out.print("Enter the allocation matrix : " );
        for(int i=0;i<proc;i++)
		{
		      
		      for(int j = 0; j < resno; j++)
		      {
		         alloc[i][j] = sc.nextInt();
		         avail[j] = avail[j] - alloc[i][j];
	          }
	     }
	     for(int i = 0; i < proc; i++) //need loop
	     {
	         for(int j = 0; j < resno; j++)
	         {
	             need[i][j] = max[i][j] - alloc[i][j];
	         }
	     }

	     boolean safe = check_state(alloc,need,avail,resno,proc,sequence);
	     System.out.println();

	     if(safe)
	     {
	        System.out.println("The system is in a Safe State");
	        System.out.print("The Safe Sequence is: ");
	        for(int i = 0; i < proc; i++)
	        System.out.print("P" + (sequence[i] + 1) + "");
	        System.out.println();
	     }
	     else
	     System.out.println("The system is not in a Safe State");

	     if(safe)
	     {
	 	     System.out.println();
		     System.out.println("Please enter the number of the Process that is requesting more resources: ");
		     int reqno = sc.nextInt();
		     int req[] = new int[resno];
		     System.out.println("Please enter the Request Matrix: ");
		     for(int i=0;i<resno;i++)
		    	 req[i]=sc.nextInt();
		     int needcount = 0, avlcount = 0;
		     for(int i = 0; i < resno; i++)
		     {
		         //req[i] = Integer.parseInt(String.valueOf(ip.charAt(i)));
		         if(req[i] <= need[reqno][i])
		         needcount++;
		         if(req[i] <= avail[i])
		         avlcount++;
		     }
		     if(needcount != resno)
		         System.out.println("The request cannot be granted since requested resources are more thanpreviously declared Maximum");
		     if(avlcount != resno)
		         System.out.println("The request cannot be granted since the amount of resources requestedare not available");
		     if(needcount == resno && avlcount == resno)
		     {
		          for(int i = 0; i < resno; i++)
                  {
		              alloc[reqno][i] += req[i];
		              need[reqno][i] -= req[i];
		              avail[i] -= req[i];
		          }
		          safe = check_state(alloc, need,avail, resno, proc,sequence);
		          System.out.println();
                  if(safe)
		          {
		               System.out.println("The system will be in a Safe State if the request is granted");
		               System.out.print("The Safe Sequence is: ");
		               for(int i = 0; i < proc; i++)
		               System.out.print("P" + (sequence[i] + 1) + "");
		               System.out.println();
		          }
		          else
		               System.out.println("The system will not be in a Safe State if the request is granted");

		    }
		}

     }

		static boolean check_state(int alloc[][], int need[][], int avail[], int resno, int proc,int sequence[])
        {
		     boolean finished[]= new boolean[proc];
		     int pos = 0;
		     boolean safe = true;
		     int work[] = new int[resno];
		     for(int i = 0; i < resno; i++)
		     work[i] = avail[i];
		     while(pos < proc && safe)
		     {
		         for(int i = 0; i < proc; i++)
		         {
		            int c = 0;
		            for(int j = 0; j < resno; j++)
		            {
		               if(need[i][j] <= work[j])
		               c++;
		            }
		            if((c == resno) && (finished[i] == false))
		            {
		               for(int j = 0; j < resno; j++)
		               {
		                 work[j] += alloc[i][j];
		               }
		               finished[i] = true;
		               sequence[pos] = i;
                       pos++;
		               break;
		            }
		            if(i == proc - 1 && c < resno)
		            {
		               safe = false;
		            }
		        }
		     }
		return safe;
	   }

}
