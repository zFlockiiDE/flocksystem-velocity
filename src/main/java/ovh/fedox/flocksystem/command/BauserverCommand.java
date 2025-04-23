package ovh.fedox.flocksystem.command;


import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.mineacademy.vfo.annotation.AutoRegister;
import org.mineacademy.vfo.command.SimpleCommand;
import org.mineacademy.vfo.remain.Remain;
import ovh.fedox.flocksystem.util.SoundUtil;

/**
 * BauserverCommand.java - Send a player to the Bauserver
 * <p>
 * Created on 4/1/2025 at 10:00 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class BauserverCommand extends SimpleCommand {

	public BauserverCommand() {
		super("bau/bauserver");

		setDescription("Teleportiere dich zum Bauserver");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final Player player = getPlayer();

		final RegisteredServer targetServer = Remain.getServer("bauserver");

		if (targetServer == null) {
			tellError("Der Bauserver konnte nicht gefunden werden.");
			return;
		}

		player.createConnectionRequest(targetServer).connect().thenAccept(result -> {
			if (result.isSuccessful()) {
				tellSuccess("Du wurdest erfolgreich zum Bauserver verbunden.");
			} else {
				tellError("Es gab ein Problem beim Verbinden mit dem Bauserver.");
				SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
			}
		}).exceptionally(throwable -> {
			tellError("Es gab ein Problem beim Verbinden mit dem Bauserver: " + throwable.getMessage());
			SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
			return null;
		});
	}
}
