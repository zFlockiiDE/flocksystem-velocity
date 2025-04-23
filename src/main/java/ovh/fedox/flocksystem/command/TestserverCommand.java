package ovh.fedox.flocksystem.command;


import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.mineacademy.vfo.annotation.AutoRegister;
import org.mineacademy.vfo.command.SimpleCommand;
import org.mineacademy.vfo.remain.Remain;
import ovh.fedox.flocksystem.util.SoundUtil;

/**
 * TestserverCommand.java - Send a player to the Testserver
 * <p>
 * Created on 4/1/2025 at 10:00 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class TestserverCommand extends SimpleCommand {

	public TestserverCommand() {
		super("testserver");

		setDescription("Teleportiere dich zum Testserver");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final Player player = getPlayer();

		final RegisteredServer targetServer = Remain.getServer("testserver");

		if (targetServer == null) {
			tellError("Der Testserver konnte nicht gefunden werden.");
			return;
		}

		player.createConnectionRequest(targetServer).connect().thenAccept(result -> {
			if (result.isSuccessful()) {
				tellSuccess("Du wurdest erfolgreich zum Testserver verbunden.");
			} else {
				tellError("Es gab ein Problem beim Verbinden mit dem Testserver.");
				SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
			}
		}).exceptionally(throwable -> {
			tellError("Es gab ein Problem beim Verbinden mit dem Testserver: " + throwable.getMessage());
			SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
			return null;
		});
	}
}
