
import java.util.* ;
class job
{
	String jobid="";
	int arrival;
	int burst;
	int remaining ;
	int completion=-1 ;
	int turn=-1;
	int wait=-1;
}
class gantt
{
	String gid="";
	int stime;
	int etime ;
	int arrived;
}
class RR
{
	
	
	void displaygantt(job S[],int q,int n)
	{
		int start,end,i,j ;
		boolean completed= false ;
		LinkedList<job> ready = new LinkedList<job>();    /*Linked list for ready queue */
		LinkedList<gantt> gan = new LinkedList<gantt>();  /*Linked list to display gantt chart*/
		LinkedList<job> complete = new LinkedList<job>() ; /*Linked list to hold all the completed jobs with their completion times */
		LinkedHashMap<String,Integer> response = new LinkedHashMap<String,Integer>() ; /*HashMap to calculate response time from the gantt chart*/
		ready.add(S[0]);
		start=ready.get(0).arrival;
		while(ready.size()>=1)
		{
			if(ready.get(0).remaining<=q)
			{ 
				end=start+ready.get(0).remaining;
				completed=true ;
				ready.get(0).completion=end ;
				complete.add(ready.get(0)) ;
				
			}
			else
			{
				end=start+q;	
				completed=false;
				ready.get(0).remaining=ready.get(0).remaining-q;
				
			}
			for(j =1;j<n;j++)
			{
				if(S[j].arrival>start && S[j].arrival<=end)
				{
					ready.addLast(S[j]) ;	
				}	
			}
			gantt g = new gantt();
			g.gid=ready.get(0).jobid ;
			g.stime=start;
			g.etime=end ;
			g.arrived=ready.get(0).arrival ;
			gan.add(g) ;
			start=end;
			if(completed==true)
				ready.remove(0);
			else
			{
				job s = ready.get(0) ;
				ready.remove(0) ;
				ready.add(s) ;
			}	
		}
		System.out.print("Gantt Chart : ") ;
		for(gantt p : gan)
		{
			System.out.print(p.gid+"("+p.stime+"-"+p.etime+")"+" ");
			if(!response.containsKey(p.gid))
			{
				response.put(p.gid,p.stime-p.arrived) ;	
			}

		}
		System.out.println();
		float avg=0;
		for(Map.Entry m : response.entrySet())
		{
			System.out.println("Response Time of"+m.getKey()+":"+m.getValue());
			avg=avg+response.get(m.getKey()) ;
		}
		System.out.println("Average response time : "+avg/n) ;
		System.out.println();
		compute(complete,n,S) ;
		
	}

	void compute(LinkedList<job> complete,int n,job S[]) 
	{
		float avgturn=0 ;
		float avgwait=0 ;
		System.out.println("JOB ID"+"   "+"AT"+"   "+"BT"+"   "+"CT"+"    "+"TT"+"   "+"WT") ;
		for(job t : complete)
		{
			t.turn=t.completion-t.arrival ;
			t.wait=t.turn-t.burst;	
	   System.out.println(t.jobid+"   "+t.arrival+"   "+t.burst+"   "+t.completion+"   "+t.turn+"   "+t.wait);
	        avgturn=avgturn+t.turn;
	        avgwait=avgwait+t.wait ;
	   
	           
		}
		System.out.println("Average TurnAround Time = "+(avgturn/n));
		System.out.println("Average Waiting Time = "+(avgwait/n));
		
		
	}
}
public class Roundrobin 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int i,j,n,q ;
		System.out.print("Enter no of jobs : ");
		n=sc.nextInt();
		job S[] = new job[n]  ;
		job t ;
		System.out.println("Enter jobid,Arrival ,Burst Time of "+n+" "+"jobs");
		for(i=0;i<n;i++)
		{
			S[i]=new job() ;
			S[i].jobid=sc.next();
			S[i].arrival=sc.nextInt();
			S[i].burst=sc.nextInt();
			S[i].remaining=S[i].burst ;
		}
		System.out.print("Enter Time Quantum : ");
		q=sc.nextInt() ;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n-i-1;j++)
			{
				if(S[j].arrival>S[j+1].arrival)
				{
					t=S[j];
					S[j]=S[j+1];
					S[j+1]=t ;
				}
			}
		}
		RR r =new RR();
		r.displaygantt(S,q,n) ;
				
	
		

	}

}
