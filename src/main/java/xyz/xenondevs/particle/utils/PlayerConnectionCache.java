package xyz.xenondevs.particle.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * A cache for player connections to avoid calling reflective
 * methods for each packet.
 */
public class PlayerConnectionCache implements Listener {
    
    /**
     * A {@link HashMap} containing a cache of player connections
     */
    private final Map<Player, Object> cache = new HashMap<>();
    
    /**
     * Creates a new {@link PlayerConnectionCache} and registers itself
     * as an event listener. The plugin is retrieved from the PluginClassLoader.
     */
    public PlayerConnectionCache() {
        Bukkit.getServer().getPluginManager().registerEvents(this, ReflectionUtils.PLUGIN);
    }
    
    /**
     * Gets the PlayerConnection of the specified {@link Player} from
     * the cache. If the cache doesn't contain the PlayerConnection
     * yet it uses reflective calls to retrieve the connection
     * and adds it to the cache (If the player is online).
     *
     * @param player the target {@link Player}
     * @return the PlayerConnection of the specified {@link Player}
     */
    public Object getConnection(Player player) {
        Object connection = cache.get(player);
        if (connection == null) {
            connection = ReflectionUtils.getPlayerConnection(player);
            if (player.isOnline())
                cache.put(player, connection);
        }
        return connection;
    }
    
    /**
     * Removes a leaving player from the {@link #cache}.
     *
     * @param event The {@link PlayerQuitEvent} that should be handled
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void handlePlayerQuit(PlayerQuitEvent event) {
        cache.remove(event.getPlayer());
    }
    
}