package kz.dkadirbekov.examples.algorithm.graph.search;

import kz.dkadirbekov.examples.datastructure.graph.Graph;
import kz.dkadirbekov.examples.datastructure.graph.Path;

/**
 * Created by dkadirbekov on 19.08.2016.
 */
public interface SearchAlgorithm {

  /**
   * Searches for {@link Path} from vertex with index {@code sourceIndex} to vertex with index
   * {@code targetIndex}
   *
   * @param graph       {@link Graph}
   * @param sourceIndex index of source vertex
   * @param targetIndex index of target vertex
   * @return {@link Path}
   */
  Path findShortestPath(Graph graph, int sourceIndex, int targetIndex);

}
