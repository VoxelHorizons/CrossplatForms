package dev.kejona.crossplatforms.spigot.adapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Detect releases that use modern, API-based item handling. */
public final class VersionCompatibility {
    private static final Pattern VERSION = Pattern.compile("^([0-9]+)\\.([0-9]+)(?:\\.([0-9]+))?(?:-|$).*");

    private VersionCompatibility() { }

    public static boolean usesModernBukkitApi(String bukkitVersion) {
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
