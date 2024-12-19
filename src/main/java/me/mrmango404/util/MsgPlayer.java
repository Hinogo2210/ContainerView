package me.mrmango404.util;

import me.mrmango404.ConfigHandler;
import org.bukkit.entity.Player;

public class MsgPlayer {

	public enum MESSAGE_FIELD {
		PREFIX,
		GUI_TITLE,
		CHAT_ICON,
		CHAT_BODY,
		CHAT_CLICKABLE,
		CHAT_HOVER,
		ENABLED,
		DISABLED,
		NOT_ENABLED,
		END,
		NOT_VIEWABLE,
		NOT_FOUND,
		NO_PERMISSION,
		UNKNOWN_USAGE,
		RELOADED
	}

	/**
	 * Receive a message field then send color code translated message to the player.
	 *
	 * @param player       Target player.
	 * @param messageField Which message in en_us.yml should the plugin send.
	 */
	public static void send(Player player, MESSAGE_FIELD messageField) {

		String message = "Error, please contact plugin author.";

		if (messageField == MESSAGE_FIELD.PREFIX) {
			message = ConfigHandler.Msg.PREFIX;
		} else if (messageField == MESSAGE_FIELD.GUI_TITLE) {
			message = ConfigHandler.Msg.GUI_TITLE;
		} else if (messageField == MESSAGE_FIELD.CHAT_ICON) {
			message = ConfigHandler.Msg.CHAT_ICON_SINGLE;
		} else if (messageField == MESSAGE_FIELD.CHAT_CLICKABLE) {
			message = ConfigHandler.Msg.CHAT_CLICKABLE;
		} else if (messageField == MESSAGE_FIELD.CHAT_HOVER) {
			message = ConfigHandler.Msg.CHAT_HOVER;
		} else if (messageField == MESSAGE_FIELD.ENABLED) {
			message = ConfigHandler.Msg.ENABLED.replace("{time}", ConfigHandler.VIEW_SESSION_DURATION + "");
		} else if (messageField == MESSAGE_FIELD.DISABLED) {
			message = ConfigHandler.Msg.DISABLED;
		} else if (messageField == MESSAGE_FIELD.NOT_ENABLED) {
			message = ConfigHandler.Msg.NOT_ENABLED;
		} else if (messageField == MESSAGE_FIELD.NOT_FOUND) {
			message = ConfigHandler.Msg.NOT_FOUND;
		} else if (messageField == MESSAGE_FIELD.NOT_VIEWABLE) {
			message = ConfigHandler.Msg.NOT_VIEWABLE;
		} else if (messageField == MESSAGE_FIELD.NO_PERMISSION) {
			message = ConfigHandler.Msg.NO_PERMISSION;
		} else if (messageField == MESSAGE_FIELD.UNKNOWN_USAGE) {
			message = ConfigHandler.Msg.UNKNOWN_USAGE;
		} else if (messageField == MESSAGE_FIELD.RELOADED) {
			message = ConfigHandler.Msg.RELOADED;
		}

		message = ConfigHandler.Msg.PREFIX + message;
		message = Translate.color(message);
		player.sendMessage(message);
	}
}
