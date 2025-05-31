package tcc.youajing.tcctools.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import tcc.youajing.tcctools.services.MCPlayerManager;
import tcc.youajing.tcctools.utils.MyPair;

import java.util.ArrayList;

// 创建一个继承自PlaceholderExpansion的类
public class TccToolsExpansion extends PlaceholderExpansion {
    private final Plugin plugin;

    public TccToolsExpansion(Plugin plugin) {
        this.plugin = plugin;
    }

    // 重写getIdentifier方法，返回一个唯一的标识符
    @Override
    public @NotNull String getIdentifier() {
        return "NeoTccTools";
    }

    // 重写getAuthor方法，返回插件的作者
    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    // 重写getVersion方法，返回插件的版本
    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    // 重写onPlaceholderRequest方法，处理占位符请求
    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {

        if (identifier.equals("debris")) {
            if (player == null) return null2
            return "" + MCPlayerManager.getDebrisMined(player.getUniqueId());
        } else if (identifier.equals("fish")) {
            if (player == null) return null;
            return "" + MCPlayerManager.getFishedTimes(player.getUniqueId());
        } else if(identifier.startsWith("debris-")) {
            ArrayList<MyPair<String, Integer>> ranks = MCPlayerManager.get_debris_mined_rank();

            int num = Integer.parseInt(identifier.split("[_-]")[1]);
            if(ranks.size() < num || num <= 0) {
                return "非法排名" + num;
            }
            if(identifier.endsWith("-name")) {
                return ranks.get(num-1).getKey();
            } else if(identifier.endsWith("-value")) {
                return ranks.get(num-1).getValue().toString();
            }
        } else if(identifier.startsWith("fish-")) {
            ArrayList<MyPair<String, Integer>> ranks = MCPlayerManager.get_fished_times_rank();

            int num = Integer.parseInt(identifier.split("[_-]")[1]);
            if(ranks.size() < num || num <= 0) {
                return "非法排名" + num;
            }
            if(identifier.endsWith("-name")) {
                return ranks.get(num-1).getKey();
            } else if(identifier.endsWith("-value")) {
                return ranks.get(num-1).getValue().toString();
            }
        }

        return null;
    }
}

