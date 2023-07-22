public class CarListNode {
    private Car data;
    private CarListNode next;
    private CarListNode prev;
    public CarListNode(Car initData){
        if(initData == null)throw new java.lang.IllegalArgumentException();
        data = initData;
        next = null;
        prev = null;
    }
    public Car getData(){return data;}
    public void setData(Car data){
        this.data = data;
    }
    public CarListNode getNext(){return next;}
    public void setNext(CarListNode next){
        this.next = next;
    }
    public CarListNode getPrev(){return prev;}
    public void setPrev(CarListNode prev){
        this.prev = prev;
    }
}
