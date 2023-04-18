/**
 * The StoryTreeNode class represents a node in a story tree.
 * Each node has a position, an option, and a message associated with it.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class StoryTreeNode {
    /**
     * Constant for win message.
     */
    static final String WIN_MESSAGE = "YOU WIN";

    /**
     * Constant for lose message.
     */
    static final String LOSE_MESSAGE = "YOU LOSE";

    /**
     * The position of this node in the story tree.
     */
    String position;

    /**
     * The option associated with this node.
     */
    String option;

    /**
     * The message associated with this node.
     */
    String message;

    /**
     * The left child of this node.
     */
    StoryTreeNode leftChild;

    /**
     * The middle child of this node.
     */
    StoryTreeNode middleChild;

    /**
     * The right child of this node.
     */
    StoryTreeNode rightChild;

    /**
     * Constructs a new StoryTreeNode with no children.
     */
    public StoryTreeNode() {
        this.leftChild = null;
        this.rightChild = null;
        this.middleChild = null;

    }


    /**
     * Constructs a new StoryTreeNode with the given position, option, and message, and no children.
     *
     * @param position the position of this node in the story tree
     * @param option the option associated with this node
     * @param message the message associated with this node
     */
    public StoryTreeNode(String position, String option, String message) {
        this.position = position;
        this.option = option;
        this.message = message;
        this.leftChild = null;
        this.rightChild = null;
        this.middleChild = null;
    }


    /**
     * Checks if this node is a leaf node.
     *
     * @return true if this node is a leaf node, false otherwise
     */
    public boolean isLeaf() {
        return leftChild == null && middleChild == null && rightChild == null;
    }


    /**
     * Checks if this node is a winning node.
     *
     * @return true if this node is a leaf node and its message contains the WIN_MESSAGE constant, false otherwise
     */
    public boolean isWinningNode() {
        return isLeaf() && message.contains(WIN_MESSAGE);
    }


    /**
     * Checks if this node is a losing node.
     *
     * @return true if this node is a leaf node and its message contains the LOSE_MESSAGE constant, false otherwise
     */
    public boolean isLosingNode() {
        return isLeaf() && message.contains(LOSE_MESSAGE);
    }

    /**
     * Gets the position of this node.
     *
     * @return the position of this node
     */
    String getPosition() {
        return position;
    }

    /**
     * Gets the option associated with this node.
     *
     * @return the option associated with this node
     */
    String getOption() {
        return option;
    }

    /**
     * Gets the message associated with this node.
     *
     * @return the message associated with this node
     */
    String getMessage() {
        return message;
    }

    /**
     * Sets the position of this node.
     *
     * @param newPosition the new position of this node
     */
    void setPosition(String newPosition) {
        position = newPosition;
    }

    /**
     * Sets the options of this node.
     *
     * @param newOption the new option of this node
     */
    void setOption(String newOption) {
        option = newOption;
    }

    /**
     * Sets the message of this node.
     *
     * @param newMessage the new message of this node
     */
    void setMessage(String newMessage) {
        message = newMessage;
    }

    /**
     * Gets the left child node of this node.
     * @return the left child node of this node
     */
    StoryTreeNode getLeftChild() {
        return leftChild;
    }

    /**
     * Gets the middle child node of this node.
     * @return the middle child node of this node
     */
    StoryTreeNode getMiddleChild() {
        return middleChild;
    }

    /**
     * Gets the right child node of this node.
     * @return the right child node of this node
     */
    StoryTreeNode getRightChild() {
        return rightChild;
    }

    /**
     * Sets the left child node of this node.
     * @param newNode the new left child node for this node
     */
    public void setLeftChild(StoryTreeNode newNode) {
        leftChild = newNode;
        if (leftChild != null) {
            leftChild.setPosition(position + "-1");
        }
    }

    /**
     * Sets the middle child node of this node.
     * @param newNode the new middle child node for this node
     */
    public void setMiddleChild(StoryTreeNode newNode) {
        middleChild = newNode;
        if (middleChild != null) {
            middleChild.setPosition(position + "-2");
        }
    }

    /**
     * Sets the right child node of this node.
     * @param newNode the new right child node for this node
     */
    public void setRightChild(StoryTreeNode newNode) {
        rightChild = newNode;
        if (rightChild != null) {
            rightChild.setPosition(position + "-3");
        }
    }

    /**
     * This method counts the number of children of the current StoryTree cursor
     * @return An integer representing the number of non-null children
     */
    public int countChildren() {
        int num=0;
        StoryTreeNode[] child = {StoryTree.cursor.getLeftChild(), StoryTree.cursor.getMiddleChild(), StoryTree.cursor.getRightChild()};
        for (int i =0;i<child.length;i++){
            if(child[i]!=null){
                num++;
            }
        }
        return num;
    }
}
