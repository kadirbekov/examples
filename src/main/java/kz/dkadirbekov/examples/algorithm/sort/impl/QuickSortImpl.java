package kz.dkadirbekov.examples.algorithm.sort.impl;

import kz.dkadirbekov.examples.algorithm.sort.QuickSort;

import java.util.List;

/**
 * Created by dkadirbekov on 17.08.2016.
 */
public class QuickSortImpl<T> implements QuickSort<T> {

  /**
   * {@inheritDoc}
   *
   * @param list  list of elements need to sort
   * @param order
   */
  @Override
  public void sort(List<Comparable<T>> list, Order order) {
    if (order.equals(Order.DESCENDING)) {
      throw new UnsupportedOperationException("Not implemented");
    }

    int pivotIndex = 0;
    Comparable pivot = list.get(pivotIndex);
    int rightIndex = list.size() - 1;
    int leftIndex = pivotIndex + 1;

    while (leftIndex <= rightIndex) {
      boolean needToSwap = true;
      if (list.get(leftIndex).compareTo((T) pivot) < 0) {
        leftIndex++;
        needToSwap = false;
      }
      if (list.get(rightIndex).compareTo((T) pivot) > 0) {
        rightIndex--;
        needToSwap = false;
      }

      if (needToSwap) {
        Comparable buffer = list.get(leftIndex);
        list.set(leftIndex, list.get(rightIndex));
        list.set(rightIndex, buffer);
        leftIndex++;
        rightIndex--;
      }
    }

    if (pivotIndex != leftIndex - 1) {
      list.set(pivotIndex, list.get(leftIndex - 1));
      list.set(leftIndex - 1, pivot);
    }
    pivotIndex = leftIndex - 1;
    if (pivotIndex > 1) {
      sort(list.subList(0, pivotIndex), order);
    }
    if (pivotIndex < list.size() - 2) {
      sort(list.subList(pivotIndex + 1, list.size()), order);
    }
  }
}
