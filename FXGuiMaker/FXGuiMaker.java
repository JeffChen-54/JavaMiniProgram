import java.io.IOException;
import java.util.*;
/**
 * <h1>FXGuiMaker</h1>
 * The FXGuiMaker class implements an application that
 * generates a FXComponentTree and provides an interface for a user to manipulate the tree.
 * <p>
 * <b>Note:</b> FXGuiMaker can load from file and save to file.
 *
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-03
 */
public class FXGuiMaker {
    public static void main(String[] args) throws IOException, Exception{
        FXComponentTree tree = new FXComponentTree();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to counterfeit SceneBuilder.\n");
        System.out.println("Menu:\n" +
                "    L) Load from file\n" +
                "    P) Print tree\n" +
                "    C) Move cursor to a child node\n" +
                "    R) Move cursor to root\n" +
                "    A) Add a child\n" +
                "    U) Cursor up (to parent)\n" +
                "    E) Edit text of cursor\n" +
                "    D) Delete child\n" +
                "    S) Save to file\n" +
                "    Q) Quit\n");
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
        while (option!='Q' && option!='q') {
            switch (option) {
                case 'l':
                case 'L':
                    String filename = "";
                    System.out.println("Please enter filename:");
                    filename = sc.nextLine();
                    tree = callFXComponentTree(filename);
                    break;
                case 'p':
                case 'P':
                    tree.toString(tree.getRoot(),-1);
                    System.out.println("");
                    break;
                case 'c':
                case 'C':
                    System.out.println("Please enter number of child (starting with 1): ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    tree.cursorToChild(index);
                    break;
                case 'r':
                case 'R':
                    tree.cursorToRoot();
                    break;
                case 'a':
                case 'A':
                    valid = false;
                    while(!valid){
                        System.out.println("Select component type " +
                                "(H - HBox, V - VBox, T - TextArea, B - Button, L - Label): ");
                        str = sc.nextLine();
                        if(str.length()!=1){
                            System.out.println("Invalid input. Try again please.");
                            continue;
                        }
                        if(!(str.charAt(0)=='h'|| str.charAt(0)=='H')
                                && !(str.charAt(0)=='v' || str.charAt(0)=='V')
                                && !(str.charAt(0)=='t' || str.charAt(0)=='T')
                                && !(str.charAt(0)=='b' || str.charAt(0)=='B')
                                && !(str.charAt(0)=='l' || str.charAt(0)=='L'))
                        {
                            System.out.println("Invalid input. Try again please.");
                            continue;
                        }
                        valid = true;
                    }
                    option = str.charAt(0);
                    String newType = "";
                    if(option == 'h'||option=='H') newType = "HBox";
                    else if(option == 'v'||option=='V') newType = "VBox";
                    else if(option == 't'||option=='T') newType = "TextArea";
                    else if(option == 'b'||option=='B') newType = "Button";
                    else if(option == 'l'||option=='L') newType = "Label";
                    FXTreeNode newFXTreeNode = new FXTreeNode();
                    newFXTreeNode.setType(ComponentType.valueOf(newType));
                    if(newType.equals("TextArea")||newType.equals("Button")||newType.equals("Label")){
                    System.out.println("Please enter text:");
                    String newText = sc.nextLine();
                    newFXTreeNode.setText(newText);}
                    System.out.println("Please enter an index:");
                    int newIndex = 0;
                    try {
                    newIndex = sc.nextInt();}
                    catch (Exception e){System.out.println("Invalid.");
                    sc.nextLine();
                    break;
                    }
                    sc.nextLine();
                    try{
                    tree.addChild(newIndex,newFXTreeNode);}
                    catch (Exception e){
                        System.out.println("Invalid. The node at the specified index makes a hole in the array.");
                    }
                    break;
                case 'u':
                case 'U':
                    tree.cursorToParent();
                    break;
                case 'e':
                case 'E':
                    System.out.println("Please enter new text:");
                    String edit = sc.nextLine();
                    tree.setTextAtCursor(edit);
                    break;
                case 'd':
                case 'D':
                    System.out.println("Please enter number of child (starting with 1): ");
                    int deleteIndex;
                    try{deleteIndex = sc.nextInt();}
                    catch (Exception e){
                        sc.nextLine();
                        System.out.println("Invalid.");
                        break;
                    }
                    sc.nextLine();
                    tree.deleteChild(deleteIndex);
                    break;
                case 's':
                case 'S':
                    System.out.println("Please enter a filename:");
                    String newFileName = sc.nextLine();
                    try{
                    outputFXComponentTree(tree,newFileName);
                    System.out.println("File saved.");}
                    catch (Exception e){
                        System.out.println("Invalid. Please try again.");
                    }
                    break;
                default: System.out.println("Invalid input. Try again please.");
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
        System.out.println("Make like a tree and leave!");
    }
    public static FXComponentTree callFXComponentTree(String filename){
        return FXComponentTree.readFromFile(filename);
    }
    public static void outputFXComponentTree(FXComponentTree tree, String filename) throws IOException {
        FXComponentTree.writeToFile(tree,filename);
    }
}
