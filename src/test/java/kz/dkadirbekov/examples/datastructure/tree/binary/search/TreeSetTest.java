package kz.dkadirbekov.examples.datastructure.tree.binary.search;

import org.junit.Test;

import java.util.TreeSet;

/**
 * Created by dkadirbekov on 19.08.2016.
 */
public class TreeSetTest {

  @Test
  public void testTreeSet() {
    TreeSet<Long> treeSet = new TreeSet<>();
    treeSet.add(123L);
    treeSet.add(23l);
    treeSet.add(234253L);
    treeSet.add(46123L);
    treeSet.add(55L);
    treeSet.add(23423L);
    treeSet.add(45L);
    treeSet.add(17L);

    System.out.println("");
  }
}
