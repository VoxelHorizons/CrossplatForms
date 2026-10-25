package dev.kejona.crossplatforms.spigot.v1_12_R1;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LegacyPlayerHeadTest {

    @Test
    void headItemDoesNotRequireTheFlattenedPlayerHeadEnum() {
        // This test runs with the Spigot 1.12.2 API on the runtime classpath.
        assertNull(Material.matchMaterial("PLAYER_HEAD"),
                "This test must use a legacy Spigot API, not 1.13+");
        Adapter_v1_12_R1 adapter = new Adapter_v1_12_R1();
        assertEquals(Material.SKULL_ITEM, adapter.playerHeadMaterial());

        // Must not resolve Material.PLAYER_HEAD or throw NoSuchFieldError.
        ItemStack item = assertDoesNotThrow(adapter::playerHeadItem);
        assertEquals(Material.SKULL_ITEM, item.getType());
        assertEquals(1, item.getAmount());
        assertEquals(3, item.getDurability(),
                "Pre-flattening skull item data 3 is a PLAYER head, data 0 is a skeleton");
    }

    @Test
    void preApiVersionPluginDescriptorAcceptsModernMetadataField() throws Exception {
        // The old Spigot parser should ignore unknown plugin.yml keys instead of
        // rejecting the descriptor because api-version was introduced later.
        String descriptor = "name: CrossplatForms\\n"
                + "version: 1.6.2-voxel\\n"
                + "main: dev.kejona.crossplatforms.spigot.CrossplatFormsSpigot\\n"
                + "api-version: 1.13\\n";
        PluginDescriptionFile plugin = new PluginDescriptionFile(new StringReader(
                descriptor.replace("\\n", "\n")));
        assertEquals("CrossplatForms", plugin.getName());
    }
}
