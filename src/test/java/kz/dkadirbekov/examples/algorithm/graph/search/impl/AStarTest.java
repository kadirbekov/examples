package kz.dkadirbekov.examples.algorithm.graph.search.impl;

import kz.dkadirbekov.examples.algorithm.graph.search.AStar;
import kz.dkadirbekov.examples.datastructure.graph.AdjListGraph;
import kz.dkadirbekov.examples.datastructure.graph.Graph;
import kz.dkadirbekov.examples.datastructure.graph.Path;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by dkadirbekov on 19.08.2016.
 */
public class AStarTest {

  @Test
  public void testFindShortestPath_aStar1() throws FileNotFoundException {
    String fileName = this.getClass().getClassLoader()
        .getResource("graph/shortest-path/astar-1.txt").getPath();

    Graph graph = new AdjListGraph(fileName, Graph.GraphType.UNDIRECTED);

    Scanner scanner = new Scanner(new File(fileName));
    int numberOfVertices = scanner.nextInt();
    int numberOfEdges = scanner.nextInt();
    for (int i = 0; i < numberOfEdges; i++) {
      scanner.nextInt();
      scanner.nextInt();
      scanner.nextInt();
    }

    int sourceIndex = scanner.nextInt();
    int targetIndex = scanner.nextInt();

    List<BigDecimal> heuristicValues = new ArrayList<>();
    for (int i = 0; i < numberOfVertices; i++) {
      heuristicValues.add(scanner.nextBigDecimal());
    }

    AStar aStar = new AStarImpl();
    aStar.setHeuristicValues(heuristicValues);

    Path shortestPath = aStar.findShortestPath(graph, sourceIndex, targetIndex);

    assertEquals(BigDecimal.valueOf(270), shortestPath.getCost());

    assertEquals(4, shortestPath.getVerticeIndexes().size());
    assertEquals(0, shortestPath.getVerticeIndexes().get(0).intValue());
    assertEquals(1, shortestPath.getVerticeIndexes().get(1).intValue());
    assertEquals(4, shortestPath.getVerticeIndexes().get(2).intValue());
    assertEquals(6, shortestPath.getVerticeIndexes().get(3).intValue());
  }

  @Test
  public void testFindShortestPath_aStar2() throws FileNotFoundException {
    String fileName = this.getClass().getClassLoader()
        .getResource("graph/shortest-path/astar-2.txt").getPath();

    Graph graph = new AdjListGraph(fileName, Graph.GraphType.DIRECTED);

    Scanner scanner = new Scanner(new File(fileName));
    int numberOfVertices = scanner.nextInt();
    int numberOfEdges = scanner.nextInt();
    for (int i = 0; i < numberOfEdges; i++) {
      scanner.nextInt();
      scanner.nextInt();
      scanner.nextInt();
    }

    int sourceIndex = scanner.nextInt();
    int targetIndex = scanner.nextInt();

    List<BigDecimal> heuristicValues = new ArrayList<>();
    for (int i = 0; i < numberOfVertices; i++) {
      heuristicValues.add(scanner.nextBigDecimal());
    }

    AStar aStar = new AStarImpl();
    aStar.setHeuristicValues(heuristicValues);

    Path shortestPath = aStar.findShortestPath(graph, sourceIndex, targetIndex);

    assertEquals(BigDecimal.valueOf(8), shortestPath.getCost());

    assertEquals(5, shortestPath.getVerticeIndexes().size());
    assertEquals(0, shortestPath.getVerticeIndexes().get(0).intValue());
    assertEquals(1, shortestPath.getVerticeIndexes().get(1).intValue());
    assertEquals(2, shortestPath.getVerticeIndexes().get(2).intValue());
    assertEquals(3, shortestPath.getVerticeIndexes().get(3).intValue());
    assertEquals(4, shortestPath.getVerticeIndexes().get(4).intValue());
  }

}
