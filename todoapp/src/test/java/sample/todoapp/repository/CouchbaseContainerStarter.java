package sample.todoapp.repository;

import java.time.Duration;
import java.util.stream.Collectors;

import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;

import com.couchbase.client.core.cnc.tracing.ThresholdRequestTracer;
import com.couchbase.client.core.diagnostics.ClusterState;
import com.couchbase.client.core.env.IoConfig;
import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.diagnostics.WaitUntilReadyOptions;
import com.couchbase.client.java.env.ClusterEnvironment;

import sample.todoapp.config.CouchbaseConfig;

public class CouchbaseContainerStarter {

	private final String USERNAME = "Administrator";
	private final String PASSWORD = "password";

	private String bucketName; 
	private String host;
	private int quota;
	private Cluster cluster;

	private CouchbaseContainer container;
	private MappingCouchbaseConverter mappingCouchbaseConverter = new MappingCouchbaseConverter();

	/**
	 * @param bucketName
	 * @param quota
	 */
	public void start(
			String bucketName,
			int quota) {
		this.bucketName = bucketName; 

		container = new CouchbaseContainer();
		container.withBucket(new BucketDefinition(bucketName).withQuota(quota)); 

		container.start();

		var environment = ClusterEnvironment.builder()
					.timeoutConfig(
							TimeoutConfig
								.kvTimeout(Duration.ofSeconds(90))
								.queryTimeout(Duration.ofSeconds(90))
								.connectTimeout(Duration.ofSeconds(90)))
					.ioConfig(
							IoConfig
								.maxHttpConnections(11)
								.numKvConnections(11))
					.requestTracer(
							ThresholdRequestTracer
								.builder(null)
								.queryThreshold(Duration.ofSeconds(30))
								.build()
							).maxNumRequestsInRetry(100)
					.build();
		
		this.host = container.getConnectionString();
		this.cluster = Cluster.connect(container.getConnectionString(), ClusterOptions.clusterOptions(USERNAME, PASSWORD).environment(environment));
		this.cluster.waitUntilReady(Duration.ofSeconds(15), WaitUntilReadyOptions.waitUntilReadyOptions().desiredState(ClusterState.ONLINE));
	}

	public void stop() {
		container.stop();
	}
	
	/**
	 * @param config
	 */
	public void replaceConfigProperties(CouchbaseConfig config) {
		
		config.couchbaseProperty().setHost(getHost());
		config.couchbaseProperty().setUsername(getUsername());
		config.couchbaseProperty().setPassword(getPassword());
		config.couchbaseProperty().setBucketName(getBucketName());

		// Set current bucket
		config.couchbaseProperty().setBuckets(
				config.couchbaseProperty().getBuckets().stream().filter(f -> f.getName().equals(getBucketName())).collect(Collectors.toList()));

		config.couchbaseClientFactory(cluster());
	}

	public String getHost() {
		return host;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return USERNAME;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return PASSWORD;
	}

	/**
	 * @return the bucketName
	 */
	public String getBucketName() {
		return bucketName;
	}
	
	/**
	 * @return the bucketName
	 */
	public int getQuota() {
		return quota;
	}

	/**
	 * @return mappingCouchbaseConverter
	 */
	public MappingCouchbaseConverter mappingCouchbaseConverter() {
		return mappingCouchbaseConverter;
	}

	public Cluster cluster() {
		return cluster;
	}
}
