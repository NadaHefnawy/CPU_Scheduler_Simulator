import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;


public class RoundRobin {
    int quantumTime;
    public RoundRobin(Vector<process> arr, int n)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter context switch- ");
        int contextSwitch= sc.nextInt();
        for (int i=0;i<n;i++)
        {
            System.out.print("Enter process name- ");
            String processName= sc.next();
            System.out.print("Enter burst time- ");
            int burstTime= sc.nextInt();
            process k=new process();
            k.setData(processName,0,burstTime,0,i,0,contextSwitch);
            arr.add(k);
        }
        System.out.print("Enter Quantum time- ");
        quantumTime= sc.nextInt();
    }
    public void schedule(Vector<process> arr, int n)
    {
        int time=0,c=1;
        float avgTurnAroundTime=0;
        float avgWaitingTime=0;
        while (true)
        {
            boolean flag=true;
            for (int i=0;i<n;i++)
            {
                if (arr.get(i).cBurstTime>0)
                {
                    flag=false;
                }
            }
            if (flag)
                break;
            for (int i=0;i<n;i++)
            {
                if (arr.get(i).cBurstTime>0)
                {
                    if (arr.get(i).cBurstTime-quantumTime>0)
                    {
                        arr.get(i).cBurstTime-=quantumTime;
                        time+=quantumTime;
                        //time++;
                    }
                    else if (arr.get(i).cBurstTime-quantumTime<0)
                    {
                        time+=arr.get(i).cBurstTime;
                        arr.get(i).completionTime=time;
                        //time++;
                        arr.get(i).cBurstTime=0;
                        arr.get(i).order=c;
                        c++;
                    }
                    else
                    {
                        time+=quantumTime;
                        arr.get(i).completionTime=time;
                        //time++;
                        arr.get(i).cBurstTime=0;
                        arr.get(i).order=c;
                        c++;
                    }
                    time+=arr.get(i).contextSwitch;
                }
            }
        }
        arr.get(0).turnAroundTime=arr.get(0).burstTime+arr.get(0).waitingTime;

        for (int i=0;i<n;i++)
        {
            arr.get(i).waitingTime=arr.get(i).completionTime-arr.get(i).burstTime;
            avgWaitingTime+=arr.get(i).waitingTime;
            arr.get(i).turnAroundTime=arr.get(i).completionTime-arr.get(i).arrivalTime;
            avgTurnAroundTime+=arr.get(i).turnAroundTime;
        }




        System.out.println(" " +"Process Name" + "\t\t" +"Process Order" +"\t " + "Waiting Time" +"\t\t " +"Turnaround Time");
        System.out.println("---------------------------------------------------------------------------------");
        for (int i=0;i<n;i++)
        {

            System.out.println(" " +arr.get(i).processName + "\t\t\t\t\t\t" +arr.get(i).order +"\t\t\t\t" + arr.get(i).waitingTime +"\t\t\t\t\t\t" +arr.get(i).turnAroundTime);

        }
        System.out.println("process average waiting time: "+avgWaitingTime);
        System.out.println("process average turnaround time: "+avgTurnAroundTime);

    }
}