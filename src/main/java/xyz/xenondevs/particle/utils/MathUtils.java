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

package xyz.xenondevs.particle.utils;

import xyz.xenondevs.particle.data.ParticleData;

import java.util.Random;

/**
 * Utility for Maths
 *
 * @author ByteZ
 * @since 14.09.2018
 */
public final class MathUtils {
    
    /**
     * A easy to access {@link Random} implementation for random number
     * generation. This specific field is mostly used by the random
     * methods of the {@link ParticleData} types.
     */
    public static final Random RANDOM = new Random();
    
    /**
     * Generates a random {@link Integer}.
     *
     * @param minimum the minimum value of the generated value.
     * @param maximum the maximum value of the generated value.
     * @return a randomly generated {@link Integer} in the defined range.
     * @see #RANDOM
     */
    public static int generateRandomInteger(int minimum, int maximum) {
        return minimum + (int) (RANDOM.nextDouble() * ((maximum - minimum) + 1));
    }
    
    
    /**
     * Checks if a specific {@link Integer} is in the given range.
     * If not the respective bound of the range is returned.
     *
     * @param value the value which should be checked.
     * @param max   the maximum value.
     * @param min   the minimum value
     * @return the calculated value.
     */
    public static int getMaxOrMin(int value, int max, int min) {
        return value < max ? (Math.max(value, min)) : max;
    }
}
