package ovh.fedox.flocksystem.command.maintenance;


import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import org.mineacademy.vfo.Common;
import org.mineacademy.vfo.command.SimpleCommandGroup;
import org.mineacademy.vfo.command.SimpleSubCommand;
import org.mineacademy.vfo.remain.Remain;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.database.model.MaintenanceSetting;
import ovh.fedox.flocksystem.database.repository.MaintenanceRepository;
import ovh.fedox.flocksystem.util.SoundUtil;

import java.util.Optional;

/**
 * MaintenanceToggleCommand.java -
 * <p>
 * Created on 3/30/2025 at 5:31 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public final class MaintenanceToggleCommand extends SimpleSubCommand {


	public MaintenanceToggleCommand(SimpleCommandGroup parent) {
		super(parent, "toggle");

		setDescription("Schalte den Wartungsmodus um");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		MaintenanceRepository maintenanceRepository = FlockSystem.getMongoManager().getMaintenanceRepository();
		Optional<MaintenanceSetting> maintenance = FlockSystem.getMongoManager().getMaintenanceRepository().getMaintenanceSetting();
		MaintenanceSetting maintenanceSetting = maintenance.orElseGet(MaintenanceSetting::new);

		boolean maintenanceMode = maintenanceSetting.isMaintenanceMode();
		maintenanceSetting.setMaintenanceMode(!maintenanceMode);
		maintenanceRepository.save(maintenanceSetting);

		for (Player proxiedPlayer : Remain.getOnlinePlayers()) {
			if (!proxiedPlayer.hasPermission("flocki.team")) {
				if (maintenanceMode) {
					proxiedPlayer.disconnect(Component.text(Common.colorize("&4Wartungsmodus aktiviert.")));
				}
			}
		}

		tellSuccess("Wartungsmodus erfolgreich " + (maintenanceMode ? "deaktiviert" : "aktiviert"));
		SoundUtil.playSound(getPlayer(), SoundUtil.SoundType.SUCCESS);
	}
}
