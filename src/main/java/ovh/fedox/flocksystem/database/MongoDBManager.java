package ovh.fedox.flocksystem.database;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;
import org.mineacademy.vfo.Common;
import ovh.fedox.flocksystem.database.repository.MaintenanceRepository;

/**
 * MongoDBManager.java - The manager for handling database connections
 * <p>
 * Created on 3/30/2025 at 2:44 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public class MongoDBManager {
	private MongoClient mongoClient;
	private MongoDatabase database;

	@Getter
	private MaintenanceRepository maintenanceRepository;

	public MongoDBManager(String connectionString, String databaseName) {
		try {
			this.mongoClient = MongoClients.create(connectionString);
			this.database = mongoClient.getDatabase(databaseName);

			MongoCollection<Document> maintenanceCollection = database.getCollection("maintenance");
			this.maintenanceRepository = new MaintenanceRepository(maintenanceCollection);

			Common.log("&aSuccess: &7Successfully connected to MongoDB!");
		} catch (Exception e) {
			Common.logFramed("Failed to connect to MongoDB: ", e.getMessage());
		}
	}

	/**
	 * Close the MongoDB connection
	 */
	public void close() {
		if (mongoClient != null) {
			mongoClient.close();
			Common.log("&aSuccess: &7MongoDB connection closed.");
		}
	}
}
