package ovh.fedox.flocksystem.command.maintenance;

import org.mineacademy.vfo.annotation.AutoRegister;
import org.mineacademy.vfo.command.SimpleCommandGroup;
import org.mineacademy.vfo.remain.CompChatColor;

/**
 * MaintenanceCommandGroup.java -
 * <p>
 * Created on 3/30/2025 at 5:30 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class MaintenanceCommandGroup extends SimpleCommandGroup {

	public MaintenanceCommandGroup() {
		super("maintenance|wartung|wartungen");
	}

	@Override
	protected void registerSubcommands() {
		registerSubcommand(new MaintenanceToggleCommand(this));
		registerSubcommand(new MaintenanceReasonCommand(this));
		registerSubcommand(new MaintenanceEndCommand(this));
	}

	@Override
	protected CompChatColor getTheme() {
		return CompChatColor.GREEN;
	}

	@Override
	protected String getCredits() {
		return "&7Dieses Plugin wurde für &fzFlockii &7erstellt.";
	}
}
