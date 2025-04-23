package ovh.fedox.flocksystem.command.server;


import org.mineacademy.vfo.annotation.AutoRegister;
import org.mineacademy.vfo.command.SimpleCommandGroup;
import org.mineacademy.vfo.remain.CompChatColor;

/**
 * ServerCommandGroup.java - Server command group
 * <p>
 * Created on 3/30/2025 at 5:30 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class ServerCommandGroup extends SimpleCommandGroup {

	public ServerCommandGroup() {
		super("servers");
	}

	@Override
	protected void registerSubcommands() {
		registerSubcommand(new ServerLockCommand(this));
		registerSubcommand(new ServerUnlockCommand(this));
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
