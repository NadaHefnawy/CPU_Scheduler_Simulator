import java.util.Comparator;
import java.util.Scanner;
import java.lang.String;
import java.util.Collections;
public  class process  {
    String processName="";
    int arrivalTime=0;
    int burstTime=0;
    int cBurstTime=0;
    int priority=0;
    int processNumber=0;
    int completionTime=0;
    int waitingTime=0;
    int turnAroundTime=0;
    int cTime;
    int queue;
    int temp_burst;
    boolean complete;
    char compare;
    int order=0;
    int contextSwitch;
    int quantum;
    int cQuantum;


    public void setData(String processName,int arrivalTime,  int burstTime,int priority ,int num,int quantum,int contextSwitch)
    {
        this.processName=processName;
        this.arrivalTime=arrivalTime;
        this.burstTime=burstTime;
        this.cBurstTime=burstTime;
        this.priority=priority;
        this.processNumber=num;
        this.waitingTime=0;
        this.turnAroundTime=0;
        this.quantum=quantum;
        this.cQuantum=quantum;
        this.contextSwitch=contextSwitch;
    }




}

