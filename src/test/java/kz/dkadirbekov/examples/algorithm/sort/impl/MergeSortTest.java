package kz.dkadirbekov.examples.algorithm.sort.impl;

import kz.dkadirbekov.examples.algorithm.sort.MergeSort;
import kz.dkadirbekov.examples.algorithm.sort.QuickSort;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

/**
 * Created by dkadirbekov on 20.08.2016.
 */
public class MergeSortTest {

  @Test
  public void testSort() throws FileNotFoundException {
    String fileName = this.getClass().getClassLoader().getResource("sort/sort-2.txt").getPath();
    Scanner scanner = new Scanner(new File(fileName));
    List<BigDecimal> list = new ArrayList<>();
    while (scanner.hasNext()) {
      list.add(scanner.nextBigDecimal());
    }

    MergeSort sort = new MergeSortImpl();
    sort.sort(list, QuickSort.Order.ASCENDING);
    for (int i = 1; i < list.size(); i++) {
      assertTrue(list.get(i).compareTo(list.get(i - 1)) > 0);
    }

  }

}
