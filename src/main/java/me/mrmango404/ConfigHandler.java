package me.mrmango404;

import me.mrmango404.util.DebugManager;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class ConfigHandler {
	// Config overall settings
	public static int VIEW_RANGE;
	public static long VIEW_SESSION_DURATION;
	public static boolean GUI_AUTO_CLOSE;
	public static List<String> CONTAINERS;
	public static List<String> COLORS;
	public static boolean DEBUG;
	public static String LANGUAGE;

	// Config sound settings
	public final static class Sound {
		public static boolean ENABLE;
		public static String OPEN_SOUND;
		public static String CLOSE_SOUND;
	}

	// Config permission settings
	public final static class Permission {
		public static String USE;
		public static String RELOAD;
	}

	// Overall Messages
	public final static class Msg {
		public static String PREFIX;
		public static String GUI_TITLE;
		public static String CHAT_ICON_SINGLE;
		public static String CHAT_ICON_DOUBLE;
		public static String CHAT_BODY;
		public static String CHAT_CLICKABLE;
		public static String CHAT_HOVER;
		public static String END_BODY;
		public static String END_CLICKABLE;
		public static String END_HOVER;
		public static String ENABLED;
		public static String DISABLED;
		public static String NOT_ENABLED;
		public static String NOT_VIEWABLE;
		public static String NOT_FOUND;
		public static String NO_PERMISSION;
		public static String UNKNOWN_USAGE;
		public static String RELOADED;
		public static List<String> HELP;
	}

	public static void load() {
		Main mainClass = Main.getMain();
		String configPath = "config.yml";
		File configFile = new File(mainClass.getDataFolder(), configPath);

		// Create config.yml if it doesn't exist
		if (!configFile.exists()) {
			mainClass.saveResource(configPath, false);
		}

		// Load config.yml
		YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(
				new InputStreamReader(mainClass.getResource(configPath))
		);
		config.addDefaults(defaultConfig);

		loadConfigSettings(config);
		loadLanguageFile(mainClass);
	}

	private static void loadConfigSettings(YamlConfiguration config) {
		VIEW_RANGE = config.getInt("view_range");
		VIEW_SESSION_DURATION = config.getLong("view_session_duration");
		GUI_AUTO_CLOSE = config.getBoolean("gui_auto_close");
		CONTAINERS = config.getStringList("containers");
		COLORS = config.getStringList("colors");
		DEBUG = config.getBoolean("debug");
		// Default to en_us if not specified
		LANGUAGE = config.getString("language", "en_us");

		Sound.ENABLE = config.getBoolean("sound.enable");
		Sound.OPEN_SOUND = config.getString("sound.open_sound");
		Sound.CLOSE_SOUND = config.getString("sound.close_sound");
		Permission.USE = config.getString("permissions.use");
		Permission.RELOAD = config.getString("permissions.reload");
	}

	private static void loadLanguageFile(Main mainClass) {
		// Create lang directory if it doesn't exist
		File langDir = new File(mainClass.getDataFolder(), "lang");
		if (!langDir.exists()) {
			langDir.mkdir();
		}

		// Define language file path
		String langFileName = LANGUAGE + ".yml";
		File langFile = new File(langDir, langFileName);

		// Copy default language files from resources if they don't exist
		if (!langFile.exists()) {
			try {
				mainClass.saveResource("lang/" + langFileName, false);
			} catch (IllegalArgumentException e) {
				// If the requested language file doesn't exist in resources, fall back to en_us
				mainClass.getLogger().warning("Language file " + langFileName + " not found. Falling back to en_us.yml");
				LANGUAGE = "en_us";
				langFileName = "en_us.yml";
				langFile = new File(langDir, langFileName);
				if (!langFile.exists()) {
					mainClass.saveResource("lang/" + langFileName, false);
				}
			}
		}

		// Load the language file
		YamlConfiguration lang = YamlConfiguration.loadConfiguration(langFile);
		YamlConfiguration defaultLang = YamlConfiguration.loadConfiguration(
				new InputStreamReader(mainClass.getResource("lang/" + langFileName))
		);
		lang.addDefaults(defaultLang);

		// Load all message settings
		loadMessageSettings(lang);

		// Debug logging
		logDebugInfo();
	}

	private static void loadMessageSettings(YamlConfiguration lang) {
		Msg.PREFIX = lang.getString("prefix");
		Msg.GUI_TITLE = lang.getString("gui_title");
		Msg.CHAT_ICON_SINGLE = lang.getString("chat.icon_single");
		Msg.CHAT_ICON_DOUBLE = lang.getString("chat.icon_double");
		Msg.CHAT_BODY = lang.getString("chat.body");
		Msg.CHAT_CLICKABLE = lang.getString("chat.clickable");
		Msg.CHAT_HOVER = lang.getString("chat.hover");
		Msg.END_BODY = lang.getString("end.body");
		Msg.END_CLICKABLE = lang.getString("end.clickable");
		Msg.END_HOVER = lang.getString("end.hover");
		Msg.ENABLED = lang.getString("enabled");
		Msg.DISABLED = lang.getString("disabled");
		Msg.NOT_ENABLED = lang.getString("not_enabled");
		Msg.NOT_VIEWABLE = lang.getString("not_viewable");
		Msg.NOT_FOUND = lang.getString("not_found");
		Msg.NO_PERMISSION = lang.getString("no_permission");
		Msg.UNKNOWN_USAGE = lang.getString("unknown_usage");
		Msg.RELOADED = lang.getString("reloaded");
		Msg.HELP = lang.getStringList("help");
	}

	private static void logDebugInfo() {
		DebugManager.log("LANGUAGE: " + LANGUAGE);
		DebugManager.log("VIEW_RANGE: " + VIEW_RANGE);
		DebugManager.log("VIEW_SESSION_DURATION: " + VIEW_SESSION_DURATION);
		DebugManager.log("GUI_AUTO_CLOSE: " + GUI_AUTO_CLOSE);
		DebugManager.log("CONTAINERS: " + CONTAINERS);
		DebugManager.log("COLORS: " + COLORS);
		DebugManager.log("SOUND.ENABLE: " + Sound.ENABLE);
		DebugManager.log("SOUND.OPEN_SOUND: " + Sound.OPEN_SOUND);
		DebugManager.log("SOUND.CLOSE_SOUND: " + Sound.CLOSE_SOUND);
		DebugManager.log("PERMISSION.USE: " + Permission.USE);
		DebugManager.log("PERMISSION.RELOAD: " + Permission.RELOAD);
		DebugManager.log("MSG.PREFIX: " + Msg.PREFIX);
		DebugManager.log("MSG.GUI_TITLE: " + Msg.GUI_TITLE);
		DebugManager.log("MSG.CHAT_ICON_SINGLE: " + Msg.CHAT_ICON_SINGLE);
		DebugManager.log("MSG.CHAT_ICON_DOUBLE: " + Msg.CHAT_ICON_DOUBLE);
		DebugManager.log("MSG.CHAT_BODY: " + Msg.CHAT_BODY);
		DebugManager.log("MSG.CHAT_CLICKABLE: " + Msg.CHAT_CLICKABLE);
		DebugManager.log("MSG.CHAT_HOVER: " + Msg.CHAT_HOVER);
		DebugManager.log("MSG.END_BODY: " + Msg.END_BODY);
		DebugManager.log("MSG.END_CLICKABLE: " + Msg.END_CLICKABLE);
		DebugManager.log("MSG.END_HOVER: " + Msg.END_HOVER);
		DebugManager.log("MSG.ENABLED: " + Msg.ENABLED);
		DebugManager.log("MSG.DISABLED: " + Msg.DISABLED);
		DebugManager.log("MSG.NOT_ENABLED: " + Msg.NOT_ENABLED);
		DebugManager.log("MSG.NOT_VIEWABLE: " + Msg.NOT_VIEWABLE);
		DebugManager.log("MSG.NOT_FOUND: " + Msg.NOT_FOUND);
		DebugManager.log("MSG.NO_PERMISSION: " + Msg.NO_PERMISSION);
		DebugManager.log("MSG.UNKNOWN_USAGE: " + Msg.UNKNOWN_USAGE);
		DebugManager.log("MSG.RELOADED: " + Msg.RELOADED);
		DebugManager.log("MSG.HELP: " + Msg.HELP);
	}
}