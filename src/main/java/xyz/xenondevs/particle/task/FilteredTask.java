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

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A {@link ParticleTask} implementation using a java
 * {@link Predicate} to only send the packets to {@link Player Players}
 * that match the filter.
 *
 * @author ByteZ
 * @see ParticleTask
 */
public final class FilteredTask extends ParticleTask {
    
    /**
     * The {@link Predicate} used to filter all online players
     */
    private final Predicate<Player> filter;
    
    /**
     * Creates a new {@link FilteredTask}
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param filter    The {@link Predicate} to filter the {@link Player Players}
     */
    public FilteredTask(List<Object> packets, int tickDelay, Predicate<Player> filter) {
        super(packets, tickDelay);
        this.filter = Objects.requireNonNull(filter);
    }
    
    /**
     * Uses the provided {@link #filter} to filter all
     * online {@link Player Players} and only returns
     * players that match the given {@link Predicate}.
     *
     * @return a list of {@link Player Players} matching the {@link #filter}
     */
    @Override
    public Collection<Player> getTargetPlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(filter).collect(Collectors.toList());
    }
}
