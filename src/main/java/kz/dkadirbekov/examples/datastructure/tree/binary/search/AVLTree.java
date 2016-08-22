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
      this.balance(imbalancedNode);
    }
    return newNode;
  }

  @Override
  public void remove(Comparable<T> comparable) {
    remove(getNode(), comparable);
  }

  /**
   * {@inheritDoc}
   * Also balance tree
   */
  @Override
  public Node remove(Node node, Comparable<T> comparable) {
    Node replacedWith = super.remove(node, comparable);

    if (getNode() != null) {
      Node imbalancedNode = this.findImbalancedNode(getNode());

      if (imbalancedNode != null) {
        this.balance(node);
      }

    }
    return replacedWith;
  }

  private Node findImbalancedNode(Node node) {
    if (node.getLeft() != null) {
      Node left = findImbalancedNode(node.getLeft());
      if (left != null) {
        return left;
      }
    }
    if (node.getRight() != null) {
      Node right = findImbalancedNode(node.getRight());
      if (right != null) {
        return right;
      }
    }
    node.setBalance(this.countBalance(node));
    if (Math.abs(node.getBalance()) > 1) {
      return node;
    } else {
      return null;
    }
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
    if (node == null) {
      return;
    }

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
      rotate(node, rotation);
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
