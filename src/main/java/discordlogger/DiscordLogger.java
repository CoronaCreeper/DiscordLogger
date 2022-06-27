package discordlogger;

import club.minnced.discord.webhook.WebhookClient;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class DiscordLogger extends JavaPlugin implements Listener {

    public static Logger logger;
    public static WebhookClient webhookClient;
    public static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new PlayerLogs(), this);
        this.getConfig().addDefault("webhook-url", "https://example.com");
        this.getConfig().addDefault("events.player.commandUsed", false);
        this.getConfig().addDefault("events.player.kick", true);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        try {
            webhookClient = WebhookClient.withUrl(Objects.requireNonNull(this.getConfig().getString("webhook-url")));
            webhookClient.send(":white_check_mark: DiscordLogger Enabled");
        } catch (Exception e) {
            logger.warning(ChatColor.RED + "Disabling DiscordLogger - "+e);
            this.getPluginLoader().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try {
            webhookClient.send(":x: DiscordLogger Disabled");
        } catch (Exception ignored) {}
    }
}
