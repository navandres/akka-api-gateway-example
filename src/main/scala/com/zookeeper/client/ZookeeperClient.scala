import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.cache.{ NodeCache, NodeCacheListener }
import org.apache.curator.retry.ExponentialBackoffRetry
import org.slf4j.LoggerFactory

object ZookeeperClient {

  private val logger = LoggerFactory.getLogger(this.getClass.getName)

  def main(args: Array[String]) = {
    val retryPolicy = new ExponentialBackoffRetry(1000, 3)
    val curatorZookeeperClient = CuratorFrameworkFactory.newClient("10.2.62.127:2181", retryPolicy)
    curatorZookeeperClient.start
    curatorZookeeperClient.getZookeeperClient.blockUntilConnectedOrTimedOut
    val znodePath = "/test_node"
    val originalData = new String(curatorZookeeperClient.getData.forPath(znodePath)) // This should be "Some data"

    /* Zookeeper NodeCache service to get properties from ZNode */
    val nodeCache = new NodeCache(curatorZookeeperClient, znodePath)
    nodeCache.getListenable.addListener(new NodeCacheListener {
      @Override
      def nodeChanged = {
        try {
          val dataFromZNode = nodeCache.getCurrentData
          val newData = new String(dataFromZNode.getData) // This should be some new data after it is changed in the Zookeeper ensemble
        } catch {
          case ex: Exception => logger.error("Exception while fetching properties from zookeeper ZNode, reason " + ex.getCause)
        }
      }

      nodeCache.start
    })
  }
}