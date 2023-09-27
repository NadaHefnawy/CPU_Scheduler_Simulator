import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;
public class AG {
    public AG(Vector<process> arr, int n)
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
            System.out.print("Enter quantum- ");
            int quantum= sc.nextInt();
            process k=new process();
            k.setData(processName,arrivalTime,burstTime,priority,i,quantum,0);
            arr.add(k);
        }
    }
    public void schedule(Vector<process> arr, int n)
    {
        Vector<String>v=new Vector<String>(n);
        System.out.print("Quantum time of processes= ( ");
        for (int i=0;i<n-1;i++)
            System.out.print(arr.get(i).quantum+" , ");
        System.out.print(arr.get(arr.size()-1).quantum+" )");
        System.out.print("\n");
        int count=1;
        int current,time=0,complete=0,x;
        float avgWaitinTime=0,avgTurnAroundTime=0;
        int arrMin,arrIndex=0,maxPriority=Integer.MAX_VALUE,arrPriority=0;
        Boolean zero=true;
        arrMin=Integer.MAX_VALUE;
        for (int i=0;i<n;i++)
        {
            if (arr.get(i).arrivalTime<=time&&arrMin>arr.get(i).arrivalTime&&arr.get(i).cBurstTime>0)
            {
                arrMin=arr.get(i).arrivalTime;
                arrIndex=i;
            }
        }
        current=arrIndex;

        while (time<arr.get(current).arrivalTime)
            time++;
        while (complete<=n)
        {
            if (count==1)
            {
               if (!zero)
               {
                   arrMin=Integer.MAX_VALUE;
                   for (int i=0;i<n;i++)
                   {
                       if (arr.get(i).arrivalTime<=time&&arrMin>arr.get(i).arrivalTime&&arr.get(i).cBurstTime>0)
                       {
                           arrMin=arr.get(i).arrivalTime;
                           arrIndex=i;
                       }
                   }
                   current=arrIndex;
                   zero=true;
               }
               x=(int)Math.ceil(arr.get(current).quantum/4.0);
               if(arr.get(current).cBurstTime-x>0)
               {
                   arr.get(current).cBurstTime-=x;
                   time+=x;
                   arr.get(current).cQuantum-=x;
                   count++;
               }
               else
               {
                   time+=arr.get(current).cBurstTime;
                   arr.get(current).cBurstTime=0;
                   arr.get(current).quantum=0;
                   arr.get(current).cQuantum=0;
                   arr.get(current).completionTime=time;
                   v.add(arr.get(current).processName);
                   System.out.print("Modified quantum of processes= ( ");
                   for (int i=0;i<n-1;i++)
                       System.out.print(arr.get(i).quantum+" , ");
                   System.out.print(arr.get(arr.size()-1).quantum+" )");
                   System.out.print("\n");
                   complete++;
                   count=1;
                   zero=false;
               }
               //System.out.println(arr.get(current).processName+" 111 in time: "+time);


           }
            else if (count==2)
            {
                maxPriority=Integer.MAX_VALUE;
                for(int i=0;i<n;i++)
                {
                    if(maxPriority>arr.get(i).priority&&arr.get(i).cBurstTime>0&&arr.get(i).arrivalTime<=time)
                    {
                        maxPriority=arr.get(i).priority;
                        arrPriority=i;
                    }
                }
                if (current!=arrPriority)
                {
                    zero=true;
                    arr.get(current).quantum+=Math.ceil((arr.get(current).cQuantum)/2.0) ;
                    arr.get(current).cQuantum=arr.get(current).quantum;
                    //System.out.println(arr.get(current).processName+" priority is smaller than "+arr.get(arrPriority).processName);
                    current=arrPriority;
                    count=1;
                    System.out.print("Modified quantum of processes= ( ");
                    for (int i=0;i<n-1;i++)
                        System.out.print(arr.get(i).quantum+" , ");
                    System.out.print(arr.get(arr.size()-1).quantum+" )");
                    System.out.print("\n");

                }
                else
                {
                    x= (int) Math.ceil(arr.get(current).quantum/2.0)-(int) Math.ceil(arr.get(current).quantum/4.0);
                    if (arr.get(current).cBurstTime-x>0)
                    {
                        arr.get(current).cBurstTime-=x;
                        time+=x;
                        arr.get(current).cQuantum-=x;
                        count++;
                    }
                    else
                    {
                        time+=arr.get(current).cBurstTime;
                        arr.get(current).cBurstTime=0;
                        arr.get(current).quantum=0;
                        arr.get(current).completionTime=time;
                        v.add(arr.get(current).processName);
                        System.out.print("Modified quantum of processes= ( ");
                        for (int i=0;i<n-1;i++)
                            System.out.print(arr.get(i).quantum+" , ");
                        System.out.print(arr.get(arr.size()-1).quantum+" )");
                        System.out.print("\n");
                        complete++;
                        count=1;
                        zero=false;
                    }
                    //System.out.println(arr.get(current).processName+" 222 in time: "+time);
                }

            }
            else if (count==3)
            {
                int minBurst=Integer.MAX_VALUE;
                for (int i=0;i<n;i++)
                {
                    if(arr.get(i).cBurstTime<minBurst&&arr.get(i).cBurstTime>0&&arr.get(i).arrivalTime<=time)
                    {
                        minBurst=arr.get(i).cBurstTime;
                        arrIndex=i;
                    }
                }
                if (arrIndex!=current)
                {
                    zero=true;
                    arr.get(current).quantum+=arr.get(current).cQuantum;
                    arr.get(current).cQuantum=arr.get(current).quantum;
                    //System.out.println(arr.get(current).processName+" burst time is bigger than "+arr.get(arrIndex).processName);
                    current=arrIndex;
                    count=1;
                    System.out.print("Modified quantum of processes= ( ");
                    for (int i=0;i<n-1;i++)
                        System.out.print(arr.get(i).quantum+" , ");
                    System.out.print(arr.get(arr.size()-1).quantum+" )");
                    System.out.print("\n");
                }
                else
                {
                    x=arr.get(current).quantum-arr.get(current).cQuantum;
                    if (arr.get(current).cBurstTime-x>0)
                    {
                        arr.get(current).cBurstTime-=x;
                        time+=x;
                        arr.get(current).cQuantum=arr.get(current).quantum+2;
                        arr.get(current).quantum+=2;
                    }
                    else
                    {
                        time+=arr.get(current).cBurstTime;
                        arr.get(current).cBurstTime=0;
                        arr.get(current).quantum=0;
                        arr.get(current).completionTime=time;
                        v.add(arr.get(current).processName);
                        System.out.print("Modified quantum of processes= ( ");
                        for (int i=0;i<n-1;i++)
                            System.out.print(arr.get(i).quantum+" , ");
                        System.out.print(arr.get(arr.size()-1).quantum+" )");
                        System.out.print("\n");
                        complete++;
                        count=1;
                        zero=true;
                    }
                    //System.out.println(arr.get(current).processName+" 333 in time: "+time);

                }
            }

        }
        for (int i=0;i<n;i++)
        {
            if (arr.get(i).order>n)
                arr.get(i).order=n;
            arr.get(i).waitingTime = arr.get(i).completionTime - arr.get(i).burstTime - arr.get(i).arrivalTime;
            if (arr.get(i).waitingTime<0)
                arr.get(i).waitingTime=0;
            arr.get(i).turnAroundTime = arr.get(i).waitingTime + arr.get(i).burstTime ;
            avgWaitinTime+=arr.get(i).waitingTime;
            avgTurnAroundTime+=arr.get(i).turnAroundTime;
        }
        for (int i=1;i<n;i++)
        {
            if (v.get(i)==v.get(i-1))
            {
                v.removeElementAt(i);
                i--;
            }
        }

        System.out.print("Processes are executed in this order: ");
        for (int i=0;i<n;i++)
            System.out.print(v.get(i)+"  "  );
        System.out.print("\n");
        System.out.println("Processes "+  " Burst time " + " Waiting time " + " Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {

            System.out.println(" " + arr.get(i).processName + "\t\t\t\t"  + arr.get(i).burstTime + "\t\t\t " + arr.get(i).waitingTime + "\t\t\t\t" + arr.get(i).turnAroundTime);
        }

        System.out.println("Average waiting time = " + (float)avgWaitinTime / (float)n);
        System.out.println("Average turn around time = " + (float)avgTurnAroundTime / (float)n);


    }
}
