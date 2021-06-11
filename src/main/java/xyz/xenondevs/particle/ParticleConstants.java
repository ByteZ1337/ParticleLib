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
public final class ParticleConstants {
    
    /* ---------------- Classes ---------------- */
    
    /**
     * Represents the ItemStack class.
     */
    public static final Class ITEM_STACK_CLASS;
    /**
     * Represents the Packet class.
     */
    public static final Class PACKET_CLASS;
    /**
     * Represents the PacketPlayOutWorldParticles class.
     */
    public static final Class PACKET_PLAY_OUT_WORLD_PARTICLES_CLASS;
    /**
     * Represents the EnumParticle enum.
     */
    public static final Class PARTICLE_ENUM;
    /**
     * Represents the Particle class.
     */
    public static final Class PARTICLE_CLASS;
    /**
     * Represents the MiencraftKey class.
     */
    public static final Class MINECRAFT_KEY_CLASS;
    /**
     * Represents the Vector3f class.
     */
    public static final Class VECTOR_3FA_CLASS;
    /**
     * Represents the abstract IRegistry class.
     */
    public static final Class REGISTRY_CLASS;
    /**
     * Represents the Block class.
     */
    public static final Class BLOCK_CLASS;
    /**
     * Represents the BlockPosition class.
     */
    public static final Class BLOCK_POSITION_CLASS;
    /**
     * Represents the IBLockData interface.
     */
    public static final Class BLOCK_DATA_INTERFACE;
    /**
     * Represents the Blocks class.
     */
    public static final Class BLOCKS_CLASS;
    /**
     * Represents the BlockPositionSource class.
     */
    public static final Class BLOCK_POSITION_SOURCE_CLASS;
    /**
     * Represents the VibrationPath class.
     */
    public static final Class VIBRATION_PATH_CLASS;
    /**
     * Represents the EntityPlayer class.
     */
    public static final Class ENTITY_PLAYER_CLASS;
    /**
     * Represents the PlayerConnection class.
     */
    public static final Class PLAYER_CONNECTION_CLASS;
    /**
     * Represents the CraftPlayer class.
     */
    public static final Class CRAFT_PLAYER_CLASS;
    /**
     * Represents the CraftItemStack class.
     */
    public static final Class CRAFT_ITEM_STACK_CLASS;
    /**
     * Represents the ParticleParam class.
     */
    public static final Class PARTICLE_PARAM_CLASS;
    /**
     * Represents the ParticleParamRedstone class.
     */
    public static final Class PARTICLE_PARAM_REDSTONE_CLASS;
    /**
     * Represents the DustColorTransitionOptions class.
     */
    public static final Class PARTICLE_PARAM_DUST_COLOR_TRANSITION_CLASS;
    /**
     * Represents the ParticleParamBlock class.
     */
    public static final Class PARTICLE_PARAM_BLOCK_CLASS;
    /**
     * Represents the ParticleParamItem class.
     */
    public static final Class PARTICLE_PARAM_ITEM_CLASS;
    /**
     * Represents the VibrationParticleOption class.
     */
    public static final Class PARTICLE_PARAM_VIBRATION_CLASS;
    /**
     * Represents the PluginClassLoader class.
     */
    public static final Class PLUGIN_CLASS_LOADER_CLASS;
    
    /* ---------------- Methods ---------------- */
    
    /**
     * Represents the IRegistry#get(MinecraftKey) method.
     */
    public static final Method REGISTRY_GET_METHOD;
    /**
     * Represents the PlayerConnection#sendPacket(); method.
     */
    public static final Method PLAYER_CONNECTION_SEND_PACKET_METHOD;
    /**
     * Represents the CraftPlayer#getHandle(); method.
     */
    public static final Method CRAFT_PLAYER_GET_HANDLE_METHOD;
    /**
     * Represents the Block#getBlockData(); method.
     */
    public static final Method BLOCK_GET_BLOCK_DATA_METHOD;
    /**
     * Represents the CraftItemStack#asNMSCopy(); method.
     */
    public static final Method CRAFT_ITEM_STACK_AS_NMS_COPY_METHOD;
    
    /* ---------------- Fields ---------------- */
    
    /**
     * Represents the EntityPlayer#playerConnection field.
     */
    public static final Field ENTITY_PLAYER_PLAYER_CONNECTION_FIELD;
    /**
     * Represents the PluginClassLoader#plugin field.
     */
    public static final Field PLUGIN_CLASS_LOADER_PLUGIN_FIELD;
    
    /* ---------------- Constructor ---------------- */
    
