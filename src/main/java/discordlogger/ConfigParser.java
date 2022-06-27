package discordlogger;

public class ConfigParser {

    public static boolean isDisabled(String configLine) {
        return !DiscordLogger.instance.getConfig().getBoolean(configLine);
    }
}
