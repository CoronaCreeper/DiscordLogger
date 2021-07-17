package discordlogger;

import club.minnced.discord.webhook.WebhookClient;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlayerLogs implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        File f = new File("plugins/DiscordLogger/config.yml");
        YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
        Player p = event.getPlayer();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        WebhookClient webhookClient = WebhookClient.withUrl(yamlFile.getString("webhook-url"));
        webhookClient.send("`" + event.getPlayer().getName() + "` has used command " + event.getMessage() +
                "\n" +
                "```"+
                "\nUUID: " + p.getUniqueId() +
                "\nIP: " + p.getAddress() +
                "\nIs OP: " + p.isOp() + "```");
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player p = event.getPlayer();
        String reason = event.getReason();
        File f = new File("plugins/DiscordLogger/config.yml");
        YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
        WebhookClient webhookClient = WebhookClient.withUrl(yamlFile.getString("webhook-url"));
        webhookClient.send("`" + p.getName() + "` has been kicked" +
                "\nReason: **" + reason + "**" +
                "\n" +
                "```"+
                "\nUUID: " + p.getUniqueId() +
                "\nIP: " + p.getAddress() +
                "\nIs OP: " + p.isOp() + "```");
    }

    @EventHandler
    public void onTP(PlayerTeleportEvent event) {
        Player p = event.getPlayer();
        String cause = String.valueOf(event.getCause());
        File f = new File("plugins/DiscordLogger/config.yml");
        YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
        WebhookClient webhookClient = WebhookClient.withUrl(yamlFile.getString("webhook-url"));
        webhookClient.send("`" + p.getName() + "` has teleported" +
                "\nReason: **" + cause + "**" +
                "\n" +
                "```"+
                "\nUUID: " + p.getUniqueId() +
                "\nIP: " + p.getAddress() +
                "\nIs OP: " + p.isOp() + "```");
    }
}