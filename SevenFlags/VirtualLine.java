import java.rmi.NoSuchObjectException;
import java.util.*;
/**
 * <h1>VirtualLine</h1>
 * The VirtualLine class models a Queue
 * <p>
 * <b>Note:</b> VirtualLine has enqueue, dequeue, peek, isEmpty and toString method.
 * It is used for serving people who do not need to actual waiting.
 * When the time approaches, the user will be transferred to HoldingQueue class.
 *
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-10-18
 */
public class VirtualLine {
    ArrayList<Person> thisLine = new ArrayList<>();
    public void enqueue(Person p){
        thisLine.add(p);
    }
    public Person dequeue() { return thisLine.remove(0);
    }
    public Person peek(){
        return thisLine.get(0);
    }
    public boolean isEmpty(){
        return thisLine.isEmpty();
    }
    /**
     * This method is used to return a String that shows the line information of the VirtualLine.
     * @return String This returns a string of line information of the VirtualLine.
     */
    public String toString(){
        String str = "";
        ArrayList<Person> tem = new ArrayList<>();
        Person p;
        while (!isEmpty()){
                tem.add(dequeue());
            }
        while (!tem.isEmpty()) {
                p = tem.remove(0);
                str += p.getName() + p.getNumber() + ", ";
                enqueue(p);
            }
        return str;
    }
}
