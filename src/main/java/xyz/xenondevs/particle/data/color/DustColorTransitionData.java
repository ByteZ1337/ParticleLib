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

package xyz.xenondevs.particle.data.color;

import xyz.xenondevs.particle.ParticleConstants;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.PropertyType;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.awt.*;

/**
 * This {@link DustData} implementation is solely built
 * for the {@link ParticleEffect#DUST_COLOR_TRANSITION}
 * effect. It supports 2 colors and a custom size. The
 * resulting particle will show a color fade between
 * those 2 colors.
 *
 * @author ByteZ
 * @see DustData
 * @see PropertyType#DUST
 * @see ParticleEffect#DUST_COLOR_TRANSITION
 */
public final class DustColorTransitionData extends DustData {
    
    /**
     * The red value of the second color.
     */
    private final int fadeRed;
    /**
     * The green value of the second color.
     */
    private final int fadeGreen;
    /**
     * The blue value of the second color.
     */
    private final int fadeBlue;
    
    /**
     * Creates a new {@link DustColorTransitionData} instance.
     *
     * @param color     the start color of the particle.
     * @param fadeColor the color the particle will fade to.
     * @param size      the size of the particle.
     */
    public DustColorTransitionData(Color color, Color fadeColor, float size) {
        super(color, size);
        fadeRed = fadeColor.getRed();
        fadeGreen = fadeColor.getGreen();
        fadeBlue = fadeColor.getBlue();
    }
    
    /**
     * Creates a new {@link DustColorTransitionData} instance.
     *
     * @param red       the red value of the start color.
     * @param green     the green value of the start color.
     * @param blue      the blue value of the start color.
     * @param fadeRed   the red value of the second color.
     * @param fadeGreen the green value of the second color.
     * @param fadeBlue  the blue value of the second color.
     * @param size      the size of the particle.
     */
    public DustColorTransitionData(int red, int green, int blue, int fadeRed, int fadeGreen, int fadeBlue, float size) {
        super(red, green, blue, size);
        this.fadeRed = fadeRed;
        this.fadeGreen = fadeGreen;
        this.fadeBlue = fadeBlue;
    }
    
    /**
     * Gets the red value of the color the particle will
     * fade to. <b>(Value range is 0f-1f)</b>
     *
     * @return the red value of the second color.
     */
    public float getFadeRed() {
        return fadeRed / 255f;
    }
    
    /**
     * Gets the green value of the color the particle will
     * fade to. <b>(Value range is 0f-1f)</b>
     *
     * @return the red value of the second color.
     */
    public float getFadeGreen() {
        return fadeGreen / 255f;
    }
    
    /**
     * Gets the blue value of the color the particle will
     * fade to. <b>(Value range is 0f-1f)</b>
     *
     * @return the red value of the second color.
     */
    public float getFadeBlue() {
        return fadeBlue / 255f;
    }
    
    /**
     * Creates a new instance of the nms counterpart
     * of this class.
     * <p>
     * Please note that this class is not supported in
     * any versions before 1.17 and could lead to errors
     * if used in legacy versions.
     *
     * @return the nms counterpart of this class.
     */
    @Override
    public Object toNMSData() {
        if (ReflectionUtils.MINECRAFT_VERSION < 17 || getEffect() != ParticleEffect.DUST_COLOR_TRANSITION)
            return null;
        Object fadeStart = ReflectionUtils.createVector3fa(getRed(), getGreen(), getBlue());
        Object fadeEnd = ReflectionUtils.createVector3fa(getFadeRed(), getFadeGreen(), getFadeBlue());
        try {
            return ParticleConstants.PARTICLE_PARAM_DUST_COLOR_TRANSITION_CONSTRUCTOR.newInstance(fadeStart, fadeEnd, getSize());
        } catch (Exception ex) {
            return null;
        }
    }
}
