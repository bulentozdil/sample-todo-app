package sample.todoapp.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;

public abstract class Repository {

	@Autowired
	public Cluster cluster;

	/**
	 * @return
	 */
	public Cluster cluster() {
		return cluster;
	}

	public abstract Bucket bucket();
}
