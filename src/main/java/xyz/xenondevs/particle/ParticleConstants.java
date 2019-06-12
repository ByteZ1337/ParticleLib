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

package xyz.xenondevs.particle;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static xyz.xenondevs.particle.utils.ReflectionUtils.*;

/**
 * Constants for particles.
 *
 * @author ByteZ
 * @since 10.06.2019
 */
public class ParticleConstants {

    /* ---------------- Classes ---------------- */

    /**
     * Represents the ItemStack class.
     */
    private static final Class ITEM_STACK_CLASS;
    /**
     * Represents the Packet class.
     */
    private static final Class PACKET_CLASS;
    /**
     * Represents the PacketPlayOutWorldParticles class.
     */
    private static final Class PACKET_PLAY_OUT_WORLD_PARTICLES_CLASS;
    /**
     * Represents the EnumParticle enum.
     */
    private static final Class PARTICLE_ENUM;
    /**
     * Represents the Particle class.
     */
    private static final Class PARTICLE_CLASS;
    /**
     * Represents the MiencraftKey class.
     */
    private static final Class MINECRAFT_KEY_CLASS;
    /**
     * Represents the abstract IRegistry class.
     */
    private static final Class REGISTRY_CLASS;
    /**
     * Represents the Block class.
     */
    private static final Class BLOCK_CLASS;
    /**
     * Represents the IBLockData interface.
     */
    private static final Class BLOCK_DATA_INTERFACE;
    /**
     * Represents the Blocks class.
     */
    private static final Class BLOCKS_CLASS;
    /**
     * Represents the EntityPlayer class.
     */
    private static final Class ENTITY_PLAYER_CLASS;
    /**
     * Represents the PlayerConnection class.
     */
    private static final Class PLAYER_CONNECTION_CLASS;
    /**
     * Represents the CraftPlayer class.
     */
    private static final Class CRAFT_PLAYER_CLASS;
    /**
     * Represents the CraftItemStack class.
     */
    private static final Class CRAFT_ITEM_STACK_CLASS;
    /**
     * Represents the ParticleParam class.
     */
    private static final Class PARTICLE_PARAM_CLASS;
    /**
     * Represents the ParticleParamRedstone class.
     */
    private static final Class PARTICLE_PARAM_REDSTONE_CLASS;
    /**
     * Represents the ParticleParamBlock class.
     */
    private static final Class PARTICLE_PARAM_BLOCK_CLASS;
    /**
     * Represents the ParticleParamItem class.
     */
    private static final Class PARTICLE_PARAM_ITEM_CLASS;

    /* ---------------- Methods ---------------- */

    /**
     * Represents the IRegistry#get(MinecraftKey) method.
     */
    private static final Method REGISTRY_GET_METHOD;
    /**
     * Represents the PlayerConnection#sendPacket(); method.
     */
    private static final Method PLAYER_CONNECTION_SEND_PACKET_METHOD;
    /**
     * Represents the CraftPlayer#getHandle(); method.
     */
    private static final Method CRAFT_PLAYER_GET_HANDLE_METHOD;
    /**
     * Represents the Block#getBlockData(); method.
     */
    private static final Method BLOCK_GET_BLOCK_DATA_METHOD;
    /**
     * Represents the CraftItemStack#asNMSCopy(); method.
     */
    private static final Method CRAFT_ITEM_STACK_AS_NMS_COPY_METHOD;

    /* ---------------- Fields ---------------- */

    /**
     * Represents the EntityPlayer#playerConnection field.
     */
    private static final Field ENTITY_PLAYER_PLAYER_CONNECTION_FIELD;

    /* ---------------- Constructor ---------------- */

    /**
     * Represents the PacketPlayOutWorldParticles constructor.
     */
    private static final Constructor PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR;
    /**
     * Represents the MinecraftKey constructor.
     */
    private static final Constructor MINECRAFT_KEY_CONSTRUCTOR;
    /**
     * Represents the ParticleParamRedstone constructor.
     */
    private static final Constructor PARTICLE_PARAM_REDSTONE_CONSTRUCTOR;
    /**
     * Represents the ParticleParamBlock constructor.
     */
    private static final Constructor PARTICLE_PARAM_BLOCK_CONSTRUCTOR;
    /**
     * Represents the ParticleParamItem constructor.
     */
    private static final Constructor PARTICLE_PARAM_ITEM_CONSTRUCTOR;

    /* ---------------- Object constants ---------------- */

    /**
     * Represents the ParticleType Registry.
     */
    private static final Object PARTICLE_TYPE_REGISTRY;

    /* ---------------- INIT ---------------- */

