/**
 * <h1>Ride</h1>
 * The Ride class implements an application that
 * displays the line information.
 * <p>
 * <b>Note:</b> Ride class includes HoldingQueue and VirtualLine information.
 * It also implements the time function.
 * People can easily see the time and number of people in the queue from this class.
 *
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-10-18
 */
public class Ride {
    private int duration;
    private int timeLeft;
    private String Name;
    private int capacity;
    private VirtualLine virtualLine = new VirtualLine();
    private HoldingQueue holdingQueue = new HoldingQueue();
    public int getDuration(){return duration;}
    public void setDuration(int duration){this.duration = duration;}
    public int getTimeLeft(){return timeLeft;}
    public void setTimeLeft(int timeLeft){this.timeLeft = timeLeft;}
    public String getName(){return Name;}
    public void setName(String name){this.Name = name;}
    public VirtualLine getVirtualLine(){return virtualLine;}
    public HoldingQueue getHoldingQueue(){return holdingQueue;}
    public void setVirtualLine(VirtualLine virtualLine){this.virtualLine = virtualLine;}
    public void setHoldingQueue(HoldingQueue holdingQueue){this.holdingQueue = holdingQueue;}
    public int getCapacity(){return capacity;}
    public void setCapacity(int capacity){this.capacity = capacity;}
    Person[] onRide;
    /**
     * This method is used to add users from VirtualLine to HoldingQueue.
     */
    public void enHoldingQueue(){
        HoldingQueue h = new HoldingQueue();
        int count = 0;
            while (!holdingQueue.isEmpty()){
                Person p = holdingQueue.dequeue();
                h.enqueue(p);
                count++;
            }
            while (!h.isEmpty()){
                holdingQueue.enqueue(h.dequeue());
            }
        Person bo = null;
        int sum =0;
        VirtualLine t = new VirtualLine();
        while (!virtualLine.isEmpty()){
            t.enqueue(virtualLine.dequeue());
            sum++;
        }
        while (!t.isEmpty()){
            virtualLine.enqueue(t.dequeue());
        }
            while (holdingQueue.getMaxSize()>count && !virtualLine.isEmpty()){
                Person p = virtualLine.dequeue();
                if(!p.status.equals(Status.OnRide)&&!p.status.equals(Status.Holding)){
                    p.setStatus(Status.Holding);
                    holdingQueue.enqueue(p);
                    count++;
                }else {
                    sum--;
                    virtualLine.enqueue(p);}
                if(sum<=0)break;
            }
    }
    /**
     * This method is used to add users from HoldingQueue to OnRide array.
     */
    public void enOnRide(){
        onRide = new Person[capacity];
            for (int i=0;i<capacity && !holdingQueue.isEmpty();i++){
                onRide[i] = holdingQueue.dequeue();
                onRide[i].setStatus(Status.OnRide);
            }
    }
    /**
     * This method is used to return a String that shows the line information of the Ride.
     * @return String This returns a string of line information of the Ride.
     */
    public String toString(){
        String str = "";
        for (int i=0;i<capacity&& !(onRide[i]==null);i++){
            str += onRide[i].getName() + onRide[i].getNumber() + ", ";
        }
        return  Name+" - Time remaining: "+timeLeft-- +" min\n" +
                "On Ride: " + str + "\n" +
                "Holding Queue: "+ getHoldingQueue().toString() + "\n" +
                "Virtual Queue: "+ getVirtualLine().toString() +"";
    }
}