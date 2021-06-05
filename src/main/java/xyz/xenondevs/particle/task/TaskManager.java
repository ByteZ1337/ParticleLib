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
import xyz.xenondevs.particle.utils.ParticleUtils;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

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
     * A list of IDs of the currently running tasks
     */
    private final List<Integer> runningTasks = new ArrayList<>();
    
    /**
     * Starts a new Timer for the given task.
     *
     * @param task the task that should be added to the scheduler
     * @return the id of the BukkitTask which can be canceled using {@link TaskManager#stopTask(int)}
     * @see TaskManager#stopTask(int)
     */
    public int startTask(ParticleTask task) {
        //noinspection CodeBlock2Expr
        int taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(ReflectionUtils.PLUGIN, () -> {
            ParticleUtils.sendBulk(task.getPackets(), task.getTargetPlayers());
        }, 0, task.getTickDelay()).getTaskId();
        runningTasks.add(taskId);
        
        return taskId;
    }
    
    /**
     * Stops a task that is currently running.
     *
     * @param taskId the id of the task to be stopped.
     */
    public void stopTask(int taskId) {
        if (!runningTasks.contains(taskId))
            return;
        Bukkit.getScheduler().cancelTask(taskId);
        runningTasks.remove(taskId);
    }
    
    /**
     * Get the singleton instance of the {@link TaskManager}
     *
     * @return the singleton instance of the {@link TaskManager}
     */
    public static TaskManager getTaskManager() {
        return INSTANCE;
    }
}
