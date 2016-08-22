package kz.dkadirbekov.examples.datastructure.graph;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dkadirbekov on 16.08.2016.
 */
public abstract class Graph {

  public enum GraphType {DIRECTED, UNDIRECTED}

  ;

  /**
   * Adds edge between two vertices with cost = {@code cost}
   *
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @param cost        cost of edge
   */
  public abstract void addEdge(int sourceIndex, int targetIndex, BigDecimal cost);

  /**
   * Searches for edge between two vertices; if edge is found, return cost of edge;
   * Else return {@code null};
   *
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @return cost of edge between to vertices
   */
  public abstract BigDecimal getEdgeCost(int sourceIndex, int targetIndex);

  /**
   * @return number of vertex
   */
  public abstract int getNumberOfVertices();

  /**
   * Looks for existing the edge between two vertices
   *
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @return {@code true} if edge exists and {@code false} if edge doesn't exists
   */
  public abstract boolean isEdgeExists(int sourceIndex, int targetIndex);

  /**
   * Searches for {@link List} of indexes of vertices that are target to vertex with index {@sourceIndex}
   *
   * @param sourceIndex index of source vertex
   * @return {@link List} of indexes
   */
  public abstract List<Integer> getIndexesOfNeighbourVertices(int sourceIndex);
}
