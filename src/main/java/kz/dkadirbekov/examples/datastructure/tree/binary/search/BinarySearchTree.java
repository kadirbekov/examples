package kz.dkadirbekov.examples.datastructure.tree.binary.search;

import kz.dkadirbekov.examples.datastructure.tree.binary.BinaryTree;

/**
 * Created by dkadirbekov on 18.08.2016.
 */
public class BinarySearchTree<T> extends BinaryTree<T> {

  public BinarySearchTree() {
  }

  @Override
  public Node add(Comparable data) {
    if (getNode() != null) {
      return this.add(data, getNode());
    } else {
      Node node = new Node(data, null);
      setNode(node);
      return node;
    }
  }

  public Node add(Comparable data, Node node) {
    if (node.getValue().compareTo((T) data) > 0) {
      if (node.getLeft() == null) {
        Node newNode = new Node((Comparable<T>) data, node);
        node.setLeft(newNode);
        return newNode;
      } else {
        return this.add(data, node.getLeft());
      }
    } else {
      if (node.getRight() == null) {
        Node newNode = new Node((Comparable<T>) data, node);
        node.setRight(newNode);
        return newNode;
      } else {
        return this.add(data, node.getRight());
      }
    }
  }

}
