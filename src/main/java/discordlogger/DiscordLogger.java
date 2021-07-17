package discordlogger;

import club.minnced.discord.webhook.WebhookClient;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordLogger extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new PlayerLogs(), this);
        this.getConfig().addDefault("webhook-url", "https://example.com");
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        if(getConfig().getString("webhook-url")=="https://example.com") {
            System.out.println(ChatColor.RED + "Disabling DiscordLogger - Webhook URL invalid");
            this.getPluginLoader().disablePlugin(this);
        }
        WebhookClient webhookClient = WebhookClient.withUrl(this.getConfig().getString("webhook-url"));
        webhookClient.send(":white_check_mark: DiscordLogger Enabled");
    }

    @Override
    public void onDisable() {
        WebhookClient webhookClient = WebhookClient.withUrl(this.getConfig().getString("webhook-url"));
        webhookClient.send(":x: DiscordLogger Disabled");
    }
}