    static {
        int version = MINECRAFT_VERSION;
        // Classes
        ITEM_STACK_CLASS = getNMSClass("ItemStack");
        PACKET_CLASS = getNMSClass("Packet");
        PACKET_PLAY_OUT_WORLD_PARTICLES_CLASS = getNMSClass("PacketPlayOutWorldParticles");
        PARTICLE_ENUM = version < 13 ? getNMSClass("EnumParticle") : null;
        PARTICLE_CLASS = version < 13 ? null : getNMSClass("Particle");
        MINECRAFT_KEY_CLASS = getNMSClass("MinecraftKey");
        REGISTRY_CLASS = version < 13 ? null : getNMSClass("IRegistry");
        BLOCK_CLASS = getNMSClass("Block");
        BLOCK_DATA_INTERFACE = getNMSClass("IBlockData");
        BLOCKS_CLASS = version < 13 ? null : getNMSClass("Blocks");
        ENTITY_PLAYER_CLASS = getNMSClass("EntityPlayer");
        PLAYER_CONNECTION_CLASS = getNMSClass("PlayerConnection");
        CRAFT_PLAYER_CLASS = getCraftBukkitClass("entity.CraftPlayer");
        CRAFT_ITEM_STACK_CLASS = getCraftBukkitClass("inventory.CraftItemStack");
        PARTICLE_PARAM_CLASS = version < 13 ? null : getNMSClass("ParticleParam");
        PARTICLE_PARAM_REDSTONE_CLASS = version < 13 ? null : getNMSClass("ParticleParamRedstone");
        PARTICLE_PARAM_BLOCK_CLASS = version < 13 ? null : getNMSClass("ParticleParamBlock");
        PARTICLE_PARAM_ITEM_CLASS = version < 13 ? null : getNMSClass("ParticleParamItem");
        // Methods
        REGISTRY_GET_METHOD = version < 13 ? null : getMethodOrNull(getRegistryClass(), "get", getMinecraftKeyClass());
        PLAYER_CONNECTION_SEND_PACKET_METHOD = getMethodOrNull(getPlayerConnectionClass(), "sendPacket", getPacketClass());
        CRAFT_PLAYER_GET_HANDLE_METHOD = getMethodOrNull(getCraftPlayerClass(), "getHandle");
        BLOCK_GET_BLOCK_DATA_METHOD = getMethodOrNull(getBlockClass(), "getBlockData");
        CRAFT_ITEM_STACK_AS_NMS_COPY_METHOD = getMethodOrNull(getCraftItemStackClass(), "asNMSCopy", ItemStack.class);
        //Fields
        ENTITY_PLAYER_PLAYER_CONNECTION_FIELD = getFieldOrNull(getEntityPlayerClass(), "playerConnection", false);
        //Constructors
        PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR = version < 13 ?
                getConstructorOrNull(getPacketPlayOutWorldParticlesClass(), getParticleEnum(), boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class, int[].class) :
                getConstructorOrNull(getPacketPlayOutWorldParticlesClass(), getParticleParamClass(), boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class);
        MINECRAFT_KEY_CONSTRUCTOR = getConstructorOrNull(getMinecraftKeyClass(), String.class);
        PARTICLE_PARAM_REDSTONE_CONSTRUCTOR = version < 13 ? null : getConstructorOrNull(getParticleParamRedstoneClass(), float.class, float.class, float.class, float.class);
        PARTICLE_PARAM_BLOCK_CONSTRUCTOR = version < 13 ? null : getConstructorOrNull(getParticleParamBlockClass(), getParticleClass(), getBlockDataInterface());
        PARTICLE_PARAM_ITEM_CONSTRUCTOR = version < 13 ? null : getConstructorOrNull(getParticleParamItemClass(), getParticleClass(), getItemStackClass());
        // Constants
        PARTICLE_TYPE_REGISTRY = version < 13 ? null : readField(getFieldOrNull(getRegistryClass(), "PARTICLE_TYPE", false), null);
    }

    /* ---------------- Class Getters ---------------- */

    /**
     * @return the ItemStack class.
     */
    public static Class getItemStackClass() {
        return ITEM_STACK_CLASS;
    }

    /**
     * @return the Packet class.
     */
    public static Class getPacketClass() {
        return PACKET_CLASS;
    }

    /**
     * @return the PacketPlayOutWorldParticles class.
     */
    public static Class getPacketPlayOutWorldParticlesClass() {
        return PACKET_PLAY_OUT_WORLD_PARTICLES_CLASS;
    }

    /**
     * @return the EnumParticle enum.
     */
    public static Class getParticleEnum() {
        return PARTICLE_ENUM;
    }

    /**
     * @return the Particle class.
     */
    public static Class getParticleClass() {
        return PARTICLE_CLASS;
    }

