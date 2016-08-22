package kz.dkadirbekov.examples.algorithm.graph.search.impl;

import kz.dkadirbekov.examples.algorithm.graph.search.Dijkstra;
import kz.dkadirbekov.examples.datastructure.graph.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dkadirbekov on 17.08.2016.
 */
public class DijkstraTest {

  Dijkstra dijkstra = new DijkstraImpl();
  String filePath;

  @Before
  public void prepare() {
    filePath = this.getClass().getClassLoader().getResource("graph/graph.txt").getPath();

  }

  @Test
  public void testFindShortestPath_adjListGraph() throws FileNotFoundException {
    Graph graph = new AdjListGraph(filePath, Graph.GraphType.UNDIRECTED);

    Path shortestPath = dijkstra.findShortestPath(graph, 0, 7);
    assertEquals(BigDecimal.valueOf(11), shortestPath.getCost());
  }

  @Test
  public void testFindShortestPath_matrixGraph() throws FileNotFoundException {
    Graph graph = new MatrixGraph(filePath, Graph.GraphType.UNDIRECTED);

    Path shortestPath = dijkstra.findShortestPath(graph, 0, 7);
    assertEquals(BigDecimal.valueOf(11), shortestPath.getCost());
  }

  @Test
  public void testFindShortestPath_objectPointerGraph() throws FileNotFoundException {
    Graph graph = new ObjectPointerGraph(filePath, Graph.GraphType.UNDIRECTED);

    Path shortestPath = dijkstra.findShortestPath(graph, 0, 7);
    assertEquals(BigDecimal.valueOf(11), shortestPath.getCost());
  }

  @Test
  public void testFindShortestPath_1() throws FileNotFoundException {
    String inputFilePath = this.getClass().getClassLoader()
        .getResource("graph/shortest-path/dijkstra-1.txt").getPath();

    Scanner scanner = new Scanner(new File(inputFilePath));
    int numberOfVertices = scanner.nextInt();

    Graph graph = new AdjListGraph(inputFilePath, Graph.GraphType.UNDIRECTED);
    Path shortestPath = dijkstra.findShortestPath(graph, 0, numberOfVertices - 1);

    assertEquals(BigDecimal.valueOf(5), shortestPath.getCost());

    assertEquals(0, shortestPath.getVerticeIndexes().get(0).intValue());
    assertEquals(3, shortestPath.getVerticeIndexes().get(1).intValue());
    assertEquals(2, shortestPath.getVerticeIndexes().get(2).intValue());
    assertEquals(4, shortestPath.getVerticeIndexes().get(3).intValue());
  }

}
