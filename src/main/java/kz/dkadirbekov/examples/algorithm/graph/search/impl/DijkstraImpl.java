package kz.dkadirbekov.examples.algorithm.graph.search.impl;

import kz.dkadirbekov.examples.algorithm.graph.search.Dijkstra;
import kz.dkadirbekov.examples.datastructure.graph.Graph;
import kz.dkadirbekov.examples.datastructure.graph.Path;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dkadirbekov on 16.08.2016.
 */
public class DijkstraImpl implements Dijkstra {

  class PathElement implements Comparable<PathElement> {
    Integer index;
    BigDecimal totalCost;

    public PathElement(Integer index, BigDecimal totalCost) {
      this.index = index;
      this.totalCost = totalCost;
    }

    @Override
    public int compareTo(PathElement pathElement) {
      return totalCost.compareTo(pathElement.totalCost);
    }


  }

  /**
   * {@inheritDoc}
   *
   * @param graph       {@link Graph}
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @return
   */
  @Override
  public Path findShortestPath(Graph graph, int sourceIndex, int targetIndex) {
    int numberOfVertices = graph.getNumberOfVertices();

    List<PathElement> vertexCosts = new ArrayList<>(numberOfVertices);
    List<Boolean> isVisited = new ArrayList<>(numberOfVertices);
    for (int i = 0; i < numberOfVertices; i++) {
      vertexCosts.add(new PathElement(null, BigDecimal.valueOf(Double.MAX_VALUE)));
      isVisited.add(false);
    }

    vertexCosts.get(sourceIndex).totalCost = BigDecimal.ZERO;
    int currentIndex = sourceIndex;

    while (true) {
      isVisited.set(currentIndex, true);
      List<Integer> neighbourIndexes = graph.getIndexesOfNeighbourVertices(currentIndex);
      for (Integer neighbourIndex : neighbourIndexes) {
        vertexCosts.set(neighbourIndex,
            min(
                vertexCosts.get(neighbourIndex),
                new PathElement(
                    currentIndex,
                    vertexCosts.get(currentIndex).totalCost
                        .add(graph.getEdgeCost(currentIndex, neighbourIndex)))
            ));

      }

      Integer indexOfSmallestNonVisitedVertex =
          getIndexOfSmallestNonVisitedVertex(vertexCosts, isVisited);
      if (indexOfSmallestNonVisitedVertex == null || indexOfSmallestNonVisitedVertex == targetIndex) {
        break;
      }

      currentIndex = indexOfSmallestNonVisitedVertex.intValue();
    }

    return this.getPath(vertexCosts, sourceIndex, targetIndex);
  }

  private Path getPath(List<PathElement> vertexCosts, int sourceIndex, int targetIndex) {
    LinkedList<Integer> verticesIndexes = new LinkedList<>();

    int currentIndex = targetIndex;
    while (currentIndex != sourceIndex) {
      verticesIndexes.addFirst(currentIndex);

      PathElement pathElement = vertexCosts.get(currentIndex);
      currentIndex = pathElement.index;
    }
    verticesIndexes.addFirst(sourceIndex);

    Path path = new Path();
    path.setCost(vertexCosts.get(targetIndex).totalCost);
    path.setVerticeIndexes(verticesIndexes);

    return path;
  }

  private PathElement min(PathElement a, PathElement b) {
    if (a.compareTo(b) < 0) {
      return a;
    } else {
      return b;
    }
  }

  private Integer getIndexOfSmallestNonVisitedVertex(List<PathElement> vertexCosts,
                                                     List<Boolean> isVisited) {
    Integer smallestIndex = null;
    BigDecimal smallestValue = null;
    for (int i = 0; i < vertexCosts.size(); i++) {
      if (isVisited.get(i)) {
        continue;
      }
      if (smallestValue == null) {
        smallestValue = vertexCosts.get(i).totalCost;
        smallestIndex = i;
      } else if (smallestValue.compareTo(vertexCosts.get(i).totalCost) > 0) {
        smallestValue = vertexCosts.get(i).totalCost;
        smallestIndex = i;
      }
    }

    return smallestIndex;
  }

}
