import java.io.*;
import java.util.*;
/**
 * <h1>FXComponentTree</h1>
 * The FXComponentTree class serves as the tree manager.
 * <p>
 * <b>Note:</b> FXComponentTree has methods that are able to generate and save the tree to and from a file.
 * It holds root and cursor references.
 *
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-03
 */
public class FXComponentTree {
    private FXTreeNode root = new FXTreeNode();
    private FXTreeNode cursor = root;
    public FXTreeNode getRoot() {
        return root;
    }
    public FXTreeNode getCursor() {
        return cursor;
    }
    public void setRoot(FXTreeNode root) {
        this.root = root;
    }
    public void setCursor(FXTreeNode cursor) {
        this.cursor = cursor;
    }
    /**
     * This method is used to put cursor to root.
     */
    public void cursorToRoot(){
        cursor = root;
        System.out.println("Cursor is at root.");
    }
    /**
     * This method is used to removes the child at the specified index of the FXComponentTree.
     *
     * @param index This is the index of the children array of the cursor.
     */
    public void deleteChild(int index){
        if(index>cursor.maxChildren){
            System.out.println("Invalid input. Please try again.");
            return;
        }
        if(cursor.getChildren()[index-1]==null){
            System.out.println("Invalid. The child of index is not exist.");
            return;
        }
        FXTreeNode[] newChild = new FXTreeNode[cursor.maxChildren];
        for (int i=0, j=0; i<cursor.maxChildren;i++){
            if (index-1 == i){
                continue;
            }
            newChild[j++] = cursor.getChildren()[i];
        }
        System.out.println(cursor.getChildren()[index-1].getType()+
                " "+cursor.getChildren()[index-1].getText()+" removed.");
        cursor.setChildren(newChild);
    }
    /**
     * This method is used to adds the given node to the corresponding index of the children array.
     *
     * @param index This is the index of the children array of the cursor.
     * @param node This is the new node that can be added to the corresponding index of the children array.
     * @exception Exception Throws exception if the node at the specified index makes a hole in the array.
     */
    public void addChild(int index, FXTreeNode node) throws Exception {
        if(index>cursor.maxChildren){
            System.out.println("Invalid input. Please try again.");
            return;
        }
        boolean isFull = true;
        for (int i=0; i<cursor.maxChildren;i++){
            if(cursor.getChildren()[i]==null){
                isFull = false;
                break;
            }
        }
        if (isFull) {
            System.out.println("Invalid. The children of the cursor is full.");
            return;
        }
        if(cursor == root){
            if(index != 1){
                System.out.println("Invalid. Root can only have one child.");
                return;
            }
            else if(cursor.getChildren()[0]!=null){
                System.out.println("Invalid. Root can only have one child.");
                return;
            }
        }
        FXTreeNode[] newChild = new FXTreeNode[cursor.maxChildren];
        if(cursor.getChildren()[index-1]==null){
            for (int i=0; i<cursor.maxChildren;i++){
                if(cursor.getChildren()[i]==null){
                    if(i != index-1) throw new Exception();
                    node.setParent(cursor);
                    cursor.getChildren()[i]=node;
                    break;
                }
            }
        }
        else {for (int i=0, j=0; i<cursor.maxChildren;i++){
            if (index-1 == i){
                node.setParent(cursor);
                newChild[i] = node;
                continue;
            }
            newChild[i] = cursor.getChildren()[j++];
        }
        cursor.setChildren(newChild);}
        cursor = cursor.getChildren()[index-1];
        System.out.println("Finish.");
    }
    /**
     * This method is used to sets the current node’s text to the specified text.
     *
     * @param text This is the string that can be set to the cursor's text.
     */
    public void setTextAtCursor(String text){
        if(cursor.getType().equals(ComponentType.AnchorPane)||cursor.getType().equals(ComponentType.VBox)
        ||cursor.getType().equals(ComponentType.HBox)){
            System.out.println(cursor.getType()+" can not have test. Please try again.");
            return;
        }
        cursor.setText(text);
        System.out.println("Text Edited.");
    }
    /**
     * This method is used to moves the cursor to the child node of the of the cursor corresponding
     * to the specified index.
     *
     * @param index This is the index of the children array of the cursor.
     */
    public void cursorToChild(int index){
        if(index>cursor.maxChildren){
            System.out.println("Invalid input. Please try again.");
            return;
        }
        if(cursor.getChildren()[index-1]==null){
            System.out.println("Invalid input. Please try again.");
            return;
        }
        cursor = cursor.getChildren()[index-1];
        System.out.println("Cursor Moved to "+cursor.getType()+cursor.getText()+".");
    }
    /**
     * This method is used to moves the cursor to the parent of the current node.
     */
    public void cursorToParent(){
        if(cursor.getParent()==null){
            System.out.println("The cursor is at root. Please try again.");
            return;
        }
        cursor = cursor.getParent();
        System.out.println("Cursor Moved to "+cursor.getType()+cursor.getText()+".");
    }
    /**
     * This method is used to generates the FXComponentTree based on the file name that is passed in.
     *
     * @param filename This is string that pass in the file name.
     * @return FXComponentTree This return the FXComponentTree that converted from file.
     */
    public static FXComponentTree readFromFile(String filename){
        FXComponentTree tree = new FXComponentTree();
        try{
        File file = new File(filename);
        tree = new FXComponentTree();
        Scanner scFile = new Scanner(file);
        Scanner check = new Scanner(file);
        int count = 0;
        while (check.hasNextLine()){
            String line = check.nextLine();
            String number = line.substring(0,line.indexOf(" "));
            if (number.length()>count){count=number.length();}
        }
        while (scFile.hasNextLine()) {
            String line = scFile.nextLine();
            String number = line.substring(0,line.indexOf(" "));
            String left = line.substring(line.indexOf(" ")+1);
            String type="AnchorPane";
            String text="";
            try{
            type = left.substring(0,left.indexOf(" "));
            text = left.substring(left.indexOf(" ")+1);}
            catch (Exception e){
                type = left.substring(left.indexOf(" ")+1);
            }
            if(number.length()==1) {
                tree.getRoot().setType(ComponentType.valueOf(type));
                tree.setCursor(tree.getRoot());
                tree.getCursor().setText(text);
            }
            for(int n = 3;n<=count;n+=2){
                if(number.length() == n){
                    tree.setCursor(tree.getRoot());
                    for(int i = 3; i<=n;i+=2) {
                        int num = number.charAt(i-1) - '0';
                        if (tree.getCursor().getChildren()[num]==null){
                            tree.getCursor().getChildren()[num] = new FXTreeNode();
                        }
                        tree.getCursor().getChildren()[num].setParent(tree.getCursor());
                        tree.setCursor(tree.getCursor().getChildren()[num]);
                    }
                    tree.getCursor().setType(ComponentType.valueOf(type));
                    tree.getCursor().setText(text);
                }
            }
        }
            scFile.close();
            System.out.println(filename + " loaded");
            tree.setCursor(tree.getRoot());
        }
        catch (Exception e){System.out.println(filename + " No Found");}
        return tree;
    }
    /**
     * This method is used to generates a text file that reflects the structure of the FXComponentTree.
     *
     * @param tree This is the FXComponentTree that is reflected.
     * @param filename This is string that reflects the file name of the text file.
     * @exception IOException Throw exception if there is failed or interrupted I/O operations.
     */
    public static void writeToFile(FXComponentTree tree, String filename) throws IOException {
        String fileContent = "";
        FileWriter fw = new FileWriter("src/"+filename);
        fileContent += "0 "+tree.getRoot().getType();
        if (!tree.getRoot().getText().equals("")){
            fileContent += " "+tree.getRoot().getText();
        }
        fileContent += "\n";
        fileContent += fileContent(tree.getRoot().getChildren()[0], 0,"0");
        fw.write(fileContent);
        fw.close();
    }
    /**
     * This method is used to provide the file content that can be used in writeToFile method.
     *
     * @param n This is a FXTreeNode that used to be converted to file content.
     * @param index This is the index of the children array of the cursor.
     * @param number This is the String that includes the information of the position in tree.
     * @return String This returns the file content that can be converted to file.
     */
    public static String fileContent(FXTreeNode n, int index, String number){
        String str = "";
        if(n != null){
            number += "-"+index;
            str += number+" "+n.getType();
            if (!n.getText().equals("")){
                str += " "+n.getText();
            }
            str += "\n";
            for (int i =0;i< n.maxChildren;i++){
                str += fileContent(n.getChildren()[i],i,number);
            }
        }
        return str;
    }
    /**
     * This method is used to print the FXComponentTree.
     *
     * @param n This is a FXTreeNode that this method begin to print.
     * @param count This is the number of how many "   " should be printed.
     */
    public void toString(FXTreeNode n,int count){
            if(n != null) {
                for (int i = 0;i<count;i++){
                    System.out.print("   ");}
                count++;
                if(n == cursor){
                System.out.println("==>"+n.toString());}
                else if(count == 0) System.out.println(n.toString());
                else if(count == 1) System.out.println("--+"+n.toString());
                else System.out.println("+--"+n.toString());
                for (int i=0 ; i<root.getMaxChildren();i++){
                    toString(n.getChildren()[i],count);}
        }
    }
}
