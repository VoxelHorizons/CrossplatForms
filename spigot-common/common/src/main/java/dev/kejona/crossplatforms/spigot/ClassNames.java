package dev.kejona.crossplatforms.spigot;

import dev.kejona.crossplatforms.utils.ReflectionUtils;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Thanks to Floodgate
 * https://github.com/GeyserMC/Floodgate/blob/master/spigot/src/main/java/org/geysermc/floodgate/util/ClassNames.java
 */
public final class ClassNames {

    /**
     * Includes the v at the front
     */
    public static final String NMS_VERSION;
    private static final String CRAFTBUKKIT_PACKAGE;

    public static final Method PLAYER_GET_PROFILE;
    public static final Field META_SKULL_PROFILE;

    static {
        // Paper and Spigot 1.20.5+ use the unversioned CraftBukkit package.
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        String[] segments = packageName.split("\\.");
        NMS_VERSION = segments.length > 3 && segments[3].matches("v[0-9]+_[0-9]+_R[0-9]+")
                ? segments[3] : "";
        CRAFTBUKKIT_PACKAGE = NMS_VERSION.isEmpty() ? "org.bukkit.craftbukkit"
                : "org.bukkit.craftbukkit." + NMS_VERSION;

        Class<?> craftPlayer = ReflectionUtils.requireClass(CRAFTBUKKIT_PACKAGE + ".entity.CraftPlayer");
        PLAYER_GET_PROFILE = ReflectionUtils.requireMethod(craftPlayer, "getProfile");

        Class<?> craftMetaSkull = ReflectionUtils.requireClass(CRAFTBUKKIT_PACKAGE + ".inventory.CraftMetaSkull");
        // Only legacy adapters access the old private field. Modern versions may
        // rename it or use a different type, so use the public API instead.
        META_SKULL_PROFILE = NMS_VERSION.isEmpty() ? null
                : ReflectionUtils.requireField(craftMetaSkull, "profile");
    }
}
