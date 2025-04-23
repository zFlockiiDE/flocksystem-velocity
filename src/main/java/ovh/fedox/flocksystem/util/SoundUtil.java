package ovh.fedox.flocksystem.util;


import com.velocitypowered.api.proxy.Player;
import dev.simplix.protocolize.api.Protocolize;
import dev.simplix.protocolize.api.SoundCategory;
import dev.simplix.protocolize.api.player.ProtocolizePlayer;
import dev.simplix.protocolize.data.Sound;
import lombok.experimental.UtilityClass;

/**
 * SoundUtil.java - Sound utils for playing sounds
 * <p>
 * Created on 3/12/2025 at 1:37 PM by Fedox.
 * Copyright © 2025 Fedox. All rights reserved.
 */

@UtilityClass
public class SoundUtil {

	/**
	 * Play a custom action sound for the player
	 *
	 * @param player The player to play the sound for
	 * @param type   The type of sound to play {@link SoundType}
	 */
	public static void playSound(Player player, SoundType type) {
		ProtocolizePlayer protocolizePlayer = Protocolize.playerProvider().player(player.getUniqueId());
		switch (type) {
			case SUCCESS:
				protocolizePlayer.playSound(Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1.0f, 0.4f);
				break;
			case FAILURE:
				protocolizePlayer.playSound(Sound.ENTITY_IRON_GOLEM_HURT, SoundCategory.MASTER, 1.0f, 1f);
				break;
			case INFO:
				protocolizePlayer.playSound(Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1.0f, 0.4f);
				break;
			case WARNING:
				protocolizePlayer.playSound(Sound.BLOCK_NOTE_BLOCK_BASS, SoundCategory.MASTER, 1.0f, 0.4f);
				break;
		}
	}

	/**
	 * The sound types
	 */
	public enum SoundType {
		SUCCESS,
		FAILURE,
		INFO,
		WARNING
	}

}
