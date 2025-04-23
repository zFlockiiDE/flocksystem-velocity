package ovh.fedox.flocksystem.listener;


import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.database.model.MaintenanceSetting;
import ovh.fedox.flocksystem.database.repository.MaintenanceRepository;
import ovh.fedox.flocksystem.settings.Settings;
import ovh.fedox.flocksystem.util.ColorUtil;

import java.util.List;
import java.util.Optional;

/**
 * ProxyListener.java - Listening for proxy stuff
 * <p>
 * Created on 3/30/2025 at 4:02 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public final class ProxyListener {

	@Subscribe
	public void onProxyPing(ProxyPingEvent event) {
		MaintenanceRepository maintenanceRepository = FlockSystem.getMongoManager().getMaintenanceRepository();
		Optional<MaintenanceSetting> maintenance = FlockSystem.getMongoManager().getMaintenanceRepository().getMaintenanceSetting();
		MaintenanceSetting maintenanceSetting = maintenance.orElseGet(MaintenanceSetting::new);

		boolean maintenanceMode = maintenanceSetting.isMaintenanceMode();

		String maintenanceReason = maintenanceSetting.getMaintenanceReason() == null ? "N/A" : maintenanceSetting.getMaintenanceReason();
		String maintenanceEnd = maintenanceSetting.getMaintenanceEnd() == null ? "N/A" : maintenanceSetting.getMaintenanceEnd();

		ServerPing originalPing = event.getPing();
		ServerPing.Builder pingBuilder = originalPing.asBuilder();

		if (maintenanceMode) {
			pingBuilder.version(new ServerPing.Version(47, "§4Wartungen..."));

			pingBuilder.description(LegacyComponentSerializer.legacySection().deserialize(ColorUtil.format(
					Settings.MAINTENANCE_MOTD
							.replaceAll("%end_time%", maintenanceEnd)
							.replaceAll("%reason%", maintenanceReason)
			)));
		} else {
			pingBuilder.description(LegacyComponentSerializer.legacySection().deserialize(ColorUtil.format(Settings.MOTD)));
		}

		List<ServerPing.SamplePlayer> samplePlayers = new java.util.ArrayList<>();
//
//		pingBuilder.players(new ServerPing.Players(
//				6969, // Max Players
//				FlockSystem.getInstance().getProxy().getPlayerCount(), // Online Players
//				samplePlayers // Optional: Sample players
//		));

		pingBuilder.samplePlayers(new ServerPing.Players(FlockSystem.getInstance().getProxy().getPlayerCount(), 6969, samplePlayers).getSample().toArray(new ServerPing.SamplePlayer[0]));

		event.setPing(pingBuilder.build());
	}

}
