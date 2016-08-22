package kz.dkadirbekov.examples.datastructure.graph;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by dkadirbekov on 16.08.2016.
 */
public class AdjListGraphTest {

  @Test
  public void testListGraph() throws FileNotFoundException {
    String filePath = this.getClass().getClassLoader().getResource("graph/graph.txt").getPath();

    Graph graph = new AdjListGraph(filePath, Graph.GraphType.UNDIRECTED);
    assertEquals(BigDecimal.valueOf(6), graph.getEdgeCost(3, 5));
  }
}
