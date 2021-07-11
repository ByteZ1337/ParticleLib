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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.data.color.RegularColor;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * A builder for particle packets.
 *
 * @author ByteZ
 * @since 24/01/2020
 */
public final class ParticleBuilder {
    
    /**
     * The {@link ParticleEffect} which should be displayed by the client.
     */
    private final ParticleEffect particle;
    
    /**
     * The {@link Location} of the particle.
     */
    private Location location;
    /**
     * This field has three uses:
     * <p>
     * The offsetX defines in which x oriented range the particles can
     * spawn.
     * <p>
     * It represents the x velocity a particle with the
     * {@link PropertyType#DIRECTIONAL} property should have.
     * <p>
     * It sets the red value of a {@link PropertyType#COLORABLE}
     * particle. However, since 1.13 a ParticleParam has to be used to set
     * the colors of redstone.
     */
    private float offsetX = 0;
    /**
     * This field has three uses:
     * <p>
     * The offsetY defines in which y oriented range the particles can
     * spawn.
     * <p>
     * It represents the y velocity a particle with the
     * {@link PropertyType#DIRECTIONAL}  property should have.
     * <p>
     * It sets the green value of a {@link PropertyType#COLORABLE}
     * particle. However, since 1.13 a ParticleParam has to be used to set
     * the colors of redstone.
     */
    private float offsetY = 0;
    /**
     * This field has three uses:
     * <p>
     * The offsetZ defines in which z oriented range the particles can
     * spawn.
     * <p>
     * It represents the z velocity a particle with the
     * {@link PropertyType#DIRECTIONAL} property should have.
     * <p>
     * It sets the blue value of a {@link PropertyType#COLORABLE}
     * particle. However, since 1.13 a ParticleParam has to be used to set
     * the colors of redstone.
     */
    private float offsetZ = 0;
    /**
     * Normally this field is used to multiply the velocity of a
     * particle by the given speed. There are however some special cases
     * where this value is used for something different. (e.g. {@link ParticleEffect#NOTE}).
     */
    private float speed = 1;
    /**
     * The amount of particles that should be spawned. For the extra data defined
     * in offsetX, offsetY and offsetZ to work the amount has to be set to {@code 0}.
     */
    private int amount = 0;
    /**
     * The data of the particle which should be displayed. This data contains additional
     * information the client needs to display the particle correctly.
     */
    private ParticleData particleData = null;
    
    /**
     * Initializes a new {@link ParticleBuilder}
     *
     * @param particle The {@link ParticleEffect} of the builder.
     * @param location The location at which the particle should be displayed
     */
    public ParticleBuilder(ParticleEffect particle, Location location) {
        this.particle = particle;
        this.location = location;
    }
    
    /**
     * Initializes a new {@link ParticleBuilder}
     *
     * @param particle The {@link ParticleEffect} of the builder.
     */
    public ParticleBuilder(ParticleEffect particle) {
        this.particle = particle;
        this.location = null;
    }
    
    
    /**
     * Sets the {@link Location} of the particle.
     *
     * @param location The new {@link Location} of the particle.
     * @return the current instance to support building operations
     */
    public ParticleBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }
    
    /**
     * Sets the X offset.
     *
     * @param offsetX The new value of the {@link #offsetX} field
     * @return the current instance to support building operations
     */
    public ParticleBuilder setOffsetX(float offsetX) {
        this.offsetX = offsetX;
        return this;
    }
    
    /**
     * Sets the Y offset.
     *
     * @param offsetY The new value of the {@link #offsetY} field
     * @return the current instance to support building operations
     */
    public ParticleBuilder setOffsetY(float offsetY) {
        this.offsetY = offsetY;
        return this;
    }
    
    /**
     * Sets the offset.
     *
     * @param offsetX The new value of the {@link #offsetX} field
     * @param offsetY The new value of the {@link #offsetY} field
     * @param offsetZ The new value of the {@link #offsetZ} field
     * @return the current instance to support building operations
     */
    public ParticleBuilder setOffset(float offsetX, float offsetY, float offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        return this;
    }
    
    /**
     * Sets the offset.
     *
     * @param offset a {@link Vector} containing the offset values.
     * @return the current instance to support building operations
     */
    public ParticleBuilder setOffset(Vector offset) {
        this.offsetX = (float) offset.getX();
        this.offsetY = (float) offset.getX();
        this.offsetZ = (float) offset.getX();
        return this;
    }
    
    /**
     * Sets the Z offset.
     *
     * @param offsetZ The new value of the {@link #offsetZ} field
     * @return the current instance to support building operations
     */
    public ParticleBuilder setOffsetZ(float offsetZ) {
        this.offsetZ = offsetZ;
        return this;
    }
    
    /**
     * Sets the speed.
     *
     * @param speed The new value of the {@link #speed} field
     * @return the current instance to support building operations
     */
    public ParticleBuilder setSpeed(float speed) {
        this.speed = speed;
        return this;
    }
    
    /**
     * Sets the amount.
     *
     * @param amount The new value of the {@link #amount} field
     * @return the current instance to support building operations
     */
    public ParticleBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }
    
    /**
     * Sets the particleData.
     *
     * @param particleData The new value of the {@link #particleData} field
     * @return the current instance to support building operations
     */
    public ParticleBuilder setParticleData(ParticleData particleData) {
        this.particleData = particleData;
        return this;
    }
    
    /**
     * Sets the color of the particle. Note that particle
     * needs the {@link PropertyType#COLORABLE} PropertyType
     * to work.
     *
     * @param color the {@link Color} of the particle.
     * @return the current instance to support building operations
     */
    public ParticleBuilder setColor(Color color) {
        if (this.particle.hasProperty(PropertyType.COLORABLE))
            this.particleData = new RegularColor(color);
        return this;
    }
    
    /**
     * Creates a new {@link ParticlePacket} wit the given values.
     *
     * @return the new {@link ParticlePacket}
     * @throws IllegalStateException if the location field isn't set yet.
     */
    public Object toPacket() {
        if (location == null)
            throw new IllegalStateException("Missing location of particle.");
        if (this.particleData != null)
            this.particleData.setEffect(this.particle);
        ParticlePacket packet = new ParticlePacket(this.particle, this.offsetX, this.offsetY, this.offsetZ, this.speed, this.amount, this.particleData);
        return packet.createPacket(this.location);
    }
    
    /**
     * Displays the given particle to all players.
     */
    public void display() {
        Object packet = toPacket();
        Objects.requireNonNull(location.getWorld()).getPlayers()
                .forEach(p -> ReflectionUtils.sendPacket(p, packet));
    }
    
    /**
     * Displays the given particle to the players in the array.
     *
     * @param players The players that should see the particle.
     */
    public void display(Player... players) {
        this.display(Arrays.asList(players));
    }
    
    /**
     * Display the given particle to online player that match the given filter.
     *
     * @param filter a {@link Predicate} to filter out
     *               specific {@link Player Players}.
     */
    public void display(Predicate<Player> filter) {
        Object packet = toPacket();
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(p -> filter.test(p) && p.getWorld().equals(location.getWorld()))
                .forEach(p -> ReflectionUtils.sendPacket(p, packet));
    }
    
    /**
     * Displays the given particle to all players in the {@link Collection}
     *
     * @param players a list of players that should receive the particle packet.
     */
    public void display(Collection<? extends Player> players) {
        Object packet = toPacket();
        players.stream()
                .filter(p -> p.getWorld().equals(location.getWorld()))
                .forEach(p -> ReflectionUtils.sendPacket(p, packet));
    }
    
}
