package kz.dkadirbekov.examples.datastructure.tree.binary.search;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by dkadirbekov on 21.08.2016.
 */
public class AVLTree<T> extends BinarySearchTree<T> {

  enum Rotation {
    LEFT,
    RIGHT
  }

  @Override
  public Node add(Comparable data) {
    return super.add(data);
  }

  @Override
  public Node add(Comparable data, Node node) {
    Node newNode = super.add(data, node);
    Node imbalancedNode = this.getImbalancedNode(newNode);
    if (imbalancedNode != null) {
      System.out.println("Before balance");
      try {
        this.print();
      } catch (IOException e) {
        e.printStackTrace();
      }
      this.balance(imbalancedNode);
    }
    return newNode;
  }

  private Node getImbalancedNode(Node node) {
    Node currentNode = node;

    while (currentNode != null) {
      int balance = this.countBalance(currentNode);
      currentNode.setBalance(balance);
      if (Math.abs(balance) > 1) {
        return currentNode;
      }

      currentNode = currentNode.getParent();
    }

    return null;
  }

  private void balance(Node node) {
    balance(node, new LinkedList<Rotation>());
  }

  private void balance(Node node, LinkedList<Rotation> rotations) {
    int balance = node.getBalance();

    if (rotations.size() < 2) {
      if (balance > 0) {
        if (rotations.isEmpty() || !rotations.peek().equals(Rotation.RIGHT)) {
          rotations.addFirst(Rotation.RIGHT);
          balance(node.getLeft(), rotations);
          return;
        }
      } else if (balance < 0) {
        if (rotations.isEmpty() || !rotations.peek().equals(Rotation.LEFT)) {

          rotations.addFirst(Rotation.LEFT);
          balance(node.getRight(), rotations);
          return;
        }
      }
    }

    while (!rotations.isEmpty()) {
      Rotation rotation = rotations.removeFirst();
      try {
        System.out.println("Before rotation");
        this.print();
        rotate(node, rotation);
        System.out.println("After rotation");
        this.print();
        System.out.println();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  private void rotate(Node node, Rotation rotation) {
    Node previousParent = node.getParent();
    Node absoluteParent = previousParent.getParent();

    Node child = null;
    if (rotation.equals(Rotation.LEFT)) {
      child = node.getLeft();
    } else {
      child = node.getRight();
    }

    node.setParent(absoluteParent);
    if (absoluteParent != null) {
      if (node.getValue().compareTo((T) absoluteParent.getValue()) < 0) {
        absoluteParent.setLeft(node);
      } else {
        absoluteParent.setRight(node);
      }
    } else {
      setNode(node);
    }

    if (rotation.equals(Rotation.LEFT)) {
      node.setLeft(previousParent);
    } else {
      node.setRight(previousParent);
    }

    previousParent.setParent(node);

    if (rotation.equals(Rotation.LEFT)) {
      previousParent.setRight(child);
    } else {
      previousParent.setLeft(child);
    }

    if (child != null) {
      child.setParent(previousParent);
    }
  }

  private int countBalance(Node node) {
    int leftHeight = this.getHeight(node.getLeft());
    int rightHeight = this.getHeight(node.getRight());

    return leftHeight - rightHeight;
  }

}
