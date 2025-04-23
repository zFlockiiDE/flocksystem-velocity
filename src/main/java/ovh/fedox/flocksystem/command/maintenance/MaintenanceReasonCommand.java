package ovh.fedox.flocksystem.command.maintenance;


import org.mineacademy.vfo.Common;
import org.mineacademy.vfo.command.SimpleCommandGroup;
import org.mineacademy.vfo.command.SimpleSubCommand;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.database.model.MaintenanceSetting;
import ovh.fedox.flocksystem.database.repository.MaintenanceRepository;
import ovh.fedox.flocksystem.util.SoundUtil;

import java.util.Optional;

/**
 * MaintenanceReasonCommand.java -
 * <p>
 * Created on 3/30/2025 at 5:31 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public final class MaintenanceReasonCommand extends SimpleSubCommand {

	public MaintenanceReasonCommand(SimpleCommandGroup parent) {
		super(parent, "reason");

		setUsage("<reason>");
		setMinArguments(1);
		setDescription("Setze den Wartungsgrund");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final String reason = Common.joinRange(0, args);
		MaintenanceRepository maintenanceRepository = FlockSystem.getMongoManager().getMaintenanceRepository();
		Optional<MaintenanceSetting> maintenance = FlockSystem.getMongoManager().getMaintenanceRepository().getMaintenanceSetting();
		MaintenanceSetting maintenanceSetting = maintenance.orElseGet(MaintenanceSetting::new);

		maintenanceSetting.setMaintenanceReason(reason);
		maintenanceRepository.save(maintenanceSetting);

		tellSuccess("Wartungsgrund erfolgreich auf '" + reason + "' gesetzt.");
		SoundUtil.playSound(getPlayer(), SoundUtil.SoundType.SUCCESS);
	}
}
