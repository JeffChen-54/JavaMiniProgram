import java.util.*;
public class MailroomManager {
    public static void main(String[] args) throws EmptyStackException, FullStackException{
        Package pack;
        String recipient;
        double weight;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Irving Mailroom Manager. You can try to make it better," +
                " but the odds are stacked against you. It is day 0.");
        System.out.println("Menu:\n" +
                "     D) Deliver a package\n" +
                "     G) Get someone's package\n" +
                "     T) Make it tomorrow\n" +
                "     P) Print the stacks\n" +
                "     M) Move a package from one stack to another\n" +
                "     F) Find packages in the wrong stack and move to floor\n" +
                "     L) List all packages awaiting a user\n" +
                "     E) Empty the floor.\n" +
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
        char option = str.charAt(0);// make sure that the input is from A to Z.
        while (option!='Q' && option!='q'){
            switch (option) {
                case 'd':
                case 'D':
                        try{System.out.println("Please enter the recipient name: ");
                        recipient = sc.nextLine();
                        System.out.println("Please enter the weight (lbs): ");
                        weight = sc.nextDouble();
                        sc.nextLine();}
                        catch (Exception e){
                            System.out.println("Invalid input. Please try again!");
                            sc.nextLine();
                            break;
                        }
                        pack = new Package(recipient, packageStack.getArrivalDay(), weight);
                        try{packageStack.push(pack);}
                        catch (FullStackException e){packageStack.pushToAnotherStack(pack);}
                        break;
                case 'g':
                case 'G':
                    System.out.println("Please enter the recipient name:");
                    recipient = sc.nextLine();
                    packageStack.getPackage(recipient);
                    break;
                case 't':
                case 'T':
                    int count = 0;
                    packageStack.setArrivalDay(packageStack.getArrivalDay()+1);
                    System.out.printf("It is now day %d.\n",packageStack.getArrivalDay());
                    count += packageStack.arrivalDay(packageStack.getStack1());
                    count += packageStack.arrivalDay(packageStack.getStack2());
                    count += packageStack.arrivalDay(packageStack.getStack3());
                    count += packageStack.arrivalDay(packageStack.getStack4());
                    count += packageStack.arrivalDay(packageStack.getStack5());
                    count += packageStack.arrivalDay(packageStack.getFloor());
                    if(count > 0) System.out.printf("%d packages have been returned to sender.\n",count);
                    break;
                case 'p':
                case 'P':
                    System.out.println(packageStack.toString());
                    break;
                case 'm':
                case 'M':
                    try{
                    System.out.println("Please enter the source stack (enter 0 for floor):");
                    int source = sc.nextInt();
                    System.out.println("Please enter the destination stack:");
                    int destination = sc.nextInt();
                    packageStack.move(source,destination);
                    sc.nextLine();
                    break;}
                    catch (Exception e){
                        System.out.println("The source stack you input is either not exist or empty." +
                                " Please try again.");
                        sc.nextLine();
                        break;}
                case 'f':
                case 'F':
                    packageStack.misplacedPackages(packageStack.getStack1(),"Stack 1");
                    packageStack.misplacedPackages(packageStack.getStack2(),"Stack 2");
                    packageStack.misplacedPackages(packageStack.getStack3(),"Stack 3");
                    packageStack.misplacedPackages(packageStack.getStack4(),"Stack 4");
                    packageStack.misplacedPackages(packageStack.getStack5(),"Stack 5");
                    System.out.println("Misplaced packages moved to floor.");
                    break;
                case 'l':
                case 'L':
                    System.out.println("Please enter the recipient name:");
                    recipient = sc.nextLine();
                    System.out.println(packageStack.listAllPackagesAwaitingAUser(recipient));
                    break;
                case 'e':
                case 'E':
                    packageStack.setFloor(new Stack<>());
                    System.out.println("The floor has been emptied. Mr. Trash Can is no longer hungry.");
                    break;
                default:
                    System.out.println("Invalid. Please try again!");
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
        System.out.println("Use Amazon Locker next time.");
    }
    private static PackageStack packageStack = new PackageStack();
}
