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
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleConstants;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Useful utilities for handling large amounts of particles.
 * <p>
 * <b>Note:</b> While not recommended, these methods can also
 * be used with non particle packets.
 *
 * @author ByteZ
 */
public final class ParticleUtils {
    
    /**
     * Sends the given {@link Collection} of packets to the target player
     * while caching the PlayerConnection in a local variable instead of
     * calling the ConnectionCache for each packet.
     *
     * @param packets the packets that should be sent to the player
     * @param player  the target {@link Player} that should receive the packets
     */
    public static void sendBulk(Collection<Object> packets, Player player) {
        Object connection = ReflectionUtils.PLAYER_CONNECTION_CACHE.getConnection(player);
        for (Object packet : packets) {
            try {
                ParticleConstants.PLAYER_CONNECTION_SEND_PACKET_METHOD.invoke(connection, packet);
            } catch (Exception ignored) {// Ignored in case non packet objects are in the packets Iterable
            }
        }
    }
    
    /**
     * Sends the given {@link Collection} of packets to the target players
     * while caching the PlayerConnections instead of calling the ConnectionCache
     * for each packet.
     *
     * @param packets the packets that should be sent to the players
     * @param players the target {@link Player Players} that should receive the packets
     */
    public static void sendBulk(Collection<Object> packets, Collection<Player> players) {
        for (Player player : players) {
            sendBulk(packets, player);
        }
    }
    
    /**
     * Sends the given {@link Collection} of packets to every player that is currently on
     * the server. Also caches the PlayerConnections instead of calling the ConnectionCache
     * for each packet.
     *
     * @param packets the packets that should be sent
     */
    public static void sendBulk(Collection<Object> packets) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendBulk(packets, player);
        }
    }
    
    /**
     * Converts the given {@link Collection} of {@link ParticleBuilder ParticleBuilders}
     * to packets and sends them to the target player. Also caches the PlayerConnection
     * in a local variable instead of calling the ConnectionCache for each packet.
     *
     * @param builders the {@link ParticleBuilder ParticleBuilders} that should be converted and sent to the player
     * @param player   the target {@link Player} that should receive the packets
     */
    public static void sendBulkBuilders(Collection<ParticleBuilder> builders, Player player) {
        sendBulk(builders.stream().map(ParticleBuilder::toPacket).collect(Collectors.toList()), player);
    }
    
    /**
     * Converts the given {@link Collection} of {@link ParticleBuilder ParticleBuilders}
     * to packets and sends them to the target players. Also caches the PlayerConnections
     * in a local variable instead of calling the ConnectionCache for each packet.
     *
     * @param builders the {@link ParticleBuilder ParticleBuilders} that should be converted and sent to the players
     * @param players  the target {@link Player Players} that should receive the packets
     */
    public static void sendBulkBuilders(Collection<ParticleBuilder> builders, Collection<Player> players) {
        List<Object> packets = builders.stream().map(ParticleBuilder::toPacket).collect(Collectors.toList());
        for (Player player : players) {
            sendBulk(packets, player);
        }
    }
    
    /**
     * Converts the given {@link Collection} of {@link ParticleBuilder ParticleBuilders}
     * to packets and sends them to all players that are currently on the server. Also
     * caches the PlayerConnections in a local variable instead of calling the ConnectionCache for each packet.
     *
     * @param builders the {@link ParticleBuilder ParticleBuilders} that should be converted and sent to the players
     */
    public static void sendBulkBuilders(Collection<ParticleBuilder> builders) {
        List<Object> packets = builders.stream().map(ParticleBuilder::toPacket).collect(Collectors.toList());
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendBulk(packets, player);
        }
    }
}
