[![GitHub issues](https://img.shields.io/github/issues/ByteZ1337/ParticleLib)](https://github.com/ByteZ1337/ParticleLib/issues)
[![GitHub stars](https://img.shields.io/github/stars/ByteZ1337/ParticleLib)](https://github.com/ByteZ1337/ParticleLib/stargazers)
[![GitHub license](https://img.shields.io/github/license/ByteZ1337/ParticleLib)](https://github.com/ByteZ1337/ParticleLib/blob/master/LICENSE)
[![Twitter](https://img.shields.io/twitter/url/https/github.com/ByteZ1337/ParticleLib?style=social)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2FByteZ1337%2FParticleLib)

# ParticleLib
A spigot library to support all particles from 1.8 to 1.15

## How to use this library

Choose the particle you want to display and then use the display method.

<b>Example:</b>
```java
ParticleEffect.FLAME.display(player.getLocation(), Bukkit.getOnlinePlayers());
```
Parameter explanation:

1. The location at which the particle should be displayed.
2. The players that will see the particle.

<b>You can also give extra data to the particle.</b>

### Directional Particles

If a particle has the PropertyType "Directional" it can have a custom velocity. This velocity is specified in the offsetX, offsetY and offsetZ parameters. However, you can also specify a Vector in the display method.

Special: The particles Enchantment_Table and Nautilus will be displayed at the offset location and fly to the original location.

<b>Example:</b>
```java
ParticleEffect.CAMPFIRE_SIGNAL_SMOKE.display(player.getLocation(), new Vector(1, 0, 1), 1f, 0, null, Bukkit.getOnlinePlayers());
```
Parameter explanation:

1. The location at which the particle should be displayed.
2. The velocity at which the particle will fly off.
3. The speed of the particle.
4. The amount of particles. <b>This parameter has to be 0 or it won't have the defined velocity.</b>
5. Since our particle doesn't need any extra data, the ParticleData is set to ``null`` 
6. The players that will see the particle.


### Color Particles

If a particle has the PropertyType "Colorable" it can have a user defined color. This color can be set with a implementation of the ParticleColor class. The RGB values of the color are set as the offsetX, offsetY and offsetZ values of the particle.

Special:
* Since 1.13 redstone has a own object for its RGB values. Therefore offsetX, offsetY and offsetZ can be used.
* Note colors don't have a RGB value. They only support a note value from 0 to 24. Use "NoteColor" for this particle.

<b>Example:</b>
```java
ParticleEffect.SPELL_MOB.display(player.getLocation(), new RegularColor(new Color(52, 152, 219)));
```
Parameter explanation:
1. The location at which the particle should be displayed.
2. A new RegularColor object to specify the color the particle will have.

### Texture Particles

If a particle has the PropertyType "Requires Block" or "Requires Item" a texture of either a block or item can be defined. This texture
is set with a implementation of the ParticleTexture class.

<b>Block Example:</b>
```java
ParticleEffect.FALLING_DUST.display(player.getLocation(), 5, 0, 5, 1f, 5, new BlockTexture(Material.REDSTONE_BLOCK), Bukkit.getOnlinePlayers());
```
Parameter explanation:
1. The location at which the particle should be displayed.
2. The x offset of the particle.
3. The y offset of the particle.
4. The Z offset of the particle.
5. The speed at which the particle flies of.
6. The amount of particles that will be displayed
7. The texture the particle will should have. (In this case it's a redstone block.)
8. The players that will see the particle.

<b>Item Example:</b>
```java
ParticleEffect.ITEM_CRACK.display(player.getLocation(), 5, 0, 5, 1f, 5, new ItemTexture(new ItemStack(Material.DIAMOND_SWORD)), Bukkit.getOnlinePlayers());
```
Parameter explanation:
1. The location at which the particle should be displayed.
2. The x offset of the particle.
3. The y offset of the particle.
4. The Z offset of the particle.
5. The speed at which the particle flies of.
6. The amount of particles that will be displayed
7. The texture the particle will should have. (In this case it's a Diamond sword.)
8. The players that will see the particle.
