package kz.dkadirbekov.examples.algorithm.sort;

import java.util.List;

/**
 * Created by dkadirbekov on 17.08.2016.
 */
public interface QuickSort<T> extends SortAlgorithm {

  /**
   * Sorts elements of list in the given {@link Order}
   *
   * @param list  list of elements need to sort
   * @param order {@link Order} of sorting
   */
  void sort(List<Comparable<T>> list, Order order);

}
