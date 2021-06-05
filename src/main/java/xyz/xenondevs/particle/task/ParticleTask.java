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

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A {@link ParticleTask} is a repeating task that sends
 * a {@link #packets List of packets} to target {@link Player Players}
 * with a custom {@link #tickDelay}. The implementations
 * of this class support different ways of retrieving the
 * list of targeted {@link Player Players}. Custom implementations
 * of this class are encouraged if none of the built-in tasks match.
 * <p>
 * <b>A more in depth explanation can be found on the</b> <a href="https://github.com/ByteZ1337/ParticleLib/wiki/ParticleTasks">Wiki</a>
 * <p>
 * This class holds the basic information needed by the
 * {@link TaskManager}.
 *
 * @author ByteZ
 * @see GlobalTask
 * @see TargetedTask
 * @see SingularTask
 * @see FilteredTask
 * @see SuppliedTask
 */
public abstract class ParticleTask {
    
    /**
     * A {@link List} of packets to be sent to the target {@link Player Players}
     */
    private final List<Object> packets;
    /**
     * The amount of ticks between each execution
     */
    private final int tickDelay;
    
    /**
     * Creates a new {@link ParticleTask}
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     */
    public ParticleTask(List<Object> packets, int tickDelay) {
        this.packets = Objects.requireNonNull(packets);
        this.tickDelay = tickDelay;
    }
    
    /**
     * Gets the packets that should be sent to the target {@link Player Players}.
     *
     * @return the value of the {@link #packets} field
     */
    public List<Object> getPackets() {
        return packets;
    }
    
    /**
     * Gets the amount of ticks between each execution
     *
     * @return the value of the {@link #tickDelay} field
     */
    public int getTickDelay() {
        return tickDelay;
    }
    
    /**
     * Returns a {@link Collection} of {@link Player Players}
     * that will receive the {@link #packets}. This method
     * has to be implemented by every direct subclass of
     * {@link ParticleTask}
     *
     * @return A {@link Collection} of {@link Player Players} that should receive the specified {@link #packets}
     */
    public abstract Collection<Player> getTargetPlayers();
}
