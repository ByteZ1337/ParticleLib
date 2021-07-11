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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xyz.xenondevs.particle.ParticleConstants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static xyz.xenondevs.particle.ParticleConstants.BLOCK_POSITION_CONSTRUCTOR;
import static xyz.xenondevs.particle.ParticleConstants.PLUGIN_CLASS_LOADER_PLUGIN_FIELD;

/**
 * @author ByteZ
 * @see ParticleConstants
 * @since 30.08.2018
 */
public final class ReflectionUtils {
    
    /* ---------------- NMS & CB paths ---------------- */
    
    /**
     * Represents the net minecraft server path
     * <p>
     * e.g. {@code net.minecraft.server.v1_8_R3}, {@code net.minecraft.server.v1_12_R1}
     */
    private static final String NET_MINECRAFT_SERVER_PACKAGE_PATH;
    /**
     * Represents the craftbukkit path
     * <p>
     * e.g. {@code org.bukkit.craftbukkit.v1_8_R3}, {@code org.bukkit.craftbukkit.v1_12_R1}
     */
    private static final String CRAFT_BUKKIT_PACKAGE_PATH;
    
    /* ---------------- PlayerConnection caching ---------------- */
    
    /**
     * The current Minecraft version as an int.
     */
    public static final int MINECRAFT_VERSION;
    
    /**
     * A cache for playerconnections.
     */
    public static final PlayerConnectionCache PLAYER_CONNECTION_CACHE;
    
    /**
     * The current {@link Plugin} using ParticleLib.
     */
    public static Plugin plugin;
    
    static {
        String serverPath = Bukkit.getServer().getClass().getPackage().getName();
        String version = serverPath.substring(serverPath.lastIndexOf(".") + 1);
        String packageVersion = serverPath.substring(serverPath.lastIndexOf(".") + 2);
        MINECRAFT_VERSION = Integer.parseInt(packageVersion.substring(0, packageVersion.lastIndexOf("_")).replace("_", ".").substring(2));
        NET_MINECRAFT_SERVER_PACKAGE_PATH = "net.minecraft" + (MINECRAFT_VERSION < 17 ? ".server." + version : "");
        CRAFT_BUKKIT_PACKAGE_PATH = "org.bukkit.craftbukkit." + version;
        plugin = readDeclaredField(PLUGIN_CLASS_LOADER_PLUGIN_FIELD, ReflectionUtils.class.getClassLoader());
        PLAYER_CONNECTION_CACHE = new PlayerConnectionCache();
    }
    
