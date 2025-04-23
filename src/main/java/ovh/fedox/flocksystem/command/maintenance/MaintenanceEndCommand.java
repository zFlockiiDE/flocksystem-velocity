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

public final class MaintenanceEndCommand extends SimpleSubCommand {

	public MaintenanceEndCommand(SimpleCommandGroup parent) {
		super(parent, "end");

		setUsage("<end>");
		setMinArguments(1);
		setDescription("Setze das vorraussichtliche Ende der Wartung.");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final String end = Common.joinRange(0, args);

		MaintenanceRepository maintenanceRepository = FlockSystem.getMongoManager().getMaintenanceRepository();
		Optional<MaintenanceSetting> maintenance = FlockSystem.getMongoManager().getMaintenanceRepository().getMaintenanceSetting();
		MaintenanceSetting maintenanceSetting = maintenance.orElseGet(MaintenanceSetting::new);

		maintenanceSetting.setMaintenanceEnd(end);
		maintenanceRepository.save(maintenanceSetting);

		tellSuccess("Das vorraussichtliche Ende der Wartung wurde auf '" + end + "' gesetzt.");
		SoundUtil.playSound(getPlayer(), SoundUtil.SoundType.SUCCESS);
	}
}
