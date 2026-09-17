# Dirt Muncher

A Fabric mod for Minecraft **1.21.11**. Adds the **Dirt Muncher** — a hostile
mob shaped like a walking dirt block that ignores players and hunts pigs
instead. When it lands the killing blow on a pig, it "eats" it: heals a
little, puffs out some particles, and plays an eating sound. Comes with a
spawn egg (find it in the Spawn Eggs creative tab) and drops 1–3 dirt when
killed.

## What's in here

```
src/main/java/...      shared code: the entity, item/entity registration, mod entrypoint
src/client/java/...    client-only code: the model + renderer
src/main/resources/    fabric.mod.json, textures, models, lang, loot table
.github/workflows/     CI that builds the jar for you on GitHub
```

## Building it yourself

You'll need **Java 21** installed (`java -version` to check).

1. **Generate the Gradle wrapper** (this repo has `gradle-wrapper.properties`
   but not the wrapper jar itself, since it's a binary I couldn't fetch in
   my sandbox — no internet there). One-time step, needs any Gradle install:
   ```
   gradle wrapper --gradle-version 8.11.1
   ```
   If you don't have Gradle at all yet, install it once (e.g. via
   [sdkman](https://sdkman.io): `sdk install gradle`, or your OS package
   manager), run the command above, and you won't need it again — from then
   on `./gradlew` (or `gradlew.bat` on Windows) handles everything itself.

2. **Build:**
   ```
   ./gradlew build
   ```
   The first run downloads Minecraft, Yarn mappings, and Fabric API — it
   can take a few minutes. The finished jar lands in `build/libs/`, named
   something like `dirtmuncher-1.0.0.jar`.

3. **Test it locally** (optional but recommended before uploading):
   ```
   ./gradlew runClient
   ```
   This launches a dev Minecraft client with the mod loaded. Give yourself
   the spawn egg in creative and try it out on a pig.

### Or: let GitHub build it for you

Push this folder to a new GitHub repo. The included workflow
(`.github/workflows/build.yml`) builds the mod on every push and attaches
the jar as a downloadable artifact under the Actions tab — no local Java
setup needed at all.

## Publishing to Modrinth

1. Go to [modrinth.com/dashboard/projects](https://modrinth.com/dashboard/projects) → **New project** → **Mod**.
2. Fill in the name/summary/description (feel free to reuse the description
   in `fabric.mod.json`), and pick a license — I put a placeholder CC0
   `LICENSE` file in the repo; swap it for whatever you actually want.
3. Under **Versions**, upload the jar from `build/libs/`, set:
   - Loader: **Fabric**
   - Game version: **1.21.11**
   - Release channel: Release (or Beta/Alpha while testing)
4. You'll also need `fabric-api` listed as a **required dependency** on the
   Modrinth version page, since this mod uses it.
5. Publish.

## Things worth knowing / double-checking

- I pinned the toolchain to what's current for 1.21.11 as of writing:
  Fabric Loader 0.17.3, Yarn `1.21.11+build.3`, Fabric API
  `0.141.5+1.21.11`. If a newer patch of any of these has shipped by the
  time you build, `./gradlew build` will still work fine with what's in
  `gradle.properties` — bumping them is optional, not required.
- 1.21.11 was also the **last Minecraft version with Yarn mappings** — if
  you ever port this mod to 26.1 or later, you'll be migrating to Mojang's
  official mappings instead (Fabric has a `migrateMappings` Gradle task for
  that).
- The renderer uses Minecraft's newer "entity render state" API (introduced
  around 1.21.2), which is a fairly recent, fast-moving corner of the
  Fabric/Yarn mappings. I couldn't compile-test this in my sandbox (no
  network access to fetch Minecraft/Fabric artifacts), so if
  `DirtMuncherEntityModel.java` or `DirtMuncherEntityRenderer.java` throws a
  compile error, it's almost certainly a small signature mismatch there —
  compare against `ZombieEntityModel`/`ZombieEntityRenderer` in your IDE's
  decompiled Minecraft sources (Loom generates these automatically) and
  adjust to match.
- The entity texture (`textures/entity/dirt_muncher.png`) is a procedurally
  generated brown speckle pattern, not a copy of Minecraft's actual dirt
  texture — it looks dirt-*ish* but isn't pixel-perfect. If you want it to
  look exactly like the vanilla dirt block, open it in any pixel editor (or
  Blockbench) and touch it up, or paint a new one — the UV layout is a
  standard single 16×16×16 cube on a 64×32 canvas, so any editor showing
  that grid will line up.
- Balance numbers (20 HP, 0.28 speed, 3 attack damage) are a reasonable
  starting point, not tuned — adjust `createDirtMuncherAttributes()` in
  `DirtMuncherEntity.java` to taste.
