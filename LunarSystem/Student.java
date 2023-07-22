import java.io.Serializable;
import java.util.ArrayList;
/**
 * <h1>Student</h1>
 * The Student class serve as the stored element of the Lunar System database.
 * <p>
 * <b>Note:</b> Each Student object includes a webID and a list of courses the student has taken.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-15
 */
public class Student implements Serializable {
    private String webID;
    private ArrayList<Course> courses = new ArrayList<Course>();
    public Student(String webID){
        this.webID = webID;
    }
    public String getWebID() {
        return webID;
    }
    public void setWebID(String webID) {
        this.webID = webID;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
