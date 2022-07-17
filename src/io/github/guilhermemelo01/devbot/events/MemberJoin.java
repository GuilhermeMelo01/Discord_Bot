package io.github.guilhermemelo01.devbot.events;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MemberJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

        TextChannel textChannelById = event.getGuild().getTextChannelById(997883620335300638L);

        String userName = event.getUser().getName();

        assert textChannelById != null;
        textChannelById.sendMessage(userName + " entrou no servidor!").queue();

    }
}
