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

import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleConstants;
import xyz.xenondevs.particle.PropertyType;
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.utils.ReflectionUtils;

/**
 * A implementation of the {@link ParticleTexture} object to support item texture particles.
 *
 * @author ByteZ
 * @see PropertyType#REQUIRES_ITEM
 * @since 11.06.2019
 */
public class ItemTexture extends ParticleTexture {
    
    /**
     * The {@link ItemStack} that will be displayed by the particle.
     */
    private final ItemStack itemStack;
    
    /**
     * Initializes a new {@link ParticleData} object.
     *
     * @param itemStack the {@link ItemStack} which
     *                  should be displayed by the
     *                  particle.
     */
    public ItemTexture(ItemStack itemStack) {
        super(itemStack == null ? null : itemStack.getType(), (byte) 0);
        this.itemStack = itemStack;
    }
    
    /**
     * Converts the current {@link ParticleData} instance into nms data. If the current
     * minecraft version was released before 1.13 a int array should be returned. If the
     * version was released after 1.12 a nms ParticleParam has to be returned.
     *
     * @return the nms data.
     */
    @Override
    public Object toNMSData() {
        if (getMaterial() == null || getData() < 0 || getEffect() == null || !getEffect().hasProperty(PropertyType.REQUIRES_ITEM))
            return null;
        if (ReflectionUtils.MINECRAFT_VERSION < 13)
            return super.toNMSData();
        else {
            try {
                return ParticleConstants.PARTICLE_PARAM_ITEM_CONSTRUCTOR.newInstance(getEffect().getNMSObject(), toNMSItemStack(getItemStack()));
            } catch (Exception ex) {
                return null;
            }
        }
    }
    
    /**
     * Gets the {@link ItemStack} that will be displayed by the particle.
     *
     * @return the assigned {@link ItemStack}.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }
    
    /**
     * Gets the NMS ItemStack instance of a CraftItemSTack.
     *
     * @param itemStack the CraftItemStack
     * @return the ItemStack instance of the specified CraftItemStack or {@code null} if either the given parameter is invalid or an error occurs.
     */
    public static Object toNMSItemStack(ItemStack itemStack) {
        if (itemStack == null)
            return null;
        try {
            return ParticleConstants.CRAFT_ITEM_STACK_AS_NMS_COPY_METHOD.invoke(null, itemStack);
        } catch (Exception ex) {
            return null;
        }
    }
    
}
