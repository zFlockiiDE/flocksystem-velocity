package ovh.fedox.flocksystem.database.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.Document;

/**
 * MaintenanceSetting.java -
 * <p>
 * Created on 3/30/2025 at 3:14 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@Setter
@Getter
@ToString
public class MaintenanceSetting implements GameEntity {

	private boolean maintenanceMode;
	private String maintenanceReason;
	private String maintenanceEnd;

	public MaintenanceSetting() {
	}

	public MaintenanceSetting(boolean maintenanceMode, String maintenanceReason, String maintenanceEnd) {
		this.maintenanceMode = maintenanceMode;
		this.maintenanceReason = maintenanceReason;
		this.maintenanceEnd = maintenanceEnd;
	}

	@Override
	public Document toDocument() {
		Document document = new Document();

		document.append("maintenanceMode", maintenanceMode);

		if (maintenanceReason != null) {
			document.append("maintenanceReason", maintenanceReason);
		}

		if (maintenanceEnd != null) {
			document.append("maintenanceEnd", maintenanceEnd);
		}

		return document;
	}

	@Override
	public void fromDocument(Document document) {
		if (document.containsKey("maintenanceMode")) {
			maintenanceMode = document.getBoolean("maintenanceMode");
		}

		if (document.containsKey("maintenanceReason")) {
			maintenanceReason = document.getString("maintenanceReason");
		}

		if (document.containsKey("maintenanceEnd")) {
			maintenanceEnd = document.getString("maintenanceEnd");
		}
	}

	@Override
	public String getId() {
		return "maintenance";
	}

}
