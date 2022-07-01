import java.util.Scanner;
public class DeadlockDetection
{
	public static void main(String[] args) 
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the total number of Resources: ");
		int resno = sc.nextInt();
		int res[] = new int[resno];
		int avail[] = new int[resno];
		for(int i = 0; i < resno; i++)
		{
		      System.out.println("Enter total number of instances for Resource"+ (i+1) +":");
		      res[i] = sc.nextInt();
		      avail[i] = res[i];
		}
		System.out.println("Enter number of processes: ");
		int proc=sc.nextInt();
        int sequence[] = new int[proc];
		int request[][] = new int[proc][resno];
		int alloc[][] = new int[proc][resno];
	    System.out.print("Enter the request matrix : " );
		for(int i = 0; i<proc; i++)
		{
		      for(int j = 0; j < resno; j++)
		      {
		           request[i][j] = sc.nextInt() ;
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
	     boolean safe = check_state(request, alloc,avail,resno,proc,sequence);
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
		     System.out.println("Please enter the process number and additional resources it is requesting : ");
		     int p = sc.nextInt();
		     int r = sc.nextInt();
		     request[p][r]++;
	          safe = check_state(request, alloc,avail, resno, proc,sequence);
	          System.out.println();
              if(safe)
              {
	               System.out.println("deadlock does'nt exist : ");
	               System.out.print("The Safe Sequence is: ");
	               for(int i = 0; i < proc; i++)
		               System.out.print("P" + (sequence[i] + 1) + "");
	               System.out.println();  
              }
              else
	               System.out.println("deadlock exists");

		}

     }

		static boolean check_state(int request[][], int alloc[][], int avail[], int resno, int proc,int sequence[])
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
		               if(request[i][j] <= work[j])
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
