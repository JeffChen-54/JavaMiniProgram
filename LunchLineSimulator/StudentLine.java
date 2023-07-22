public class StudentLine implements Cloneable {
    final int CAPACITY = 20;
    private Student[] students = new Student[CAPACITY];
    private int studentCount;
    private Student removeStudent;
    public Student getRemoveStudent() {
        return removeStudent;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int numStudents() {
        return studentCount;
    }

    public Student getStudent(int index) {
        if (studentCount < index) {
            throw new ArrayIndexOutOfBoundsException("That's an invalid index.");
        }
        return students[index];
    }

    public void addStudent(Student s, int index) throws DeanException {
        if (studentCount == 20) throw new DeanException();
        if (studentCount <= index) {
            throw new ArrayIndexOutOfBoundsException("That's an invalid index.");
        }
        Student[] newStudents = new Student[CAPACITY];
        for (int i = 0, j = 0; i < studentCount; i++, j++) {
            if (i == index) {
                newStudents[j] = s;
                j++;
            }
            newStudents[j] = students[i];
        }
        students = newStudents;
        studentCount++;
    }

    public Student removeStudent(int index) throws EmptyLineException {
        if (studentCount == 0) {
            throw new EmptyLineException();
        }
        if (studentCount <= index) {
            throw new ArrayIndexOutOfBoundsException("That's an invalid index.");
        }
        removeStudent = students[index - 1];
        Student[] newStudents = new Student[CAPACITY];
        for (int i = 0, j = 0; i < studentCount; i++) {
            if (i == index - 1) {
                continue;
            }
            newStudents[j++] = students[i];
        }
        students = newStudents;
        studentCount--;
        return removeStudent;
    }

    public void addingStudent(int index, Student student) {
        students[index] = student;
    }

    public void swapStudents(int index1, int index2) {
        if (studentCount <= index1 || studentCount <= index2) {
            throw new ArrayIndexOutOfBoundsException("That's an invalid index.");
        }
        Student s1 = students[index2];
        students[index2] = students[index1];
        students[index1] = s1;
    }

    public void moneyUpdate(int index, double money) throws NegativeMoneyException {
        if(studentCount<index) throw new ArrayIndexOutOfBoundsException();
        if(money<0) throw new NegativeMoneyException();
        students[index - 1].setMoney(money);
    }

    public StudentLine clone() throws CloneNotSupportedException {
        StudentLine studentLine = (StudentLine) super.clone();
        studentLine.students = new Student[CAPACITY];
        for (int a = 0; a < CAPACITY; a++) {
            studentLine.students[a] = students[a];
        }
        return studentLine;
    }

    public boolean equals(Object o) {
        StudentLine stu = (StudentLine) o;
        for (int a = 0; a < CAPACITY; a++) {
            if (stu.students[a] != students[a]) return false;
        }
        return true;
    }

    public String toString() {
        if (studentCount <= 0) return "No student in this line.\n";
        String str = "";
        for (int a = 0, j = 1; a < this.studentCount; a++, j++) {
            str += j + "." + students[a].getName() + "    $" + students[a].getMoney() + "\n";
        }
        return str;
    }
}
