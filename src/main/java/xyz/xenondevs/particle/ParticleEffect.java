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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.data.VibrationData;
import xyz.xenondevs.particle.data.color.*;
import xyz.xenondevs.particle.data.texture.BlockTexture;
import xyz.xenondevs.particle.data.texture.ItemTexture;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static xyz.xenondevs.particle.ParticleConstants.PARTICLE_TYPE_REGISTRY;
import static xyz.xenondevs.particle.ParticleConstants.REGISTRY_GET_METHOD;
import static xyz.xenondevs.particle.PropertyType.*;

/**
 * {@link Enum} to reflect particle packets with their respective data.
 * <p>
 * List of all currently supported effects:
 * <ul>
 * <li>{@link #ASH}</li>
 * <li>{@link #BARRIER}</li>
 * <li>{@link #BLOCK_CRACK}</li>
 * <li>{@link #BLOCK_DUST}</li>
 * <li>{@link #BUBBLE_COLUMN_UP}</li>
 * <li>{@link #BUBBLE_POP}</li>
 * <li>{@link #CAMPFIRE_COSY_SMOKE}</li>
 * <li>{@link #CAMPFIRE_SIGNAL_SMOKE}</li>
 * <li>{@link #CLOUD}</li>
 * <li>{@link #COMPOSTER}</li>
 * <li>{@link #CRIMSON_SPORE}</li>
 * <li>{@link #CRIT}</li>
 * <li>{@link #CRIT_MAGIC}</li>
 * <li>{@link #CURRENT_DOWN}</li>
 * <li>{@link #DAMAGE_INDICATOR}</li>
 * <li>{@link #DOLPHIN}</li>
 * <li>{@link #DRAGON_BREATH}</li>
 * <li>{@link #DRIP_LAVA}</li>
 * <li>{@link #DRIP_WATER}</li>
 * <li>{@link #DRIPPING_DRIPSTONE_LAVA}</li>
 * <li>{@link #DRIPPING_DRIPSTONE_WATER}</li>
 * <li>{@link #DRIPPING_HONEY}</li>
 * <li>{@link #DRIPPING_OBSIDIAN_TEAR}</li>
 * <li>{@link #DUST_COLOR_TRANSITION}</li>
 * <li>{@link #ELECTRIC_SPARK}</li>
 * <li>{@link #ENCHANTMENT_TABLE}</li>
 * <li>{@link #END_ROD}</li>
 * <li>{@link #EXPLOSION_HUGE}</li>
 * <li>{@link #EXPLOSION_LARGE}</li>
 * <li>{@link #EXPLOSION_NORMAL}</li>
 * <li>{@link #FALLING_DRIPSTONE_LAVA}</li>
 * <li>{@link #FALLING_DRIPSTONE_WATER}</li>
 * <li>{@link #FALLING_DUST}</li>
 * <li>{@link #FALLING_HONEY}</li>
 * <li>{@link #FALLING_NECTAR}</li>
 * <li>{@link #FALLING_OBSIDIAN_TEAR}</li>
 * <li>{@link #FALLING_SPORE_BLOSSOM}</li>
 * <li>{@link #FIREWORKS_SPARK}</li>
 * <li>{@link #FLAME}</li>
 * <li>{@link #FLASH}</li>
 * <li>{@link #FOOTSTEP}</li>
 * <li>{@link #GLOW}</li>
 * <li>{@link #GLOW_SQUID_INK}</li>
 * <li>{@link #HEART}</li>
 * <li>{@link #ITEM_CRACK}</li>
 * <li>{@link #LANDING_HONEY}</li>
 * <li>{@link #LANDING_OBSIDIAN_TEAR}</li>
 * <li>{@link #LAVA}</li>
 * <li>{@link #LIGHT}</li>
 * <li>{@link #MOB_APPEARANCE}</li>
 * <li>{@link #NAUTILUS}</li>
 * <li>{@link #NOTE}</li>
 * <li>{@link #PORTAL}</li>
 * <li>{@link #REDSTONE}</li>
 * <li>{@link #REVERSE_PORTAL}</li>
 * <li>{@link #SCRAPE}</li>
 * <li>{@link #SLIME}</li>
 * <li>{@link #SMOKE_LARGE}</li>
 * <li>{@link #SMOKE_NORMAL}</li>
 * <li>{@link #SNEEZE}</li>
 * <li>{@link #SNOWBALL}</li>
 * <li>{@link #SNOWFLAKE}</li>
 * <li>{@link #SNOW_SHOVEL}</li>
 * <li>{@link #SOUL}</li>
 * <li>{@link #SOUL_FIRE_FLAME}</li>
 * <li>{@link #SPELL}</li>
 * <li>{@link #SPELL_INSTANT}</li>
 * <li>{@link #SPELL_MOB}</li>
 * <li>{@link #SPELL_MOB_AMBIENT}</li>
 * <li>{@link #SPELL_WITCH}</li>
 * <li>{@link #SPIT}</li>
 * <li>{@link #SPORE_BLOSSOM_AIR}</li>
 * <li>{@link #SQUID_INK}</li>
 * <li>{@link #SUSPENDED}</li>
 * <li>{@link #SUSPENDED_DEPTH}</li>
 * <li>{@link #SWEEP_ATTACK}</li>
 * <li>{@link #TOTEM}</li>
 * <li>{@link #TOWN_AURA}</li>
 * <li>{@link #VIBRATION}</li>
 * <li>{@link #VILLAGER_ANGRY}</li>
 * <li>{@link #VILLAGER_HAPPY}</li>
 * <li>{@link #WARPED_SPORE}</li>
 * <li>{@link #WATER_BUBBLE}</li>
 * <li>{@link #WATER_DROP}</li>
 * <li>{@link #WATER_SPLASH}</li>
 * <li>{@link #WATER_WAKE}</li>
 * <li>{@link #WAX_OFF}</li>
 * <li>{@link #WAX_ON}</li>
 * <li>{@link #WHITE_ASH}</li>
 * </ul>
 *
 * @author ByteZ
 * @since 28.05.2019
 */
