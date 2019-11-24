package games.bevs.library.modules.playerdata;


import games.bevs.library.modules.playerdata.types.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager<P extends PlayerData>
{
    private HashMap<UUID, P> playerDatas;

    public PlayerDataManager()
    {
        this.playerDatas = new HashMap<>();
    }

    public void registerPlayerData(P playerData)
    {
        this.playerDatas.put(playerData.getUniqueId(), playerData);
    }

    public void unregisterPlayerData(UUID uniqueId)
    {
        this.playerDatas.remove(uniqueId);
    }

    public P getPlayerData(UUID uniqueId)
    {
        this.playerDatas.forEach((key, value) -> {
            System.out.println(">>>> " + key + " : " + value);
        });
        return this.playerDatas.get(uniqueId);
    }

    public P getPlayerData(Player player)
    {
        return this.playerDatas.get(player.getUniqueId());
    }
}
