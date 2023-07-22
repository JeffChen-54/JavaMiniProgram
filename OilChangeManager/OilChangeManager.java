import java.util.*;
public class OilChangeManager {
    private static CarList JoeCarList = new CarList();
    private static CarList DonnyCarList = new CarList();
    private static CarList FinishedList = new CarList();
    private static CarList thisCarList = new CarList();
    public static void main(String[] args) throws IllegalArgumentException, EndOfListException {
        Car removedCar = null;
        String worker = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tony's Discount Oil Change Computer Management System!" +
                " It's way better than a rolodex, cork board, post-its, and pre-chewed bubblegum!");
        System.out.println("Menu:\n" +
                "     L) Edit Job Lists for Joe and Donny\n" +
                "     M) Merge Job Lists\n" +
                "     P) Print Job Lists\n" +
                "     F) Paste car to end of finished car list\n" +
                "     S) Sort Job Lists\n" +
                "     Q) Quit");
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
        char option = str.charAt(0);
        while (option != 'Q' && option != 'q'){
            switch (option){
                case 'l':
                case 'L':
                    valid = false;
                    while(!valid){
                        System.out.println("Please select a list - Joe (J) or Donny (D):");
                        str = sc.nextLine();
                        if(str.length()!=1){
                            System.out.println("Invalid input. Try again please.");
                            continue;
                        }
                        if(!(str.charAt(0)=='j'|| str.charAt(0)=='J' || str.charAt(0)=='d' || str.charAt(0)=='D')){
                            System.out.println("Invalid input. Try again please.");
                            continue;
                        }
                        valid = true;
                    }
                    option = str.charAt(0);
                    if (option == 'j' || option == 'J'){thisCarList = JoeCarList;
                    worker = "Joe";}
                    if (option == 'd' || option == 'D'){thisCarList = DonnyCarList;
                    worker = "Donny";}
                    System.out.println("Options:\n" +
                            "     A) Add a car to the end of the list\n" +
                            "     F) Cursor Forward\n" +
                            "     H) Cursor to Head\n" +
                            "     T) Cursor to Tail\n" +
                            "     B) Cursor Backward\n" +
                            "     I) Insert car before cursor\n" +
                            "     X) Cut car at cursor\n" +
                            "     V) Paste before cursor\n" +
                            "     R) Remove cursor");
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
                    switch (option){
                        case 'a':
                        case 'A':
                            System.out.print("Please enter vehicle make " +
                                    "(Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln):");
                            String s = sc.nextLine();
                            s = s.toUpperCase(Locale.ROOT);
                            if(!(s.equals("FORD")||s.equals("GMC")||s.equals("JEEP")||s.equals("DODGE")||s.equals("CHEVY")
                                    ||s.equals("CHRYSLER")||s.equals("LINCOLN"))){
                                System.out.println("We don't service " +s+"!");
                                break;
                            }
                            Make make = Make.valueOf(s);
                            System.out.println("Please enter owner's name:");
                            String name = sc.nextLine();
                            Car newCar = new Car(make,name);
                            thisCarList.appendToTail(newCar);
                            System.out.printf("%s's %s has been scheduled for an oil change with %s " +
                                    "and has been added to his list.\n",name,s,worker);
                            break;
                        case 'f':
                        case 'F':
                            try{thisCarList.cursorForward();
                            System.out.printf("Cursor Moved Forward in %s's List.\n",worker);}
                            catch (EndOfListException e){
                                System.out.println("It is the tail of the list. Please try again.");
                            }
                            break;
                        case 'h':
                        case 'H':
                            thisCarList.resetCursorToHead();
                            System.out.printf("Cursor Moved To Head in %s's List.\n",worker);
                            break;
                        case 't':
                        case 'T':
                            thisCarList.resetCursorToTail();
                            System.out.printf("Cursor Moved To Tail in %s's List.\n",worker);
                            break;
                        case 'b':
                        case 'B':
                            try{thisCarList.cursorBackward();
                            System.out.printf("Cursor Moved Backward in %s's List.\n",worker);}
                            catch (EndOfListException e){
                                System.out.println("It is the head of the list. Please try again.");
                            }
                            break;
                        case 'i':
                        case 'I':
                            System.out.print("Please enter vehicle make " +
                                    "(Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln):");
                            s = sc.nextLine();
                            s = s.toUpperCase(Locale.ROOT);
                            if(!(s.equals("FORD")||s.equals("GMC")||s.equals("JEEP")||s.equals("DODGE")||s.equals("CHEVY")
                                    ||s.equals("CHRYSLER")||s.equals("LINCOLN"))){
                                System.out.println("We don't service " +s+"!");
                                break;
                            }
                            make = Make.valueOf(s);
                            System.out.println("Please enter owner's name:");
                            name = sc.nextLine();
                            try{thisCarList.insertBeforeCursor(new Car(make,name));
                            System.out.printf("%s's %s has been scheduled for an oil change with %s " +
                                    "and has been added to his list before the cursor.\n",name,s,worker);}
                            catch (IllegalArgumentException e){
                                System.out.println("Invalid input. Please try again.");
                            }
                            break;
                        case 'x':
                        case 'X':
                            try{removedCar = thisCarList.removeCursor();
                            System.out.printf("Cursor cut in %s's List.\n",worker);}
                            catch (EndOfListException e){
                                System.out.println("The cursor is null. Please try again.");
                            }
                            break;
                        case 'v':
                        case 'V':
                            try{thisCarList.insertBeforeCursor(removedCar);}
                            catch (IllegalArgumentException e){
                                System.out.println("Nothing to paste.");
                                break;
                            }
                            removedCar = null;
                            System.out.printf("Cursor pasted in %s's List.\n",worker);
                            break;
                        case 'r':
                        case 'R':
                            try{thisCarList.removeCursor();
                            System.out.printf("Cursor removed in %s's List.\n",worker);}
                            catch (EndOfListException e){
                                System.out.println("The cursor is null. Please try again.");
                            }
                            break;
                        default:System.out.println("Invalid! Please try again.");
                    }
                    break;
                case 'M':
                case 'm':
                    valid = false;
                    while(!valid){
                        System.out.println("Please select a destination list - Joe (J) or Donny (D):");
                        str = sc.nextLine();
                        if(str.length()!=1){
                            System.out.println("Invalid input. Try again please.");
                            continue;
                        }
                        if(!(str.charAt(0)=='j'|| str.charAt(0)=='J'|| str.charAt(0)=='d' || str.charAt(0)=='D')){
                            System.out.println("Invalid input. Try again please.");
                            continue;
                        }
                        valid = true;
                    }
                    option = str.charAt(0);
                    if(option == 'j' || option == 'J'){
                        CarListNode newCarListNode = JoeCarList.getCursor();
                        if(newCarListNode==null){
                            JoeCarList = DonnyCarList;
                            DonnyCarList = new CarList();
                            System.out.println("Donny's list merged into Joe's.");
                            break;
                        }
                        JoeCarList.resetCursorToHead();
                        CarList newCarList = new CarList();
                        int a = DonnyCarList.numCars()+ JoeCarList.numCars();
                        int b = JoeCarList.numCars();
                        for(int i=1, j=1; i<= a;i++){
                            if(DonnyCarList.getHead()!=null) newCarList.appendToTail(DonnyCarList.getHead().getData());
                            if(j<= b--){
                                newCarList.appendToTail(JoeCarList.removeCursor());
                            }
                            if(newCarList.getTail().getData()==newCarListNode.getData())
                                newCarList.setCursor(newCarList.getTail());
                            if(DonnyCarList.getHead()!=null)DonnyCarList.setHead(DonnyCarList.getHead().getNext());
                        }
                        DonnyCarList = new CarList();
                        JoeCarList = newCarList;
                        System.out.println("Donny's list merged into Joe's.");
                    }
                    if (option == 'd' || option == 'D'){
                        if(DonnyCarList.getCursor()==null){
                            DonnyCarList = JoeCarList;
                            JoeCarList = new CarList();
                            System.out.println("Joe's list merged into Donny's.");
                            break;
                        }
                        JoeCarList.resetCursorToHead();
                        CarList newCarList = new CarList();
                        int a = DonnyCarList.numCars()+ JoeCarList.numCars();
                        int b = JoeCarList.numCars();
                        for(int i=1, j=1; i<= a;i++){
                            if(DonnyCarList.getHead()!=null){newCarList.appendToTail(DonnyCarList.getHead().getData());
                            if(newCarList.getTail().getData()==DonnyCarList.getCursor().getData())
                                newCarList.setCursor(newCarList.getTail());}
                            if(j<= b--){
                                newCarList.appendToTail(JoeCarList.removeCursor());
                            }
                            if(DonnyCarList.getHead()!=null)DonnyCarList.setHead(DonnyCarList.getHead().getNext());
                        }
                        DonnyCarList = newCarList;
                        JoeCarList = new CarList();
                        System.out.println("Joe's list merged into Donny's.");
                    }
                    break;
                case 'p':
                case 'P':
                    System.out.println(JoeCarList.toString("Joe's"));
                    System.out.println(DonnyCarList.toString("Donny's"));
                    System.out.println(FinishedList.toString("Finished"));
                    break;
                case 'f':
                case 'F':
                    try{FinishedList.appendToTail(removedCar);
                    removedCar = null;
                    System.out.println("Car pasted in finished list.");}
                    catch (IllegalArgumentException e){
                        System.out.println("Nothing to paste.");
                    }
                    break;
                case 's':
                case 'S':
                    JoeCarList = JoeCarList.sort();
                    DonnyCarList = DonnyCarList.sort();
                    System.out.println("Finishing sorting.");
                    break;
                default:System.out.println("Invalid! Please try again.");
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
        System.out.println("Enjoy your retirement!");
    }
}
