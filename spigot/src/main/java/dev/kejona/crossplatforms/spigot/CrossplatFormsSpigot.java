package dev.kejona.crossplatforms.spigot;

import dev.kejona.crossplatforms.spigot.adapter.SpigotAdapter;
import dev.kejona.crossplatforms.spigot.adapter.Version;
import dev.kejona.crossplatforms.spigot.adapter.VersionMap;
import dev.kejona.crossplatforms.spigot.adapter.Versioned;

import java.util.function.Supplier;
import org.bukkit.Bukkit;
import dev.kejona.crossplatforms.spigot.adapter.VersionCompatibility;

public class CrossplatFormsSpigot extends SpigotBase {

    private static final int SUPPORTED_MAJOR_VERSION = 1;
    private static final VersionMap<Supplier<SpigotAdapter>> INDEXER = new VersionMap<>(SUPPORTED_MAJOR_VERSION);

    static {
        registerVersion("1_8_R3");
        registerVersion("1_9_R2");
        registerVersion("1_12_R1");
        registerVersion("1_13_R2");
        registerVersion("1_14_R1");
        registerFallback("1_20_R1", "1_14_R1"); // authlib changes in 1_20_R2 - use the older adapter instead
        registerVersion("1_20_R2");
    }

    @Override
    public Versioned<SpigotAdapter> findVersionAdapter() {
        // Since 1.20.5, release naming no longer maps to v1_x_Ry packages.
        // The modern adapter only uses stable Bukkit APIs for metadata and items.
        if (VersionCompatibility.usesModernBukkitApi(Bukkit.getBukkitVersion())) {
            return Versioned.supported(createAdapter("1_20_R2"));
        }
        if (ClassNames.NMS_VERSION.isEmpty()) {
            return Versioned.unsupported("1.20.5 or newer");
        }
        Versioned<Supplier<SpigotAdapter>> adapterSupplier = INDEXER.lenientSearch(ClassNames.NMS_VERSION.substring(1));
        return Versioned.convertSupplierType(adapterSupplier);
    }

    private static void registerVersion(String version) {
        INDEXER.put(new Version(version), () -> createAdapter(version));
    }

    private static void registerFallback(String version, String fallback) {
        INDEXER.put(new Version(version), () -> createAdapter(fallback));
    }

    private static SpigotAdapter createAdapter(String version) throws IllegalStateException {
        try {
            Class<?> adapterVersion = Class.forName("dev.kejona.crossplatforms.spigot.v" + version + ".Adapter_v" + version);
            return (SpigotAdapter) adapterVersion.getConstructor().newInstance();
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to create adapter for " + version, e);
        }
    }
}
