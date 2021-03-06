package sample.todoapp.config;

import java.util.Arrays;
import java.util.Map; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory; 
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;  
 
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.manager.bucket.BucketManager;
import com.couchbase.client.java.manager.bucket.BucketSettings;
import com.couchbase.client.java.manager.bucket.BucketType;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

	private final Logger logger = LoggerFactory.getLogger(CouchbaseConfig.class);

	/**
	 * 
	 */
	@Autowired
	private CouchbaseProperty couchbaseProperty;
	

	@Override
	public String getBucketName() {
		return couchbaseProperty.getBucketName();
	}

	@Override
	public String getConnectionString() {
		return couchbaseProperty.getHost();
	}

	@Override
	public String getPassword() {
		return couchbaseProperty.getPassword();
	}

	@Override
	public String getUserName() {
		return couchbaseProperty.getUsername();
	}

	@Bean
	public Cluster cluster() {
		return Cluster.connect(couchbaseProperty.getHost(), ClusterOptions.clusterOptions(couchbaseProperty.getUsername(), couchbaseProperty.getPassword()));
	}

	@Override
	public CouchbaseClientFactory couchbaseClientFactory(
			Cluster couchbaseCluster) {

		var manager = couchbaseCluster.buckets();
		var allBuckets = manager.getAllBuckets();

		couchbaseProperty.getBuckets().stream().forEach(f -> {
			
			// Create buckets
			createBuckets(manager, allBuckets, f);

			// Create indexes
			createIndexes(couchbaseCluster, f);
		});

		return super.couchbaseClientFactory(couchbaseCluster);
	}
	
	public CouchbaseProperty couchbaseProperty() {
		return couchbaseProperty;
	}

	/**
	 * @param couchbaseCluster
	 * @param f
	 */
	private void createIndexes(
			Cluster couchbaseCluster,
			sample.todoapp.config.CouchbaseProperty.BucketSettings f) {
		couchbaseCluster.queryIndexes().getAllIndexes(f.getName())
			.stream()
			.filter(p -> p.name().equals(String.format("ix_%s", f.getName())))
			.findFirst()
			.ifPresentOrElse(
				(value) -> {
					logger.info(String.format("The bucket [%s] already has an index [%s]", f.getName(),value.name()));
				},
				() -> {
					if (!f.getIndexes().isBlank()) {
						couchbaseCluster.queryIndexes()
							.createIndex(
									f.getName(), 
									String.format("ix_%s", f.getName()), 
									Arrays.asList(f.getIndexes().split(",")));
						
						logger.info(String.format("The bucket [%s] index [%s] has been created", f.getName(),String.format("ix_%s", f.getName())));
					}
				});
	}

	/**
	 * @param manager
	 * @param allBuckets
	 * @param f
	 */
	private void createBuckets(
			BucketManager manager,
			Map<String, BucketSettings> allBuckets,
			sample.todoapp.config.CouchbaseProperty.BucketSettings f) {
		allBuckets.entrySet()
			.stream()
			.filter(p -> p.getKey().equals(f.getName()))
			.findFirst()
			.ifPresentOrElse(
				(value) -> {
					logger.info(String.format("The bucket [%s] already exists", value.getKey()));
				},
				() -> {
					manager.createBucket(
							BucketSettings
								.create(f.getName())
								.bucketType(BucketType.valueOf(f.getType()))
								.ramQuotaMB(f.getRamQuotaMB()));
					
					logger.info(String.format("The bucket [%s] has been created", f.getName()));
				});
	}
}
