package dev.kejona.crossplatforms.spigot;

import dev.kejona.crossplatforms.spigot.adapter.VersionCompatibility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VersionCompatibilityTest {
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
