import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Vector;

public  class FCFS{
    public FCFS(Vector<process> arr, int n)
    {
        Scanner sc=new Scanner(System.in);
        for (int i=0;i<n;i++)
        {
            System.out.print("Enter process name- ");
            String processName= sc.next();
            System.out.print("Enter arrival time- ");
            int arrivalTime= sc.nextInt();
            System.out.print("Enter burst time- ");
            int burstTime= sc.nextInt();
            System.out.print("Enter priority- ");
            int priority= sc.nextInt();
            process k=new process();
            k.setData(processName,arrivalTime,burstTime,priority,i,0,0);
            arr.add(k);
        }
    }
    public void schedule(Vector<process> arr, int n)
    {
        float avgTurnAroundTime=0;
        float avgWaitingTime=0;
        for (int i=0;i<n-1;i++)
        {
            for (int j=n-1;j>i;j--)
            {

                if (arr.get(j-1).arrivalTime>arr.get(j).arrivalTime)
                {
                    process k=new process();
                    k=arr.get(j);
                    arr.set(j, arr.get(j-1));
                    arr.set(j-1, k);
                }

            }
        }
        arr.get(0).completionTime=0;
        arr.get(0).waitingTime=0;
        arr.get(0).turnAroundTime=arr.get(0).burstTime+arr.get(0).waitingTime;
        arr.get(0).order=1;


        for (int i=1;i<n;i++)
        {
            arr.get(i).completionTime=arr.get(i-1).burstTime+arr.get(i-1).completionTime;
            arr.get(i).waitingTime=arr.get(i).completionTime-arr.get(i).arrivalTime;
            avgWaitingTime+=arr.get(i).waitingTime;
            arr.get(i).turnAroundTime=arr.get(i).burstTime+arr.get(i).waitingTime;
            avgTurnAroundTime+=arr.get(i).turnAroundTime;
            arr.get(i).order=i+1;
        }
        for (int i=0;i<n-1;i++)
        {
            for (int j=n-1;j>i;j--)
            {
                if (arr.get(j-1).processNumber>arr.get(j).processNumber)
                {
                    process k=new process();
                    k=arr.get(j);
                    arr.setElementAt(arr.get(j-1),j);
                    arr.setElementAt(k,j-1);
                }
            }
        }

        for (int i=0;i<n;i++)
        {
            System.out.println("process name: "+arr.get(i).processName);
            System.out.println("process order: "+arr.get(i).order);
            System.out.println("process waiting time: "+arr.get(i).waitingTime);
            System.out.println("process turnaround time: "+arr.get(i).turnAroundTime);

        }
        System.out.println("process average waiting time: "+avgWaitingTime);
        System.out.println("process average turnaround time: "+avgTurnAroundTime);

    }
}