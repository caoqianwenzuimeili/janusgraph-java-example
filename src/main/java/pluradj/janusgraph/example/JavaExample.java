package pluradj.janusgraph.example;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.ConfiguredGraphFactory;
import org.janusgraph.graphdb.management.JanusGraphManager;
import org.apache.tinkerpop.gremlin.server.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

public class JavaExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaExample.class);

    public static void main(String[] args) {
        // 配置 类似于gremlin server 的yaml 文件
        Settings settings = new Settings();
        settings.graphManager = "org.janusgraph.graphdb.management.JanusGraphManager";
        settings.graphs.put("ConfigurationManagementGraph", "conf/janusgraph-hbase-es.properties");
        JanusGraphManager janusGraphManager = new JanusGraphManager(settings);
        Set<String> stringSet = ConfiguredGraphFactory.getGraphNames();
        LOGGER.info(stringSet.toString());

        // 动态图的操作
        JanusGraph janusGraph = ConfiguredGraphFactory.open("search");
        GraphTraversalSource graphTraversalSource = janusGraph.traversal();
        Long count = graphTraversalSource.V().count().next();
        LOGGER.info(count.toString());
    }
}
