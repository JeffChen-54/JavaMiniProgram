import java.util.*;
import java.io.*;
/**
 * <h1>LunarSystem</h1>
 * The LunarSystem class implements an application that
 * allows the user to interact with a database of Students
 * <p>
 * <b>Note:</b> LunarSystem can provide the user with a menu-based interface that allows them to
 * add, remove,and edit Students, as well as their courses.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-15
 */
public class LunarSystem {
    private static HashMap<String, Student> database;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String webid;
        System.out.println("Welcome to the Lunar System, " +
                "a second place course registration system for second rate courses at a second class school.");
        try{
        database = loadData();}
        catch (FileNotFoundException | ClassNotFoundException e){
            System.out.println("\nNo previous data found.");
            database = new HashMap<String,Student>();
        }
        System.out.println(
                "\nMenu:\n" +
                        "    L)Login\n" +
                        "    X)Save state and quit\n" +
                        "    Q)Quit without saving state.\n");

        char option = input();
        while (option!='Q' && option!='q'){
            switch (option){
                case 'L':
                case 'l':
                    System.out.println("Please enter webid:");
                    webid = sc.nextLine();
                    if(webid.equals("REGISTRAR")){
                        registrar();
                    }
                    else student(webid);
                    break;
                case 'X':
                case 'x':
                    FileOutputStream file = new FileOutputStream("storage.obj");
                    ObjectOutputStream outStream = new ObjectOutputStream(file);
                    outStream.writeObject(database);
                    System.out.println("System state saved, system shut down for maintenance.");
                    outStream.close();
                    return;
                default:System.out.println("Invalid input. Try again please.");
            }
            System.out.println(
                    "\nMenu:\n" +
                            "    L)Login\n" +
                            "    X)Save state and quit\n" +
                            "    Q)Quit without saving state.\n");
            option = input();
        }
            System.out.println("Good bye, please pick the right SUNY next time!");
        File file = new File("storage.obj");
        file.delete();
    }
    /**
     * This method is used to load data if previous data found.
     *
     * @return HashMap<String,Student> This return the database that converted from file.
     * @exception IOException Throws exception if there is failed or interrupted I/O operations.
     * @exception ClassNotFoundException Throws exception if the class is not found.
     */
    public static HashMap<String,Student> loadData() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("storage.obj");
        ObjectInputStream inStream = new ObjectInputStream(file);
        HashMap<String,Student> storage;
        storage = (HashMap<String, Student>) inStream.readObject();
        inStream.close();
        System.out.println("\nPrevious data loaded.");
        return storage;
    }
    /**
     * This method is used to moves the cursor to the parent of the current node.
     *
     * @return char This return a char that can be used for options.
     */
    public static char input(){
        Scanner sc = new Scanner(System.in);
        char option;
        String str="";
        boolean valid = false;
        while(!valid){
            System.out.println("Please select an option:");
            str = sc.nextLine();
            if(str.length()!=1){
                System.out.println("Invalid input. Try again please.");
                continue;
            }
            if(!(str.charAt(0)>='a'&& str.charAt(0)<='z') && !(str.charAt(0)>='A' && str.charAt(0)<='Z')){
                System.out.println("Invalid input. Try again please.");
                continue;
            }
            valid = true;
        }
        option = str.charAt(0);
        return option;
    }
    /**
     * This method is used to operate the registrar option.
     */
    public static void registrar(){
        System.out.println("\nWelcome Registrar.\n\n" +
                "Options:\n" +
                "     R) Register a student\n" +
                "     D) De-register a student\n" +
                "     E) View course enrollment\n" +
                "     L) Logout\n");
        char option = input();
        Scanner sc = new Scanner(System.in);
        String webid;
        String courseName;
        while (option!='L' && option!='l'){
            switch (option){
                case 'R':
                case 'r':
                    System.out.println("Please enter a webid for the new student:");
                    webid = sc.nextLine();
                    boolean flag = false;
                    for (int i=0;i<database.size();i++){
                        if(database.keySet().toArray()[i].toString().equals(webid)){
                            System.out.println(webid+" is already registered.\n");
                            flag = true;
                        }
                        else if(database.keySet().toArray()[i].toString().equalsIgnoreCase(webid)){
                            System.out.println(database.keySet().toArray()[i].toString()
                                    +" is already registered. webid is case insensitive. Please try again.\n");
                            flag = true;
                        }
                    }
                    if (flag){
                        break;
                    }
                    database.put(webid,new Student(webid));
                    System.out.println(webid+" registered.\n");
                    break;
                case 'D':
                case 'd':
                    System.out.println("Please enter a webid for the student to be deregistered:");
                    webid = sc.nextLine();
                    for (int i=0;i<database.size();i++){
                        if (database.keySet().toArray()[i].toString().equalsIgnoreCase(webid)){
                            webid = database.keySet().toArray()[i].toString();
                        }
                    }
                    if(!database.containsKey(webid)){
                        System.out.println("Wrong webID. Please try again.");
                        break;
                    }
                    database.remove(webid);
                    System.out.println(webid + " deregistered.");
                    break;
                case 'E':
                case 'e':
                    System.out.println("Please enter course: ");
                    courseName = sc.nextLine();
                    String department;
                    int number;
                    try{
                        department = courseName.substring(0,courseName.indexOf(" "));
                        number = Integer.parseInt(courseName.substring(courseName.indexOf(" ")+1));}
                    catch (Exception e){
                        System.out.println("Please enter a designated department and a three-digit course number" +
                                " in correct format. Ex: \"CSE 214\"");
                        break;
                    }
                    if(number>999||number<100||department.length()!=3){
                        System.out.println("Please enter a designated department and a three-digit course number" +
                                " in correct format. Ex: \"CSE 214\"");
                        break;
                    }
                    System.out.println("Students Registered in "+department+" "+number+":\n" +
                            "Student    Semester\n" +
                            "-------------------");
                    for (int i=0;i<database.size();i++){
                        for (int j=0;j<database.get(database.keySet().toArray()[i]).getCourses().size();j++){
                            if(database.get(database.keySet().toArray()[i]).
                                    getCourses().get(j).getDepartment().equalsIgnoreCase(department)&&
                                    database.get(database.keySet().toArray()[i]).
                                            getCourses().get(j).getNumber()==number){
                                System.out.println(database.keySet().toArray()[i]+"         "+
                                        database.get(database.keySet().toArray()[i]).
                                                getCourses().get(j).getSemester());
                            }
                        }
                    }
                    break;
                default:System.out.println("Invalid input. Try again please.");
            }
            option = input();
        }
        System.out.println("Registrar logged out.");
    }
    /**
     * This method is used to operate the student option.
     *
     * @param webid This is the key that access the associated student object.
     */
    public static void student(String webid){
        for (int i=0;i<database.size();i++){
            if (database.keySet().toArray()[i].toString().equalsIgnoreCase(webid)){
                webid = database.keySet().toArray()[i].toString();
            }
        }
        if(!database.containsKey(webid)){
            System.out.println("Wrong webID. Please try again.");
            return;
        }
        System.out.println("\nWelcome "+webid+".\n" +
                "\n" +
                "Options:\n" +
                "    A)Add a class\n" +
                "    D)Drop a class\n" +
                "    C)View your classes sorted by course name/department\n" +
                "    S)View your courses sorted by semester\n"+
                "    L) Logout\n");
        char option = input();
        Scanner sc = new Scanner(System.in);
        String courseName;
        String department;
        String semesterName = "";
        int number;
        String semester;
        while (option!='L' && option!='l'){
            switch (option){
                case 'A':
                case 'a':
                    System.out.println("Please enter course name:");
                    courseName = sc.nextLine();
                    try{
                    department = courseName.substring(0,courseName.indexOf(" ")).toUpperCase(Locale.ROOT);
                    number = Integer.parseInt(courseName.substring(courseName.indexOf(" ")+1));}
                    catch (Exception e){
                        System.out.println("Please enter a designated department and a three-digit course number" +
                                " in correct format. Ex: \"CSE 214\"");
                        break;
                    }
                    if(number>999||number<100||department.length()!=3){
                        System.out.println("Please enter a designated department and a three-digit course number" +
                                " in correct format. Ex: \"CSE 214\"");
                        break;
                    }
                    System.out.println("Please select a semester:");
                    semester = sc.nextLine();
                    try{
                    if(semester.length()!=5 || Integer.parseInt(semester.substring(1))>2025
                            || Integer.parseInt(semester.substring(1))<2010
                            || (semester.charAt(0)!='F'&& semester.charAt(0)!='S')){
                        System.out.println("Please enter S or F followed by a number 2010-2025. Ex: \"F2021\"");
                        break;
                    }}
                    catch (Exception e){
                        System.out.println("Wrong input. " +
                                "Please enter S or F followed by a number 2010-2025. Ex: \"F2021\"");
                        break;
                    }
                    if(semester.charAt(0)=='F'){
                        semesterName = "Fall "+semester.substring(1);
                    }
                    else if(semester.charAt(0) == 'S'){
                        semesterName = "Spring "+semester.substring(1);
                    }
                    boolean bool = true;
                    for (int j=0;j<database.get(webid).getCourses().size();j++){
                        if(database.get(webid).
                                getCourses().get(j).getDepartment().equalsIgnoreCase(department)&&
                                database.get(webid).getCourses().get(j).getNumber()==number){
                            System.out.println(department+" "+number+" already in the course list! Please try again.");
                            bool = false;
                        }
                    }
                    if(bool){
                    database.get(webid).getCourses().add(new Course(department,number,semester));
                    System.out.println(department+" "+number+" added in "+semesterName+".\n");}
                    break;
                case 'D':
                case 'd':
                    System.out.println("Please enter course name: ");
                    courseName = sc.nextLine();
                    try{
                        department = courseName.substring(0,courseName.indexOf(" "));
                        number = Integer.parseInt(courseName.substring(courseName.indexOf(" ")+1));}
                    catch (Exception e){
                        System.out.println("Please enter a designated department and a three-digit course number" +
                                " in correct format. Ex: \"CSE 214\"");
                        break;
                    }
                    if(number>999||number<100||department.length()!=3){
                        System.out.println("Please enter a designated department and a three-digit course number" +
                                " in correct format. Ex: \"CSE 214\"");
                        break;
                    }
                    for (int i=0;i<database.get(webid).getCourses().size();i++){
                        if(database.get(webid).getCourses().get(i).getDepartment().equalsIgnoreCase(department) &&
                                database.get(webid).getCourses().get(i).getNumber()==number){
                            semester = database.get(webid).getCourses().get(i).getSemester();
                            if(semester.charAt(0)=='F'){
                            semesterName = "Fall "+semester.substring(1);
                            }
                            else if(semester.charAt(0) == 'S'){
                                semesterName = "Spring "+semester.substring(1);
                            }
                            database.get(webid).getCourses().remove(i);
                            System.out.println(department+" "+number+" dropped from "+semesterName+".\n");
                        }
                    }
                    break;
                case 'C':
                case 'c':
                    Collections.sort(database.get(webid).getCourses(),new CourseNameComparator());
                    System.out.println(arraylistToString(database.get(webid).getCourses()));
                    break;
                case 'S':
                case 's':
                    Collections.sort(database.get(webid).getCourses(),new SemesterComparator());
                    System.out.println(arraylistToString(database.get(webid).getCourses()));
                    break;
                default:
                    System.out.println("Invalid input. Try again please.");
            }
            option = input();
        }
        System.out.println(webid+" logged out.");
    }
    /**
     * This method is used to get the String of the information of the ArrayList of Courses.
     *
     * @param c This is the ArrayList that used to be converted to String.
     * @return String This returns the String that can print the information of ArrayList.
     */
    public static String arraylistToString(ArrayList<Course> c){
        String str = "Dept. Course Semester\n" +
                "-------------------------------\n";
        ArrayList<Course> newCourse = new ArrayList<>();
        while (!c.isEmpty()){
            newCourse.add(c.remove(0));
        }
        while (!newCourse.isEmpty()){
            Course a = newCourse.remove(0);
            str += a.toString();
            c.add(a);
        }
        return str;
    }
}
