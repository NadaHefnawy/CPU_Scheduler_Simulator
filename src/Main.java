import java.util.*;
import java.lang.String;
public class Main {
    public static void main(String[] args) {
        Vector<process> arr = new Vector<process>();
        int n;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of processes- ");
        //System.out.println(Math.ceil(7/4.0));
        n= sc.nextInt();
        int choice;

        System.out.println("Please enter ur choice");
        System.out.println("1- SJF");
        System.out.println("2- Round Robin");
        System.out.println("3- Priority");
        System.out.println("4- AG");
        choice=sc.nextInt();
        if (choice==1)
        {
            SJF sjf=new SJF(arr,n);
            sjf.schedule(arr,n);
        }
        else if (choice==2)
        {
            RoundRobin rr=new RoundRobin(arr,n);
            rr.schedule(arr,n);
        }
        else if (choice==3)
        {
            Priority p=new Priority(arr,n);
            p.schedule(arr,n);
        }
        else if (choice==4)
        {
            AG ag=new AG(arr,n);
            ag.schedule(arr,n);
        }





    }
}