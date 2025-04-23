package ovh.fedox.flocksystem.command.server;


import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.mineacademy.vfo.Common;
import org.mineacademy.vfo.command.SimpleCommandGroup;
import org.mineacademy.vfo.command.SimpleSubCommand;
import org.mineacademy.vfo.remain.Remain;
import ovh.fedox.flocksystem.util.SoundUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ServerEnterCommand.java - Enter command for the server
 * <p>
 * Created on 4/1/2025 at 5:55 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public final class ServerEnterCommand extends SimpleSubCommand {

	public ServerEnterCommand(SimpleCommandGroup parent) {
		super(parent, "enter");

		setUsage("<server>");
		setMinArguments(1);
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final Player player = getPlayer();

		String server = args[0];

		Collection<RegisteredServer> servers = Remain.getServers();
		RegisteredServer target = Remain.getServer(server);

		List<String> serverNames = new ArrayList<>();

		for (RegisteredServer serverInfo : servers) {
			serverNames.add(serverInfo.getServerInfo().getName());
		}

		if (target == null) {
			tellError("Der Server konnte nicht gefunden werden. Verfügbare Server: " + Common.join(serverNames, ", "));
			SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
			return;
		}

		SoundUtil.playSound(player, SoundUtil.SoundType.SUCCESS);

		player.createConnectionRequest(target)
				.connect()
				.thenAccept(result -> {
					if (result.isSuccessful()) {
						tellSuccess("Du wirst zum Server " + target.getServerInfo().getName() + " verbunden.");
					} else {
						tellError("Es gab ein Problem beim Verbinden mit dem Server " + target.getServerInfo().getName() + ".");
						SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
					}
				})
				.exceptionally(throwable -> {
					tellError("Es gab ein Problem beim Verbinden mit dem Server " + target.getServerInfo().getName() + ": " + throwable.getMessage());
					SoundUtil.playSound(player, SoundUtil.SoundType.FAILURE);
					return null;
				});
	}

}