    /**
     * Represents the PacketPlayOutWorldParticles constructor.
     */
    public static final Constructor PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR;
    /**
     * Represents the MinecraftKey constructor.
     */
    public static final Constructor MINECRAFT_KEY_CONSTRUCTOR;
    /**
     * Represents the Vector3fa constructor.
     */
    public static final Constructor VECTOR_3FA_CONSTRUCTOR;
    /**
     * Represents the BlockPosition constructor.
     */
    public static final Constructor BLOCK_POSITION_CONSTRUCTOR;
    /**
     * Represents the BlockPositionSource constructor.
     */
    public static final Constructor BLOCK_POSITION_SOURCE_CONSTRUCTOR;
    /**
     * Represents the VibrationPath constructor.
     */
    public static final Constructor VIBRATION_PATH_CONSTRUCTOR;
    /**
     * Represents the ParticleParamRedstone constructor.
     */
    public static final Constructor PARTICLE_PARAM_REDSTONE_CONSTRUCTOR;
    /**
     * Represents the DustColorTransitionOptions constructor.
     */
    public static final Constructor PARTICLE_PARAM_DUST_COLOR_TRANSITION_CONSTRUCTOR;
    /**
     * Represents the ParticleParamBlock constructor.
     */
    public static final Constructor PARTICLE_PARAM_BLOCK_CONSTRUCTOR;
    /**
     * Represents the ParticleParamItem constructor.
     */
    public static final Constructor PARTICLE_PARAM_ITEM_CONSTRUCTOR;
    /**
     * Represents the VibrationParticleOption constructor.
     */
    public static final Constructor PARTICLE_PARAM_VIBRATION_CONSTRUCTOR;
    
    
    /* ---------------- Object constants ---------------- */
    
    /**
     * Represents the ParticleType Registry.
     */
    public static final Object PARTICLE_TYPE_REGISTRY;
    /**
     * Represents the Block Registry.
     */
    public static final Object BLOCK_REGISTRY;
    
    /* ---------------- INIT ---------------- */
    
