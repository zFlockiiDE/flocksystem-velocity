package ovh.fedox.flocksystem.command.server;


import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.mineacademy.vfo.Common;
import org.mineacademy.vfo.command.SimpleCommandGroup;
import org.mineacademy.vfo.command.SimpleSubCommand;
import org.mineacademy.vfo.remain.Remain;
import ovh.fedox.flocksystem.database.RedisManager;
import ovh.fedox.flocksystem.util.SoundUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ServerUnlockCommand.java - Unlock command for the server
 * <p>
 * Created on 4/1/2025 at 5:55 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public final class ServerUnlockCommand extends SimpleSubCommand {

	public ServerUnlockCommand(SimpleCommandGroup parent) {
		super(parent, "unlock");

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

		RedisManager.getJedis().sadd("available-server", target.getServerInfo().getName().toLowerCase());

		Common.tell(player, "Der Server " + target.getServerInfo().getName() + " wurde entsperrt.");
	}

}
