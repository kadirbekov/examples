package kz.dkadirbekov.examples.datastructure.graph;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by dkadirbekov on 16.08.2016.
 */
public class Path {

  private LinkedList<Integer> verticeIndexes;
  private BigDecimal cost;

  public LinkedList<Integer> getVerticeIndexes() {
    return verticeIndexes;
  }

  public void setVerticeIndexes(LinkedList<Integer> verticeIndexes) {
    this.verticeIndexes = verticeIndexes;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }
}
