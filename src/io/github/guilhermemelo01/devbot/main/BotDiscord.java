package io.github.guilhermemelo01.devbot.main;

import io.github.guilhermemelo01.devbot.commands.Ping;
import io.github.guilhermemelo01.devbot.commands.Prefix;
import io.github.guilhermemelo01.devbot.database.CRUD;
import io.github.guilhermemelo01.devbot.database.Config;
import io.github.guilhermemelo01.devbot.events.MemberJoin;
import io.github.guilhermemelo01.devbot.events.MemberLeave;
import jdk.dynalink.linker.LinkerServices;
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

    static {
        try {
            jda = JDABuilder.create("OTk3NjIyMDk5MDM0MjQzMTIy.GawKzX.KZG2az5ULM0146IsbbDbALyFbrSkLvcFNA7508",
                    EnumSet.allOf(GatewayIntent.class)).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Character> prefixMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException, SQLException, IOException {

        Config.createFilesAndTable();

        jda.addEventListener(new Ping());
        jda.addEventListener(new MemberJoin());
        jda.addEventListener(new MemberLeave());
        jda.addEventListener(new Prefix());
        for (Guild guild : jda.awaitReady().getGuilds()){
            CRUD.insert(guild.getId(), '$');
        }

        for (Guild guild : jda.awaitReady().getGuilds()){
            CRUD.select(guild.getId());
        }

    }
}
