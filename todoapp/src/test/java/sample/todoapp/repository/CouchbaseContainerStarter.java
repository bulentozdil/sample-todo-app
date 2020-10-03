package sample.todoapp.repository;

import java.time.Duration;
import java.util.stream.Collectors;

import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;
 
import com.couchbase.client.core.diagnostics.ClusterState;
import com.couchbase.client.core.env.IoConfig;
import com.couchbase.client.core.env.IoEnvironment;
import com.couchbase.client.core.env.LoggerConfig;
import com.couchbase.client.core.env.OrphanReporterConfig;
import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.core.retry.BestEffortRetryStrategy;
import com.couchbase.client.core.service.ServiceType;
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
				.timeoutConfig(TimeoutConfig
		                .kvTimeout(Duration.ofMillis(300000))
		                .kvDurableTimeout(Duration.ofMillis(300000))
		                .queryTimeout(Duration.ofMillis(300000))
		            )
		            .ioConfig(IoConfig
		                .captureTraffic(ServiceType.KV)
		                .captureTraffic(ServiceType.QUERY)
		                .numKvConnections(2)
		            )
		            .loggerConfig(LoggerConfig
		                .enableDiagnosticContext(true)
		            )
		            .ioEnvironment(IoEnvironment
		                .eventLoopThreadCount(4)
		            )
		            .orphanReporterConfig(OrphanReporterConfig
		                .sampleSize(Integer.MAX_VALUE)
		            )
		            .retryStrategy(BestEffortRetryStrategy.INSTANCE)
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
