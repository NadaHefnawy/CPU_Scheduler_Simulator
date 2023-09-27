
import java.util.Scanner;
import java.util.Vector;

public class SJF
{
    public SJF(Vector<process> arr, int n)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter context switch- ");
        int contextSwitch= sc.nextInt();
        for (int i=0;i<n;i++)
        {
            System.out.print("Enter process name- ");
            String processName= sc.next();
            System.out.print("Enter arrival time- ");
            int arrivalTime= sc.nextInt();
            System.out.print("Enter burst time- ");
            int burstTime= sc.nextInt();
            process k=new process();
            k.setData(processName,arrivalTime,burstTime,0,i,0,contextSwitch);
            arr.add(k);
        }

    }
    public void schedule(Vector<process> arr, int n)
    {
        findavgTime(arr, n);
    }
    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(Vector<process> arr, int n, int wt[])
    {
        int rt[] = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = arr.get(i).burstTime;

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time,count=0;
        boolean check = false;

        // Process until all processes gets
        // completed
        while (complete != n)
        {
            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            int checker=shortest;
            for (int j = 0; j < n; j++)
            {
                if ((arr.get(j).arrivalTime <= t) && (rt[j] < minm) && rt[j] > 0)
                {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }
            if (shortest==0&&check)
                count++;
            if (checker!=shortest)
            {
                if (count==0)
                    count++;
                else
                {
                    t+=arr.get(shortest).contextSwitch;
                }
            }



            if (check == false)
            {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;
            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely
            // executed
            if (rt[shortest] == 0)
            {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;
                // Calculate waiting time
                wt[shortest] = finish_time - arr.get(shortest).burstTime - arr.get(shortest).arrivalTime;
                arr.get(shortest).order=complete;

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Vector<process> arr, int n, int wt[], int tat[])
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = arr.get(i).burstTime + wt[i];
    }

    // Method to calculate average time
    static void findavgTime(Vector<process> arr, int n)
    {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(arr, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(arr, n, wt, tat);

      // Display processes along with all
        // details
        System.out.println("Processes "+ " Order " + " Burst time " + " Waiting time " + " Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + arr.get(i).processName +"\t\t\t" + arr.get(i).order + "\t\t\t" + arr.get(i).burstTime +"\t\t\t "  + wt[i] + "\t\t\t\t" + tat[i]);
        }

        System.out.println("Average waiting time = " + (float)total_wt / (float)n);
        System.out.println("Average turn around time = " + (float)total_tat / (float)n);
    }


}
