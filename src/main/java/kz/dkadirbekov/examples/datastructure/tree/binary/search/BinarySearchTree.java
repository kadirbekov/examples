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

  @Override
  public void remove(Comparable<T> data) {
    remove(getNode(), data);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node remove(Node node, Comparable<T> comparable) {
    if (node == null) {
      throw new IllegalArgumentException("There is no node with data = " + comparable.toString());
    }

    if (comparable.compareTo((T) node.getValue()) < 0) {
      return remove(node.getLeft(), comparable);
    } else if (comparable.compareTo((T) node.getValue()) > 0) {
      return remove(node.getRight(), comparable);
    } else if (node.getLeft() == null && node.getRight() == null) {
      free(node);
      return null;
    } else if (getHeight(node.getLeft()) > getHeight(node.getRight())) {
      Node maxNode = removeMax(node.getLeft());
      replace(node, maxNode);
      return maxNode;
    } else {
      Node minNode = removeMin(node.getRight());
      replace(node, minNode);
      return minNode;
    }
  }

  /**
   * Replaces the {@link Node} by second {@link Node}
   *
   * @param replaceThe {@link Node} that is going to be replaced
   * @param replaceBy  {@link Node} that is going to be replaced by
   * @return {@link Node} replaced by
   */
  protected Node replace(Node replaceThe, Node replaceBy) {
    Node parent = replaceThe.getParent();
    if(parent == null) {
      setNode(replaceBy);
    }
    replaceBy.setLeft(replaceThe.getLeft());
    replaceBy.setRight(replaceThe.getRight());
    replaceBy.setParent(parent);

    if (parent != null) {
      if (replaceBy.getValue().compareTo((T) parent.getValue()) < 0) {
        parent.setLeft(replaceBy);
      } else {
        parent.setRight(replaceBy);
      }
    }
    if(replaceBy.getLeft() != null) {
      replaceBy.getLeft().setParent(replaceBy);
    }
    if(replaceBy.getRight() != null) {
      replaceBy.getRight().setParent(replaceBy);
    }

    return replaceBy;
  }

  protected Node removeMin(Node node) {
    if (node.getLeft() != null) {
      return removeMin(node.getLeft());
    } else {
      free(node);
      return node;
    }
  }

  protected Node removeMax(Node node) {
    if (node.getRight() != null) {
      return removeMax(node.getRight());
    } else {
      free(node);
      return node;
    }
  }

  protected void free(Node node) {
    if(getNode().equals(node)) {
      setNode(null);
    }
    if (node.getParent() != null) {
      if (node.getValue().compareTo((T) node.getParent().getValue()) < 0) {
        if (node.getParent().getLeft().equals(node)) {
          node.getParent().setLeft(null);
        }
      } else if (node.getValue().compareTo((T) node.getParent().getValue()) > 0) {
        if (node.getParent().getRight().equals(node)) {
          node.getParent().setRight(null);
        }
      }
    }
    node.setParent(null);
  }

}