public enum ParticleEffect {
    
    /**
     * In vanilla, this particle is randomly displayed in the
     * basalt deltas nether biome.
     * <p>
     * The movement of this particle is handled completely clientside
     * and can therefore not be influenced.
     * <p>
     * <b>Information</b>
     * <ul>
     * <li>Appearance: Gray/White square</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: This Particle gets a random velocity while falling down.</li>
     * </ul>
     */
    ASH(version -> version < 16 ? "NONE" : "ash"),
    /**
     * In vanilla, this particle is displayed by barrier blocks
     * when a player holds a barrier item in the main or off hand.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Red box with a slash through it.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    BARRIER(version -> version < 8 ? "NONE" : (version < 13 ? "BARRIER" : "barrier")),
    /**
     * In vanilla, this particle is displayed when a player breaks
     * a block or sprints. It's also displayed by iron golems while
     * walking.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Little piece of a texture.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: This particle needs a block texture in order to work.</li>
     * </ul>
     */
    BLOCK_CRACK(version -> version < 8 ? "NONE" : (version < 13 ? "BLOCK_CRACK" : "block"), REQUIRES_BLOCK),
    /**
     * In vanilla, this particle is displayed when an entity hits the ground
     * after falling. It's also displayed when a armorstand is broken.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Little piece of a texture.</li>
     * <li>Extra:<ul>
     * <li>  The velocity of this particle can be set. The amount has to be 0</li>
     * <li> This particle needs a block texture in order to work.</li></ul></li>
     * </ul>
     */
    BLOCK_DUST(version -> version < 8 ? "NONE" : (version < 13 ? "BLOCK_DUST" : "falling_dust"), DIRECTIONAL, REQUIRES_BLOCK),
    /**
     * In vanilla, this particle is randomly displayed by magma
     * blocks and soulsand underwater.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Bubble with blue outline.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    BUBBLE_COLUMN_UP(version -> version < 13 ? "NONE" : "bubble_column_up", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed at the top of
     * bubble columns.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue circle.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    BUBBLE_POP(version -> version < 13 ? "NONE" : "bubble_pop", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by campfires.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Smoke cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    CAMPFIRE_COSY_SMOKE(version -> version < 14 ? "NONE" : "campfire_cosy_smoke", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by campfires with
     * a hay bale placed under them.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Smoke cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    CAMPFIRE_SIGNAL_SMOKE(version -> version < 14 ? "NONE" : "campfire_signal_smoke", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when an entity dies.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Large white cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    CLOUD(version -> version < 8 ? "NONE" : (version < 13 ? "CLOUD" : "cloud"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when a composter
     * is used by a player.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Green start</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    COMPOSTER(version -> version < 14 ? "NONE" : "composter"),
    /**
     * In vanilla, this particle is displayed in the crimson forest
     * nether biome.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Pink square.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: This Particle gets a random velocity up.</li>
     * </ul>
     */
    CRIMSON_SPORE(version -> version < 16 ? "NONE" : "crimson_spore"),
    /**
     * In vanilla, this particle is displayed when a player lands
     * a critical hit on an entity or an  arrow is launched with full power.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Light brown cross.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * </ul>
     */
    CRIT(version -> version < 8 ? "NONE" : (version < 13 ? "CRIT" : "crit"), DIRECTIONAL),
    /**
     * In vanilla, this particle  is displayed when a player hits
     * an entity with a sharpness sword.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Cyan star.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * </ul>
     */
    CRIT_MAGIC(version -> version < 8 ? "NONE" : (version < 13 ? "CRIT_MAGIC" : "enchanted_hit"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by magma blocks
     * under water.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Cyan star.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    CURRENT_DOWN(version -> version < 13 ? "NONE" : "current_down"),
    /**
     * In vanilla, this particle is displayed when a Player hits
     * an Entity by melee attack.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A dark red heart.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    DAMAGE_INDICATOR(version -> version < 9 ? "NONE" : (version < 13 ? "DAMAGE_INDICATOR" : "damage_indicator"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed as a trail of
     * dolphins.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A blue square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    DOLPHIN(version -> version < 13 ? "NONE" : "dolphin"),
    /**
     * In vanilla, this particle is displayed by the ender dragons
     * breath and ender fireballs.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A purple cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    DRAGON_BREATH(version -> version < 9 ? "NONE" : (version < 13 ? "DRAGON_BREATH" : "dragon_breath"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed randomly when a
     * lava block is above a block.
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Orange drop.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    DRIP_LAVA(version -> version < 8 ? "NONE" : (version < 13 ? "DRIP_LAVA" : "dripping_lava")),
    /**
     * In vanilla, this particle is displayed randomly when a
     * water block is above a block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue drop.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    DRIP_WATER(version -> version < 8 ? "NONE" : (version < 13 ? "DRIP_WATER" : "dripping_water")),
    /**
     * In vanilla, this particle is shown dripping from the
     * tip of pointed dripstones.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Orange drop.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    DRIPPING_DRIPSTONE_LAVA(version -> version < 17 ? "NONE" : "dripping_dripstone_lava"),
    /**
     * In vanilla, this particle is shown dripping from the
     * tip of pointed dripstones.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue drop.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    DRIPPING_DRIPSTONE_WATER(version -> version < 17 ? "NONE" : "dripping_dripstone_water"),
    /**
     * In vanilla, this particle is displayed by beehives filled
     * with honey. As opposed to the {@link #FALLING_HONEY} particles,
     * this particle floats in the air before falling to the ground.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A rectangular honey drop.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: Spawns a {@link #LANDING_HONEY} particle after landing on a block.</li>
     * </ul>
     */
    DRIPPING_HONEY(version -> version < 15 ? "NONE" : "dripping_honey"),
    /**
     * In vanilla, this particle is displayed by crying obsidian.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A rectangular obsidian tear.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: Spawns a {@link #LANDING_OBSIDIAN_TEAR} particle after landing on a block.</li>
     * </ul>
     */
    DRIPPING_OBSIDIAN_TEAR(version -> version < 16 ? "NONE" : "dripping_obsidian_tear"),
    /**
     * In vanilla, this particle is displayed when a sculk sensor is triggered.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny colored cloud that changes color.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: This particle supports 2 colors. It will display a fade animation between the two colors. It also
     * supports a custom size.
     * More information can be found here: {@link PropertyType#DUST}, {@link DustColorTransitionData}</li>
     * </ul>
     */
    DUST_COLOR_TRANSITION(version -> version < 17 ? "NONE" : "dust_color_transition", COLORABLE, DUST),
    /**
     * In vanilla, this particle appears when a lightning bolt hits
     * copper blocks.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A small spark.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set.</li>
     * </ul>
     */
    ELECTRIC_SPARK(version -> version < 17 ? "NONE" : "electric_spark", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by bookshelves near
     * an enchanting table.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A random letter from the galactic alphabet.</li>
     * <li>Speed value: Influences the spread of this particle effect.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    ENCHANTMENT_TABLE(version -> version < 8 ? "NONE" : (version < 13 ? "ENCHANTMENT_TABLE" : "enchant"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by end rods and
     * shulker bullets.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: White circle.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    END_ROD(version -> version < 9 ? "NONE" : (version < 13 ? "END_ROD" : "end_rod"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when tnt or creeper
     * explodes.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Gray ball which fades away after a few seconds.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    EXPLOSION_HUGE(version -> version < 8 ? "NONE" : (version < 13 ? "EXPLOSION_HUGE" : "explosion_emitter")),
    /**
     * In vanilla, this particle is displayed when a fireball
     * explodes or a wither skull hits a block/entity.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Gray ball which fades away after a few seconds.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    EXPLOSION_LARGE(version -> version < 8 ? "NONE" : (version < 13 ? "EXPLOSION_LARGE" : "explosion")),
    /**
     * In vanilla, this particle is displayed when either a creeper or
     * a tnt explodes.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: White smoke.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * </ul>
     */
    EXPLOSION_NORMAL(version -> version < 8 ? "NONE" : (version < 13 ? "EXPLOSION_NORMAL" : "poof"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed after {@link #DRIPPING_DRIPSTONE_LAVA}
     * starts falling from pointed dripstones.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Orange drop.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    FALLING_DRIPSTONE_LAVA(version -> version < 17 ? "NONE" : "falling_dripstone_lava"),
    /**
     * In vanilla, this particle is displayed after {@link #DRIPPING_DRIPSTONE_WATER}
     * starts falling from pointed dripstones.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue drop.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    FALLING_DRIPSTONE_WATER(version -> version < 17 ? "NONE" : "falling_dripstone_water"),
    /**
     * In vanilla, this particle is displayed randomly by floating sand
     * and gravel.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: a circle part of a texture.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: This particle needs a block texture in order to work.</li>
     * </ul>
     */
    FALLING_DUST(version -> version < 10 ? "NONE" : (version < 13 ? "FALLING_DUST" : "falling_dust"), REQUIRES_BLOCK),
    /**
     * In vanilla, this particle is displayed below beehives filled
     * with honey. As opposed to the {@link #DRIPPING_HONEY} particles,
     * this particle falls instantly.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A rectangular honey drop.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: Spawns a {@link #LANDING_HONEY} after landing on a block.</li>
     * </ul>
     */
    FALLING_HONEY(version -> version < 15 ? "NONE" : "falling_honey"),
    /**
     * In vanilla, this particle is displayed by bees that have pollen
     * and are on their way to the beehive.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: White square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    FALLING_NECTAR(version -> version < 15 ? "NONE" : "falling_nectar"),
    /**
     * In vanilla, this particle is displayed below crying obsidian
     * blocks.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Purple square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    FALLING_OBSIDIAN_TEAR(version -> version < 16 ? "NONE" : "falling_obsidian_tear"),
    /**
     * In vanilla, this particle is displayed below spore blossoms.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Green square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    FALLING_SPORE_BLOSSOM(version -> version < 17 ? "NONE" : "falling_spore_blossom"),
    /**
     * In vanilla, this particle is displayed when a firework is
     * launched.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Sparkling white star.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * </ul>
     */
    FIREWORKS_SPARK(version -> version < 8 ? "NONE" : (version < 13 ? "FIREWORKS_SPARK" : "firework"), DIRECTIONAL),
    /**
     * In vanilla, this particle is randomly displayed by torches,
     * active furnaces,spawners and magma cubes.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny flame.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    FLAME(version -> version < 8 ? "NONE" : (version < 13 ? "FLAME" : "flame"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by exploding fireworks
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A white glow.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: The color of this flash can't be set since it's only set clientside.</li>
     * </ul>
     */
    FLASH(version -> version < 14 ? "NONE" : "flash"),
    /**
     * This particle is unused and is removed in the version 1.13.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Low opacity gray square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    FOOTSTEP(version -> version > 8 && version < 13 ? "FOOTSTEP" : "NONE"),
    /**
     * In vanilla, this particle is displayed by a glow squid.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Cyan star.</li>
     * <li>Speed value: Doesn't seem to influence the particle.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0. Please note that this particle
     * is barely moveable.</li>
     * </ul>
     */
    GLOW(version -> version < 17 ? "NONE" : "glow", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by a glow squid when it gets hurt.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Cyan ink.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    GLOW_SQUID_INK(version -> version < 17 ? "NONE" : "glow_squid_ink", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when taming or
     * breeding animals.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Red heart.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    HEART(version -> version < 8 ? "NONE" : (version < 13 ? "HEART" : "heart")),
    /**
     * In vanilla, this particle is displayed when a tool is
     * broken, a egg or a splash potion hits an entity or a block, It is
     * also displayed when a player eats or a eye of ender breaks.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Little piece of a texture.</li>
     * <li>Extra:<ul>
     * <li> The velocity of this particle can be set. The amount has to be 0.</li>
     * <li> This particle needs a item texture in order to work.</li></ul></li>
     * </ul>
     */
    ITEM_CRACK(version -> version < 8 ? "NONE" : (version < 13 ? "ITEM_CRACK" : "item"), DIRECTIONAL, REQUIRES_ITEM),
    /**
     * In vanilla, this particle is displayed after a falling or
     * dripping Honey particle reaches a block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Honey colored lines.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: This Particle stays on the ground and doesn't instantly despawn.</li>
     * </ul>
     */
    LANDING_HONEY(version -> version < 15 ? "NONE" : "landing_honey"),
    /**
     * In vanilla, this particle is displayed after a falling or
     * dripping obsidian tear reaches a block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Purple colored lines.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: This Particle stays on the ground and doesn't instantly despawn.</li>
     * </ul>
     */
    LANDING_OBSIDIAN_TEAR(version -> version < 16 ? "NONE" : "landing_obsidian_tear"),
    /**
     * In vanilla, this particle is randomly displayed by lava.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Orange lava ball.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    LAVA(version -> version < 8 ? "NONE" : (version < 13 ? "LAVA" : "lava")),
    /**
     * In vanilla, this particle is displayed by the light block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: four yellow stars.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    LIGHT(version -> version < 17 ? "NONE" : "light"),
    /**
     * In vanilla, this particle is displayed by elder guardians.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A elder guardian.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    MOB_APPEARANCE(version -> version < 8 ? "NONE" : (version < 13 ? "MOB_APPEARANCE" : "elder_guardian")),
    /**
     * In vanilla, this particle is displayed by active conduits.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue circle with a brown core.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    NAUTILUS(version -> version < 13 ? "NONE" : "nautilus", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when rightclicking
     * or activating a note block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Colored Note.</li>
     * <li>Speed value: Causes the particle to be green when set to 0.</li>
     * <li>Extra: the offsetX parameter represents which note should be displayed. The amount has to be 0 or the color won't work.</li>
     * </ul>
     */
    NOTE(version -> version < 8 ? "NONE" : (version < 13 ? "NOTE" : "note"), COLORABLE),
    /**
     * In vanilla, this particle is randomly displayed by nether
     * portal, endermen, ender chests, dragon eggs, endermites and end
     * gateway portals. It is also displayed when a ender pearl hits
     * a block or an entity, when a eye of ender beaks or when the player eats
     * a chorus fruit.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Purple cloud.</li>
     * <li>Speed value: Influences the spread of this particle effect.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    PORTAL(version -> version < 8 ? "NONE" : (version < 13 ? "PORTAL" : "portal"), DIRECTIONAL),
    /**
     * In vanilla, this particle is randomly displayed by active
     * redstone ore, active redstone, active redstone repeater and
     * active redstone torches. Since 1.13 it is also displayed when
     * pressing a button, activating a lever or stepping onto a pressure
     * plate
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny colored cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: offsetX, offsetY and offsetZ represent the rgb values of the particle. The amount has to be 0 or the color won't work.</li>
     * </ul>
     */
    REDSTONE(version -> version < 8 ? "NONE" : (version < 13 ? "REDSTONE" : "dust"), COLORABLE, DUST),
    /**
     * Currently Unused in vanilla. It's pretty much the same as the normal portal
     * particle but instead of flying to the original location it flies away at the specified
     * velocity.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Purple Cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    REVERSE_PORTAL(version -> version < 16 ? "NONE" : "reverse_portal", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when oxidation is scraped off a copper block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Cyan star.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    SCRAPE(version -> version < 17 ? "NONE" : "scrape", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by jumping slimes.
     * <p>
     * The particle originates from the nms EntitySlime class.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny part of the slimeball icon.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    SLIME(version -> version < 8 ? "NONE" : (version < 13 ? "SLIME" : "item_slime")),
    /**
     * In vanilla, this particle is randomly displayed by fire, furnace
     * minecarts and blazes. It's also displayed when trying to place water
     * in the nether.
     * <p>
     * The particle originates from the nms ItemBucket, EntityBlaze
     * BlockFluids and EntityMinecart classes.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Large gray cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * </ul>
     */
    SMOKE_LARGE(version -> version < 8 ? "NONE" : (version < 13 ? "SMOKE_LARGE" : "large_smoke"), DIRECTIONAL),
    /**
     * In vanilla, this particle is randomly displayed by primed
     * tnt, torches, end portals, active brewing stands, monster
     * spawners or when either a dropper or dispenser gets triggered. It's
     * also displayed when taming a wild animal or an explosion occurs.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Little gray cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * </ul>
     */
    SMOKE_NORMAL(version -> version < 8 ? "NONE" : (version < 13 ? "SMOKE_NORMAL" : "smoke"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by sneezing baby pandas.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Green cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    SNEEZE(version -> version < 14 ? "NONE" : "sneeze", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when a snowball
     * hits an entity or a block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Little peace of the snowball texture.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    SNOWBALL(version -> version < 8 ? "NONE" : (version < 13 ? "SNOWBALL" : "item_snowball")),
    /**
     * In vanilla, this particle is displayed when a player sinks in powder snow.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A small white snowflake.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    SNOWFLAKE(version -> version < 17 ? "NONE" : "snowflake", DIRECTIONAL),
    /**
     * This particle is unused and is merged into "poof" in 1.13.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny white cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    SNOW_SHOVEL(version -> version < 8 ? "NONE" : (version < 13 ? "SNOW_SHOVEL" : "poof"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when a player walks
     * on soulsand with the soul speed enchantment.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A soul.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    SOUL(version -> version < 16 ? "NONE" : "soul", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed by soul torches
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue flame.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    SOUL_FIRE_FLAME(version -> version < 16 ? "NONE" : "soul_fire_flame", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when a splash potion or
     * a experience bottle hits a block or an entity. It's also displayed by
     * evokers.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: White swirl.</li>
     * <li>Speed value: Causes the particle to only fly up when set to 0.</li>
     * <li>Extra: Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0</li>
     * </ul>
     */
    SPELL(version -> version < 8 ? "NONE" : (version < 13 ? "SPELL" : "effect")),
    /**
     * In vanilla, this particle is displayed when a instant splash
     * potion (e.g. instant health) hits a block or an entity.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: White swirl.</li>
     * <li>Speed value: Causes the particle to only fly up when set to 0.</li>
     * <li>Extra: Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0</li>
     * </ul>
     */
    SPELL_INSTANT(version -> version < 8 ? "NONE" : (version < 13 ? "SPELL_INSTANT" : "instant_effect")),
    /**
     * In vanilla, this particle is displayed when a entity has
     * a active potion effect with the "ShowParticles" tag set to 1.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: colored swirl.</li>
     * <li>Speed value: Represents the lightness of the color.</li>
     * <li>Extra: offsetX, offsetY and offsetZ represent the rgb values of the particle. The amount has to be 0 or the color won't work.</li>
     * </ul>
     */
    SPELL_MOB(version -> version < 8 ? "NONE" : (version < 13 ? "SPELL_MOB" : "entity_effect"), COLORABLE),
    /**
     * In vanilla, this particle is displayed when a entity has
     * a active potion effect from a nearby beacon.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: colored swirl.</li>
     * <li>Speed value: Represents the lightness of the color.</li>
     * <li>Extra: offsetX, offsetY and offsetZ represent the rgb values of the particle. The amount has to be 0 or the color won't work.</li>
     * </ul>
     */
    SPELL_MOB_AMBIENT(version -> version < 8 ? "NONE" : (version < 13 ? "SPELL_MOB_AMBIENT" : "ambient_entity_effect"), COLORABLE),
    /**
     * In vanilla, this particle is displayed randomly by witches.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Purple cross.</li>
     * <li>Speed value: Causes the particle to only fly up when set to 0.</li>
     * <li>Extra: Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0</li>
     * </ul>
     */
    SPELL_WITCH(version -> version < 8 ? "NONE" : (version < 13 ? "SPELL_WITCH" : "witch")),
    /**
     * In vanilla, this particle is displayed by llamas while
     * attacking an entity.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: White cloud.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    SPIT(version -> version < 11 ? "NONE" : (version < 13 ? "SPIT" : "spit")),
    /**
     * In vanilla, this particle is emitted around spore blossoms.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Green square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    SPORE_BLOSSOM_AIR(version -> version < 17 ? "NONE" : "spore_blossom_air"),
    /**
     * In vanilla, this particle is displayed when a squid gets
     * damaged.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Black ink.</li>
     * <li>Speed value:Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    SQUID_INK(version -> version < 13 ? "NONE" : "squid_ink", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed randomly in water.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny blue square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    SUSPENDED(version -> version < 8 ? "NONE" : (version < 13 ? "SUSPENDED" : "underwater"), REQUIRES_WATER),
    /**
     * In vanilla, this particle is displayed when a player is close
     * to bedrock or the void.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny gray square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    SUSPENDED_DEPTH(version -> version > 8 && version < 13 ? "SUSPENDED_DEPTH" : "NONE", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when a Player hits
     * multiple entities at once with a sword.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A white curve.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: The size of this particle can be set in the offsetX parameter. The amount has to be 0 and the speed has to be 1.</li>
     * </ul>
     */
    SWEEP_ATTACK(version -> version < 9 ? "NONE" : (version < 13 ? "SWEEP_ATTACK" : "sweep_attack"), RESIZEABLE),
    /**
     * In vanilla, this particle is displayed when a totem of
     * undying is used.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: A green/yellow circle.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    TOTEM(version -> version < 11 ? "NONE" : (version < 13 ? "TOTEM" : "totem_of_undying"), DIRECTIONAL),
    /**
     * In vanilla, this particle is randomly displayed by mycelium
     * blocks.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny gray square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    TOWN_AURA(version -> version < 8 ? "NONE" : (version < 13 ? "TOWN_AURA" : "mycelium"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when a sculk sensor is triggered.
     * <p>
     * The vibration particle needs a lot of data in order to be displayed. Please
     * have a look at the {@link VibrationData} documentation for more information.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: cyan wave.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: Takes a start and destination location. (More information: {@link VibrationData})</li>
     * </ul>
     */
    VIBRATION(version -> version < 17 ? "NONE" : "vibration", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when attacking a village.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Gray cloud with a lightning.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    VILLAGER_ANGRY(version -> version < 8 ? "NONE" : (version < 13 ? "VILLAGER_ANGRY" : "angry_villager")),
    /**
     * In vanilla, this particle is displayed when trading with a
     * villager, using bone meal on crops, feeding baby animals or walking on
     * turtle eggs.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Green star.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    VILLAGER_HAPPY(version -> version < 8 ? "NONE" : (version < 13 ? "VILLAGER_HAPPY" : "happy_villager"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed in the warped forest
     * nether biome.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue square.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: This Particle gets a random velocity up.</li>
     * </ul>
     */
    WARPED_SPORE(version -> version < 16 ? "NONE" : "warped_spore"),
    /**
     * In vanilla, this particle is displayed when a Entity is
     * swimming in water, a projectile flies into the water or a fish
     * bites onto the bait.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Bubble with blue outline.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    WATER_BUBBLE(version -> version < 8 ? "NONE" : (version < 13 ? "WATER_BUBBLE" : "bubble"), DIRECTIONAL, REQUIRES_WATER),
    /**
     * In vanilla, this particle is displayed when rain hits the ground.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue droplet.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    WATER_DROP(version -> version > 8 && version < 13 ? "WATER_DROP" : "NONE"),
    /**
     * In vanilla, this particle is displayed when a Entity is
     * swimming in water, wolves shaking  off after swimming or boats.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Blue droplet.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * </ul>
     */
    WATER_SPLASH(version -> version < 8 ? "NONE" : (version < 13 ? "WATER_SPLASH" : "splash"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when a fish bites
     * onto the bait of a fishing rod.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Tiny blue square.</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * </ul>
     */
    WATER_WAKE(version -> version < 8 ? "NONE" : (version < 13 ? "WATER_WAKE" : "fishing"), DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when wax is removed from a copper block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: White star.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    WAX_OFF(version -> version < 17 ? "NONE" : "wax_off", DIRECTIONAL),
    /**
     * In vanilla, this particle is displayed when wax is applied to a copper block.
     * <p>
     * <b>Information</b>:
     * <ul>
     * <li>Appearance: Orange star.</li>
     * <li>Speed value: Influences the velocity at which the particle flies off.</li>
     * <li>Extra: The velocity of this particle can be set. The amount has to be 0.</li>
     * </ul>
     */
    WAX_ON(version -> version < 17 ? "NONE" : "wax_on", DIRECTIONAL),
    /**
     * In vanilla, this particle is randomly displayed in the
     * basalt deltas nether biome.
     * <p>
     * The movement of this particle is handled completely clientside
     * and can therefore not be influenced.
     * <p>
     * <b>Information</b>
     * <ul>
     * <li>Appearance: White square</li>
     * <li>Speed value: Doesn't influence the particle.</li>
     * <li>Extra: This Particle gets a random velocity in the -x and -z directiont while falling down.</li>
     * </ul>
     */
    WHITE_ASH(version -> version < 16 ? "NONE" : "white_ash");
    
    /**
     * An {@link IntFunction} to get the name of the particle by checking the version.
     */
    private final IntFunction<String> fieldNameMapper;
    /**
     * A list of {@link PropertyType properties}
     * the current particle instance supports.
     */
    private final PropertyType[] properties;
    
    /**
     * An array with all {@link ParticleEffect ParticleEffects}.
     */
    public static final List<ParticleEffect> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    /**
     * A {@link HashMap} to store the nms instances of all currently supported
     * {@link ParticleEffect ParticleEffects}.
     */
    public static final Map<ParticleEffect, Object> NMS_EFFECTS;
    
    static {
        //noinspection ConstantConditions
        NMS_EFFECTS = Collections.unmodifiableMap(
                VALUES.stream()
                        .filter(effect -> !"NONE".equals(effect.getFieldName()))
                        .collect(Collectors.toMap(Function.identity(), ParticleEffect::getNMSObject))
        );
    }
    
    /**
     * Returns a set of all effects that are available in the current version.
     *
     * @return all available effects.
     */
    public static Set<ParticleEffect> getAvailableEffects() {
        return NMS_EFFECTS.keySet();
    }
    
    /**
     * Creates a new {@link ParticleEffect}
     *
     * @param fieldNameMapper the {@link IntFunction} to map the version to the name of the
     *                        respective particle.
     * @param properties      A list of {@link PropertyType properties} supported by this particle.
     */
    ParticleEffect(IntFunction<String> fieldNameMapper, PropertyType... properties) {
        this.fieldNameMapper = fieldNameMapper;
        this.properties = properties;
    }
    
    /**
     * Applies the {@link IntFunction} defined in the constructor to get the respective
     * name of the particle.
     *
     * @return the {@link String} name of the particle.
     */
    public String getFieldName() {
        return fieldNameMapper.apply(ReflectionUtils.MINECRAFT_VERSION);
    }
    
    /**
     * Checks if the current {@link ParticleEffect} instance has a specific {@link PropertyType}.
     *
     * @param propertyType the {@link PropertyType} that should be searched.
     * @return {@code true} if the current {@link ParticleEffect} instance supports the given {@link PropertyType}.
     */
    public boolean hasProperty(PropertyType propertyType) {
        return propertyType != null && Arrays.asList(properties).contains(propertyType);
    }
    
    /**
     * Checks if the current {@link ParticleEffect} instance supports the given {@link ParticleData}.
     *
     * @param data the {@link ParticleData} that should that should be checked.
     * @return {@code true} if the current instance supports the given {@link ParticleData}.
     */
    public boolean isCorrectData(ParticleData data) {
        if (data == null)
            return true;
        if (data instanceof ParticleColor)
            return isCorrectColor(((ParticleColor) data));
        if (data instanceof BlockTexture)
            return hasProperty(REQUIRES_BLOCK);
        if (data instanceof ItemTexture)
            return hasProperty(REQUIRES_ITEM);
        return data instanceof VibrationData && this == VIBRATION;
    }
    
    /**
     * Checks if the current {@link ParticleEffect} instance needs the given {@link ParticleColor}.
     * <p>
     * Note: Use {@link NoteColor} for the color of notes.
     *
     * @param color the {@link ParticleColor} data that should be checked.
     * @return {@code true} if the current instance supports the given {@link ParticleColor}.
     */
    public boolean isCorrectColor(ParticleColor color) {
        if (color instanceof DustColorTransitionData)
            return this == DUST_COLOR_TRANSITION;
        if (color instanceof DustData)
            return hasProperty(DUST);
        return hasProperty(COLORABLE) && (this.equals(ParticleEffect.NOTE) ? color instanceof NoteColor : color instanceof RegularColor);
    }
    
    /**
     * Gets the nms instance of the current {@link ParticleEffect} instance.
     *
     * @return The NMS instance or {@code null} if the particle isn't supported in the current minecraft version.
     */
    public Object getNMSObject() {
        if (NMS_EFFECTS != null && NMS_EFFECTS.containsKey(this))
            return NMS_EFFECTS.get(this);
        String fieldName = getFieldName();
        if ("NONE".equals(fieldName))
            return null;
        if (ReflectionUtils.MINECRAFT_VERSION < 13)
            return Arrays.stream(ParticleConstants.PARTICLE_ENUM.getEnumConstants()).filter(effect -> effect.toString().equals(fieldName)).findFirst().orElse(null);
        else try {
            return REGISTRY_GET_METHOD.invoke(PARTICLE_TYPE_REGISTRY, ReflectionUtils.getMinecraftKey(fieldName));
        } catch (Exception ignored) {
        }
        return null;
    }
    
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param color    the {@link ParticleColor} the particle should have.
     * @param players  a list of players that should receive the particle packet.
     */
    public void display(Location location, ParticleColor color, Player... players) {
        display(location, 0f, 0f, 0f, 1f, 0, color, players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param color    the {@link Color} the particle should have.
     * @param players  a list of players that should receive the particle packet.
     */
    public void display(Location location, Color color, Player... players) {
        display(location, new RegularColor(color), players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param color    the {@link ParticleColor} the particle should have.
     * @param filter   a {@link Predicate} to filter out specific {@link Player Players}.
     */
    public void display(Location location, ParticleColor color, Predicate filter) {
        display(location, 0f, 0f, 0f, 1f, 0, color, filter);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param color    the {@link Color} the particle should have.
     * @param filter   a {@link Predicate} to filter out  specific {@link Player Players}.
     */
    public void display(Location location, Color color, Predicate filter) {
        display(location, new RegularColor(color), filter);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which  the particle should be displayed.
     * @param color    the {@link ParticleColor} the particle should have.
     * @param players  a {@link Collection} of players that should receive the particle packet.
     */
    public void display(Location location, ParticleColor color, Collection<? extends Player> players) {
        display(location, 0f, 0f, 0f, 1f, 0, color, players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param color    the {@link Color} the particle  should have.
     * @param players  a {@link Collection} of players that should receive the particle packet.
     */
    public void display(Location location, Color color, Collection<? extends Player> players) {
        display(location, new RegularColor(color), players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param color    the {@link ParticleColor} the particle should have.
     */
    public void display(Location location, ParticleColor color) {
        display(location, 0f, 0f, 0f, 1f, 0, color);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param color    the {@link Color} the particle should have.
     */
    public void display(Location location, Color color) {
        display(location, new RegularColor(color));
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param players  a list of players that should receive the particle packet.
     */
    public void display(Location location, Player... players) {
        display(location, 0f, 0f, 0f, 0f, 1, null, players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param filter   a {@link Predicate} to filter out specific {@link Player Players}.
     */
    public void display(Location location, Predicate filter) {
        display(location, 0f, 0f, 0f, 0f, 1, null, filter);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param players  a {@link Collection} of players that should receive the particle packet.
     */
    public void display(Location location, Collection<? extends Player> players) {
        display(location, 0f, 0f, 0f, 0f, 1, null, players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     */
    public void display(Location location) {
        display(location, 0f, 0f, 0f, 0f, 1, null, Bukkit.getOnlinePlayers());
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which
     * @param vector   the velocity at which the particle should fly off.
     *                 the multiplier of the particle velocity.
     * @param speed    the multiplier of the particle velocity.
     * @param amount   the amount of particles that should be displayed.
     * @param data     the {@link ParticleData} the particle should have.
     * @param players  a list of players that should receive the particle packet.
     */
    public void display(Location location, Vector vector, float speed, int amount, ParticleData data, Player... players) {
        display(location, (float) vector.getX(), (float) vector.getY(), (float) vector.getZ(), speed, amount, data, players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which
     * @param vector   the velocity at which the particle should fly off.
     *                 the multiplier of the particle velocity.
     * @param speed    the multiplier of the particle velocity.
     * @param amount   the amount of particles that should be displayed.
     * @param data     the {@link ParticleData} the particle  should have.
     * @param filter   a {@link Predicate} to filter out specific {@link Player Players}.
     */
    public void display(Location location, Vector vector, float speed, int amount, ParticleData data, Predicate filter) {
        display(location, (float) vector.getX(), (float) vector.getY(), (float) vector.getZ(), speed, amount, data, filter);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which
     * @param vector   the velocity at which the particle should fly off.
     *                 the multiplier of the particle  velocity.
     * @param speed    the multiplier of the particle velocity.
     * @param amount   the amount of particles that should be displayed.
     * @param data     the {@link ParticleData} the particle should have.
     * @param players  a {@link Collection} of players that should receive the particle packet.
     */
    public void display(Location location, Vector vector, float speed, int amount, ParticleData data, Collection<? extends Player> players) {
        display(location, (float) vector.getX(), (float) vector.getY(), (float) vector.getZ(), speed, amount, data, players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which
     * @param vector   the velocity at which the particle should fly off.
     *                 the multiplier of the particle velocity.
     * @param speed    the multiplier of the particle velocity.
     * @param amount   the amount of particles that should be displayed.
     * @param data     the {@link ParticleData} the particle should have.
     */
    public void display(Location location, Vector vector, float speed, int amount, ParticleData data) {
        display(location, (float) vector.getX(), (float) vector.getY(), (float) vector.getZ(), speed, amount, data);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param offsetX  the offsetX data of the particle.
     * @param offsetY  the offsetY data of the particle.
     * @param offsetZ  the offsetZ data of the particle.
     * @param speed    the multiplier of the particle velocity.
     * @param amount   the amount of particles that should be displayed.
     * @param data     the {@link ParticleData} the particle should have.
     * @param players  a list of players that should receive the particle packet.
     */
    public void display(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData data, Player... players) {
        ArrayList<Player> playerList = Arrays.stream(players).collect(Collectors.toCollection(ArrayList::new));
        display(location, offsetX, offsetY, offsetZ, speed, amount, data, playerList);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param offsetX  the offsetX data of the particle.
     * @param offsetY  the offsetY data of the particle.
     * @param offsetZ  the offsetZ data of the particle.
     * @param speed    the multiplier of the particle  velocity.
     * @param amount   the amount of particles that should be displayed.
     * @param data     the {@link ParticleData} the particle should have.
     * @param filter   a {@link Predicate} to filter out specific {@link Player Players}.
     */
    public void display(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData data, Predicate<Player> filter) {
        ArrayList<Player> players = Bukkit.getOnlinePlayers().stream().filter(filter).collect(Collectors.toCollection(ArrayList::new));
        display(location, offsetX, offsetY, offsetZ, speed, amount, data, players);
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param offsetX  the offsetX data of the particle.
     * @param offsetY  the offsetY data of the particle.
     * @param offsetZ  the offsetZ data of the particle.
     * @param speed    the multiplier of the particle  velocity.
     * @param amount   the amount of particles that should be displayed.
     * @param data     the {@link ParticleData} the particle should have.
     */
    public void display(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData data) {
        display(location, offsetX, offsetY, offsetZ, speed, amount, data, Bukkit.getOnlinePlayers());
    }
    
    /**
     * Displays the current {@link ParticleEffect}.
     *
     * @param location the {@link Location} at which the particle should be displayed.
     * @param offsetX  the offsetX data of the particle.
     * @param offsetY  the offsetY data of the particle.
     * @param offsetZ  the offsetZ data of the particle.
     * @param speed    the multiplier of the particle velocity.
     * @param amount   the amount of particles that should be displayed.
     * @param data     the {@link ParticleData} the particle /should have.
     * @param players  a {@link Collection} of players that should receive the particle packet.
     */
    public void display(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData data, Collection<? extends Player> players) {
        if (!isCorrectData(data))
            return;
        if (data != null)
            data.setEffect(this);
        ParticlePacket packet = new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, data);
        Object nmsPacket = packet.createPacket(location);
        players.stream()
                .filter(p -> p.getWorld().equals(location.getWorld()))
                .forEach(p -> ReflectionUtils.sendPacket(p, nmsPacket));
    }
    
}
