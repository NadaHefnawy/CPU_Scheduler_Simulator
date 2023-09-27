import java.util.Scanner;
import java.util.Vector;

public class Priority {
    public Priority(Vector<process> arr, int n) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.print("Enter process name- ");
            String processName = sc.next();
            System.out.print("Enter arrival time- ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter burst time- ");
            int burstTime = sc.nextInt();
            System.out.print("Enter priority- ");
            int priority = sc.nextInt();
            process k = new process();
            k.setData(processName, arrivalTime, burstTime, priority, i, 0, 0);
            arr.add(k);
        }
    }

    public void schedule(Vector<process> arr, int n) {
        findavgTime(arr, n);
    }

    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(Vector<process> arr, int n, int wt[]) {
        int c = 0;
        int rt[] = new int[n];
        int pt[] = new int[n];
        // Copy the burst time into rt[]
        // Copy the priority into pt[]
        for (int i = 0; i < n; i++) {
            rt[i] = arr.get(i).burstTime;
            pt[i] = arr.get(i).priority;
        }
        int complete = 0, t = 0, maxi = Integer.MAX_VALUE;
        int highest = 0, finish_time;
        boolean check = false;
        // Process until all processes gets
        // completed
        while (complete != n) {
            // Find process with minimum
            // priority among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++) {
                if ((arr.get(j).arrivalTime <= t) && (pt[j] < maxi) && rt[j] > 0) {
                    maxi = pt[j];
                    highest = j;
                    check = true;
                }
            }
            if (check == false) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[highest]--;
            c++;
            if (c == 3) {
                for (int i = 0; i < n; i++) {
                    if (arr.get(i).arrivalTime <= t && rt[i] > 0) {
                        pt[i]--;
                    }
                }
                c = 0;
            }
            // Update minimum
            maxi = pt[highest];

            if (rt[highest] == 0)
                maxi = Integer.MAX_VALUE;
            // If a process gets completely
            // executed
            System.out.println("highest priority= " + maxi);
            if (rt[highest] == 0) {
                // Increment complete
                complete++;
                check = false;
                // Find finish time of current
                // process
                finish_time = t + 1;
                // Calculate waiting time
                wt[highest] = finish_time - arr.get(highest).burstTime - arr.get(highest).arrivalTime;
                arr.get(highest).order = complete;
                if (wt[highest] < 0)
                    wt[highest] = 0;

            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Vector<process> arr, int n, int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = arr.get(i).burstTime + wt[i];
    }

    // Method to calculate average time
    static void findavgTime(Vector<process> arr, int n) {
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
        System.out.println("Processes " + " Order " + " Burst time " + " Waiting time " + " Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + arr.get(i).processName + "\t\t\t" + arr.get(i).order + "\t\t\t" + arr.get(i).burstTime + "\t\t\t " + wt[i] + "\t\t\t\t" + tat[i]);
        }

        System.out.println("Average waiting time = " + (float) total_wt / (float) n);
        System.out.println("Average turn around time = " + (float) total_tat / (float) n);
    }
}
