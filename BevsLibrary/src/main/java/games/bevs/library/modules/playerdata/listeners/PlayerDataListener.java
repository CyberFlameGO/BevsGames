package games.bevs.library.modules.playerdata.listeners;

import games.bevs.library.commons.CC;
import games.bevs.library.commons.Console;
import games.bevs.library.modules.playerdata.PlayerDataHandler;
import games.bevs.library.modules.playerdata.types.PlayerData;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@AllArgsConstructor
public class PlayerDataListener<P extends PlayerData> implements Listener
{
    private PlayerDataHandler<P> playerDataHandler;

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent e)
    {
        UUID uniqueId = e.getUniqueId();
        String username = e.getName();

        this.playerDataHandler.connnect(username, uniqueId);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        P playerdata = this.playerDataHandler.getPlayerDataManager().getPlayerData(player.getUniqueId());

        if(playerdata != null && playerdata.isLoaded())
        {
            player.setDisplayName(playerdata.getRank().getTagColor() + player.getName());
            player.setPlayerListName(player.getDisplayName());
            player.sendMessage(CC.gray + "Welcome, you're a " + playerdata.getRank().getColouredDisplayName());
            return;
        }

        Console.log("PlayerData", "failed to load " + player.getName() + " in a timely manner.");
        player.kickPlayer(CC.red + "Failed to load data\nplease reconnect");
        //oh no, nothing was loaded
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Player player = e.getPlayer();
        UUID uniqueId = player.getUniqueId();
        String username = player.getName();
        this.playerDataHandler.disconnnect(username, uniqueId);
    }
}
