package kz.dkadirbekov.examples.datastructure.tree.binary.search;

import kz.dkadirbekov.examples.datastructure.tree.binary.BinaryTree;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by dkadirbekov on 21.08.2016.
 */
public class AVLTreeTest {

  @Test
  public void testAdd() throws IOException {
    BinaryTree<Integer> binaryTree = new AVLTree<>();

    binaryTree.add(21);
    binaryTree.print();
    binaryTree.add(26);
    binaryTree.print();
    binaryTree.add(30);
    binaryTree.print();
    binaryTree.add(9);
    binaryTree.print();
    binaryTree.add(4);
    binaryTree.print();
    binaryTree.add(14);
    binaryTree.print();
    binaryTree.add(28);
    binaryTree.print();
    binaryTree.add(18);
    binaryTree.print();
    binaryTree.add(15);
    binaryTree.print();
    binaryTree.add(10);
    binaryTree.print();
    binaryTree.add(2);
    binaryTree.print();
    binaryTree.add(3);
    binaryTree.print();
    binaryTree.add(7);
    binaryTree.print();

    binaryTree.remove(21);
    binaryTree.print();


  }

  @Test
  public void testRemove() throws IOException {
    BinaryTree<Integer> binaryTree = new AVLTree<>();
    binaryTree.add(1);
    binaryTree.add(3);
    binaryTree.add(5);
    binaryTree.add(7);
    binaryTree.add(9);
    binaryTree.add(12);
    binaryTree.add(13);
    binaryTree.add(15);
    binaryTree.add(17);
    binaryTree.add(19);
    binaryTree.print();

    binaryTree.remove(19);
    binaryTree.print();
    binaryTree.remove(13);
    binaryTree.print();
    binaryTree.remove(12);
    binaryTree.print();
    binaryTree.remove(15);
    binaryTree.print();
    binaryTree.remove(3);
    binaryTree.print();
    binaryTree.remove(7);
    binaryTree.print();
    binaryTree.remove(17);
    binaryTree.print();
    binaryTree.remove(5);
    binaryTree.print();
    binaryTree.remove(1);
    binaryTree.print();
    binaryTree.remove(9);
    binaryTree.print();
  }

}
