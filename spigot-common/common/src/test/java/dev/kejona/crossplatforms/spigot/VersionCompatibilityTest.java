package dev.kejona.crossplatforms.spigot;

import dev.kejona.crossplatforms.spigot.adapter.VersionCompatibility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VersionCompatibilityTest {
    @Test void unversionedCraftBukkitAlwaysUsesModernAdapter() {
        // This is the 26.3 production failure regression: the old implementation
        // rejected unversioned CraftBukkit if getBukkitVersion() was unfamiliar.
        assertTrue(VersionCompatibility.usesModernBukkitApi("", "26.3-custom-build+abc"));
        assertTrue(VersionCompatibility.usesModernBukkitApi("", "26.3.0.1"));
        assertTrue(VersionCompatibility.usesModernBukkitApi("", "unknown"));
        assertTrue(VersionCompatibility.usesModernBukkitApi("", null));

        // Old revisioned CraftBukkit packages retain their legacy adapters.
        assertFalse(VersionCompatibility.usesModernBukkitApi("v1_8_R3", "1.8.8-R0.1-SNAPSHOT"));
        assertFalse(VersionCompatibility.usesModernBukkitApi("v1_20_R1", "1.20.1-R0.1-SNAPSHOT"));
        assertTrue(VersionCompatibility.usesModernBukkitApi("v1_20_R2", "26.3-R0.1-SNAPSHOT"));
    }

    @Test void versionRouting() {
        assertTrue(VersionCompatibility.usesModernBukkitApi("26.3-R0.1-SNAPSHOT"));
        assertTrue(VersionCompatibility.usesModernBukkitApi("26.2-R0.1-SNAPSHOT"));
        assertTrue(VersionCompatibility.usesModernBukkitApi("1.21.11-R0.1-SNAPSHOT"));
        assertTrue(VersionCompatibility.usesModernBukkitApi("1.20.5-R0.1-SNAPSHOT"));
        assertFalse(VersionCompatibility.usesModernBukkitApi("1.20.4-R0.1-SNAPSHOT"));
        assertFalse(VersionCompatibility.usesModernBukkitApi("1.19.4-R0.1-SNAPSHOT"));
        assertFalse(VersionCompatibility.usesModernBukkitApi("unknown"));
    }
}
