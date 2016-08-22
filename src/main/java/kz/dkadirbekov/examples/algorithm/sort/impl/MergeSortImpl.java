package kz.dkadirbekov.examples.algorithm.sort.impl;

import kz.dkadirbekov.examples.algorithm.sort.MergeSort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkadirbekov on 20.08.2016.
 */
public class MergeSortImpl<T> implements MergeSort<T> {

  /**
   * {@inheritDoc}
   *
   * @param list  {@link List} to sort
   * @param order {@link Order} to sort
   * @return
   */
  @Override
  public List<Comparable<T>> sort(List<Comparable<T>> list, Order order) {
    if (order.equals(Order.DESCENDING)) {
      throw new UnsupportedOperationException("Not implemented");
    }

    if (list.size() == 1) {
      return list;
    }

    int pivotIndex = (list.size() / 2);
    List<Comparable<T>> firstPart = new ArrayList<>(list.subList(0, pivotIndex));
    List<Comparable<T>> secondPart = new ArrayList<>(list.subList(pivotIndex, list.size()));
    firstPart = sort(firstPart, order);
    secondPart = sort(secondPart, order);

    int firstIndex = 0;
    int secondIndex = 0;
    int mergeIndex = 0;

    while (firstIndex < firstPart.size()
        && secondIndex < secondPart.size()) {
      if (firstPart.get(firstIndex).compareTo((T) secondPart.get(secondIndex)) <= 0) {
        list.set(mergeIndex++, firstPart.get(firstIndex++));
      } else {
        list.set(mergeIndex++, secondPart.get(secondIndex++));
      }
    }

    while (firstIndex < firstPart.size()) {
      list.set(mergeIndex++, firstPart.get(firstIndex++));
    }

    while (secondIndex < secondPart.size()) {
      list.set(mergeIndex++, secondPart.get(secondIndex++));
    }

    return list;
  }

}
