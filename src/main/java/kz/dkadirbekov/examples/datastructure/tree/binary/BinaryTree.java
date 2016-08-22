package kz.dkadirbekov.examples.datastructure.tree.binary;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by dkadirbekov on 18.08.2016.
 */
public abstract class BinaryTree<T> {

  Node node;

  /**
   * Adds new {@link Node} with {@code data} to tree
   *
   * @param data data of new {@link Node}
   * @return new {@link Node}
   */
  public abstract Node add(Comparable<T> data);

  public int getHeight(Node node) {
    return this.getHeight(node, 1);
  }

  private int getHeight(Node node, int currentHeight) {
    if (node == null) {
      return currentHeight - 1;
    }
    int leftHeight = currentHeight;
    int rightHeight = currentHeight;
    if (node.getLeft() != null) {
      leftHeight = getHeight(node.getLeft(), currentHeight + 1);
    }
    if (node.getRight() != null) {
      rightHeight = getHeight(node.getRight(), currentHeight + 1);
    }

    return Math.max(leftHeight, rightHeight);
  }

  public class Node implements Comparable<Node> {
    Node left;
    Node right;
    Comparable<T> value;
    Integer balance;
    Node parent;

    public Node(Comparable<T> value, Node parent) {
      this.value = value;
      this.parent = parent;
    }

    public Node getLeft() {
      return left;
    }

    public void setLeft(Node left) {
      this.left = left;
    }

    public Node getRight() {
      return right;
    }

    public void setRight(Node right) {
      this.right = right;
    }

    public Comparable<T> getValue() {
      return value;
    }

    public void setValue(Comparable<T> value) {
      this.value = value;
    }

    public Integer getBalance() {
      return balance;
    }

    public void setBalance(Integer balance) {
      this.balance = balance;
    }

    public Node getParent() {
      return parent;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }

    public void printTree(OutputStreamWriter out) throws IOException {
      if (right != null) {
        right.printTree(out, true, "");
      }
      printNodeValue(out);
      if (left != null) {
        left.printTree(out, false, "");
      }
    }

    private void printNodeValue(OutputStreamWriter out) throws IOException {
      if (value == null) {
        System.out.print("<null>");
        out.write("<null>");
      } else {
        System.out.print(value.toString());
        out.write(value.toString());
      }
      System.out.print('\n');
      out.write('\n');
    }

    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
      if (right != null) {
        right.printTree(out, true, indent + (isRight ? "        " : " |      "));
      }
      System.out.print(indent);
      out.write(indent);
      if (isRight) {
        System.out.print(" /");
        out.write(" /");
      } else {
        System.out.print(" \\");
        out.write(" \\");
      }
      System.out.print("----- ");
      out.write("----- ");
      printNodeValue(out);
      if (left != null) {
        left.printTree(out, false, indent + (isRight ? " |      " : "        "));
      }
    }

    @Override
    public int compareTo(Node o) {
      return getValue().compareTo((T) o.getValue());
    }
  }

  public BinaryTree() {
  }

  protected Node getNode() {
    return node;
  }

  protected void setNode(Node node) {
    this.node = node;
  }

  public void print() throws IOException {
    node.printTree(new OutputStreamWriter(System.out));
  }

}
