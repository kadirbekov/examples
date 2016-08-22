package kz.dkadirbekov.examples.algorithm.graph.search;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dkadirbekov on 19.08.2016.
 */
public interface AStar extends SearchAlgorithm {
  void setHeuristicValues(List<BigDecimal> heuristicValues);
}
