package kz.dkadirbekov.examples.datastructure.graph;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by dkadirbekov on 16.08.2016.
 */
public class MatrixGraphTest {

  @Test
  public void testMatrixGraph() throws FileNotFoundException {
    String filePath = this.getClass().getClassLoader().getResource("graph/graph.txt").getPath();

    Graph graph = new AdjListGraph(filePath, Graph.GraphType.UNDIRECTED);
    assertEquals(BigDecimal.valueOf(6), graph.getEdgeCost(3, 5));
  }
}
