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

package xyz.xenondevs.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static xyz.xenondevs.particle.utils.ReflectionUtils.*;

/**
 * Maps classes, methods and fields to their respective names for different versions of Minecraft.
 */
public class ParticleMappings {
    
    /**
     * {@link Map} containing all mappings needed by ParticleLib.
     */
    private static final Map<String, String> mappings = new HashMap<>();
    
    static {
        double version = ReflectionUtils.MINECRAFT_VERSION;
        try (InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(ReflectionUtils.getResourceStreamSafe("mappings.json")))) {
            //noinspection deprecation - Outdated gson is used in pre 1.18 versions
            JsonArray array = version < 18
                ? new JsonParser().parse(reader).getAsJsonArray()
                : JsonParser.parseReader(reader).getAsJsonArray();
            
            for (int i = 0; i < array.size(); ++i) {
                JsonObject object = array.get(i).getAsJsonObject();
                processMapping(object, version);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not load mappings", ex);
        }
    }
    
    // private static void processMapping(JsonObject object, double version) {
    
    /**
     * Processes a mapping {@link JsonObject} and adds it to {@link #mappings} if
     * it exists in the current version of Minecraft.
     *
     * @param object  the mapping {@link JsonObject}
     * @param version the current version of Minecraft
     */
    private static void processMapping(JsonObject object, double version) {
        if (version < object.get("min").getAsDouble() || version > object.get("max").getAsDouble())
            return;
        String name = object.get("name").getAsString();
        JsonArray mappingsArray = object.get("mappings").getAsJsonArray();
        String bestMatch = null;
        double lastVersion = 0;
        for (int i = 0; i < mappingsArray.size(); ++i) {
            JsonObject mapping = mappingsArray.get(i).getAsJsonObject();
            double from = mapping.get("from").getAsDouble();
            if (version >= from && from > lastVersion)
                bestMatch = mapping.get("value").getAsString();
        }
        if (bestMatch != null)
            mappings.put(name, bestMatch);
    }
    
    /**
     * Gets the mapped {@link Class} for the given name.
     *
     * @param name the name of the class
     * @return the mapped {@link Class}
     */
    public static Class<?> getMappedClass(String name) {
        if (!mappings.containsKey(name))
            return null;
        return getNMSClass(mappings.get(name));
    }

    /**
     * Gets the mapped {@link Method} for the given name.
     *
     * @param targetClass    the class to get the method from
     * @param name           the name of the method
     * @param parameterTypes the parameter types of the method
     * @return the mapped {@link Method}
     */
    public static Method getMappedMethod(Class<?> targetClass, String name, Class<?>... parameterTypes) {
        if (!mappings.containsKey(name))
            return null;
        return getMethodOrNull(targetClass, mappings.get(name), parameterTypes);
    }

    /**
     * Gets the mapped {@link Field} for the given name.
     *
     * @param targetClass the class to get the field from
     * @param name        the name of the field
     * @param declared    whether to get the declared field or not
     * @return the mapped {@link Field}
     */
    public static Field getMappedField(Class targetClass, String name, boolean declared) {
        if (!mappings.containsKey(name))
            return null;
        return getFieldOrNull(targetClass, mappings.get(name), declared);
    }
    
}
