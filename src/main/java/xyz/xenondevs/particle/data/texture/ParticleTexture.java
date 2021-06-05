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

package xyz.xenondevs.particle.data.texture;

import org.bukkit.Material;
import xyz.xenondevs.particle.PropertyType;
import xyz.xenondevs.particle.data.ParticleData;

/**
 * A implementation of {@link ParticleData} to support particles that require a texture
 * to function properly.
 *
 * @author ByteZ
 * @see PropertyType#REQUIRES_BLOCK
 * @see PropertyType#REQUIRES_ITEM
 * @since 11.06.2019
 */
public class ParticleTexture extends ParticleData {
    
    /**
     * The {@link Material} that should be displayed by the particle.
     */
    private final Material material;
    /**
     * The damage data to be displayed by the given texture.
     */
    private final byte data;
    
    /**
     * Initializes a new {@link ParticleData} object.
     *
     * @param material the {@link Material} the particle should display.
     * @param data     the damage value that should influence the texture.
     */
    ParticleTexture(Material material, byte data) {
        this.material = material;
        this.data = data;
    }
    
    /**
     * Gets the {@link Material} that will be displayed b the particle.
     *
     * @return the {@link Material} the current data is assigned to
     */
    public Material getMaterial() {
        return material;
    }
    
    /**
     * Gets the damage value that will be displayed by the client.
     *
     * @return the damage value of the current texture.
     */
    public byte getData() {
        return data;
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
        return new int[] {getMaterial().ordinal(), getData()};
    }
}
