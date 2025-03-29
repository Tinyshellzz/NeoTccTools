package tcc.youajing.tcctools;

import com.google.gson.Gson;
import org.bukkit.plugin.Plugin;
import tcc.youajing.tcctools.config.PluginConfig;
import tcc.youajing.tcctools.database.MCPlayerMapper;
import tcc.youajing.tcctools.database.PlacedDebrisMapper;
import tcc.youajing.tcctools.services.ToolsCommandService;

public class ObjectPool {
    public static Plugin plugin;
    public static Gson gson = new Gson();
    public static MCPlayerMapper mcPlayerMapper;
    public static PlacedDebrisMapper placedDebrisMapper;
    public static ToolsCommandService toolsCommandService;
}
