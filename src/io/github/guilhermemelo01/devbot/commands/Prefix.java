package io.github.guilhermemelo01.devbot.commands;

import io.github.guilhermemelo01.devbot.database.CRUD;
import io.github.guilhermemelo01.devbot.main.BotDiscord;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class Prefix extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        MessageChannel channel = event.getChannel();

        if (args[0].equalsIgnoreCase(BotDiscord.prefixMap.get(event.getGuild().getId()) + "prefix")) {
            channel.sendMessage("O prefixo para esse servidor é: " + BotDiscord.prefixMap.get(
                    event.getGuild().getId())).queue();
        }

        if(args[0].equalsIgnoreCase(BotDiscord.prefixMap.get(event.getGuild().getId()) + "setprefix")){
            BotDiscord.prefixMap.replace(event.getGuild().getId(), args[1].charAt(0));
            try {
                CRUD.update(event.getGuild().getId(), args[1].charAt(0));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            channel.sendMessage("O prefixo do servidor foi alterado para: " + BotDiscord.prefixMap.get(
                    event.getGuild().getId())).queue();
        }
    }
}
