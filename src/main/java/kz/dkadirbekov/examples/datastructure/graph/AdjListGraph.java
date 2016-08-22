package kz.dkadirbekov.examples.datastructure.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dkadirbekov on 16.08.2016.
 */
public class AdjListGraph extends Graph {

  List<Vertex> vertices;
  GraphType graphType;

  class Vertex {
    LinkedList<Edge> adjList;

    public Vertex() {
      adjList = new LinkedList<Edge>();
    }

  }

  class Edge {
    int targetVertexIndex;
    BigDecimal cost;

    public Edge(int targetVertexIndex, BigDecimal cost) {
      this.targetVertexIndex = targetVertexIndex;
      this.cost = cost;
    }
  }

  public AdjListGraph(String fileName, GraphType graphType) throws FileNotFoundException {
    this.graphType = graphType;

    Scanner scanner = new Scanner(new File(fileName));
    int numberOfVertices = scanner.nextInt();
    int numberOfEdges = scanner.nextInt();

    vertices = new ArrayList<>(numberOfVertices);
    for (int i = 0; i < numberOfVertices; i++) {
      vertices.add(new Vertex());
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
   * @param sourceIndex index of source {@link AdjListGraph.Vertex}
   * @param targetIndex index of target {@link AdjListGraph.Vertex}
   * @param cost        cost of {@link AdjListGraph.Edge}
   */
  @Override
  public void addEdge(int sourceIndex, int targetIndex, BigDecimal cost) {
    vertices.get(sourceIndex).adjList.add(new Edge(targetIndex, cost));

    if (graphType.equals(GraphType.UNDIRECTED)) {
      vertices.get(targetIndex).adjList.add(new Edge(sourceIndex, cost));
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param sourceIndex index of source {@link AdjListGraph.Vertex}
   * @param targetIndex index of target {@link AdjListGraph.Vertex}
   * @return cost of {@link AdjListGraph.Edge}
   */
  @Override
  public BigDecimal getEdgeCost(int sourceIndex, int targetIndex) {
    for (Edge edge : vertices.get(sourceIndex).adjList) {
      if (edge.targetVertexIndex == targetIndex) {
        return edge.cost;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return
   */
  @Override
  public int getNumberOfVertices() {
    return vertices.size();
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
    for (Edge edge : vertices.get(sourceIndex).adjList) {
      if (edge.targetVertexIndex == targetIndex) {
        return true;
      }
    }
    return false;
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
    for (Edge edge : vertices.get(sourceIndex).adjList) {
      indexes.add(edge.targetVertexIndex);
    }

    return indexes;
  }

}
