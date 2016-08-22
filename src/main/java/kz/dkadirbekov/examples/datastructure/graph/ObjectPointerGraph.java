package kz.dkadirbekov.examples.datastructure.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by dkadirbekov on 17.08.2016.
 */
public class ObjectPointerGraph extends Graph {

  List<Vertex> vertices;
  GraphType graphType;

  class Vertex {
    LinkedList<Edge> edges;

    public Vertex() {
      edges = new LinkedList<>();
    }
  }

  class Edge {
    Vertex sourceVertex;
    Vertex targetVertex;
    BigDecimal cost;

    public Edge(Vertex sourceVertex, Vertex targetVertex, BigDecimal cost) {
      this.sourceVertex = sourceVertex;
      this.targetVertex = targetVertex;
      this.cost = cost;
    }
  }

  public ObjectPointerGraph(String fileName, GraphType graphType) throws FileNotFoundException {
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
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @param cost        cost of edge
   */
  @Override
  public void addEdge(int sourceIndex, int targetIndex, BigDecimal cost) {
    vertices.get(sourceIndex).edges
        .addLast(new Edge(vertices.get(sourceIndex), vertices.get(targetIndex), cost));

    if (graphType.equals(GraphType.UNDIRECTED)) {
      vertices.get(targetIndex).edges
          .addLast(new Edge(vertices.get(targetIndex), vertices.get(sourceIndex), cost));
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
    Vertex vertex = vertices.get(sourceIndex);
    for (Edge edge : vertex.edges) {
      if (edge.targetVertex.equals(vertices.get(targetIndex))) {
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
    Vertex vertex = vertices.get(sourceIndex);
    for (Edge edge : vertex.edges) {
      if (edge.targetVertex.equals(vertices.get(targetIndex))) {
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
    List<Integer> indexes = vertices.get(sourceIndex).edges
        .stream().map(edge ->
            this.getIndexOfVertex(edge.targetVertex))
        .collect(Collectors.toList());

    return indexes;
  }

  private Integer getIndexOfVertex(Vertex vertex) {
    for (int i = 0; i < vertices.size(); i++) {
      if (vertices.get(i).equals(vertex)) {
        return i;
      }
    }

    return null;
  }
}
