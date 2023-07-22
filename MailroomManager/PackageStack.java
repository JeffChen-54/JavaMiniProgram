import java.util.Stack;
public class PackageStack {
    private int arrivalDay = 0; //The package's arrival day.
    private final int CAPACITY = 7;  //The max package number in stacks.
    private Stack<Package> Stack1 = new Stack<>();
    private Stack<Package> Stack2 = new Stack<>();
    private Stack<Package> Stack3 = new Stack<>();
    private Stack<Package> Stack4 = new Stack<>();
    private Stack<Package> Stack5 = new Stack<>();
    private Stack<Package> Floor = new Stack<>();
    Stack<Package> newStack = new Stack<>(); //used for copying the stack.
    private String stackNumber = ""; // string name of stacks.
    public int getArrivalDay(){return arrivalDay;}
    public void setArrivalDay(int arrivalDay){
        this.arrivalDay = arrivalDay;
    }
    Stack<Package> myStack = new Stack<>(); //used as cursor to take the stack.
    /**
    *packageName distributes stack by user's name
    *@param x
    *    a new package.
    */
    public void packageName(Package x){
        if((x.getRecipient().charAt(0)>='A'&& x.getRecipient().charAt(0)<='G')//user's name start with A to G to stack 1
                ||(x.getRecipient().charAt(0)>='a'&&x.getRecipient().charAt(0)<='g')){
            myStack = Stack1;
            stackNumber = "Stack 1";
        }
        if((x.getRecipient().charAt(0)>='H'&& x.getRecipient().charAt(0)<='J')//user's name start with H to J to stack 2
                ||(x.getRecipient().charAt(0)>='h'&&x.getRecipient().charAt(0)<='j')){
            myStack = Stack2;
            stackNumber = "Stack 2";
        }
        if((x.getRecipient().charAt(0)>='K'&& x.getRecipient().charAt(0)<='M')//user's name start with K to M to stack 3
                ||(x.getRecipient().charAt(0)>='k'&&x.getRecipient().charAt(0)<='m')) {
            myStack = Stack3;
            stackNumber = "Stack 3";
        }
        if((x.getRecipient().charAt(0)>='N'&& x.getRecipient().charAt(0)<='R')//user's name start with N to R to stack 4
                ||(x.getRecipient().charAt(0)>='n'&&x.getRecipient().charAt(0)<='r')){
            myStack = Stack4;
            stackNumber = "Stack 4";
        }
        if((x.getRecipient().charAt(0)>='S'&& x.getRecipient().charAt(0)<='Z')//user's name start with S to Z to stack 5
                ||(x.getRecipient().charAt(0)>='s'&&x.getRecipient().charAt(0)<='z')){
            myStack = Stack5;
            stackNumber = "Stack 5";
        }
    }
    /*
    pushToAnotherStack push package to another stack
    @param x
        a new package.
     */
    public void pushToAnotherStack(Package x){
        packageName(x);
        String newStackNumber = null;
        if (myStack.size() >= CAPACITY) {
            myStack = Stack1;
            newStackNumber = "Stack 1";
        }
        if (myStack.size() >= CAPACITY) {
            myStack = Stack2;
            newStackNumber = "Stack 2";
        }
        if (myStack.size() >= CAPACITY) {
            myStack = Stack3;
            newStackNumber = "Stack 3";
        }
        if (myStack.size() >= CAPACITY) {
            myStack = Stack4;
            newStackNumber = "Stack 4";
        }
        if (myStack.size() >= CAPACITY) {
            myStack = Stack5;
            newStackNumber = "Stack 5";
        }
        if (myStack.size() >= CAPACITY){
            myStack = Floor;
            newStackNumber = "Floor";
        }
        myStack.push(x);
        System.out.println("A "+x.getWeight()+" lb package is awaiting pickup by "+x.getRecipient()+".");
        if (newStackNumber != null) System.out.printf("As %s was full, it was placed in %s.\n",
                stackNumber,newStackNumber);
    }
    /*
    push method push package to stack.
    @param x
        A new package.
    @exception FullStackException
        Indicates that teh stack is full.
    */
    public void push(Package x)throws FullStackException{
        packageName(x);
        if (myStack.size()>=CAPACITY) throw new FullStackException();
        myStack.push(x);
        System.out.println("A "+x.getWeight()+" lb package is awaiting pickup by "+x.getRecipient()+".");
    }
    /*
    pop method pop the top package of stack.
    @exception EmptyStackException
        Indicates that teh stack is empty.
*/
    public Package pop() throws EmptyStackException{
        if (myStack.size()<=0) throw new EmptyStackException();
        return myStack.pop();
    }
    /*
    peek method returns the top item in the stack.
    @return
        Return the top package.
    @exception EmptyStackException
        Indicates that the stack is empty.
     */
    public Package peek() throws EmptyStackException{
        if (myStack.size()<=0) throw new EmptyStackException();
        return myStack.peek();
    }
    /*
    isFull returns whether the stack is full.
    @return
        True if the stack is full, false if the stack is not full.
     */
    public boolean isFull(){
        return myStack.size() >= CAPACITY;
    }
    /*
    isEmpty returns whether the stack is empty.
    @return
        True if the stack is empty, false if the stack is not empty.
     */
    public boolean isEmpty(){
        return myStack.isEmpty();
    }
    /*
    ListAllPackagesAwaitingAUser lists all packages awaiting user.
    @param user
        The name of user.
    @return
        Return strings telling how many packages the user have.
     */
    public String listAllPackagesAwaitingAUser(String user){
        String str = "";
        int count = 0;
        Stack<Package> s = new Stack<>();
        while (!Stack1.isEmpty()){
            s.push(Stack1.pop());
        }
        while (!s.isEmpty()){
            Stack1.push(s.pop());
            if (Stack1.peek().getRecipient().equals(user)) {
                count++;
                str += "Package "+count+" is in Stack 1, it was delivered on day "+Stack1.peek().getArrivalDate()+
                        ", and weighs "+Stack1.peek().getWeight()+" lbs.\n";
            }
        }
        while (!Stack2.isEmpty()){
            s.push(Stack2.pop());
        }
        while (!s.isEmpty()){
            Stack2.push(s.pop());
            if (Stack2.peek().getRecipient().equals(user)) {
                count++;
                str += "Package "+count+" is in Stack 2, it was delivered on day "+Stack2.peek().getArrivalDate()+
                        ", and weighs "+Stack2.peek().getWeight()+" lbs.\n";
            }
        }
        while (!Stack3.isEmpty()){
            s.push(Stack3.pop());
        }
        while (!s.isEmpty()){
            Stack3.push(s.pop());
            if (Stack3.peek().getRecipient().equals(user)) {
                count++;
                str += "Package "+count+" is in Stack 3, it was delivered on day "+Stack3.peek().getArrivalDate()+
                        ", and weighs "+Stack3.peek().getWeight()+" lbs.\n";
            }
        }
        while (!Stack4.isEmpty()){
            s.push(Stack4.pop());
        }
        while (!s.isEmpty()){
            Stack4.push(s.pop());
            if (Stack4.peek().getRecipient().equals(user)) {
                count++;
                str += "Package "+count+" is in Stack 4, it was delivered on day "+Stack4.peek().getArrivalDate()+
                        ", and weighs "+Stack4.peek().getWeight()+" lbs.\n";
            }
        }
        while (!Stack5.isEmpty()){
            s.push(Stack5.pop());
        }
        while (!s.isEmpty()){
            Stack5.push(s.pop());
            if (Stack5.peek().getRecipient().equals(user)) {
                count++;
                str += "Package "+count+" is in Stack 5, it was delivered on day "+Stack5.peek().getArrivalDate()+
                        ", and weighs "+Stack5.peek().getWeight()+" lbs.\n";
            }
        }
        while (!Floor.isEmpty()){
            s.push(Floor.pop());
        }
        while (!s.isEmpty()){
            Floor.push(s.pop());
            if (Floor.peek().getRecipient().equals(user)) {
                count++;
                str += "Package "+count+" is in Floor, it was delivered on day "+Floor.peek().getArrivalDate()+
                        ", and weighs "+Floor.peek().getWeight()+" lbs.\n";
            }
        }
        String string = "";
        string += user+" has " +count+" packages total.\n" + str;
        return string;
    }
    /*
    getPackage method let the user get the package.
    @param user
        The name of user.
     */
    public void getPackage(String user){
        int count = 0;
        while (!Stack1.isEmpty() && count >= 0){
            if (Stack1.peek().getRecipient().equals(user)) {
                System.out.println("Move "+count+" packages from Stack 1 to floor.");
                System.out.println(this.toString());
                System.out.printf("Give %s %f lb package delivered on day %d.\n",
                        user,Stack1.peek().getWeight(),Stack1.peek().getArrivalDate());
                Stack1.pop();
                System.out.println("Return "+count+" packages to stack 1 from floor.");
                while (count>0){
                    Stack1.push(Floor.pop());
                    count--;
                }
                System.out.println(this.toString());
                count = -1;
                break;
            }
            Floor.push(Stack1.pop());
            count++;
        }
        while (count>0){
            Stack1.push(Floor.pop());
            count--;
        }
        while (!Stack2.isEmpty() && count >= 0){
            if (Stack2.peek().getRecipient().equals(user)) {
                System.out.println("Move "+count+" packages from Stack 2 to floor.");
                System.out.println(this.toString());
                System.out.printf("Give %s %f lb package delivered on day %d.\n",
                        user,Stack2.peek().getWeight(),Stack2.peek().getArrivalDate());
                Stack2.pop();
                System.out.println("Return "+count+" packages to stack 2 from floor.");
                while (count>0){
                    Stack2.push(Floor.pop());
                    count--;
                }
                System.out.println(this.toString());
                count = -1;
                break;
            }
            Floor.push(Stack2.pop());
            count++;
        }
        while (count>0){
            Stack2.push(Floor.pop());
            count--;
        }
        while (!Stack3.isEmpty() && count >= 0){
            if (Stack3.peek().getRecipient().equals(user)) {
                System.out.println("Move "+count+" packages from Stack 3 to floor.");
                System.out.println(this.toString());
                System.out.printf("Give %s %f lb package delivered on day %d.\n",
                        user,Stack3.peek().getWeight(),Stack3.peek().getArrivalDate());
                Stack3.pop();
                System.out.println("Return "+count+" packages to stack 3 from floor.");
                while (count>0){
                    Stack3.push(Floor.pop());
                    count--;
                }
                System.out.println(this.toString());
                count = -1;
                break;
            }
            Floor.push(Stack3.pop());
            count++;
        }
        while (count>0){
            Stack3.push(Floor.pop());
            count--;
        }
        while (!Stack4.isEmpty() && count >= 0){
            if (Stack4.peek().getRecipient().equals(user)) {
                System.out.println("Move "+count+" packages from Stack 4 to floor.");
                System.out.println(this.toString());
                System.out.printf("Give %s %f lb package delivered on day %d.\n",
                        user,Stack4.peek().getWeight(),Stack4.peek().getArrivalDate());
                Stack4.pop();
                System.out.println("Return "+count+" packages to stack 4 from floor.");
                while (count>0){
                    Stack4.push(Floor.pop());
                    count--;
                }
                System.out.println(this.toString());
                count = -1;
                break;
            }
            Floor.push(Stack4.pop());
            count++;
        }
        while (count>0){
            Stack4.push(Floor.pop());
            count--;
        }
        while (!Stack5.isEmpty() && count >= 0){
            if (Stack5.peek().getRecipient().equals(user)) {
                System.out.println("Move "+count+" packages from Stack 5 to floor.");
                System.out.println(this.toString());
                System.out.printf("Give %s %f lb package delivered on day %d.\n",
                        user,Stack5.peek().getWeight(),Stack5.peek().getArrivalDate());
                Stack5.pop();
                System.out.println("Return "+count+" packages to stack 5 from floor.");
                while (count>0){
                    Stack5.push(Floor.pop());
                    count--;
                }
                System.out.println(this.toString());
                count = -1;
                break;
            }
            Floor.push(Stack5.pop());
            count++;
        }
        while (count>0){
            Stack5.push(Floor.pop());
            count--;
        }
        if (count>=0) System.out.println("Invalid. Please enter the name that match the list.");
    }
    /*
    move method move a package from a stack to another stack.
    @param a
        The source stack.
    @param b
        The destination stack.
     */
    public void move(int a,int b){
        if(a == 0) myStack = Floor;
        else if (a == 1) myStack = Stack1;
        else if (a == 2) myStack = Stack2;
        else if (a == 3) myStack = Stack3;
        else if (a == 4) myStack = Stack4;
        else if (a == 5) myStack = Stack5;

        if(b == 0) newStack = Floor;
        else if (b == 1) newStack = Stack1;
        else if (b == 2) newStack = Stack2;
        else if (b == 3) newStack = Stack3;
        else if (b == 4) newStack = Stack4;
        else if (b == 5) newStack = Stack5;
        newStack.push(myStack.pop());
        System.out.println("Finish.");
    }
    /*
    arrivalDay method counts how many packages are returned to sender.
    @param
        The stack of the package stack.
    @return
        Returns the number of packages that are returned to sender.
     */
    public int arrivalDay(Stack<Package> stack){
        Stack<Package> s = new Stack<>();
        int count = 0;
        while (!stack.isEmpty()){
            s.push(stack.pop());
            if (s.peek().getArrivalDate()<=arrivalDay-5) {
                s.pop();
                count++;
            }
        }
        while (!s.isEmpty()){
            stack.push(s.pop());
        }
        return count;
    }
    /*
    misplacedPackages method moves misplaced packages to floor.
    @param stack
        The stack of package stack
    @param sti
        The string name of the stack.
     */
    public void misplacedPackages(Stack<Package> stack,String sti){
        Stack<Package> s = new Stack<>();
        while (!stack.isEmpty()){
            s.push(stack.pop());
            this.packageName(s.peek());
            if(!stackNumber.equals(sti)) Floor.push(s.pop());
        }
        while (!s.isEmpty()){
            stack.push(s.pop());
        }
    }
    /*
    s method returns string that prints the information of the packages in a stack.
    @param stack
        The stack of package stack.
    @return
        Returns information of the packages in a stack.
     */
    public String s(Stack<Package> stack){
        Stack<Package> s = new Stack<>();
        while (!stack.isEmpty()){
            s.push(stack.pop());
        }
        String str = "";
        if(s.isEmpty()) str = "empty.";
        while (!s.isEmpty()){
            str += "[" + s.peek().getRecipient() + "  " + s.peek().getArrivalDate() + "]";
            stack.push(s.pop());
        }
        return str;
    }
    /*
    toString method returns string of the package stack.
    @return
        Returns information of package stack.
     */
    public String toString(){
        return "Current Packages:\n" +
                "--------------------------------\n"+
                "Stack 1 (A-G):|" + s(Stack1) + "\n" +
                "Stack 2 (H-J):|" + s(Stack2) + "\n" +
                "Stack 3 (K-M):|" + s(Stack3) + "\n" +
                "Stack 4 (N-R):|" + s(Stack4) + "\n" +
                "Stack 5 (S-Z):|" + s(Stack5) + "\n" +
                "Floor: |" + s(Floor);

    }
    public Stack<Package> getStack1(){return Stack1;}
    public Stack<Package> getStack2(){return Stack2;}
    public Stack<Package> getStack3(){return Stack3;}
    public Stack<Package> getStack4(){return Stack4;}
    public Stack<Package> getStack5(){return Stack5;}
    public Stack<Package> getFloor(){return Floor;}
    public void setStack1(Stack<Package> stack){Stack1 = stack;}
    public void setStack2(Stack<Package> stack){Stack2 = stack;}
    public void setStack3(Stack<Package> stack){Stack3 = stack;}
    public void setStack4(Stack<Package> stack){Stack4 = stack;}
    public void setStack5(Stack<Package> stack){Stack5 = stack;}
    public void setFloor(Stack<Package> stack){Floor = stack;}
}
