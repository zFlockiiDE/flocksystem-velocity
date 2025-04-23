package ovh.fedox.flocksystem.command;


import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.mineacademy.vfo.annotation.AutoRegister;
import org.mineacademy.vfo.command.SimpleCommand;
import org.mineacademy.vfo.remain.Remain;
import ovh.fedox.flocksystem.util.SoundUtil;

/**
 * HubCommand.java - Command to get to the hub
 * <p>
 * Created on 3/31/2025 at 1:43 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@AutoRegister
public final class HubCommand extends SimpleCommand {

	public HubCommand() {
		super("hub|l|lobby|spawn|leave");

		setDescription("Teleportiere dich zu der Hub");
		setPermission(null);
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final Player player = getPlayer();
		final String serverName = player.getCurrentServer()
				.map(server -> server.getServerInfo().getName())
				.orElse("unknown");

		if (serverName.equalsIgnoreCase("lobby")) {
			tellError("Du bist bereits auf dem Lobby-Server.");
			SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
		} else {
			final RegisteredServer lobbyServer = Remain.getServer("lobby");

			player.createConnectionRequest(lobbyServer).connect().thenAccept(result -> {
				if (result.isSuccessful()) {
					tellSuccess("Du wirst zu der Lobby teleportiert.");
				} else {
					tellError("Es gab ein Problem beim Verbinden mit der Lobby.");
					SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
				}
			}).exceptionally(throwable -> {
				tellError("Es gab ein Problem beim Verbinden mit der Lobby: " + throwable.getMessage());
				SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
				return null;
			});
		}
	}
}
