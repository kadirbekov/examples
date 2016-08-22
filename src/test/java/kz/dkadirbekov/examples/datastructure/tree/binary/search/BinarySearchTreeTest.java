package kz.dkadirbekov.examples.datastructure.tree.binary.search;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by dkadirbekov on 18.08.2016.
 */
public class BinarySearchTreeTest {

  @Test
  public void addNode() {
    BinarySearchTree<BigDecimal> binarySearchTree = new BinarySearchTree<>();
    binarySearchTree.add(BigDecimal.valueOf(10));
  }
}
