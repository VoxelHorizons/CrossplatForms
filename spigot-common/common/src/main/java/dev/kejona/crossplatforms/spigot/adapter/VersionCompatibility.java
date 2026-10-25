package dev.kejona.crossplatforms.spigot.adapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Detect releases that use modern, API-based item handling. */
public final class VersionCompatibility {
    private static final Pattern VERSION = Pattern.compile("^([0-9]+)\\.([0-9]+)(?:\\.([0-9]+))?(?:-|$).*");

    private VersionCompatibility() { }

    /**
     * The absence of a v1_x_Ry CraftBukkit revision means we are on the modern
     * unversioned package layout. This takes precedence over Bukkit version text
     * (which may include custom/snapshot/build metadata).
     */
    public static boolean usesModernBukkitApi(String craftBukkitRevision, String bukkitVersion) {
        return (craftBukkitRevision != null && craftBukkitRevision.isEmpty())
                || usesModernBukkitApi(bukkitVersion);
    }

    public static boolean usesModernBukkitApi(String bukkitVersion) {
        if (bukkitVersion == null) {
            return false;
        }
        Matcher match = VERSION.matcher(bukkitVersion);
        if (!match.matches()) {
            return false;
        }
        int major = Integer.parseInt(match.group(1));
        int minor = Integer.parseInt(match.group(2));
        int patch = match.group(3) == null ? 0 : Integer.parseInt(match.group(3));
        return major > 1 || (major == 1 && (minor > 20 || (minor == 20 && patch >= 5)));
    }
}
