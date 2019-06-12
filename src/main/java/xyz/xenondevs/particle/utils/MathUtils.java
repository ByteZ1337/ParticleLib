/*
 *
 * Copyright (c) 2019 Xenondevs
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

package xyz.xenondevs.particle.utils;

/**
 * Utility for Maths
 *
 * @author ByteZ
 * @since 14.09.2018
 */
public class MathUtils {
    /**
     * Checks if a specific value is between
     * two other values. If the first value
     * is greater than the max value, the max
     * value is returned. If the given value
     * is lesser than the min value. the min
     * value is returned. Otherwise the given
     * value is returned.
     *
     * @param value the value which should be checked.
     * @param max   the maximum value.
     * @param min   the minimum value
     * @return the calculated value.
     */
    public static int getMaxOrMin(int value, int max, int min) {
        return value >= max ? max : (value <= min ? min : value);
    }
}
