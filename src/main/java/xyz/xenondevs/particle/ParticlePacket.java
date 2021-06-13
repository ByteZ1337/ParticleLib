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

import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.data.VibrationData;
import xyz.xenondevs.particle.data.color.DustData;
import xyz.xenondevs.particle.data.color.NoteColor;
import xyz.xenondevs.particle.data.color.ParticleColor;
import xyz.xenondevs.particle.data.color.RegularColor;
import xyz.xenondevs.particle.data.texture.BlockTexture;
import xyz.xenondevs.particle.data.texture.ItemTexture;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.lang.reflect.Constructor;

import static xyz.xenondevs.particle.ParticleConstants.PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR;
import static xyz.xenondevs.particle.ParticleEffect.NOTE;
import static xyz.xenondevs.particle.ParticleEffect.REDSTONE;


/**
 * Represents the nms "PacketPlayOutWorldParticles" packet.
 *
 * @author ByteZ
 * @since 10.06.2019
 */
public final class ParticlePacket {
    
    /**
     * The {@link ParticleEffect} which should be displayed by the client.
     */
    private final ParticleEffect particle;
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
    private final float offsetX;
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
     * particle. However, since 1.13 a  ParticleParam has to be used to set
     * the colors of redstone.
     */
    private final float offsetY;
    /**
     * This field has three uses:
     * <p>
     * The offsetZ defines in which z oriented range the particles can
     * spawn.
     * <p>
     * It represents the z velocity a  particle with the
     * {@link PropertyType#DIRECTIONAL}  property should have.
     * <p>
     * It sets the blue value of a {@link PropertyType#COLORABLE}
     * particle. However, since 1.13 a ParticleParam has to be used to set
     * the colors of redstone.
     */
    private final float offsetZ;
    /**
     * Normally this field is used to multiply the velocity of a
     * particle by the given speed. There  are however some special cases
     * where this value is used for something different. (e.g. {@link ParticleEffect#NOTE}).
     */
    private final float speed;
    /**
     * The amount of particles that should be spawned. For the extra data defined
     * in offsetX, offsetY and offsetZ to work the amount has to be set to {@code 0}.
     */
    private final int amount;
    /**
     * The data of the particle which should be displayed. This data contains additional
     * information the client needs to display  the particle correctly.
     */
    private final ParticleData particleData;
    
    /**
     * Creates a new {@link ParticlePacket} that can be sent to one or multiple
     * {@link Player players}.
     *
     * @param particle     the {@link ParticleEffect} that should be sent.
     * @param offsetX      the offsetX or extra data the particle should have.
     * @param offsetY      the offsetY or extra data the particle should have.
     * @param offsetZ      the offsetZ or extra data the particle should have.
     * @param speed        the multiplier of the velocity.
     * @param amount       the amount of particles that should be spawned.
     * @param particleData the {@link ParticleData} of the particle
     * @see #particle
     * @see #offsetX
     * @see #offsetY
     * @see #offsetZ
     * @see #speed
     * @see #amount
     * @see #particleData
     * @see ParticleData
     */
    public ParticlePacket(ParticleEffect particle, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData particleData) {
        this.particle = particle;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.speed = speed;
        this.amount = amount;
        this.particleData = particleData;
    }
    
    /**
     * Creates a new {@link ParticlePacket} that can be sent to one or multiple
     * {@link Player players}.
     *
     * @param particle the {@link ParticleEffect} that should be sent.
     * @param offsetX  the offsetX or extra data the particle should have.
     * @param offsetY  the offsetY or extra data the particle should have.
     * @param offsetZ  the offsetZ or extra data the particle should have.
     * @param speed    the multiplier of the velocity.
     * @param amount   the amount of particles that should be spawned.
     * @see #particle
     * @see #offsetX
     * @see #offsetY
     * @see #offsetZ
     * @see #speed
     * @see #amount
     */
    public ParticlePacket(ParticleEffect particle, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        this.particle = particle;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.speed = speed;
        this.amount = amount;
        this.particleData = null;
    }
    
