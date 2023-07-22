/**
 * <h1>FXTreeNode</h1>
 * The FXTreeNode class includes the information of the node of FXTreeNode.
 * <p>
 * <b>Note:</b> FXTreeNode class includes parent and child references, the number of max children and
 * the information of type and text.
 *
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-03
 */
public class FXTreeNode {
    private String text="";
    final int maxChildren=10;
    private ComponentType type = ComponentType.AnchorPane;
    private FXTreeNode parent;
    private FXTreeNode[] children = new FXTreeNode[maxChildren];
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public ComponentType getType() {
        return type;
    }
    public void setType(ComponentType type) {
        this.type = type;
    }
    public FXTreeNode getParent() {
        return parent;
    }
    public void setParent(FXTreeNode parent) {
        this.parent = parent;
    }
    public FXTreeNode[] getChildren() {
        return children;
    }
    public void setChildren(FXTreeNode[] children) {
        this.children = children;
    }
    public String toString(){
        return "" + type +": "+text;
    }
    public int getMaxChildren() {
        return maxChildren;
    }
}
