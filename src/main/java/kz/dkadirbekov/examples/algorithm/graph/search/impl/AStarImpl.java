package kz.dkadirbekov.examples.algorithm.graph.search.impl;

import kz.dkadirbekov.examples.algorithm.graph.search.AStar;
import kz.dkadirbekov.examples.datastructure.graph.Graph;
import kz.dkadirbekov.examples.datastructure.graph.Path;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dkadirbekov on 19.08.2016.
 */
public class AStarImpl implements AStar {

  class PathElement implements Comparable<PathElement> {
    BigDecimal actualCost;
    BigDecimal heuristicValue;
    Integer previousIndex;
    Boolean isVisited = Boolean.FALSE;

    public PathElement(BigDecimal actualCost,
                       BigDecimal heuristicValue,
                       Integer previousIndex,
                       Boolean isVisited) {
      this.actualCost = actualCost;
      this.heuristicValue = heuristicValue;
      this.previousIndex = previousIndex;
      this.isVisited = false;
    }

    @Override
    public int compareTo(PathElement o) {
      return actualCost.add(heuristicValue).compareTo(o.actualCost.add(o.heuristicValue));
    }
  }

  private List<BigDecimal> heuristicValues;

  @Override
  public Path findShortestPath(Graph graph, int sourceIndex, int targetIndex) {
    List<PathElement> vertexCosts = this.initializeVertexCostsList(graph);

    int currentIndex = sourceIndex;
    vertexCosts.get(currentIndex).actualCost = BigDecimal.ZERO;

    while (true) {
      vertexCosts.get(currentIndex).isVisited = true;

      List<Integer> neighbourIndexes = graph.getIndexesOfNeighbourVertices(currentIndex);
      for (Integer neighbourIndex : neighbourIndexes) {
        BigDecimal edgeCost = graph.getEdgeCost(currentIndex, neighbourIndex);
        vertexCosts.set(neighbourIndex, min(
            new PathElement(
                vertexCosts.get(currentIndex).actualCost.add(edgeCost),
                vertexCosts.get(neighbourIndex).heuristicValue,
                currentIndex,
                false),
            vertexCosts.get(neighbourIndex)));
      }

      Integer lowestUnvisitedIndex = this.getLowestUnvisitedElementIndex(vertexCosts);
      if (lowestUnvisitedIndex == null || lowestUnvisitedIndex == targetIndex) {
        break;
      }
      currentIndex = lowestUnvisitedIndex;
    }

    return this.getPath(vertexCosts, sourceIndex, targetIndex);
  }

  private Path getPath(List<PathElement> vertexCosts, int sourceIndex, int targetIndex) {
    LinkedList<Integer> verticesIndexes = new LinkedList<>();

    int currentIndex = targetIndex;
    while (currentIndex != sourceIndex) {
      verticesIndexes.addFirst(currentIndex);

      PathElement pathElement = vertexCosts.get(currentIndex);
      currentIndex = pathElement.previousIndex;
    }
    verticesIndexes.addFirst(sourceIndex);

    Path path = new Path();
    path.setCost(vertexCosts.get(targetIndex).actualCost);
    path.setVerticeIndexes(verticesIndexes);

    return path;
  }


  private Integer getLowestUnvisitedElementIndex(List<PathElement> vertexCosts) {
    Integer lowestIndex = null;

    for (int i = 0; i < vertexCosts.size(); i++) {
      if (vertexCosts.get(i).isVisited) {
        continue;
      }

      if (lowestIndex == null) {
        lowestIndex = i;
      }

      if (vertexCosts.get(i).compareTo(vertexCosts.get(lowestIndex)) < 0) {
        lowestIndex = i;
      }
    }

    return lowestIndex;
  }

  private PathElement min(PathElement a, PathElement b) {
    if (a.actualCost.compareTo(b.actualCost) < 0) {
      return a;
    } else {
      return b;
    }
  }

  private List<PathElement> initializeVertexCostsList(Graph graph) {
    List<PathElement> vertexCosts = new ArrayList<>();

    for (int i = 0; i < graph.getNumberOfVertices(); i++) {
      vertexCosts.add(new PathElement(
          BigDecimal.valueOf(Double.MAX_VALUE),
          heuristicValues.get(i),
          null,
          false));
    }

    return vertexCosts;
  }

  @Override
  public void setHeuristicValues(List<BigDecimal> heuristicValues) {
    this.heuristicValues = heuristicValues;
  }
}
