package dev.kejona.crossplatforms.spigot.v1_20_R2;

import com.mojang.authlib.properties.Property;
import dev.kejona.crossplatforms.spigot.v1_14_R1.Adapter_v1_14_R1;
import dev.kejona.crossplatforms.utils.SkinUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Adapter_v1_20_R2 extends Adapter_v1_14_R1 {
    /**
     * Use the public profile API, not CraftMetaSkull's internal profile field.
     * The field's type changed in modern Minecraft releases.
     */
    @Override
    public void setSkullProfile(SkullMeta meta, String name, String textures) {
        if (textures == null) {
            if (name == null) {
                throw new IllegalArgumentException("Both name and textures cannot be null");
            }
            meta.setOwner(name);
            return;
        }

        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID(), name);
        PlayerTextures playerTextures = profile.getTextures();
        try {
            playerTextures.setSkin(new URL(SkinUtils.urlFromEncoding(textures)));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid player skin URL", e);
        }
        profile.setTextures(playerTextures);
        meta.setOwnerProfile(profile);
    }

    @Override
    public String propertyValue(Property property) {
        // Property was changed to a record in the version of authlib that 1.20.2 bumped to
        return property.value();
    }
}
