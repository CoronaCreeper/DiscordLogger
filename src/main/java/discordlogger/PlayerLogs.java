package discordlogger;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;

import java.time.Instant;

import static discordlogger.ConfigParser.isDisabled;
import static discordlogger.DiscordLogger.webhookClient;

public class PlayerLogs implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(isDisabled("events.player.commandUsed")) return;
        Player p = event.getPlayer();
        WebhookEmbedBuilder embedMessage = new WebhookEmbedBuilder()
                .setAuthor(new WebhookEmbed.EmbedAuthor("Command Used", null, null))
                .setColor(0xffff00)
                .addField(new WebhookEmbed.EmbedField(true, "Player", "`"+p.getName()+"`"))
                .addField(new WebhookEmbed.EmbedField(true, "UUID", "`"+p.getUniqueId()+"`"))
                .addField(new WebhookEmbed.EmbedField(true, "Command", "`"+event.getMessage()+"`"))
                .setTimestamp(Instant.now());
        webhookClient.send(embedMessage.build());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        if(isDisabled("events.player.kick")) return;
        Player p = event.getPlayer();
        WebhookEmbedBuilder embedMessage = new WebhookEmbedBuilder()
                .setAuthor(new WebhookEmbed.EmbedAuthor("Player kick", null, null))
                .setColor(0xffff00)
                .addField(new WebhookEmbed.EmbedField(true, "Player", "`"+p.getName()+"`"))
                .addField(new WebhookEmbed.EmbedField(true, "UUID", "`"+p.getUniqueId()+"`"))
                .addField(new WebhookEmbed.EmbedField(true, "Reason", "`"+event.getReason()+"`"))
                .setTimestamp(Instant.now());
        webhookClient.send(embedMessage.build());
    }
}