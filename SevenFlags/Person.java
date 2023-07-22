/**
 * <h1>Person</h1>
 * The Person class includes the information of users.
 * <p>
 * <b>Note:</b> Person class includes users' status, line information and their plan information.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-10-18
 */
public class Person {
    private int number;
    private int maxLines = 1;
    Ride[] line = new Ride[1];
    Status status = Status.Available;
    String name;
    public Person(int number){
        if(number <= 0) throw new java.lang.IllegalArgumentException("The number must be positive.");
        this.number = number;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public int getNumber(){return number;}
    public int getMaxLines(){return maxLines;}
    public void setNumber(int number){this.number = number;}
    public void setMaxLines(int maxLines){this.maxLines = maxLines;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Ride[] getLine() {
        return line;
    }
    public void setLine(Ride[] line) {
        this.line = line;
    }
    public void setLineNumber(int a,Ride l){
        line[a] = l;
    }
    public Ride getLineNumber(int a){
        return line[a];
    }
}