    /**
     * Gets the {@link ParticleEffect} that will be displayed by the client.
     *
     * @return The {@link ParticleEffect} which should be displayed by the client.
     */
    public ParticleEffect getParticle() {
        return particle;
    }
    
    /**
     * Gets the offsetX value of the particle.
     *
     * @return the offsetX value.
     */
    public float getOffsetX() {
        return offsetX;
    }
    
    /**
     * Gets the offsetY value of the particle.
     *
     * @return the offsetY value.
     */
    public float getOffsetY() {
        return offsetY;
    }
    
    /**
     * Gets the offsetZ value of the particle.
     *
     * @return the offsetZ value.
     */
    public float getOffsetZ() {
        return offsetZ;
    }
    
    /**
     * Gets the speed at which the particle will fly off.
     *
     * @return the speed of the particle.
     */
    public float getSpeed() {
        return speed;
    }
    
    /**
     * Gets how many particles will be shown by the client.
     *
     * @return the amount of particles to be spawned.
     */
    public int getAmount() {
        return amount;
    }
    
    /**
     * Gets the {@link ParticleData} that should be used when displaying the
     * particle.
     *
     * @return the {@link ParticleData} that will be used.
     */
    public ParticleData getParticleData() {
        return particleData;
    }
    
    /**
     * Creates a NMS PacketPlayOutWorldParticles packet with the data in the current
     * {@link ParticlePacket} data.
     *
     * @param location the {@link Location} the particle should be displayed at.
     * @return a PacketPlayOutWorldParticles or {@code null} when something goes wrong.
     */
    public Object createPacket(Location location) {
        try {
            ParticleEffect effect = getParticle();
            ParticleData data = getParticleData();
            int version = ReflectionUtils.MINECRAFT_VERSION;
            if (effect == null || effect.getFieldName().equals("NONE"))
                return null;
            if (data != null) {
                if (data.getEffect() != effect)
                    return null;
                Object nmsData = data.toNMSData();
                if (nmsData == null)
                    return null;
                if ((data instanceof DustData && version >= 13)
                        || (data instanceof VibrationData && version >= 17)
                        || (data instanceof RegularColor && (version >= 17 && effect.hasProperty(PropertyType.DUST))))
                    return createGenericParticlePacket(location, nmsData);
                if ((data instanceof BlockTexture && effect.hasProperty(PropertyType.REQUIRES_BLOCK))
                        || (data instanceof ItemTexture && effect.hasProperty(PropertyType.REQUIRES_ITEM)))
                    return createTexturedParticlePacket(location, nmsData);
                if (data instanceof ParticleColor && effect.hasProperty(PropertyType.COLORABLE)) {
                    return createColoredParticlePacket(location, nmsData);
                } else
                    return null;
            } else if (!effect.hasProperty(PropertyType.REQUIRES_BLOCK) && !effect.hasProperty(PropertyType.REQUIRES_ITEM))
                return createPacket(effect.getNMSObject(),
                        (float) location.getX(), (float) location.getY(), (float) location.getZ(),
                        getOffsetX(), getOffsetY(), getOffsetZ(),
                        getSpeed(), getAmount(), new int[0]);
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Creates a new packet for particles that don't need any extra checks.
     * <b>Note: This method does not check if the given particle and
     * data match!</b>
     *
     * @param location the {@link Location} the particle should be displayed at.
     * @param param    the pre-built ParticleParam.
     * @return a PacketPlayOutWorldParticles or {@code null} when something goes wrong.
     */
    private Object createGenericParticlePacket(Location location, Object param) {
        return createPacket(param,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(),
                getOffsetX(), getOffsetY(), getOffsetZ(),
                getSpeed(), getAmount(), new int[0]
        );
    }
    
    /**
     * Creates a new packet for particles that support custom textures.
     * <p>
     * <b>Note: This method does not check if the given particle and
     * data match!</b>
     *
     * @param location the {@link Location} the particle should be displayed at.
     * @param param    the pre-built ParticleParam.
     * @return a PacketPlayOutWorldParticles or {@code null} when something goes wrong.
     * @see PropertyType#REQUIRES_BLOCK
     * @see PropertyType#REQUIRES_ITEM
     */
    private Object createTexturedParticlePacket(Location location, Object param) {
        ParticleEffect effect = getParticle();
        int version = ReflectionUtils.MINECRAFT_VERSION;
        return createPacket(version < 13 ? effect.getNMSObject() : param,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(),
                getOffsetX(), getOffsetY(), getOffsetZ(),
                getSpeed(), getAmount(), version < 13 ? (int[]) param : new int[0]
        );
    }
    
    /**
     * Creates a new packet for particles that support custom colors.
     * <p>
     * <b>Note: This method does not check if the given particle and
     * data match!</b>
     *
     * @param location the {@link Location} the particle should be displayed at.
     * @param param    the pre-built ParticleParam.
     * @return a PacketPlayOutWorldParticles or {@code null} when something goes wrong.
     * @see PropertyType#COLORABLE
     */
    private Object createColoredParticlePacket(Location location, Object param) {
        ParticleEffect effect = getParticle();
        ParticleData data = getParticleData();
        if (data instanceof NoteColor && effect.equals(NOTE)) {
            return createPacket(effect.getNMSObject(),
                    (float) location.getX(), (float) location.getY(), (float) location.getZ(),
                    ((NoteColor) data).getRed(), 0f, 0f,
                    getSpeed(), getAmount(), new int[0]
            );
        } else if (data instanceof RegularColor) {
            RegularColor color = ((RegularColor) data);
            if (ReflectionUtils.MINECRAFT_VERSION < 13 || !effect.equals(REDSTONE)) {
                return createPacket(effect.getNMSObject(),
                        (float) location.getX(), (float) location.getY(), (float) location.getZ(),
                        (effect.equals(REDSTONE) && color.getRed() == 0 ? Float.MIN_NORMAL : color.getRed()), color.getGreen(), color.getBlue(),
                        1f, 0, new int[0]
                );
            } else {
                return createPacket(param,
                        (float) location.getX(), (float) location.getY(), (float) location.getZ(),
                        getOffsetX(), getOffsetY(), getOffsetZ(),
                        getSpeed(), getAmount(), new int[0]
                );
            }
        } else return null;
    }
    
    /**
     * Creates a new PacketPlayOutWorldParticles
     * object with the given data.
     *
     * @param param     the ParticleParam of the  packet.
     * @param locationX the x coordinate of the location the particle
     *                  should be displayed at.
     * @param locationY the y coordinate of the location the particle
     *                  should be displayed at.
     * @param locationZ the z coordinate of the location the particle
     *                  should be displayed at.
     * @param offsetX   the offset x value of the packet.
     * @param offsetY   the offset y value of the packet.
     * @param offsetZ   the offset z value of the packet.
     * @param speed     the speed of the particle.
     * @param amount    the amount of particles.
     * @param data      extra data for the particle.
     * @return A PacketPlayOutWorldParticles instance with the given data or {@code null} if an error occurs.
     */
    private Object createPacket(Object param, float locationX, float locationY, float locationZ, float offsetX, float offsetY, float offsetZ, float speed, int amount, int[] data) {
        Constructor packetConstructor = PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR;
        try {
            if (ReflectionUtils.MINECRAFT_VERSION < 13)
                return packetConstructor.newInstance(param, true, locationX, locationY, locationZ, offsetX, offsetY, offsetZ, speed, amount, data);
            if (ReflectionUtils.MINECRAFT_VERSION < 15)
                return packetConstructor.newInstance(param, true, locationX, locationY, locationZ, offsetX, offsetY, offsetZ, speed, amount);
            return packetConstructor.newInstance(param, true, (double) locationX, (double) locationY, (double) locationZ, offsetX, offsetY, offsetZ, speed, amount);
        } catch (Exception ex) {
            return null;
        }
    }
    
}
