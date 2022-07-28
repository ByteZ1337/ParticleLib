<h1 align="center">
  <img width=250 height=250 src="https://raw.githubusercontent.com/ByteZ1337/ParticleLib/0703161cf8b3402541c895885fbd0083ac688394/.github/img/logo.svg" />
  <br>ParticleLib<br>
</h1>

<p align="center">
  <b>A spigot library supporting all particles from <code>1.8</code> to <code>1.19.1</code></b><br><br>
  <a href="https://app.codacy.com/manual/ByteZ1337/ParticleLib?utm_source=github.com&utm_medium=referral&utm_content=ByteZ1337/ParticleLib&utm_campaign=Badge_Grade_Dashboard">
    <img src="https://api.codacy.com/project/badge/Grade/166f125b74014326831ca21c1d7df65b" alt="codacy"/>
  </a>
  <a href="https://search.maven.org/artifact/xyz.xenondevs/particle">
    <img src="https://img.shields.io/maven-central/v/xyz.xenondevs/particle" alt="maven"> 
  </a>
  <a href="https://github.com/ByteZ1337/ParticleLib/issues">
    <img src="https://img.shields.io/github/issues/ByteZ1337/ParticleLib" alt="issues"/>
  </a>
  <a href="https://github.com/ByteZ1337/ParticleLib/stargazers">
    <img src="https://img.shields.io/github/stars/ByteZ1337/ParticleLib" alt="stars"/>
  </a>
  <a href="https://github.com/ByteZ1337/ParticleLib/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/ByteZ1337/ParticleLib" alt="license"/>
  </a>
  <img src="https://img.shields.io/github/workflow/status/ByteZ1337/ParticleLib/Java%20CI%20with%20Maven" alt="build"/><br><br>
  <a href="#support">Support</a> •
  <a href="#features">Features</a> •
  <a href="#download">Download</a> •
  <a href="#usage">Usage</a>
</p>

## Support

Join the [Discord](https://discord.gg/EpVMXtXB2t) if you have any questions. **Don't** open an issue to ask for support.

## Features

* Multiversion support from 1.8 - 1.19.1 <sup>(All in one Jar)</sup>
* Colored particles
* Particles with custom velocities
* Particles with textures
* Support for every particle in minecraft
* An easy and fluent API to easily spawn particles with custom data

## Download

The latest version <img src="https://img.shields.io/github/v/release/ByteZ1337/ParticleLib"/> can be downloaded on the
<a href="https://github.com/ByteZ1337/ParticleLib/releases">releases</a> page.<br>

### Maven

```xml
<dependencies>
    <dependency>
        <groupId>xyz.xenondevs</groupId>
        <artifactId>particle</artifactId>
        <version>1.8.1</version>
    </dependency>
</dependencies>
```

### Gradle

```groovy
dependencies {
    implementation 'xyz.xenondevs:particle:1.8.1'
}
```

<small>Note: ParticleLib is on the central repository, so no extra repositories are required.</small>

## Usage

<b>For more advanced usage explanations check out the [Wiki](https://github.com/ByteZ1337/ParticleLib/wiki). </b>

### Simple

To spawn particles, you can either use the ``ParticleEffet#display`` method, or you can use the ``ParticleBuilder``.
<br>For normal particles without any extra data, the display method is the best choice.

**Example:**

```java
ParticleEffect.FLAME.display(location);
```

This code will spawn a flame particle at the specified location.

**Some particles can have extra data. This data can contain a range of different properties.**<br>
For these special cases, I will only use the ParticleBuilder. Display methods with the specific parameters still exist,
but shouldn't be used to avoid confusion.

---

### Directional

Some particles accept a custom velocity. When given a ``Vector`` they will travel to the specified offset. The velocity
is stored in the offsetX, offsetY and offsetZ properties of the particle.

To see if a particle is Directional check if it has the ``DIRECTIONAL`` PropertyType.

**Note:** The particles ``Enchantment_Table`` and ``Nautilus`` will be displayed at the offset location and fly to the
original location.

**Example:**

```java
new ParticleBuilder(ParticleEffect.FLAME, player.getLocation())
        .setOffsetY(1f)
        .setSpeed(0.1f)
        .display();
```

This code will spawn a flame particle that flies to the player's head.<br>

Minecraft's particles can behave quite weirdly, so you may have to tweak the speed parameter when using directional
particles.

---

### Colored

A few particles like ``Redstone`` can have custom colors applied to them. This color can be set with ``ParticleColor``
implementations:

* ``RegularColor``
* ``NoteColor``

If your plugin runs on a pre 1.13 server, you can also set the RGB values in the offset properties.

To see if a particle is colorable check if it has the ``COLORABLE`` PropertyType.

**Note:**

* Since 1.13 ``Redstone`` particles are storing their color values in another property. Therefore, the offset properties can be properly used on servers above 1.13.
* ``Note`` particles don't accept a custom color. Instead, they support a note value from 0 to 24. Use ``NoteColor`` for
  this particle.

**Regular Example:**

```java
new ParticleBuilder(ParticleEffect.REDSTONE, location)
        .setParticleData(new RegularColor(255,255,0))
        .display()
```

This code will spawn a yellow ``Redstone`` particle at the specified location.

``setParticleData(new RegularColor(255, 255, 0))`` can also be replaced with ``setColor(Color.YELLOW)`` in case you want
to use ``java.awt.Color`` instead.

**Note Example:**

```java
new ParticleBuilder(ParticleEffect.NOTE, location)
        .setParticleData(new NoteColor(1))
        .display()
```

This code will spawn a green ``Note`` particle at the specified location.

---

### Textured

Several particles even accept textures as custom properties! These textures are modified with implementations of the
``ParticleTexture`` class:

* ``BlockTexture``
* ``ItemTexture``

**Warning:** These particles **NEED** the texture property, or the particle won't be displayed.

To see if a particle supports custom textures check if it has the ``REQUIRES_BLOCK`` or the ``REQUIRES_ITEM``
PropertyType.

<b>Block texture example:</b>

```java
new ParticleBuilder(ParticleEffect.FALLING_DUST, location)
        .setParticleData(new BlockTexture(Material.STONE))
        .display()
```

This code will spawn a ``Falling Dust`` particle with a stone texture.

<b>Item texture example:</b>

```java
ItemStack item = new ItemStack(Material.DIAMOND_AXE);
new ParticleBuilder(ParticleEffect.ITEM_CRACK, location)
        .setParticleData(new ItemTexture(item))
        .display();
```

This code will spawn an ``Item Crack`` particle with a diamond axe texture.
