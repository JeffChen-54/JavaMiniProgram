import java.io.Serializable;
/**
 * <h1>Course</h1>
 * The Course class includes the information of courses.
 * <p>
 * <b>Note:</b> Course class will have a designated department,
 * a three-digit course number, and a semester associated with it.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-15
 */
public class Course implements Comparable, Serializable {
    private String department;
    private int number;
    private String semester;
    public Course(String department,int number,String semester){
        this.department = department;
        this.number = number;
        this.semester = semester;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public int compareTo(Object o){
        Course course = (Course) o;
        if(this.number == course.number){
            return 0;
        }
        else if(this.number > course.number){
            return 1;
        }
        return -1;
    }
    public String toString(){
        return department + "    " +number + "    "+semester+"\n";
    }
}
