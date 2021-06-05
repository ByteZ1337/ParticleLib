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
 * A {@link ParticleTask} implementation that targets
 * a provided {@link Collection} of {@link Player Players}.
 * <p>
 * Please Note that players that leave the server aren't
 * automatically removed from the targets collection.
 *
 * @author ByteZ
 * @see ParticleTask
 */
public final class TargetedTask extends ParticleTask {
    
    /**
     * The {@link Player Players} that will receive the particles.
     */
    private final Collection<Player> targets;
    
    /**
     * Creates a new {@link TargetedTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param targets   A {@link Collection} of {@link Player Players} that will receive the particles.
     */
    public TargetedTask(List<Object> packets, int tickDelay, Collection<Player> targets) {
        super(packets, tickDelay);
        this.targets = Objects.requireNonNull(targets);
    }
    
    /**
     * Returns the pre-specified {@link Collection} of target {@link Player Players}
     *
     * @return {@link #targets}
     */
    @Override
    public Collection<Player> getTargetPlayers() {
        return targets;
    }
}
