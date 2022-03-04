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

public class ParticleMappings {
    
    private static final Map<String, String> mappings = new HashMap<>();
    
    static {
        //noinspection UnnecessaryLocalVariable - More efficient than always accessing the field
        double version = ReflectionUtils.MINECRAFT_VERSION;
        try (InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(ReflectionUtils.getResourceStreamSafe("mappings.json")))) {
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
    
    public static Class<?> getMappedClass(String name) {
        if (!mappings.containsKey(name))
            return null;
        return getNMSClass(mappings.get(name));
    }
    
    public static Method getMappedMethod(Class<?> targetClass, String name, Class<?>... parameterTypes) {
        if (!mappings.containsKey(name))
            return null;
        return getMethodOrNull(targetClass, mappings.get(name), parameterTypes);
    }
    
    public static Field getMappedField(Class targetClass, String name, boolean declared) {
        if (!mappings.containsKey(name))
            return null;
        return getFieldOrNull(targetClass, mappings.get(name), declared);
    }
    
}