    /**
     * @return the MiencraftKey class.
     */
    public static Class getMinecraftKeyClass() {
        return MINECRAFT_KEY_CLASS;
    }

    /**
     * @return the abstract IRegistry class.
     */
    public static Class getRegistryClass() {
        return REGISTRY_CLASS;
    }

    /**
     * @return the Block class.
     */
    public static Class getBlockClass() {
        return BLOCK_CLASS;
    }

    /**
     * @return the IBLockData interace.
     */
    public static Class getBlockDataInterface() {
        return BLOCK_DATA_INTERFACE;
    }

    /**
     * @return the Blocks class.
     */
    public static Class getBlocksClass() {
        return BLOCKS_CLASS;
    }

    /**
     * @return the EntityPlayer class.
     */
    public static Class getEntityPlayerClass() {
        return ENTITY_PLAYER_CLASS;
    }

    /**
     * @return the PlayerConnection class.
     */
    public static Class getPlayerConnectionClass() {
        return PLAYER_CONNECTION_CLASS;
    }

    /**
     * @return the CraftPlayer class.
     */
    public static Class getCraftPlayerClass() {
        return CRAFT_PLAYER_CLASS;
    }

    /**
     * @return the CraftItemStack class.
     */
    public static Class getCraftItemStackClass() {
        return CRAFT_ITEM_STACK_CLASS;
    }

    /**
     * @return the ParticleParam class.
     */
    public static Class getParticleParamClass() {
        return PARTICLE_PARAM_CLASS;
    }

    /**
     * @return the ParticleParamRedstone class.
     */
    public static Class getParticleParamRedstoneClass() {
        return PARTICLE_PARAM_REDSTONE_CLASS;
    }

    /**
     * @return the ParticleParamBlock class.
     */
    public static Class getParticleParamBlockClass() {
        return PARTICLE_PARAM_BLOCK_CLASS;
    }

    /**
     * @return the ParticleParamItem class.
     */
    public static Class getParticleParamItemClass() {
        return PARTICLE_PARAM_ITEM_CLASS;
    }

    /* ---------------- Method Getters ---------------- */

    /**
     * @return the IRegistry#get(MinecraftKey) method
     */
    public static Method getRegistryGetMethod() {
        return REGISTRY_GET_METHOD;
    }

    /**
     * @return the PlayerConnection#sendPacket(); method.
     */
    public static Method getPlayerConnectionSendPacketMethod() {
        return PLAYER_CONNECTION_SEND_PACKET_METHOD;
    }

    /**
     * @return the CraftPlayer#getHandle(); method.
     */
    public static Method getCraftPlayerGetHandleMethod() {
        return CRAFT_PLAYER_GET_HANDLE_METHOD;
    }

    /**
     * @return the Block#getBlockData(); method.
     */
    public static Method getBlockGetBlockDataMethod() {
        return BLOCK_GET_BLOCK_DATA_METHOD;
    }

    /**
     * @return the CraftItemStack#asNMSCopy(); method
     */
    public static Method getCraftItemStackAsNmsCopyMethod() {
        return CRAFT_ITEM_STACK_AS_NMS_COPY_METHOD;
    }

    /* ---------------- Field Getters ---------------- */

    /**
     * @return the EntityPlayer#playerConnection field.
     */
    public static Field getEntityPlayerPlayerConnectionField() {
        return ENTITY_PLAYER_PLAYER_CONNECTION_FIELD;
    }

    /* ---------------- Constructor Getters ---------------- */

    /**
     * @return the PacketPlayOutWorldParticles constructor.
     */
    public static Constructor getPacketPlayOutWorldParticlesConstructor() {
        return PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR;
    }

    /**
     * @return the MinecraftKey constructor.
     */
    public static Constructor getMinecraftKeyConstructor() {
        return MINECRAFT_KEY_CONSTRUCTOR;
    }

    /**
     * @return the ParticleParamRedstone constructor.
     */
    public static Constructor getParticleParamRedstoneConstructor() {
        return PARTICLE_PARAM_REDSTONE_CONSTRUCTOR;
    }

    /**
     * @return the ParticleParamBlock constructor.
     */
    public static Constructor getParticleParamBlockConstructor() {
        return PARTICLE_PARAM_BLOCK_CONSTRUCTOR;
    }

    /**
     * @return the ParticleParamItem constructor.
     */
    public static Constructor getParticleParamItemConstructor() {
        return PARTICLE_PARAM_ITEM_CONSTRUCTOR;
    }

    /* ---------------- Other Getters ---------------- */

    /**
     * @return the ParticleType Registry.
     */
    public static Object getParticleTypeRegistry() {
        return PARTICLE_TYPE_REGISTRY;
    }
}
