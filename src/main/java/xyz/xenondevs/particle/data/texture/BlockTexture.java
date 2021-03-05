/*
 *
 * Copyright (c) 2020 Xenondevs
 *
 * The MIT License (MIT)
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
 *
 */

package xyz.xenondevs.particle.data.texture;

import org.bukkit.Material;
import xyz.xenondevs.particle.ParticleConstants;
import xyz.xenondevs.particle.PropertyType;
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * A implementation of the {@link ParticleTexture} object to support block texture particles.
 *
 * @author ByteZ
 * @see PropertyType#REQUIRES_BLOCK
 * @since 11.06.2019
 */
public class BlockTexture extends ParticleTexture {
    
    /**
     * Initializes a new {@link ParticleData} object.
     *
     * @param material the {@link Material} the particle should display.
     */
    public BlockTexture(Material material) {
        super(material, (byte) 0);
    }
    
    /**
     * Initializes a new {@link ParticleData} Object.
     *
     * @param material the {@link Material} the particle should display.
     * @param data     the damage value that should influence the texture.
     */
    public BlockTexture(Material material, byte data) {
        super(material, data);
    }
    
    /**
     * Converts the current {@link ParticleData} instance into nms data. If the current
     * minecraft version was released before 1.13 a int array should be returned. If the
     * version was released after 1.12 a nms "ParticleParam" has to be returned.
     *
     * @return the nms data.
     */
    @Override
    public Object toNMSData() {
        if (getMaterial() == null || !getMaterial().isBlock() || getEffect() == null || !getEffect().hasProperty(PropertyType.REQUIRES_BLOCK))
            return null;
        if (ReflectionUtils.MINECRAFT_VERSION < 13)
            return super.toNMSData();
        Object block = getBlockData(getMaterial());
        if (block == null)
            return null;
        try {
            return ParticleConstants.PARTICLE_PARAM_BLOCK_CONSTRUCTOR.newInstance(getEffect().getNMSObject(), block);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Gets the nms block data of the given bukkit {@link Material}.
     *
     * @param material the {@link Material}
     *                 whose data should be
     *                 getted.
     * @return the block data of the specified {@link Material} or {@code null} when an error occurs.
     */
    public Object getBlockData(Material material) {
        try {
            Field blockField = ReflectionUtils.getFieldOrNull(ParticleConstants.BLOCKS_CLASS, material.name(), false);
            if (blockField == null)
                return null;
            Object block = ReflectionUtils.readField(blockField, null);
            return ParticleConstants.BLOCK_GET_BLOCK_DATA_METHOD.invoke(block);
        } catch (Exception ex) {
            return null;
        }
    }
    
}
