package io.github.guilhermemelo01.devbot.commands;

import io.github.guilhermemelo01.devbot.main.BotDiscord;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.utils.config.AuthorizationConfig;
import org.jetbrains.annotations.NotNull;

public class Ping extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        MessageChannel channel = event.getChannel();

        if (args[0].equalsIgnoreCase(BotDiscord.prefixMap.get(event.getGuild().getIdLong()) + "ping")) {
            if (BotDiscord.jda == null){
                BotDiscord.jda = new JDAImpl(new AuthorizationConfig(""));
                long pingDefault = BotDiscord.jda.getGatewayPing() + 50;
                channel.sendMessage(pingDefault + "ms").queue();
            } else{
                channel.sendMessage(BotDiscord.jda.getGatewayPing() + "ms").queue();
            }
        }
    }
}
