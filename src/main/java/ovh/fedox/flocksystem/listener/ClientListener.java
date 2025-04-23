package ovh.fedox.flocksystem.listener;


import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import org.mineacademy.vfo.Common;
import org.mineacademy.vfo.remain.Remain;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.database.model.MaintenanceSetting;

import java.util.Optional;

/**
 * ClientListener.java - Listening for client stuff
 * <p>
 * Created on 3/30/2025 at 4:43 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public final class ClientListener {

	@Subscribe
	public void onClientConnect(ServerPostConnectEvent event) {
		Optional<MaintenanceSetting> maintenance = FlockSystem.getMongoManager().getMaintenanceRepository().getMaintenanceSetting();
		MaintenanceSetting maintenanceSetting = maintenance.orElseGet(MaintenanceSetting::new);

		boolean maintenanceMode = maintenanceSetting.isMaintenanceMode();

		if (maintenanceMode) {
			if (!event.getPlayer().hasPermission("flocki.team")) {
				Common.logFramed("Maintenance mode is enabled, disconnecting " + event.getPlayer().getUsername());
				event.getPlayer().disconnect(Component.text("§4Wartungsmodus aktiviert\n\n§7Grund: §c" + maintenanceSetting.getMaintenanceReason() + "\n§7Vorrausichtliche Endzeit: §c" + maintenanceSetting.getMaintenanceEnd()));

				for (Player proxiedPlayer : Remain.getOnlinePlayers()) {
					Common.tell(proxiedPlayer, "§7" + event.getPlayer().getUsername() + " §7hat versucht, den Server zu betreten, während der Wartungsmodus aktiviert ist.");
				}
			}
		}
//		} else {
//			RegisteredServer lobbyServer = Remain.getServer("lobby");
//
//			if (lobbyServer == null) {
//				Common.logFramed("Lobby server not found, disconnecting " + event.getPlayer().getUsername());
//				event.getPlayer().disconnect(Component.text("§4Lobby-Server nicht gefunden"));
//				return;
//			}
//
//			event.getPlayer().createConnectionRequest(lobbyServer).connect();
//		}

	}

}
