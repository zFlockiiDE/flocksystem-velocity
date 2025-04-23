package ovh.fedox.flocksystem.listener;


import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.mineacademy.vfo.remain.Remain;
import ovh.fedox.flocksystem.FlockSystem;
import ovh.fedox.flocksystem.settings.Settings;
import ovh.fedox.flocksystem.util.ColorUtil;

import java.util.List;

/**
 * ServerListener.java -
 * <p>
 * Created on 3/30/2025 at 4:17 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

public final class ServerListener {

	@Subscribe
	public void onServerConnect(ServerConnectedEvent event) {
		refreshTablist(event.getPlayer());
	}

	@Subscribe
	public void onServerDisconnect(DisconnectEvent event) {
		refreshTablist(event.getPlayer());
	}

	private void refreshTablist(Player player) {
		StringBuilder header = new StringBuilder();
		StringBuilder footer = new StringBuilder();

		List<String> headerList = Settings.Tablist.HEADER;
		List<String> footerList = Settings.Tablist.FOOTER;

		for (String s : headerList) {
			header.append(s).append("\n");
		}

		for (String s : footerList) {
			footer.append(s).append("\n");
		}

		String formattedHeader = header.toString().replaceAll("%online%", FlockSystem.getInstance().getProxy().getPlayerCount() + "");
		String formattedFooter = footer.toString().replaceAll("%online%", FlockSystem.getInstance().getProxy().getPlayerCount() + "");

		Component headerComponent = Component.text(ColorUtil.format(formattedHeader));
		Component footerComponent = Component.text(ColorUtil.format(formattedFooter));

		for (Player onlinePlayer : Remain.getOnlinePlayers()) {
			Audience.audience(onlinePlayer).sendPlayerListHeaderAndFooter(headerComponent, footerComponent);
		}

		if (player != null) {
			Audience.audience(player).sendPlayerListHeaderAndFooter(headerComponent, footerComponent);
		}
	}

}
