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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * A {@link ParticleTask} implementation that only
 * targets a single {@link Player}.
 *
 * @author ByteZ
 * @see ParticleTask
 */
public final class SingularTask extends ParticleTask {
    
    /**
     * The {@link UUID} of the target {@link Player}
     */
    private final UUID target;
    
    /**
     * Creates a new {@link SingularTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param target    The {@link UUID} of the target {@link Player}
     */
    public SingularTask(List<Object> packets, int tickDelay, UUID target) {
        super(packets, tickDelay);
        this.target = Objects.requireNonNull(target);
    }
    
    /**
     * Creates a new {@link SingularTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param target    The target {@link Player}
     */
    public SingularTask(List<Object> packets, int tickDelay, Player target) {
        super(packets, tickDelay);
        this.target = Objects.requireNonNull(target).getUniqueId();
    }
    
    /**
     * Gets a singleton list with the target {@link Player}
     * or an empty list if the player isn't online.
     *
     * @return a singleton list with the target {@link Player}
     */
    @Override
    public List<Player> getTargetPlayers() {
        Player player = Bukkit.getPlayer(target);
        return player == null ? Collections.EMPTY_LIST : Collections.singletonList(player);
    }
}
