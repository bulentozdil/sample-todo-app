package sample.todoapp.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.style.ToStringCreator;

@Configuration
@ConfigurationProperties(prefix = "app.couchbase")
public class CouchbaseProperty {

	@Value("${app.couchbase.host}")
	private String host;
	@Value("${app.couchbase.username}")
	private String username;
	@Value("${app.couchbase.password}")
	private String password;
	@Value("${app.couchbase.bucket-name}")
	private String bucketName;
	private List<BucketSettings> buckets;

	/**
	 * 
	 */
	public CouchbaseProperty() {

	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the bucketName
	 */
	public String getBucketName() {
		return bucketName;
	}

	/**
	 * @return the buckets
	 */
	public List<BucketSettings> getBuckets() {
		return buckets;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(
			String host) {
		this.host = host;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(
			String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(
			String password) {
		this.password = password;
	}

	/**
	 * @param bucketName the bucketName to set
	 */
	public void setBucketName(
			String bucketName) {
		this.bucketName = bucketName;
	}

	/**
	 * @param buckets the buckets to set
	 */
	public void setBuckets(
			List<BucketSettings> buckets) {
		this.buckets = buckets;
	}

	public static class BucketSettings {

		private String name;
		private String type;
		private int ramQuotaMB;
		private String indexes;

		/**
		 * 
		 */
		public BucketSettings() {
		}

		/**
		 * @param name
		 * @param type
		 * @param ramQuotaMB
		 * @param indexes
		 */
		public BucketSettings(
				String name,
				String type,
				int ramQuotaMB,
				String indexes) {
			this.name = name;
			this.type = type;
			this.ramQuotaMB = ramQuotaMB;
			this.indexes = indexes;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @return the ramQuotaMB
		 */
		public int getRamQuotaMB() {
			return ramQuotaMB;
		}

		/**
		 * @return the indexes
		 */
		public String getIndexes() {
			return indexes;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(
				String name) {
			this.name = name;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(
				String type) {
			this.type = type;
		}

		/**
		 * @param ramQuotaMB the ramQuotaMB to set
		 */
		public void setRamQuotaMB(
				int ramQuotaMB) {
			this.ramQuotaMB = ramQuotaMB;
		}

		/**
		 * @param indexes the indexes to set
		 */
		public void setIndexes(
				String indexes) {
			this.indexes = indexes;
		}

		@Override
		public String toString() {
			return new ToStringCreator(this).append("name", name).append("type", type).append("ramQuotaMB", ramQuotaMB).append("indexes", indexes).toString();
		}
	}
}
