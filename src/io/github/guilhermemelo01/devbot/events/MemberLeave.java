package io.github.guilhermemelo01.devbot.events;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MemberLeave extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        TextChannel textChannelById = event.getGuild().getTextChannelById(997888051932057710L);
        String userName = event.getUser().getName();

        assert textChannelById != null;
        textChannelById.sendMessage(userName + " saiu do servidor!").queue();
    }
}
