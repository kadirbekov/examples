package kz.dkadirbekov.examples.algorithm.sort;

import java.util.List;

/**
 * Created by dkadirbekov on 20.08.2016.
 */
public interface MergeSort<T> extends SortAlgorithm<T> {

  /**
   * Sorts {@link List} in {@code order}
   *
   * @param list  {@link List} to sort
   * @param order {@link Order} to sort
   * @return sorted in {@code order} {@link List}
   */
  List<Comparable<T>> sort(List<Comparable<T>> list, Order order);

}
