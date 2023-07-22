public class Package{
    private String recipient;
    private int arrivalDate;
    private double weight;
    public Package(String recipient, int arrivalDate, double weight){
        this.recipient = recipient;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
    }
    public String getRecipient(){return  recipient;}
    public int getArrivalDate(){return arrivalDate;}
    public double getWeight(){return weight;}
    public void setRecipient(String recipient){this.recipient = recipient;}
    public void setArrivalDate(int arrivalDate){this.arrivalDate = arrivalDate;}
    public void setWeight(double weight){this.weight = weight;
    }
}
