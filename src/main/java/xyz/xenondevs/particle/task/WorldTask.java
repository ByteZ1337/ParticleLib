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

package xyz.xenondevs.particle.task;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

/**
 * A {@link ParticleTask} implementation to send particles
 * to every player in a specific {@link #world}. This is needed
 * because minecraft doesn't serialize world data into the packet
 * which leads to particles being displayed in every world.
 *
 * @author ByteZ
 * @see ParticleTask
 */
public final class WorldTask extends ParticleTask {
    
    /**
     * The target {@link World}
     */
    private final World world;
    
    /**
     * Creates a new {@link WorldTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param world     The {@link World} in which the particles should be displayed.
     */
    public WorldTask(List<Object> packets, int tickDelay, World world) {
        super(packets, tickDelay);
        this.world = world;
    }
    
    /**
     * Gets all players in the {@link #world}.
     *
     * @return all players in the specified {@link #world}.
     */
    @Override
    public Collection<Player> getTargetPlayers() {
        return world.getPlayers();
    }
}
