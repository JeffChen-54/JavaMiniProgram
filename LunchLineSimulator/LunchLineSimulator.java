import java.util.*;
public class LunchLineSimulator {
    public static void main(String[] args) throws CloneNotSupportedException, EmptyLineException, DeanException, NegativeMoneyException {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Welcome to the Middle School where you are the master of the lunch line,\n"+
                 "and you may subject your kids to whatever form of culinary torture best fits your mood.\n"+
                 "You are in Reality A.");
        System.out.println(
                "Menu:\n" +
                "     A) Add a student to the line at the end\n" +
                "     C) Have a new student cut a friend\n" +
                "     T) Have two students trade places\n" +
                "     B) Have the bully remove a student\n" +
                "     U) Update a student's money amount\n" +
                "     S) Serve a student\n" +
                "     P) Print the current reality's lunch line\n" +
                "     O) Switch to the other reality\n" +
                "     E) Check if the realities are equal\n" +
                "     D) Duplicate this reality into the other reality\n" +
                "     Q) Quit middle school and move on to real life.");
        String str = null;
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
        String string = "Reality A";
        char option = str.charAt(0);
        while (option != 'Q' && option != 'q') {
            switch(option){
                case 'a':
                case 'A' :
                    if(getReality().numStudents()>=20){
                        System.out.println("The lunch line can only hold 20 students. You can't add students.");
                        break;
                    }
                    try{System.out.println("Please enter student name: ");
                    String studentName;
                    studentName= sc.nextLine();
                    System.out.println("Please enter money amount:");
                    double moneyAmount = sc.nextDouble();
                    Student s = new Student(studentName,moneyAmount);
                    getReality().addingStudent(getReality().numStudents(),s);
                    System.out.printf("%s has been added to the line in position %d. %s has $%.2f.\n",
                            getReality().getStudent(getReality().numStudents()).getName(), getReality().numStudents()+1,
                            getReality().getStudent(getReality().numStudents()).getName(),
                            getReality().getStudent(getReality().numStudents()).getMoney());
                    getReality().setStudentCount(getReality().numStudents()+1);}
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("That's an invalid index. Please try again.");
                    }
                    catch (Exception e){
                        System.out.println("That's an invalid input. Please try again.");
                    }
                    sc.nextLine();
                    break;
                case 'c':
                case 'C':
                    try{
                    System.out.println("Please enter student name: ");
                    String cuttingStudentName = sc.nextLine();
                    System.out.println("Please enter money amount:");
                    double cuttingStudentMoneyAmount = sc.nextDouble();
                    System.out.println("Please enter position:");
                    int position = sc.nextInt();
                    getReality().addStudent(new Student(cuttingStudentName,cuttingStudentMoneyAmount),position-1);
                    System.out.printf("%s has cut %s and is now in position %d. ",
                            getReality().getStudent(position-1).getName(),getReality().getStudent(position).getName(),
                            position);
                    System.out.printf("%s has $%.2f.\n",getReality().getStudent(position-1).getName(),
                            getReality().getStudent(position-1).getMoney());}
                    catch (DeanException e){
                        System.out.println("The lunch line can only hold 20 students. You can't add students.");
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Invalid index. Please try again.");
                    }
                    catch (Exception e){
                        System.out.println("Invalid input. Please try again.");
                    }
                    sc.nextLine();
                    break;
                case 't':
                case 'T':{
                    try{System.out.println("Please enter student1 index:");
                    int index1 = sc.nextInt();
                    System.out.println("Please enter student2 index:");
                    int index2 = sc.nextInt();
                    getReality().swapStudents(index1 - 1, index2 - 1);
                    System.out.printf("%s has traded places with %s.\n", getReality().getStudent(index2 - 1).getName(),
                            getReality().getStudent(index1 - 1).getName());
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("That's an invalid index. Please try again.");
                    }
                    catch (Exception e){
                        System.out.println("That's an invalid input. Please try again.");
                    }
                    sc.nextLine();
                    break;
                 }
                case 'b':
                case 'B':{
                    try{System.out.println("Please enter student index: ");
                    int a = sc.nextInt();
                    sc.nextLine();
                        System.out.printf("The bully has stolen %s's lunch money, and %s has left, feeling hungry.\n"
                                ,getReality().removeStudent(a).getName(),getReality().getRemoveStudent().getName());
                    }catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println("That's an invalid index. Please try again.");
                    }
                    catch (EmptyLineException e){
                        System.out.println("There's no student in this line. Please try again.");
                    }
                    catch (Exception e){
                        System.out.println("That's an invalid input. Please try again.");}
                    break;
                }
                case 'u':
                case 'U':{
                    try{
                    System.out.println("Please enter student index:");
                    int b = sc.nextInt();
                    System.out.println("Please enter new money amount:");
                    double money = sc.nextDouble();
                    getReality().moneyUpdate(b,money);
                    System.out.printf("As a result of a shady transaction with the floor, %s now has $%.2f.\n",
                            getReality().getStudent(b-1).getName(), getReality().getStudent(b-1).getMoney());}
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("That's an invalid index. The line was not updated.");
                    }
                    catch (NegativeMoneyException e){
                        System.out.println("You can't have debt in middle school. The lunch line was not updated.");
                    }
                    catch (Exception e){
                        System.out.println("That's an invalid input. Please try again.");
                    }
                    sc.nextLine();
                    break;
                }
                case 's':
                case 'S':{
                    System.out.printf("%s has been served today's special: Bouncy \"Chicken?\" Nuggets." +
                            " We hope he lives to see another day!\n",getReality().removeStudent(1).getName());
                    break;
                }
                case 'p':
                case 'P':
                    System.out.println("Lunch Line in "+string+":");
                    System.out.print(getReality().toString());
                    break;
                case 'o':
                case 'O':{
                    if(string.equals("Reality A")){
                        setThisReality(realityB);
                        System.out.println("You are in Reality B. I reject your reality and substitute my own.");
                        string = "Reality B";
                    }else {
                        setThisReality(realityA);
                        System.out.println("You are in Reality A. I reject your reality and substitute my own.");
                        string = "Reality A";
                    }
                    break;
                }
                case 'e':
                case 'E':{
                    if(getReality()==realityA){
                        if(getReality().equals(realityB))
                            System.out.println("The realities are equal.");
                        else System.out.println("The realities are not equal.");
                    }
                    else if(getReality().equals(realityA))
                        System.out.println("The realities are equal.");
                    else System.out.println("The realities are not equal.");
                    break;
                }
                case 'd':
                case 'D':{
                    if(getReality()==realityA){
                        realityB = realityA.clone();
                        System.out.println("Reality A has been copied into Reality B.");
                        break;
                    }
                    if(getReality()==realityB){
                        realityA = realityB.clone();
                        System.out.println("Reality B has been copied into Reality A.");
                    }
                    break;
                }
                default: System.out.println("Invalid!!! Please try again.");
            }
            valid = false;
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
        }
        System.out.println("You are now leaving the Middle School Lunch Line Simulator.\n" +
                "We congratulate you on your decision to do something more productive with your time.");
    }
    private static StudentLine realityA = new StudentLine();
    private static StudentLine realityB = new StudentLine();
    private static StudentLine thisReality = realityA;
    public static StudentLine getReality(){return thisReality;}
    public static void setThisReality(StudentLine a){thisReality = a;}
}
