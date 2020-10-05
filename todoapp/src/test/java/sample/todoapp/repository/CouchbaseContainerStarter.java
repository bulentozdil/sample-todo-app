package sample.todoapp.repository;

import java.util.stream.Collectors;

import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;

import com.couchbase.client.java.Cluster;

import sample.todoapp.config.CouchbaseConfig;

public class CouchbaseContainerStarter {

	private final String USERNAME = "Administrator";
	private final String PASSWORD = "password";

	private String bucketName;
	private String host;
	private int quota;

	private Cluster cluster;

	private CouchbaseContainer container;

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

		this.host = container.getConnectionString();
	}

	public void stop() {
		container.stop();
	}

	/**
	 * @param config
	 */
	public void replaceConfigProperties(
			CouchbaseConfig config) {

		config.couchbaseProperty().setHost(getHost());
		config.couchbaseProperty().setUsername(getUsername());
		config.couchbaseProperty().setPassword(getPassword());
		config.couchbaseProperty().setBucketName(getBucketName());

		// Set current bucket
		config.couchbaseProperty().setBuckets(config.couchbaseProperty().getBuckets().stream().filter(f -> f.getName().equals(getBucketName())).collect(Collectors.toList()));
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

	public Cluster cluster() {
		return cluster;
	}
}
