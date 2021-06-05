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

package xyz.xenondevs.particle.data;


import xyz.xenondevs.particle.ParticleEffect;

/**
 * A Object to easier hold data of a particle.
 *
 * @author ByteZ
 * @since 10.06.2019
 */
public abstract class ParticleData {
    
    /**
     * The {@link ParticleEffect} the current {@link ParticleData} instance is
     * assigned to.
     */
    private ParticleEffect effect;
    
    /**
     * Sets the {@link ParticleEffect}.
     *
     * @param effect the {@link ParticleEffect} that should be displayed.
     */
    public void setEffect(ParticleEffect effect) {
        this.effect = effect;
    }
    
    /**
     * Converts the current {@link ParticleData} instance into nms data. If the current
     * minecraft version was released before 1.13 a int array should be returned. If the
     * version was released after 1.12 a nms "ParticleParam" has to be returned.
     *
     * @return the nms data.
     */
    public abstract Object toNMSData();
    
    /**
     * Gets the {@link ParticleEffect} the current {@link ParticleData} is assigned to.
     *
     * @return the current {@link ParticleEffect}
     */
    public ParticleEffect getEffect() {
        return effect;
    }
}
