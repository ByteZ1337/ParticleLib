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

package xyz.xenondevs.particle.data.color;

import xyz.xenondevs.particle.PropertyType;
import xyz.xenondevs.particle.data.ParticleData;

/**
 * The {@link ParticleColor} class is used to store the rgb values of colors and
 * convert them into the corresponding nms objects.
 *
 * @author ByteZ
 * @see PropertyType#COLORABLE
 * @since 10.06.2019
 */
public abstract class ParticleColor extends ParticleData {
    
    /**
     * The red value of the rgb value.
     */
    private final int red;
    /**
     * The green value of the rgb value.
     */
    private final int green;
    /**
     * The blue value of the rgb value.
     */
    private final int blue;
    
    /**
     * Initializes a new {@link ParticleData} object.
     *
     * @param red   the red value of the color.
     * @param green the green value of the color.
     * @param blue  the blue value of the color.
     */
    ParticleColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    /**
     * Converts the current {@link ParticleData} instance into nms data. If the current
     * minecraft version was released before 1.13 a int array should be returned. If the
     * version was released after 1.12 a nms "ParticleParam" has to be returned.
     *
     * @return the nms data.
     */
    @Override
    public abstract Object toNMSData();
    
    /**
     * Gets the red value of the color.
     *
     * @return the red value.
     */
    public float getRed() {
        return red;
    }
    
    /**
     * Gets green red value of the color.
     *
     * @return the green value.
     */
    public float getGreen() {
        return green;
    }
    
    /**
     * Gets the blue value of the color.
     *
     * @return the blue value.
     */
    public float getBlue() {
        return blue;
    }
}
