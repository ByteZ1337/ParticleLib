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

package xyz.xenondevs.particle.data;

import org.bukkit.Location;
import xyz.xenondevs.particle.ParticleConstants;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.util.Objects;

/**
 * This class holds all data that is needed
 * by the client to display a {@link ParticleEffect#VIBRATION}
 * particle. The required information is:
 * The start {@link Location}, The destination
 * and the amount of ticks it will take the
 * particle to fly this path.
 * <p>
 * Minecraft only supports full block coordinates
 * for the start and destination location. So any
 * particle will spawn at the center of a block.
 *
 * @author ByteZ
 * @see ParticleEffect#VIBRATION
 */
public final class VibrationData extends ParticleData {
    
    /**
     * The start {@link Location} of the particle. (Will be mapped to the block location)
     */
    private final Location start;
    /**
     * The destination {@link Location} of the particle. (Will be mapped to the block location)
     */
    private final Location destination;
    /**
     * The amount of ticks it will take the particle to fly from the {@link #start} to the {@link #destination}
     */
    private final int ticks;
    
    /**
     * Creates a new {@link VibrationData} instance.
     *
     * @param start       the start {@link Location} of the particle.
     * @param destination the destination {@link Location} of the particle.
     * @param ticks       the amount of ticks it will take the particle to reach the {@link #destination}
     */
    public VibrationData(Location start, Location destination, int ticks) {
        this.start = Objects.requireNonNull(start);
        this.destination = Objects.requireNonNull(destination);
        this.ticks = ticks;
    }
    
    /**
     * Gets the start {@link Location} of the particle.
     *
     * @return the start {@link Location} of the particle.
     */
    public Location getStart() {
        return start;
    }
    
    /**
     * Gets the destination {@link Location} of the particle.
     *
     * @return the destination {@link Location} of the particle.
     */
    public Location getDestination() {
        return destination;
    }
    
    /**
     * Gets the amount of ticks it will take the particle to travel.
     *
     * @return the travel time in ticks.
     */
    public int getTicks() {
        return ticks;
    }
    
    /**
     * Creates a new VibrationParticleOption instance with the data of
     * the current {@link VibrationData} instance.
     * <p>
     * Please note that this class is not supported in
     * any versions before 1.17 and could lead to errors
     * if used in legacy versions.
     *
     * @return a new VibrationParticleOption with the data of the current {@link VibrationData} instance.
     */
    @Override
    public Object toNMSData() {
        if (ReflectionUtils.MINECRAFT_VERSION < 17 || getEffect() != ParticleEffect.VIBRATION)
            return null;
        Object start = ReflectionUtils.createBlockPosition(getStart());
        Object dest = ReflectionUtils.createBlockPosition(getDestination());
        try {
            Object source = ParticleConstants.BLOCK_POSITION_SOURCE_CONSTRUCTOR.newInstance(dest);
            Object path = ParticleConstants.VIBRATION_PATH_CONSTRUCTOR.newInstance(start, source, getTicks());
            return ParticleConstants.PARTICLE_PARAM_VIBRATION_CONSTRUCTOR.newInstance(path);
        } catch (Exception ex) {
            return null;
        }
    }
}
