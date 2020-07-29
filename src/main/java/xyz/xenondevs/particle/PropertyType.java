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

package xyz.xenondevs.particle;

/**
 * {@link Enum} to easily define which properties are supported by specific
 * {@link ParticleEffect ParticleEffects}.
 * <p>
 * Current Property types
 * <ul>
 * <li>{@link #REQUIRES_WATER}</li>
 * <li>{@link #REQUIRES_BLOCK}</li>
 * <li>{@link #REQUIRES_ITEM}</li>
 * <li>{@link #DIRECTIONAL}</li>
 * <li>{@link #COLORABLE}</li>
 * <li>{@link #RESIZEABLE}</li>
 * </ul>
 *
 * @author ByteZ
 * @since 05.06.2019
 */
public enum PropertyType {
    /**
     * Specifies that the direction of thegiven particle can be edited. This
     * data is set as the offsetX, offsetY and offsetZ of the particle packet.
     */
    DIRECTIONAL,
    /**
     * Specifies that the color of the given particle can be set. Before 1.13 the
     * RGB values of the color is simply set as the offsetX, offsetY and offsetZ of
     * the particle packet. But that has changed since 1.13. Redstone particles
     * now have a custom ParticleParam called "ParticleParamRedstone". However other
     * particles like spell_mob/effect_entity are still handled the old way.
     */
    COLORABLE,
    /**
     * Specifies that the given particle  needs the data of a block to be
     * functional. Before 1.13 this data was saved in an int array that
     * could easily be set in the constructor of the packet. However, because of
     * the major 1.13 particle changes this data can no longer be set in a simple
     * int array. There is now a custom object to set the data of such
     * particles. The class of this object is ParticleParamBlock and it
     * implements the ParticleParam interface, which is now needed in the constructor.
     */
    REQUIRES_BLOCK,
    /**
     * Specifies that the given particle needs the data of an item to be
     * functional. Before 1.13 this data was saved in an int array that could
     * easily be set in the constructor of the packet. However, because of the
     * major 1.13 particle changes this data can no longer be set in a simple int
     * array. There is now a custom object to set the data of such particles. The
     * class of this object is ParticleParamItem  and it implements the ParticleParam
     * interface, which is now needed in the constructor.
     */
    REQUIRES_ITEM,
    /**
     * Specifies that the given particle needs water in order to be displayed correctly.
     */
    REQUIRES_WATER,
    /**
     * Specifies that the size of the given particle can be changed in the offsetX parameter.
     */
    RESIZEABLE
}
