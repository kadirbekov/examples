package kz.dkadirbekov.examples.datastructure.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dkadirbekov on 16.08.2016.
 */
public class MatrixGraph extends Graph {

  BigDecimal[][] matrix;
  GraphType graphType;
  private int numberOfVertices;

  public MatrixGraph(String fileName, GraphType graphType) throws FileNotFoundException {
    this.graphType = graphType;

    Scanner scanner = new Scanner(new File(fileName));
    numberOfVertices = scanner.nextInt();
    int numberOfEdges = scanner.nextInt();

    matrix = new BigDecimal[numberOfVertices][numberOfVertices];
    for (int i = 0; i < numberOfVertices; i++) {
      for (int j = 0; j < numberOfVertices; j++) {
        if (i != j) {
          matrix[i][j] = BigDecimal.valueOf(-1);
        } else {
          matrix[i][j] = BigDecimal.ZERO;
        }
      }
    }

    for (int i = 0; i < numberOfEdges; i++) {
      int sourceIndex = scanner.nextInt();
      int targetIndex = scanner.nextInt();
      BigDecimal cost = scanner.nextBigDecimal();
      this.addEdge(sourceIndex, targetIndex, cost);
    }
  }


  /**
   * {@inheritDoc}
   *
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @param cost        cost of edge
   */
  @Override
  public void addEdge(int sourceIndex, int targetIndex, BigDecimal cost) {
    matrix[sourceIndex][targetIndex] = cost;

    if (graphType.equals(GraphType.UNDIRECTED)) {
      matrix[targetIndex][sourceIndex] = cost;
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @return
   */
  @Override
  public BigDecimal getEdgeCost(int sourceIndex, int targetIndex) {
    BigDecimal cost = matrix[sourceIndex][targetIndex];
    if (cost.compareTo(BigDecimal.valueOf(-1)) == 0) {
      return null;
    } else {
      return cost;
    }
  }

  /**
   * {@inheritDoc}
   *
   * @return
   */
  @Override
  public int getNumberOfVertices() {
    return numberOfVertices;
  }

  /**
   * {@inheritDoc}
   *
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @return
   */
  @Override
  public boolean isEdgeExists(int sourceIndex, int targetIndex) {
    if (sourceIndex == targetIndex) {
      if (!matrix[sourceIndex][targetIndex].equals(BigDecimal.ZERO)) {
        return true;
      } else {
        return false;
      }
    }
    if (matrix[sourceIndex][targetIndex].equals(BigDecimal.valueOf(-1))) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param sourceIndex index of source vertex
   * @return
   */
  @Override
  public List<Integer> getIndexesOfNeighbourVertices(int sourceIndex) {
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < numberOfVertices; i++) {
      if (isEdgeExists(sourceIndex, i)) {
        indexes.add(i);
      }
    }
    return indexes;
  }
}
