import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
/**
 * <h1>HoldingQueue</h1>
 * The HoldingQueue class serve as the group of people who are up next for a ride
 * <p>
 * <b>Note:</b> HoldingQueue class extends VirtualLine.
 * The main difference between this class and VirtualLine is that it has maxsize.
 *
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-10-18
 */
public class HoldingQueue extends VirtualLine {
    private int maxSize;
    public int getMaxSize(){return maxSize;}
    public void setMaxSize(int maxSize){this.maxSize = maxSize;}
    /**
     * This method is used to return a String that shows the line information of the HoldingQueue.
     * @return String This returns a string of line information of the HoldingQueue.
     */
    public String toString(){
        String str = "";
        ArrayList<Person> tem = new ArrayList<>();
            while (!isEmpty()){
                Person p = dequeue();
                str += p.getName()+p.getNumber() + ", ";
                tem.add(p);
            }
            while (!tem.isEmpty()){
                enqueue(tem.remove(0));
            }
        return str;
    }
}
