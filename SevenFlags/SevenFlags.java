import java.util.*;
/**
 * <h1>SevenFlags</h1>
 * The SevenFlags program implements an application that
 * lets users' waiting time more bearable.
 * <p>
 * <b>Note:</b> This class don't need to let users wait too long for a single Ride.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-10-18
 */
public class SevenFlags {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int bsodCount =0;
        int kkCount=0;
        int totCount=0;
        int gfCount=0;
        int goldCustomer=0;
        int silverCustomer=0;
        int regularCustomer=0;
        bsod.setName("BSOD");
        kk.setName("KK  ");
        tot.setName("ToT ");
        gf.setName("GF  ");
        int regular;
        Person[] regularPerson;
        int silver;
        Person[] silverPerson;
        int gold;
        Person[] goldPerson;
        int length;
        int time;
        int bsodTime;
        int bsodCapacity;
        int bsodSize;
        int kkTime;
        int kkCapacity;
        int kkSize;
        int totTime;
        int totCapacity;
        int totSize;
        int gfTime;
        int gfCapacity;
        while (true) {
            try {
                System.out.println("Welcome to Seven Flags!");
                System.out.println("\nPlease enter the number of regular customers:");
                regular = sc.nextInt();
                regularPerson = new Person[regular];
                for (int i = 0; i < regular; i++) {
                    regularPerson[i] = new Person(i + 1);
                    regularPerson[i].setMaxLines(1);
                    regularPerson[i].setName("Regular ");
                    regularPerson[i].setLine(new Ride[1]);
                }
                System.out.println("Please enter the number of silver customers:");
                silver = sc.nextInt();
                silverPerson = new Person[silver];
                for (int i = 0; i < silver; i++) {
                    silverPerson[i] = new Person(i + 1);
                    silverPerson[i].setMaxLines(2);
                    silverPerson[i].setName("Silver ");
                    silverPerson[i].setLine(new Ride[2]);
                }
                System.out.println("Please enter the number of gold customers:");
                gold = sc.nextInt();
                goldPerson = new Person[gold];
                for (int i = 0; i < gold; i++) {
                    goldPerson[i] = new Person(i + 1);
                    goldPerson[i].setMaxLines(3);
                    goldPerson[i].setName("Gold ");
                    goldPerson[i].setLine(new Ride[3]);
                }
            }catch (Exception e){
                System.out.println("Please enter positive number.");
                continue;
            }
                System.out.println("Please enter simulation length:");
                length = sc.nextInt();
                if(length<0){
                    System.out.println("Please enter positive number.");
                    continue;}
                System.out.println("\nPlease enter the duration of Blue Scream of Death (minutes):");
                bsodTime = sc.nextInt();
                if(bsodTime<0){
                System.out.println("Please enter positive number.");
                continue;}
                bsod.setDuration(bsodTime);
                bsod.setTimeLeft(bsodTime);
                System.out.println("Please enter the capacity of Blue Scream of Death:");
                bsodCapacity = sc.nextInt();
                if(bsodCapacity<0){
                System.out.println("Please enter positive number.");
                continue;}
                bsod.setCapacity(bsodCapacity);
                System.out.println("Please enter the holding queue size for Blue Scream of Death:");
                bsodSize = sc.nextInt();
                if(bsodSize<0){
                System.out.println("Please enter positive number.");
                continue;}
                bsod.getHoldingQueue().setMaxSize(bsodSize);
                System.out.println("\nPlease enter the duration of Kingda Knuth (minutes):");
                kkTime = sc.nextInt();
                if(kkTime<0){
                System.out.println("Please enter positive number.");
                continue;}
                kk.setDuration(kkTime);
                kk.setTimeLeft(kkTime);
                System.out.println("Please enter the capacity of Kingda Knuth:");
                kkCapacity = sc.nextInt();
                if(kkCapacity<0){
                System.out.println("Please enter positive number.");
                continue;}
                kk.setCapacity(kkCapacity);
                System.out.println("Please enter the holding queue size for Kingda Knuth:");
                kkSize = sc.nextInt();
                if(kkSize<0){
                System.out.println("Please enter positive number.");
                continue;}
                kk.getHoldingQueue().setMaxSize(kkSize);
                System.out.println("\nPlease enter the duration of i386 Tower of Terror (minutes):");
                totTime = sc.nextInt();
                if(totTime<0){
                System.out.println("Please enter positive number.");
                continue;}
                tot.setDuration(totTime);
                tot.setTimeLeft(totTime);
                System.out.println("Please enter the capacity of i386 Tower of Terror:");
                totCapacity = sc.nextInt();
                if(totCapacity<0){
                System.out.println("Please enter positive number.");
                continue;}
                tot.setCapacity(totCapacity);
                System.out.println("Please enter the holding queue size for i386 Tower of Terror:");
                totSize = sc.nextInt();
                if(totSize<0){
                System.out.println("Please enter positive number.");
                continue;}
                tot.getHoldingQueue().setMaxSize(totSize);
                System.out.println("\nPlease enter the duration of GeForce (minutes):");
                gfTime = sc.nextInt();
                if(gfTime<0){
                System.out.println("Please enter positive number.");
                continue;}
                gf.setDuration(gfTime);
                gf.setTimeLeft(gfTime);
                System.out.println("Please enter the capacity of GeForce:");
                gfCapacity = sc.nextInt();
                if(gfCapacity<0){
                System.out.println("Please enter positive number.");
                continue;}
                gf.setCapacity(gfCapacity);
                System.out.println("Please enter the holding queue size for GeForce:");
                int gftSize = sc.nextInt();
                if(gftSize<0){
                System.out.println("Please enter positive number.");
                continue;}
                gf.getHoldingQueue().setMaxSize(gftSize);
            break;
        }
        for (int i=0; i<gold;i++){
            thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
            thisLine.getVirtualLine().enqueue(goldPerson[i]);
            goldPerson[i].setLineNumber(0,thisLine);

        }
        for (int i=0; i<silver;i++){
            thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
            thisLine.getVirtualLine().enqueue(silverPerson[i]);
            silverPerson[i].setLineNumber(0,thisLine);

        }
        for (int i=0; i<regular;i++){
            thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
            thisLine.getVirtualLine().enqueue(regularPerson[i]);
            regularPerson[i].setLineNumber(0,thisLine);

        }
        for (int i=0; i<gold;i++){
            thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
            thisLine.getVirtualLine().enqueue(goldPerson[i]);
            goldPerson[i].setLineNumber(1,thisLine);
        }
        for (int i=0; i<silver;i++){
            thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
            thisLine.getVirtualLine().enqueue(silverPerson[i]);
            silverPerson[i].setLineNumber(1,thisLine);
        }
        for (int i=0; i<gold;i++){
            thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
            thisLine.getVirtualLine().enqueue(goldPerson[i]);
            goldPerson[i].setLineNumber(2,thisLine);
        }
        enrollHoldingQueue();
        enrollOnRide();
        enrollHoldingQueue();
        for (time=0;time<length;time++){
            string(time);
            regularCustomers(regular,regularPerson);
            silverCustomers(silver,silverPerson);
            goldCustomers(gold,goldPerson);
            if(bsod.getTimeLeft()==0){
                for (int i=0;i<bsodCapacity && !(bsod.onRide[i]==null);i++){
                    bsod.onRide[i].setStatus(Status.Available);
                    thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
                    thisLine.getVirtualLine().enqueue(bsod.onRide[i]);
                    int index = 0;
                    for (int j=0; j<bsod.onRide[i].getMaxLines();j++){
                        if(bsod.onRide[i].getLineNumber(j)==bsod){index = j;}
                    }
                    bsod.onRide[i].setLineNumber(index,thisLine);
                    bsodCount++;
                    if(bsod.onRide[i].getMaxLines()==3) goldCustomer++;
                    if(bsod.onRide[i].getMaxLines()==2) silverCustomer++;
                    if(bsod.onRide[i].getMaxLines()==1) regularCustomer++;
                }
                bsod.setTimeLeft(bsodTime);
                bsod.enOnRide();
                enrollHoldingQueue();
            }
            if(kk.getTimeLeft()==0){
                for (int i=0;i<kkCapacity && !(kk.onRide[i]==null);i++){
                    kk.onRide[i].setStatus(Status.Available);
                    thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
                    thisLine.getVirtualLine().enqueue(kk.onRide[i]);
                    int index = 0;
                    for (int j=0; j<kk.onRide[i].getMaxLines();j++){
                        if(kk.onRide[i].getLineNumber(j)==kk){index = j;}
                    }
                    kk.onRide[i].setLineNumber(index,thisLine);
                    kkCount++;
                    if(kk.onRide[i].getMaxLines()==3) goldCustomer++;
                    if(kk.onRide[i].getMaxLines()==2) silverCustomer++;
                    if(kk.onRide[i].getMaxLines()==1) regularCustomer++;
                }
                kk.setTimeLeft(kkTime);
                kk.enOnRide();
                enrollHoldingQueue();
            }
            if(tot.getTimeLeft()==0){
                for (int i=0;i<totCapacity && !(tot.onRide[i]==null);i++){
                    tot.onRide[i].setStatus(Status.Available);
                    thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
                    thisLine.getVirtualLine().enqueue(tot.onRide[i]);
                    int index = 0;
                    for (int j=0; j<tot.onRide[i].getMaxLines();j++){
                        if(tot.onRide[i].getLineNumber(j)==tot){index = j;}
                    }
                    tot.onRide[i].setLineNumber(index,thisLine);
                    totCount++;
                    if(tot.onRide[i].getMaxLines()==3) goldCustomer++;
                    if(tot.onRide[i].getMaxLines()==2) silverCustomer++;
                    if(tot.onRide[i].getMaxLines()==1) regularCustomer++;
                }
                tot.setTimeLeft(totTime);
                tot.enOnRide();
                enrollHoldingQueue();
            }
            if(gf.getTimeLeft()==0){
                for (int i=0;i<gfCapacity && !(gf.onRide[i]==null);i++){
                    gf.onRide[i].setStatus(Status.Available);
                    thisLine = RandomGenerator.selectRide(new Ride[]{bsod,kk,tot,gf},new double[]{0.25,0.25,0.25,0.25});
                    thisLine.getVirtualLine().enqueue(gf.onRide[i]);
                    int index = 0;
                    for (int j=0; j<gf.onRide[i].getMaxLines();j++){
                        if(gf.onRide[i].getLineNumber(j)==gf){index = j;}
                    }
                    gf.onRide[i].setLineNumber(index,thisLine);
                    gfCount++;
                    if(gf.onRide[i].getMaxLines()==3) goldCustomer++;
                    if(gf.onRide[i].getMaxLines()==2) silverCustomer++;
                    if(gf.onRide[i].getMaxLines()==1) regularCustomer++;
                }
                gf.setTimeLeft(gfTime);
                gf.enOnRide();
                enrollHoldingQueue();
            }
        }
        string(time);
        regularCustomers(regular,regularPerson);
        silverCustomers(silver,silverPerson);
        goldCustomers(gold,goldPerson);
        System.out.println("\nOn average, Gold customers have taken "+
                (double)goldCustomer/(double) gold+" rides.");
        System.out.println("On average, Silver customers have taken "+
                (double)silverCustomer/(double) silver+" rides.");
        System.out.println("On average, regular customers have taken "+
                (double)regularCustomer/(double) regular+" rides.\n");
        System.out.printf("BSOD has completed rides for %d people.\n",bsodCount);
        System.out.printf("KK has completed rides for %d people.\n",kkCount);
        System.out.printf("ToT has completed rides for %d people.\n",totCount);
        System.out.printf("GF has completed rides for %d people.\n",gfCount);
    }
    /**
     * This method is used to add users from VirtualLine to HoldingQueue in all Rides.
     */
    public static void enrollHoldingQueue(){
        bsod.enHoldingQueue();
        kk.enHoldingQueue();
        tot.enHoldingQueue();
        gf.enHoldingQueue();
    }
    /**
     * This method is used to add users from HoldingQueue to OnRide array in all Rides.
     */
    public static void enrollOnRide(){
        bsod.enOnRide();
        kk.enOnRide();
        tot.enOnRide();
        gf.enOnRide();
    }
    /**
     * This method is used to print the line information.
     * @param time This is the time that already past.
     */
    public static void string(int time){
        System.out.println("At Time "+time+":\n");
        System.out.println(bsod.toString()+"\n");
        System.out.println(kk.toString()+"\n");
        System.out.println(tot.toString()+"\n");
        System.out.println(gf.toString()+"\n");
    }
    /**
     * This method is used to print the information of regularCustomers.
     * @param regular This is the number of regularCustomers.
     * @param regularPerson This is the array of the information of regularCustomers.
     */
    public static void regularCustomers(int regular,Person[] regularPerson){
        System.out.println("Regular Customers:\n" +
                "\n" +
                "Num   Line   Status\n" +
                "----------------");
        for (int i=0; i<regular; i++){
            System.out.print(i+1+".    ");
            System.out.println(regularPerson[i].getLineNumber(0).getName()+"   "+regularPerson[i].status);
        }
        System.out.println("");
    }
    /**
     * This method is used to print the information of silverCustomers.
     * @param silver This is the number of silverCustomers.
     * @param silverPerson This is the array of the information of silverCustomers.
     */
    public static void silverCustomers(int silver,Person[] silverPerson){
        System.out.println("Silver Customers:\n" +
                "\n" +
                "Num   Line 1   Line 2   Status\n" +
                "----------------");
        for (int i=0; i<silver; i++){
            System.out.print(i+1+".    ");
            System.out.println(silverPerson[i].getLineNumber(0).getName()+"     "+
                    silverPerson[i].getLineNumber(1).getName()+"     "+silverPerson[i].status);
        }
        System.out.println("");
    }
    /**
     * This method is used to print the information of goldCustomers.
     * @param gold This is the number of goldCustomers.
     * @param goldPerson This is the array of the information of goldCustomers.
     */
    public static void goldCustomers(int gold,Person[] goldPerson){
        System.out.println("Gold Customers:\n" +
                "\n" +
                "Num   Line 1   Line 2   Line 3   Status\n" +
                "----------------");
        for (int i=0; i<gold; i++){
            System.out.print(i+1+".    ");
            System.out.println(goldPerson[i].getLineNumber(0).getName()+"     "+
                    goldPerson[i].getLineNumber(1).getName()+"     "+
                    goldPerson[i].getLineNumber(2).getName()+"     "+goldPerson[i].status);
        }
        System.out.println("");
    }
    private static Ride thisLine = new Ride();
    private static Ride bsod = new Ride();
    private static Ride kk = new Ride();
    private static Ride tot = new Ride();
    private static Ride gf = new Ride();
}
