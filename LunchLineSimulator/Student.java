public class Student implements Cloneable{
        private String name;
        private double money;
        public Student(String name, double money){
                this.name = name;
                this.money = money;
        }
        public String getName(){return name;}
        public void setName(String name){this.name = name;}
        public void setMoney(double money){this.money = money;}
        public double getMoney(){return money;}
        public String toString(){return name+"    $"+money;}
        public boolean equals(Object obj){
                Student student = (Student) obj;
                if (!(this.name.equals(student.name))) return false;
                else if (!(this.money==student.money))return false;
                return true;
        }
        public Student clone() throws CloneNotSupportedException {
                Student student = (Student) super.clone();
                student.name = name;
                student.money=money;
                return student;
        }}
