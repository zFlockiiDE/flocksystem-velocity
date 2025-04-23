package ovh.fedox.flocksystem.database.repository;


import com.mongodb.client.MongoCollection;
import org.bson.Document;
import ovh.fedox.flocksystem.database.model.MaintenanceSetting;

import java.util.Optional;

/**
 * MaintenanceRepository.java -
 * <p>
 * Created on 3/30/2025 at 3:17 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public class MaintenanceRepository extends MongoRepository<MaintenanceSetting> {

	/**
	 * Creates a new MongoDB Repository
	 *
	 * @param collection The MongoDB Collection
	 */
	public MaintenanceRepository(MongoCollection<Document> collection) {
		super(collection, MaintenanceSetting.class, MaintenanceSetting::new);
	}

	public Optional<MaintenanceSetting> getMaintenanceSetting() {
		return findById("maintenance");
	}

	public void toggleMaintenance(boolean maintenance) {
		Optional<MaintenanceSetting> currentSetting = getMaintenanceSetting();

		MaintenanceSetting setting;
		setting = currentSetting.orElseGet(MaintenanceSetting::new);

		setting.setMaintenanceMode(maintenance);

		save(setting);
	}

	public void setMaintenanceReason(String reason) {
		Optional<MaintenanceSetting> currentSetting = getMaintenanceSetting();

		MaintenanceSetting setting;
		setting = currentSetting.orElseGet(MaintenanceSetting::new);

		setting.setMaintenanceReason(reason);

		save(setting);
	}

	public void setMaintenanceEnd(String end) {
		Optional<MaintenanceSetting> currentSetting = getMaintenanceSetting();

		MaintenanceSetting setting;
		setting = currentSetting.orElseGet(MaintenanceSetting::new);

		setting.setMaintenanceEnd(end);

		save(setting);
	}
}
