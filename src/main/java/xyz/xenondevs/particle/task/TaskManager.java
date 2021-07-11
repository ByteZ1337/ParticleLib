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
import xyz.xenondevs.particle.utils.ParticleUtils;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A manager to handle different {@link ParticleTask ParticleTasks}
 *
 * @author ByteZ
 * @see ParticleTask
 */
public final class TaskManager {
    
    /**
     * Singleton instance of the {@link TaskManager}
     */
    private final static TaskManager INSTANCE = new TaskManager();
    
    /**
     * Private constructor because this is a singleton class.
     */
    private TaskManager() {
    }
    
    /**
     * Starts a new Timer for the given task.
     *
     * @param task the task that should be added to the scheduler
     * @return the id of the BukkitTask which can be cancelled using {@link TaskManager#stopTask(int)}
     * @see TaskManager#stopTask(int)
     */
    public int startTask(ParticleTask task) {
        //noinspection CodeBlock2Expr
        int taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(ReflectionUtils.plugin, () -> {
            ParticleUtils.sendBulk(task.getPackets(), task.getTargetPlayers());
        }, 0, task.getTickDelay()).getTaskId();
        
        return taskId;
    }
    
    /**
     * Stops a task that is currently running.
     *
     * @param taskId the id of the task to be stopped.
     */
    public void stopTask(int taskId) {
        Bukkit.getScheduler().cancelTask(taskId);
    }
    
    /**
     * Get the singleton instance of the {@link TaskManager}
     *
     * @return the singleton instance of the {@link TaskManager}
     */
    public static TaskManager getTaskManager() {
        return INSTANCE;
    }
    
    /**
     * Starts a new {@link GlobalTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @return the id of the BukkitTask which can be cancelled using {@link TaskManager#stopTask(int)}
     * @see GlobalTask
     * @see TaskManager#stopTask(int)
     */
    public static int startGlobalTask(List<Object> packets, int tickDelay) {
        return getTaskManager().startTask(new GlobalTask(packets, tickDelay));
    }
    
    /**
     * Starts a new {@link TargetedTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param targets   A {@link Collection} of {@link Player Players} that will receive the particles.
     * @return the id of the BukkitTask which can be cancelled using {@link TaskManager#stopTask(int)}
     * @see TargetedTask
     * @see TaskManager#stopTask(int)
     */
    public static int startTargetedTask(List<Object> packets, int tickDelay, Collection<Player> targets) {
        return getTaskManager().startTask(new TargetedTask(packets, tickDelay, targets));
    }
    
    /**
     * Starts a new {@link SingularTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param target    The {@link UUID} of the target {@link Player}
     * @return the id of the BukkitTask which can be cancelled using {@link TaskManager#stopTask(int)}
     * @see SingularTask
     * @see TaskManager#stopTask(int)
     */
    public static int startSingularTask(List<Object> packets, int tickDelay, UUID target) {
        return getTaskManager().startTask(new SingularTask(packets, tickDelay, target));
    }
    
    /**
     * Starts a new {@link SingularTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param target    The target {@link Player}
     * @return the id of the BukkitTask which can be cancelled using {@link TaskManager#stopTask(int)}
     * @see SingularTask
     * @see TaskManager#stopTask(int)
     */
    public static int startSingularTask(List<Object> packets, int tickDelay, Player target) {
        return getTaskManager().startTask(new SingularTask(packets, tickDelay, target));
    }
    
    /**
     * Starts a new {@link FilteredTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param filter    The {@link Predicate} to filter the {@link Player Players}
     * @return the id of the BukkitTask which can be cancelled using {@link TaskManager#stopTask(int)}
     * @see FilteredTask
     * @see TaskManager#stopTask(int)
     */
    public static int startFilteredTask(List<Object> packets, int tickDelay, Predicate<Player> filter) {
        return getTaskManager().startTask(new FilteredTask(packets, tickDelay, filter));
    }
    
    /**
     * Starts a new {@link SuppliedTask}.
     *
     * @param packets   {@link List} of packets
     * @param tickDelay The delay of ticks between each execution
     * @param supplier  The {@link Supplier} used to retrieve the {@link Collection} of target {@link Player Players}
     * @return the id of the BukkitTask which can be cancelled using {@link TaskManager#stopTask(int)}
     * @see SuppliedTask
     * @see TaskManager#stopTask(int)
     */
    public static int startSuppliedTask(List<Object> packets, int tickDelay, Supplier<Collection<Player>> supplier) {
        return getTaskManager().startTask(new SuppliedTask(packets, tickDelay, supplier));
    }
    
}
