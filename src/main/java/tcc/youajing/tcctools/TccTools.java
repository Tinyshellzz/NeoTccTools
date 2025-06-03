package tcc.youajing.tcctools;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import tcc.youajing.tcctools.command.ToolsCommand;
import tcc.youajing.tcctools.command.WelcomeCommand;
import tcc.youajing.tcctools.config.PluginConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import tcc.youajing.tcctools.database.MCPlayerMapper;
import tcc.youajing.tcctools.database.PlacedDebrisMapper;
import tcc.youajing.tcctools.entity.MCPlayer;
import tcc.youajing.tcctools.listener.*;
import tcc.youajing.tcctools.placeholder.TccToolsExpansion;
import tcc.youajing.tcctools.services.CancelSoundOfEnderDragonAndWither;
import tcc.youajing.tcctools.services.ToolsCommandService;

import static tcc.youajing.tcctools.ObjectPool.mcPlayerMapper;


public class TccTools extends JavaPlugin {

    @Override
    public void onEnable() {
        // team,启动！
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.AQUA + "######################");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.AQUA + "#                    #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.AQUA + "#TccTools已启动#");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.AQUA + "#                    #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.AQUA + "######################");

        init();
        register();
    }

    public void init(){
        ObjectPool.plugin = this;
        PluginConfig.reload();

        mcPlayerMapper = new MCPlayerMapper();
        ObjectPool.toolsCommandService = new ToolsCommandService();
        ObjectPool.placedDebrisMapper = new PlacedDebrisMapper();
        new CancelSoundOfEnderDragonAndWither(this);

        @NotNull OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();
        for(OfflinePlayer offlinePlayer : offlinePlayers){
            mcPlayerMapper.insert_player(new MCPlayer(offlinePlayer.getName(), offlinePlayer.getUniqueId(), 0, 0));
        }
    }

    public void register() {
        // 注册监听器
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityInteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityExplodeListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerStatisticsListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerAtNameListener(), this);

        // 注册命令
        this.getCommand("tools").setExecutor(new ToolsCommand());
        this.getCommand("welcome").setExecutor(new WelcomeCommand());

        // 注册 PlaceHolder
        if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new TccToolsExpansion(this).register();
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "[TccTools]" + ChatColor.GREEN + "PlaceholderAPI 准备就绪");
        }
    }

    @Override
    public void onDisable() {
        //TODO
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.RED + "######################");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.RED + "#                    #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.RED + "#TccTools已关闭#");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.RED + "#                    #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[TccTools]" + ChatColor.RED + "######################");
    }

}