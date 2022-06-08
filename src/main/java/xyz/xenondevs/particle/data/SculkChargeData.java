/*
 * MIT License
 *
 * Copyright (c) 2022 ByteZ1337
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

import xyz.xenondevs.particle.ParticleConstants;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.utils.ReflectionUtils;

/**
 * This class holds the roll data needed, to display the {@link ParticleEffect#SCULK_CHARGE} particle. This roll data is
 * a float ranging from 0 to 1.0. The particle will roll from 0 to 360 degrees.
 *
 * @see ParticleEffect#SCULK_CHARGE
 * @author ByteZ
 */
public final class SculkChargeData extends ParticleData {
    
    /**
     * The roll data.
     */
    private final float roll;
    
    /**
     * Constructs a new {@link SculkChargeData} instance.
     *
     * @param roll The roll data.
     */
    public SculkChargeData(float roll) {
        this.roll = roll;
    }
    
    /**
     * Gets the roll data.
     *
     * @return The roll data.
     */
    public float getRoll() {
        return roll;
    }
    
    /**
     * Creates a new SculkChargeParticleOptions instance with the data of the current {@link SculkChargeData} instance.
     * <p>
     * Please note that this class is not supported in any versions before 1.19 and could lead to errors
     * if used in legacy versions.
     *
     * @return a new SculkChargeParticleOptions instance with the data of the current {@link SculkChargeData} instance.
     */
    @Override
    public Object toNMSData() {
        if (ReflectionUtils.MINECRAFT_VERSION < 19 || getEffect() != ParticleEffect.SCULK_CHARGE)
            return null;
        try {
            return ParticleConstants.PARTICLE_PARAM_SCULK_CHARGE_CONSTRUCTOR.newInstance(getRoll());
        } catch (Exception ex) {
            return null;
        }
    }
}
