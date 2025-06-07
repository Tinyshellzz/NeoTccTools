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
    public static String server_ip;
    public static int rcon_port;
    public static String rcon_password;


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
        server_ip = yamlconfig.getString("server_ip");
        rcon_port = yamlconfig.getInt("rcon_port");
        rcon_password = yamlconfig.getString("rcon_password");
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
