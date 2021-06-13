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

package xyz.xenondevs.particle;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.data.VibrationData;
import xyz.xenondevs.particle.task.TaskManager;

import java.util.List;

public class ParticleLib extends JavaPlugin {
    
    @Override
    public void onEnable() {
        World world = Bukkit.getWorld("world");
        TaskManager.startGlobalTask(getPackets(), 100);
        TaskManager.startGlobalTask(Lists.newArrayList(
                new ParticleBuilder(ParticleEffect.FLAME, new Location(world, 5, 100, 0))
                        .toPacket(),
                new ParticleBuilder(ParticleEffect.FLAME, new Location(world, -5, 100, 0))
                        .toPacket()
        ), 1);
    }
    
    private List<Object> getPackets() {
        World world = Bukkit.getWorld("world");
        List<Object> packets = Lists.newArrayList();
        ParticleData vibrationData = new VibrationData(
                new Location(world, 5, 100, 0),
                new Location(world, -5, 100, 0),
                100
        );
        packets.add(new ParticleBuilder(ParticleEffect.VIBRATION)
                .setParticleData(vibrationData)
                .setLocation(new Location(world, 0, 100, 0))
                .toPacket());
        return packets;
    }
}
