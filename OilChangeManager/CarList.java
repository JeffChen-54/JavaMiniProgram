public class CarList{
    private CarListNode head = null;
    private CarListNode tail = null;
    private CarListNode cursor = null;
    private int num;
    public int numCars(){return num;}
    public Car getCursorCar(){
        return cursor.getData();
    }
    //Reset the cursor to the head.
    public void resetCursorToHead(){
        cursor = head;
    }
    //Reset the cursor to the tail.
    public void resetCursorToTail(){
        cursor = tail;
    }
    //Move the cursor forward.
    public void cursorForward() throws EndOfListException {
        if(cursor == tail) throw new EndOfListException();
        cursor = cursor.getNext();
    }
    //Move the cursor backward.
    public void cursorBackward() throws EndOfListException {
        if (cursor == head) throw new EndOfListException();
        cursor = cursor.getPrev();
    }
    //Insert a CaeListNode before cursor.
    public void insertBeforeCursor(Car newCar) throws IllegalArgumentException {
        if(newCar==null)throw new IllegalArgumentException();
        CarListNode newCarListNode = new CarListNode(newCar);
        if (cursor==null){
            head = newCarListNode;
            cursor = newCarListNode;
            tail = newCarListNode;
        }
        else if(cursor.getPrev()==null) {
            head = newCarListNode;
            head.setNext(cursor);
            cursor.setPrev(head);
        }
        else {
            cursor.getPrev().setNext(newCarListNode);
            newCarListNode.setPrev(cursor.getPrev());
            newCarListNode.setNext(cursor);
            cursor.setPrev(newCarListNode);
        }
        num++;
    }
    //Add a CarListNode at the end of the CarList.
    public void appendToTail(Car newCar)throws IllegalArgumentException{
        if(newCar == null)throw new IllegalArgumentException();
        CarListNode newCarListNode = new CarListNode(newCar);
        if (cursor == null){
            head = newCarListNode;
            cursor = newCarListNode;
            tail = newCarListNode;
            num++;
        }
        else {
            tail.setNext(newCarListNode);
            newCarListNode.setPrev(tail);
            tail = newCarListNode;
            num++;}
    }
    //Remove the CarListNode of cursor.
    public Car removeCursor()throws EndOfListException{
        if(cursor == null)throw new EndOfListException();
        CarListNode car;
        if(cursor == tail){
            tail = cursor.getPrev();
            car = cursor;
            cursor = tail;
        }
        else if(cursor == head) {
            head = cursor.getNext();
            car = cursor;
            cursor = head;
        }
        else {cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        car = cursor;
        cursor = cursor.getNext();}
        num--;
        return car.getData();
    }
    //Print the information of the CarList.
    public String toString(String a){
        String str = "";
        CarListNode position = head;
        System.out.printf(
                "%s List:\n" +
                "Make        Owner\n" +
                "----------------------\n",a);
        if(position == null)return "[empty]\n";
        for (int i=1 ; i<=num;i++){
            if(position == cursor) str += "-->";
            str += position.getData().toString()+"\n";
            position = position.getNext();
        }
        return str;
    }
    //Sorting lists so that all the cars of a particular make are grouped together.
    public CarList sort() throws IllegalArgumentException {
        CarList newCarList = new CarList();
        cursor=head;
        for (int i=1; i<=num;i++){
            if(cursor.getData().getMake().equals(Make.valueOf("FORD")))
                newCarList.appendToTail(this.getCursor().getData());
                cursor=cursor.getNext();
        }
        cursor=head;
        for (int j=1; j<=num;j++){
            if(cursor.getData().getMake().equals(Make.valueOf("GMC")))
                newCarList.appendToTail(this.getCursor().getData());
            cursor=cursor.getNext();
        }
        cursor=head;
        for (int a=1; a<=num;a++){
            if(cursor.getData().getMake().equals(Make.valueOf("CHEVY")))
                newCarList.appendToTail(this.getCursor().getData());
            cursor=cursor.getNext();
        }
        cursor=head;
        for (int b=1; b<=num;b++){
            if(cursor.getData().getMake().equals(Make.valueOf("JEEP")))
                newCarList.appendToTail(this.getCursor().getData());
            cursor=cursor.getNext();
        }
        cursor=head;
        for (int c=1; c<=num;c++){
            if(cursor.getData().getMake().equals(Make.valueOf("DODGE")))
                newCarList.appendToTail(this.getCursor().getData());
            cursor=cursor.getNext();
        }
        cursor=head;
        for (int d=1; d<=num;d++){
            if(cursor.getData().getMake().equals(Make.valueOf("CHRYSLER")))
                newCarList.appendToTail(this.getCursor().getData());
            cursor=cursor.getNext();
        }
        cursor=head;
        for (int e=1; e<=num;e++){
            if(cursor.getData().getMake().equals(Make.valueOf("LINCOLN")))
                newCarList.appendToTail(this.getCursor().getData());
            cursor=cursor.getNext();
        }
        return newCarList;
    }
    public CarListNode getHead(){return head;}
    public CarListNode getCursor(){return cursor;}
    public CarListNode getTail(){return tail;}
    public void setHead(CarListNode head){
        this.head = head;
    }
    public void setCursor(CarListNode cursor){
        this.cursor = cursor;
    }
    public void setTail(CarListNode tail){
        this.tail = tail;
    }
}
