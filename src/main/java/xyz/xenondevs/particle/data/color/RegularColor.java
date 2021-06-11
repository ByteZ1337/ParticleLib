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
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.utils.MathUtils;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.awt.*;

/**
 * A implementation of the {@link ParticleColor} class that supports normal RGB values.
 * <p>
 * If you want to define a custom size for {@link ParticleEffect#REDSTONE} or the second
 * color for {@link ParticleEffect#DUST_COLOR_TRANSITION}, use {@link DustData} and
 * {@link DustColorTransitionData}. You can however use this class if you're just looking
 * to set the color.
 *
 * @author ByteZ
 * @see PropertyType#COLORABLE
 * @since 10.06.2019
 */
public class RegularColor extends ParticleColor {
    
    /**
     * Initializes a new {@link ParticleData} object.
     *
     * @param color the {@link Color} the
     *              particle should have.
     */
    public RegularColor(Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    /**
     * Initializes a new {@link ParticleData} object.
     *
     * @param red   the red value of the color.
     * @param green the green value of the color.
     * @param blue  the blue value of the color.
     */
    public RegularColor(int red, int green, int blue) {
        super(MathUtils.getMaxOrMin(red, 255, 0), MathUtils.getMaxOrMin(green, 255, 0), MathUtils.getMaxOrMin(blue, 255, 0));
    }
    
    /**
     * Gets the red value of the color.
     *
     * @return the red value.
     */
    @Override
    public float getRed() {
        return super.getRed() / 255f;
    }
    
    /**
     * Gets green red value of the color.
     *
     * @return the green value.
     */
    @Override
    public float getGreen() {
        return super.getGreen() / 255f;
    }
    
    /**
     * Gets the blue value of the color.
     *
     * @return the blue value.
     */
    @Override
    public float getBlue() {
        return super.getBlue() / 255f;
    }
    
    /**
     * Converts the current {@link ParticleData} instance into nms data. If the current
     * minecraft version was released before 1.13 an int array should be returned. If the
     * version was released after 1.12 a nms "ParticleParam" has to be returned.
     * <p>
     * This method also supports TransitioningDust particles since 1.6.
     *
     * @return the nms data.
     */
    @Override
    public Object toNMSData() {
        if (ReflectionUtils.MINECRAFT_VERSION < 13 || (getEffect() != ParticleEffect.REDSTONE && getEffect() != ParticleEffect.DUST_COLOR_TRANSITION))
            return new int[0];
        try {
            if (getEffect() == ParticleEffect.REDSTONE)
                return ReflectionUtils.MINECRAFT_VERSION < 17
                        ? ParticleConstants.PARTICLE_PARAM_REDSTONE_CONSTRUCTOR.newInstance(getRed(), getGreen(), getBlue(), 1f)
                        : ParticleConstants.PARTICLE_PARAM_REDSTONE_CONSTRUCTOR.newInstance(ReflectionUtils.createVector3fa(getRed(), getGreen(), getBlue()), 1f);
            if (ReflectionUtils.MINECRAFT_VERSION < 17)
                return null;
            Object colorVector = ReflectionUtils.createVector3fa(getRed(), getGreen(), getBlue());
            return ParticleConstants.PARTICLE_PARAM_DUST_COLOR_TRANSITION_CONSTRUCTOR.newInstance(colorVector, colorVector, 1f);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Generates a random {@link RegularColor} instance with a high saturation. If you
     * want a completely random {@link Color} use {@link #random(boolean)} with false
     * as the highSaturarion parameter.
     *
     * @return a randomly generated {@link RegularColor} instance.
     */
    public static RegularColor random() {
        return random(true);
    }
    
    /**
     * Generates a random {@link RegularColor} instance. If the highSaturation parameter
     * is set to true, a random hue from the HSV spectrum will be used. Otherwise 3 random
     * integers ranging from 0 to 255 for the RGB values will be generated.
     *
     * @param highSaturation determines if the colors should have a high saturation.
     * @return a randomly generated {@link RegularColor} instance.
     */
    public static RegularColor random(boolean highSaturation) {
        if (highSaturation)
            return fromHSVHue(MathUtils.generateRandomInteger(0, 360));
        else
            return new RegularColor(new Color(MathUtils.RANDOM.nextInt(256), MathUtils.RANDOM.nextInt(256), MathUtils.RANDOM.nextInt(256)));
    }
    
    /**
     * Constructs a {@link RegularColor} using the HSV color spectrum.
     *
     * @param hue the hue the the specific color has.
     * @return a {@link RegularColor} instance with the given HSV value as its {@link Color}.
     * @see Color#HSBtoRGB(float, float, float)
     */
    public static RegularColor fromHSVHue(int hue) {
        return new RegularColor(new Color(Color.HSBtoRGB(hue / 360f, 1f, 1f)));
    }
    
}
