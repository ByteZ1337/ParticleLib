/*
 * MIT License
 *
 * Copyright (c) 2021 ByteZ1337
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
 *
 * @author ByteZ
 */
public final class PlayerConnectionCache implements Listener {
    
    /**
     * A {@link HashMap} containing a cache of player connections
     */
    private final Map<Player, Object> cache = new HashMap<>();
    
    /**
     * Creates a new {@link PlayerConnectionCache} and registers itself
     * as an event listener. The plugin is retrieved from the PluginClassLoader.
     */
    public PlayerConnectionCache() {
        Bukkit.getServer().getPluginManager().registerEvents(this, ReflectionUtils.plugin);
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