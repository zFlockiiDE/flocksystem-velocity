package ovh.fedox.flocksystem.constants;


import dev.simplix.protocolize.data.ItemType;
import lombok.Getter;

/**
 * PunishReason.java -
 * <p>
 * Created on 4/2/2025 at 6:29 AM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@Getter
public enum PunishReason {

	HACKING(1, "Hacking / Cheating", "Killaura, Autoclicker, X-Ray, etc.", PunishType.BAN, 30, ItemType.DIAMOND_SWORD),
	SPAM(2, "Spam", "Zeichenspam, Nachrichtenspam etc.", PunishType.MUTE, 2, ItemType.PAPER),
	ADVERTISEMENT(3, "Advertisement", "Werbung für andere Server, Dienste etc.", PunishType.MUTE, 4, ItemType.BOOK),
	INSULT(4, "Beleidigung / Provokation", "Beleidigungen, Hate Speech etc.", PunishType.MUTE, 6, ItemType.IRON_SWORD),
	RACISM(5, "Rassismus / Nationalsozialismus", "Rassistische Äußerungen, Rassismus etc.", PunishType.BAN, 3, ItemType.IRON_AXE),
	PHISHING(6, "Phishing", "Versuch, Accountdaten zu stehlen", PunishType.BAN, 7, ItemType.IRON_PICKAXE),
	BUG_ABUSE(7, "Bug Abuse", "Ausnutzen von Fehlern im Spiel", PunishType.BAN, 1, ItemType.SPIDER_EYE),
	BAD_NAME(8, "Bad Name", "Unangemessener Name", PunishType.BAN, 1, ItemType.NAME_TAG),
	BAD_SKIN(9, "Bad Skin", "Unangemessener Skin (Böse Symbole, Nacktskins etc)", PunishType.BAN, 1, ItemType.LEATHER_CHESTPLATE),
	TEAMING(10, "Teaming", "Zusammenarbeit mit anderen Spielern in PVP Modis", PunishType.BAN, 1, ItemType.TOTEM_OF_UNDYING),
	HAUSVERBOT(11, "Hausverbot", "Hausverbot auf dem Server. (Nur nach Absprache nutzen!!)", PunishType.BAN, 999, ItemType.BARRIER);

	private final int id;
	private final String name;
	private final String description;
	private final PunishType type;
	private final int duration;
	private final ItemType icon;

	PunishReason(int id, String name, String description, PunishType type, int duration, ItemType icon) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.duration = duration;
		this.icon = icon;
	}

}
