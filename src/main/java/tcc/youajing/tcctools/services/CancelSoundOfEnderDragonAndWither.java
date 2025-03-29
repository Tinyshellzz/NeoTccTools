package tcc.youajing.tcctools.services;

import org.bukkit.plugin.Plugin;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;


public class CancelSoundOfEnderDragonAndWither {
    public CancelSoundOfEnderDragonAndWither(Plugin plugin) {
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(plugin, ListenerPriority.HIGHEST, PacketType.Play.Server.WORLD_EVENT) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        PacketContainer packetContainer = event.getPacket();
                        int eventId = packetContainer.getIntegers().read(0);

                        // 当事件ID为1023或1028时，将包中的布尔值改为false（取消末影龙死亡和凋灵召唤音效）
                        if (eventId == 1023 || eventId == 1028) {
                            packetContainer.getBooleans().write(0, false);
                        }
                    }
                }
        );
    }
}
