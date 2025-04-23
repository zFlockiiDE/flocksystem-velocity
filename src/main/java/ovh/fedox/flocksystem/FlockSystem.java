package ovh.fedox.flocksystem;


import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import org.mineacademy.vfo.Common;
import org.mineacademy.vfo.plugin.SimplePlugin;
import org.slf4j.Logger;
import ovh.fedox.flocksystem.database.MongoDBManager;
import ovh.fedox.flocksystem.database.RedisManager;
import ovh.fedox.flocksystem.listener.ClientListener;
import ovh.fedox.flocksystem.listener.ProxyListener;
import ovh.fedox.flocksystem.listener.ServerListener;
import ovh.fedox.flocksystem.settings.Settings;

import java.nio.file.Path;

/**
 * FlockSystem.java -
 * <p>
 * Created on 4/22/2025 at 6:35 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@Getter
@Plugin(id = "flocksystem", name = "FlockSystem", version = "1.0.0", authors = {"Fedox"})
public class FlockSystem extends SimplePlugin {

	@Getter
	public static MongoDBManager mongoManager;
	@Getter
	public static RedisManager redisManager;

	@Inject
	public FlockSystem(final ProxyServer proxyServer, Logger logger, final @DataDirectory Path dataDirectory) {
		super(proxyServer, logger, dataDirectory);
	}

	@Override
	protected void onReloadablesStart() {
		loadLibrary("org.openjdk.nashorn", "nashorn-core", "15.3");
		loadLibrary("org.mongodb", "mongodb-driver-sync", "4.9.1");
		loadLibrary("redis.clients", "jedis", "5.2.0");

		Common.setTellPrefix("&8&l➽ &a&lFlockSystem &8&l•&7 ");

		String connectionString = Settings.MongoDB.MONGO_CONNECTION_STRING;
		String database = Settings.MongoDB.MONGO_DATABASE;

		mongoManager = new MongoDBManager(connectionString, database);

		RedisManager.connect();
		RedisManager.getJedis().keys("players:*").forEach(RedisManager.getJedis()::del);
	}

	@Override
	protected void onPluginStart() {
		Common.setTellPrefix("&8&l➽ &a&lFlockSystem &8&l•&7 ");

		registerEvents(new ServerListener());
		registerEvents(new ProxyListener());
		registerEvents(new ClientListener());
	}

	@Override
	protected void onPluginStop() {
		mongoManager.close();
		RedisManager.close();
	}

}