    static {
        int version = MINECRAFT_VERSION;
        // Classes
        ITEM_STACK_CLASS = getNMSClass(version < 17 ? "ItemStack" : "world.item.ItemStack");
        PACKET_CLASS = getNMSClass(version < 17 ? "Packet" : "network.protocol.Packet");
        PACKET_PLAY_OUT_WORLD_PARTICLES_CLASS = getNMSClass(version < 17 ? "PacketPlayOutWorldParticles" : "network.protocol.game.PacketPlayOutWorldParticles");
        PARTICLE_ENUM = version < 13 ? getNMSClass("EnumParticle") : null;
        PARTICLE_CLASS = version < 13 ? null : getNMSClass(version < 17 ? "Particle" : "core.particles.Particle");
        MINECRAFT_KEY_CLASS = getNMSClass(version < 17 ? "MinecraftKey" : "resources.MinecraftKey");
        VECTOR_3FA_CLASS = version < 17 ? getNMSClass("Vector3f") : getClassSafe("com.mojang.math.Vector3fa");
        REGISTRY_CLASS = version < 13 ? null : getNMSClass(version < 17 ? "IRegistry" : "core.IRegistry");
        BLOCK_CLASS = getNMSClass(version < 17 ? "Block" : "world.level.block.Block");
        BLOCK_POSITION_CLASS = getNMSClass(version < 17 ? "BlockPosition" : "core.BlockPosition");
        BLOCK_DATA_INTERFACE = getNMSClass(version < 17 ? "IBlockData" : "world.level.block.state.IBlockData");
        BLOCKS_CLASS = version < 13 ? null : getNMSClass(version < 17 ? "Blocks" : "world.level.block.Blocks");
        BLOCK_POSITION_SOURCE_CLASS = version < 17 ? null : getNMSClass("world.level.gameevent.BlockPositionSource");
        VIBRATION_PATH_CLASS = version < 17 ? null : getNMSClass("world.level.gameevent.vibrations.VibrationPath");
        ENTITY_PLAYER_CLASS = getNMSClass(version < 17 ? "EntityPlayer" : "server.level.EntityPlayer");
        PLAYER_CONNECTION_CLASS = getNMSClass(version < 17 ? "PlayerConnection" : "server.network.PlayerConnection");
        CRAFT_PLAYER_CLASS = getCraftBukkitClass("entity.CraftPlayer");
        CRAFT_ITEM_STACK_CLASS = getCraftBukkitClass("inventory.CraftItemStack");
        PARTICLE_PARAM_CLASS = version < 13 ? null : getNMSClass(version < 17 ? "ParticleParam" : "core.particles.ParticleParam");
        PARTICLE_PARAM_REDSTONE_CLASS = version < 13 ? null : getNMSClass(version < 17 ? "ParticleParamRedstone" : "core.particles.ParticleParamRedstone");
        PARTICLE_PARAM_DUST_COLOR_TRANSITION_CLASS = version < 17 ? null : getNMSClass("core.particles.DustColorTransitionOptions");
        PARTICLE_PARAM_BLOCK_CLASS = version < 13 ? null : getNMSClass(version < 17 ? "ParticleParamBlock" : "core.particles.ParticleParamBlock");
        PARTICLE_PARAM_ITEM_CLASS = version < 13 ? null : getNMSClass(version < 17 ? "ParticleParamItem" : "core.particles.ParticleParamItem");
        PARTICLE_PARAM_VIBRATION_CLASS = version < 17 ? null : getNMSClass("core.particles.VibrationParticleOption");
        PLUGIN_CLASS_LOADER_CLASS = getClassSafe("org.bukkit.plugin.java.PluginClassLoader");
        // Methods
        REGISTRY_GET_METHOD = version < 13 ? null : getMethodOrNull(REGISTRY_CLASS, "get", MINECRAFT_KEY_CLASS);
        PLAYER_CONNECTION_SEND_PACKET_METHOD = getMethodOrNull(PLAYER_CONNECTION_CLASS, "sendPacket", PACKET_CLASS);
        CRAFT_PLAYER_GET_HANDLE_METHOD = getMethodOrNull(CRAFT_PLAYER_CLASS, "getHandle");
        BLOCK_GET_BLOCK_DATA_METHOD = getMethodOrNull(BLOCK_CLASS, "getBlockData");
        CRAFT_ITEM_STACK_AS_NMS_COPY_METHOD = getMethodOrNull(CRAFT_ITEM_STACK_CLASS, "asNMSCopy", ItemStack.class);
        // Fields
        ENTITY_PLAYER_PLAYER_CONNECTION_FIELD = getFieldOrNull(ENTITY_PLAYER_CLASS, version < 17 ? "playerConnection" : "b", false);
        PLUGIN_CLASS_LOADER_PLUGIN_FIELD = getFieldOrNull(PLUGIN_CLASS_LOADER_CLASS, "plugin", true);
        // Constructors
        
        if (version < 13)
            PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR = getConstructorOrNull(PACKET_PLAY_OUT_WORLD_PARTICLES_CLASS, PARTICLE_ENUM, boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class, int[].class);
        else if (version < 15)
            PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR = getConstructorOrNull(PACKET_PLAY_OUT_WORLD_PARTICLES_CLASS, PARTICLE_PARAM_CLASS, boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class);
        else
            PACKET_PLAY_OUT_WORLD_PARTICLES_CONSTRUCTOR = getConstructorOrNull(PACKET_PLAY_OUT_WORLD_PARTICLES_CLASS, PARTICLE_PARAM_CLASS, boolean.class, double.class, double.class, double.class, float.class, float.class, float.class, float.class, int.class);
        
        MINECRAFT_KEY_CONSTRUCTOR = getConstructorOrNull(MINECRAFT_KEY_CLASS, String.class);
        VECTOR_3FA_CONSTRUCTOR = getConstructorOrNull(VECTOR_3FA_CLASS, float.class, float.class, float.class);
        BLOCK_POSITION_CONSTRUCTOR = getConstructorOrNull(BLOCK_POSITION_CLASS, double.class, double.class, double.class);
        BLOCK_POSITION_SOURCE_CONSTRUCTOR = version < 17 ? null : getConstructorOrNull(BLOCK_POSITION_SOURCE_CLASS, BLOCK_POSITION_CLASS);
        VIBRATION_PATH_CONSTRUCTOR = version < 17 ? null : getConstructorOrNull(VIBRATION_PATH_CLASS, BLOCK_POSITION_CLASS, getNMSClass("world.level.gameevent.PositionSource"), int.class);
        
        if (version < 13)
            PARTICLE_PARAM_REDSTONE_CONSTRUCTOR = null;
        else if (version < 17)
            PARTICLE_PARAM_REDSTONE_CONSTRUCTOR = getConstructorOrNull(PARTICLE_PARAM_REDSTONE_CLASS, float.class, float.class, float.class, float.class);
        else
            PARTICLE_PARAM_REDSTONE_CONSTRUCTOR = getConstructorOrNull(PARTICLE_PARAM_REDSTONE_CLASS, VECTOR_3FA_CLASS, float.class);
        
        PARTICLE_PARAM_DUST_COLOR_TRANSITION_CONSTRUCTOR = version < 17 ? null : getConstructorOrNull(PARTICLE_PARAM_DUST_COLOR_TRANSITION_CLASS, VECTOR_3FA_CLASS, VECTOR_3FA_CLASS, float.class);
        PARTICLE_PARAM_BLOCK_CONSTRUCTOR = version < 13 ? null : getConstructorOrNull(PARTICLE_PARAM_BLOCK_CLASS, PARTICLE_CLASS, BLOCK_DATA_INTERFACE);
        PARTICLE_PARAM_ITEM_CONSTRUCTOR = version < 13 ? null : getConstructorOrNull(PARTICLE_PARAM_ITEM_CLASS, PARTICLE_CLASS, ITEM_STACK_CLASS);
        PARTICLE_PARAM_VIBRATION_CONSTRUCTOR = version < 17 ? null : getConstructorOrNull(PARTICLE_PARAM_VIBRATION_CLASS, VIBRATION_PATH_CLASS);
        // Constants
        PARTICLE_TYPE_REGISTRY = version < 13 ? null : readField(getFieldOrNull(REGISTRY_CLASS, version < 17 ? "PARTICLE_TYPE" : "ab", false), null);
        BLOCK_REGISTRY = version < 17 ? null : readField(getFieldOrNull(REGISTRY_CLASS, "W", false), null);
    }
    
}
