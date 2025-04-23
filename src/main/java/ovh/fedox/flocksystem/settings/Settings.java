package ovh.fedox.flocksystem.settings;


import lombok.Getter;
import org.mineacademy.vfo.settings.SimpleSettings;

import java.util.List;

/**
 * Settings.java - Settings for this plugin
 * <p>
 * Created on 3/30/2025 at 3:47 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@Getter
public final class Settings extends SimpleSettings {

	public static String MOTD;
	public static String MAINTENANCE_MOTD;

	private static void init() {
		setPathPrefix(null);
		MOTD = getString("MOTD");
		MAINTENANCE_MOTD = getString("Maintenance_MOTD");
	}

	public static class MongoDB {
		public static String MONGO_CONNECTION_STRING;
		public static String MONGO_DATABASE;

		private static void init() {
			setPathPrefix("MongoDB");

			MONGO_CONNECTION_STRING = getString("Connection_String");
			MONGO_DATABASE = getString("Database");
		}
	}

	public static class Redis {
		public static String REDIS_HOST;
		public static String REDIS_PORT;
		public static String REDIS_USER;
		public static String REDIS_PASSWORD;

		private static void init() {
			setPathPrefix("Redis");

			REDIS_HOST = getString("Host");
			REDIS_PORT = getString("Port");
			REDIS_USER = getString("User");
			REDIS_PASSWORD = getString("Password");
		}
	}

	public static class Tablist {
		public static List<String> HEADER;
		public static List<String> FOOTER;

		private static void init() {
			setPathPrefix("Tablist");

			HEADER = getStringList("Header");
			FOOTER = getStringList("Footer");
		}
	}


}