    /**
     * Gets a class but returns null instead of throwing
     * a {@link ClassNotFoundException}.
     *
     * @param path the path of the class
     * @return the class. If the class isn't found null
     */
    public static Class<?> getClassSafe(String path) {
        try {
            return Class.forName(path);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Gets the nms path of a class without depending on versions
     * <p>
     * e.g.
     * getNMSPath("Block")  = "net.minecraft.server.v1_14_R1.Block"
     * getNMSPath("Entity") = "net.minecraft.server.v1_12_R1.Entity"
     *
     * @param path the path that should be added
     *             to the nms path
     * @return the nms path
     */
    public static String getNMSPath(String path) {
        return getNetMinecraftServerPackagePath() + "." + path;
    }
    
    /**
     * Directly gets the class object over the path
     *
     * @param path the path of the class
     * @return the class. If the class isn't found null
     */
    public static Class<?> getNMSClass(String path) {
        return getClassSafe(getNMSPath(path));
    }
    
    /**
     * Gets the craftbukkit path of a class without depending on versions
     * <p>
     * e.g.
     * getCraftBukkitPath("CraftChunk")              = "org.bukkit.craftbukkit.v1_15_R1.CraftChunk"
     * getCraftBukkitPath("event.CraftEventFactory") = "org.bukkit.craftbukkit.v1_8_R3.event.CraftEventFactory"
     *
     * @param path the path that should be added to the craftbukkit path
     * @return the craftbukkit path
     */
    public static String getCraftBukkitPath(String path) {
        return getCraftBukkitPackagePath() + "." + path;
    }
    
    /**
     * Method to directly get the class object over the path
     *
     * @param path the path of the class
     * @return the class. If the class isn't found null
     */
    public static Class<?> getCraftBukkitClass(String path) {
        return getClassSafe(getCraftBukkitPath(path));
    }
    
    /**
     * Method to not get disturbed by the forced try catch block
     *
     * @param targetClass    the {@link Class} the {@link Method} is in
     * @param methodName     the name of the target {@link Method}
     * @param parameterTypes the parameterTypes of the {@link Method}
     * @return if found the target {@link Method}. If not found null.
     */
    public static Method getMethodOrNull(Class targetClass, String methodName, Class<?>... parameterTypes) {
        try {
            return targetClass.getMethod(methodName, parameterTypes);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Method to not get disturbed by the forced try catch block
     *
     * @param targetClass the {@link Class} the {@link Field} is in
     * @param fieldName   the name of the target {@link Field}
     * @param declared    defines if the target {@link Field} is private
     * @return if found the target {@link Field}. If not found null.
     */
    public static Field getFieldOrNull(Class targetClass, String fieldName, boolean declared) {
        try {
            return declared ? targetClass.getDeclaredField(fieldName) : targetClass.getField(fieldName);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Gets a constructor without throwing exceptions
     *
     * @param targetClass    the {@link Class} the {@link Constructor} is in
     * @param parameterTypes the parameterTypes of the {@link Constructor}
     * @return if found the target {@link Constructor}. If not found null.
     */
    public static Constructor getConstructorOrNull(Class targetClass, Class... parameterTypes) {
        try {
            return targetClass.getConstructor(parameterTypes);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Checks if a class exists
     *
     * @param path the path of the class that should be checked
     * @return true if the defined class exists
     * @author StudioCode
     */
    public static boolean existsClass(String path) {
        try {
            Class.forName(path);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
    
    /**
     * Gets the specified Field over the specified fieldName and the given targetClass. Then reads the specified
     * {@link Field} from the specified {@link Object}. When the {@link Field} is static
     * set the object to {@code null}.
     *
     * @param targetClass the {@link Class} of the field
     * @param fieldName   the name of the searched {@link Field}
     * @param object      the {@link Object} from which the specified {@link Field Fields} value is to be extracted.
     * @return the extracted value of the specified {@link Field} in the specified {@link Object}.
     */
    public static Object readField(Class targetClass, String fieldName, Object object) {
        if (targetClass == null || fieldName == null)
            return null;
        return readField(getFieldOrNull(targetClass, fieldName, false), object);
    }
    
    /**
     * Reads the specified {@link Field} from the specified {@link Object}. When the
     * {@link Field} is static set the object to {@code null}.
     *
     * @param field  the {@link Field} from which the value should be extracted.
     * @param object the {@link Object} from which the specified {@link Field Fields} value is to be extracted.
     * @return the extracted value of the specified {@link Field} in the specified {@link Object}.
     */
    public static <T> T readField(Field field, Object object) {
        if (field == null)
            return null;
        try {
            return (T) field.get(object);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Gets the specified declared Field over  the specified fieldName and the given
     * targetClass. Then reads the specified {@link Field} from the specified
     * {@link Object}. When the {@link Field} is static set the object to {@code null}.
     *
     * @param targetClass the {@link Class} of the field
     * @param fieldName   the name of the searched {@link Field}
     * @param object      the {@link Object} from which the specified {@link Field Fields} value is to be extracted.
     * @return the extracted value of the specified {@link Field} in the specified {@link Object}.
     */
    public static Object readDeclaredField(Class targetClass, String fieldName, Object object) {
        if (targetClass == null || fieldName == null)
            return null;
        return readDeclaredField(getFieldOrNull(targetClass, fieldName, true), object);
    }
    
    /**
     * Reads the declared specified {@link Field} from the specified {@link Object}.
     * When the {@link Field} is static set the object to {@code null}.
     *
     * @param field  the {@link Field} from which the value should be extracted.
     * @param object the {@link Object} from which the specified {@link Field Fields} value is to be extracted.
     * @return the extracted value of the specified {@link Field} in the specified {@link Object}.
     */
    public static <T> T readDeclaredField(Field field, Object object) {
        if (field == null)
            return null;
        field.setAccessible(true);
        try {
            return (T) field.get(object);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Gets the specified declared {@link Field} over the specified fieldName and the given
     * targetClass. Then writes the specified value into the specified {@link Field}
     * in the given {@link Object}.
     *
     * @param targetClass the {@link Class} of the field.
     * @param fieldName   the name of the searched {@link Field}.
     * @param object      the {@link Object} whose {@link Field Fields} value should be changed.
     * @param value       the value which should be set in the specified {@link Field}.
     */
    public static void writeDeclaredField(Class targetClass, String fieldName, Object object, Object value) {
        if (targetClass == null || fieldName == null)
            return;
        writeDeclaredField(getFieldOrNull(targetClass, fieldName, true), object, value);
    }
    
    /**
     * Writes a value to the specified declared {@link Field} in the
     * given {@link Object}.
     *
     * @param field  the {@link Field} which should be changed.
     * @param object the {@link Object} whose {@link Field Fields} value should be changed.
     * @param value  the value which should be set in the specified {@link Field}.
     */
    public static void writeDeclaredField(Field field, Object object, Object value) {
        if (field == null)
            return;
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (Exception ignored) {
        }
    }
    
    /**
     * Gets the specified {@link Field} over the specified fieldName and the given
     * targetClass. Then writes the specified value into the specified {@link Field}
     * in the given {@link Object}.
     *
     * @param targetClass the {@link Class} of the field.
     * @param fieldName   the name of the searched {@link Field}.
     * @param object      the {@link Object} whose {@link Field Fields} value should be changed.
     * @param value       the value which should be set in the specified {@link Field}.
     */
    public static void writeField(Class targetClass, String fieldName, Object object, Object value) {
        if (targetClass == null || fieldName == null)
            return;
        writeField(getFieldOrNull(targetClass, fieldName, false), object, value);
    }
    
    /**
     * Writes a value to the specified {@link Field} in the given
     * {@link Object}.
     *
     * @param field  the {@link Field} which should be changed.
     * @param object the {@link Object} whose {@link Field Fields} value should be changed.
     * @param value  the value which should be set in the specified {@link Field}.
     */
    public static void writeField(Field field, Object object, Object value) {
        if (field == null)
            return;
        try {
            field.set(object, value);
        } catch (Exception ignored) {
        }
    }
    
    /**
     * @return the nms path
     */
    public static String getNetMinecraftServerPackagePath() {
        return NET_MINECRAFT_SERVER_PACKAGE_PATH;
    }
    
    /**
     * @return the craftbukkit path
     */
    public static String getCraftBukkitPackagePath() {
        return CRAFT_BUKKIT_PACKAGE_PATH;
    }
    
    /**
     * Creates a new MinecraftKey with the given data.
     *
     * @param key the data that should be
     *            used in the constructor
     *            of the key.
     * @return the new MinecraftKey
     */
    public static Object getMinecraftKey(String key) {
        if (key == null)
            return null;
        try {
            return ParticleConstants.MINECRAFT_KEY_CONSTRUCTOR.newInstance(key);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Creates a new Vector3fa instance.
     *
     * @param x x value of the vector.
     * @param y y value of the vector.
     * @param z z value of the vector.
     * @return a Vector3fa instance with the specified coordinates.
     */
    public static Object createVector3fa(float x, float y, float z) {
        try {
            return ParticleConstants.VECTOR_3FA_CONSTRUCTOR.newInstance(x, y, z);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Creates a new BlockPosition.
     *
     * @param location the {@link Location} of the block.
     * @return the BlockPosition of the location
     */
    public static Object createBlockPosition(Location location) {
        try {
            return BLOCK_POSITION_CONSTRUCTOR.newInstance(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Gets the EntityPlayer instance of a CraftPlayer
     *
     * @param player the CraftPlayer
     * @return the EntityPlayer instance of the defined CraftPlayer or {@code null} if either the given parameter is invalid or an error occurs.
     */
    public static Object getPlayerHandle(Player player) {
        if (player == null || player.getClass() != ParticleConstants.CRAFT_PLAYER_CLASS)
            return null;
        try {
            return ParticleConstants.CRAFT_PLAYER_GET_HANDLE_METHOD.invoke(player);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Gets the PlayerConnection of a {@link Player}
     *
     * @param target the target {@link Player}
     * @return the PlayerConnection of the specified target {@link Player}
     */
    public static Object getPlayerConnection(Player target) {
        try {
            return readField(ParticleConstants.ENTITY_PLAYER_PLAYER_CONNECTION_FIELD, getPlayerHandle(target));
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Sends a packet to a defined player.
     *
     * @param player the player that should receive the packet
     * @param packet the packet that should be sent
     */
    public static void sendPacket(Player player, Object packet) {
        try {
            Object connection = PLAYER_CONNECTION_CACHE.getConnection(player);
            ParticleConstants.PLAYER_CONNECTION_SEND_PACKET_METHOD.invoke(connection, packet);
        } catch (Exception ignored) {
        }
    }
    
}
