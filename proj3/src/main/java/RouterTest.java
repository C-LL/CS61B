import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RouterTest {

    private GraphDB graph;

    @Before
    public void setUp() {
        graph = new GraphDB(null); // 假设我们不需要XML文件来构建图

        // 手动添加节点和边
        GraphDB.Node node1 = new GraphDB.Node(1L, 0.0, 0.0);
        GraphDB.Node node2 = new GraphDB.Node(2L, 1.0, 0.0);
        GraphDB.Node node3 = new GraphDB.Node(3L, 1.0, 1.0);
        GraphDB.Node node4 = new GraphDB.Node(4L, 0.0, 1.0);

        node1.connect(node2);
        node2.connect(node3);
        node3.connect(node4);
        node4.connect(node1); // 形成一个四边形

        // 将节点添加到图中
        graph.addNode(graph.nodes, node1);
        graph.addNode(graph.nodes, node2);
        graph.addNode(graph.nodes, node3);
        graph.addNode(graph.nodes, node4);

        // 初始化距离和访问标记
        graph.dist.clear();
        graph.hDist.clear();
        graph.visited.clear();
        for (GraphDB.Node n : graph.nodes.values()) {
            graph.dist.put(n.id, Double.MAX_VALUE);
            graph.hDist.put(n.id, Double.MAX_VALUE);
            graph.visited.put(n.id, false);
        }

        // 设置起始点和目标点的启发式距离（这里简单地使用欧几里得距离作为启发式）
        graph.hDist.put(1L, 0.0); // 起点
        // 注意：在实际应用中，hDist 应该根据启发式算法（如A*算法的启发式）来设置
    }

    @Test
    public void testShortestPath() {
        List<Long> path = Router.shortestPath(graph, 0.0, 0.0, 1.0, 1.0);
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L), path); // 或者任何有效的最短路径，取决于边的权重和启发式

        // 注意：由于我们没有为边设置权重，所以这里假设所有边的权重都是1
        // 在实际应用中，你需要根据边的实际权重来调整期望的路径
    }

    @Test
    public void testShortestPath_SamePoint() {
        List<Long> path = Router.shortestPath(graph, 0.0, 0.0, 0.0, 0.0);
        assertEquals(Collections.singletonList(1L), path);
    }

    @Test
    public void testShortestPath_NoPath() {
        // 添加一个不与其他节点相连的孤立节点
        GraphDB.Node isolatedNode = new GraphDB.Node(5L, 2.0, 2.0);
        graph.addNode(graph.nodes, isolatedNode);

        // 尝试从起点到孤立节点的最短路径
        List<Long> path = Router.shortestPath(graph, 0.0, 0.0, 2.0, 2.0);
        assertTrue(path.isEmpty()); // 因为没有路径
    }
}