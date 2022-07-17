package io.github.guilhermemelo01.devbot.main;

import io.github.guilhermemelo01.devbot.commands.Ping;
import io.github.guilhermemelo01.devbot.database.Config;
import io.github.guilhermemelo01.devbot.events.MemberJoin;
import io.github.guilhermemelo01.devbot.events.MemberLeave;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class BotDiscord {

    public static JDA jda;
    public static Map<Long, Character> prefixMap = new HashMap<>();
    public static Map<Long, Long> joinChannelMap = new HashMap<>();
    public static Map<Long, Long> leaveChannelMap = new HashMap<>();

    public static void main(String[] args) throws LoginException, InterruptedException, SQLException, IOException {

        Config.createFilesAndTable();

        JDA jda = JDABuilder.create("OTk3NjIyMDk5MDM0MjQzMTIy.GwjUba.Fi6fhhE2NMwRwnOwv0pOUT4_IGJ6gaxvp_0cC8",
                EnumSet.allOf(GatewayIntent.class)).build();

        jda.addEventListener(new Ping());
        jda.addEventListener(new MemberJoin());
        jda.addEventListener(new MemberLeave());

        for (Guild guild : jda.awaitReady().getGuilds()){
            prefixMap.put(guild.getIdLong(), '$');
            joinChannelMap.put(guild.getIdLong(), null);
            leaveChannelMap.put(guild.getIdLong(), null);
        }

    }
}
