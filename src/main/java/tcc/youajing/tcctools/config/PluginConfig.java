package tcc.youajing.tcctools.config;


import tcc.youajing.tcctools.ObjectPool;
import org.bukkit.configuration.file.YamlConfiguration;

import static tcc.youajing.tcctools.ObjectPool.gson;
import static tcc.youajing.tcctools.ObjectPool.plugin;


public class PluginConfig {
    public static boolean debug = false;
    public static int WitherSoundRange = 128;
    public static int EnderDragonSoundRange = 128;
    public static String trigger_welcome_message = "";
    private static final ConfigWrapper configWrapper = new ConfigWrapper(plugin, "config.yml");
    public static String db_host;
    public static int db_port;
    public static String db_user;
    public static String db_passwd;
    public static String db_database;
    public static double tpa_play_time_requirement;
    public static double back_play_time_for_new_players;


    public static void reload() {
        configWrapper.reloadConfig(); // 重新加载配置文件

        YamlConfiguration yamlconfig = configWrapper.getConfig();
        debug = yamlconfig.getBoolean("debug");
        WitherSoundRange = yamlconfig.getInt("WitherSoundRange");
        EnderDragonSoundRange = yamlconfig.getInt("EnderDragonSoundRange");
        trigger_welcome_message = yamlconfig.getString("trigger_welcome_message");
        db_host = yamlconfig.getString("db_host");
        db_port = yamlconfig.getInt("db_port");
        db_user = yamlconfig.getString("db_user");
        db_passwd = yamlconfig.getString("db_passwd");
        db_database = yamlconfig.getString("db_database");
        tpa_play_time_requirement = yamlconfig.getDouble("tpa_play_time_requirement");
        back_play_time_for_new_players = yamlconfig.getDouble("back_play_time_for_new_players");
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
