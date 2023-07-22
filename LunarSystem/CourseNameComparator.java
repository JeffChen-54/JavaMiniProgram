import java.util.Comparator;
public class CourseNameComparator implements Comparator {
    public int compare (Object o1, Object o2){
        Course e1 = (Course) o1;
        Course e2 = (Course) o2;
        return (e1.getDepartment()+e1.getNumber()).compareTo((e2.getDepartment()+e2.getNumber()));
    }
}
