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

public final class ShriekData extends ParticleData {
    
    private final int delay;
    
    public ShriekData(int delay) {
        this.delay = delay;
    }
    
    public int getDelay() {
        return delay;
    }
    
    @Override
    public Object toNMSData() {
        if (ReflectionUtils.MINECRAFT_VERSION < 19 || getEffect() != ParticleEffect.SHRIEK)
            return null;
        try {
            return ParticleConstants.PARTICLE_PARAM_SHRIEK_CONSTRUCTOR.newInstance(getDelay());
        } catch (Exception ex) {
            return null;
        }
    }
}
