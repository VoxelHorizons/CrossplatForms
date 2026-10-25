package dev.kejona.crossplatforms.bungeecord.handler;

import dev.kejona.crossplatforms.Logger;
import dev.kejona.crossplatforms.bungeecord.CrossplatFormsBungeeCord;
import dev.kejona.crossplatforms.handler.FormPlayer;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;
import java.lang.reflect.Method;

@AllArgsConstructor
public class BungeeCordPlayer implements FormPlayer {

    private static final ProxyServer PROXY = ProxyServer.getInstance();

    @Nonnull
    private final ProxiedPlayer player;

    @Override
    public UUID getUuid() {
        return player.getUniqueId();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }

    @Nullable
    @Override
    public String getEncodedSkinData() {
        // BungeeCord's InitialHandler and LoginResult are internal proxy classes.
        // Access them reflectively to keep the plugin compatible with both modern
        // BungeeCord and forks without a brittle JitPack proxy dependency.
        try {
            Object handler = player.getPendingConnection();
            if (handler == null) {
                return null;
            }
            Object profile = handler.getClass().getMethod("getLoginProfile").invoke(handler);
            if (profile == null) {
                return null;
            }
            Object[] properties = (Object[]) profile.getClass().getMethod("getProperties").invoke(profile);
            if (properties == null) {
                return null;
            }
            for (Object property : properties) {
                if ("textures".equals(readProperty(property, "getName", "name"))) {
                    String value = readProperty(property, "getValue", "value");
                    if (value != null && !value.isEmpty()) {
                        return value;
                    }
                }
            }
        } catch (ReflectiveOperationException | SecurityException | ClassCastException e) {
            Logger.get().warn("Unable to retrieve BungeeCord skin textures from this proxy version");
            if (Logger.get().isDebug()) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String readProperty(Object property, String legacyGetter, String recordGetter)
            throws ReflectiveOperationException {
        Method method;
        try {
            method = property.getClass().getMethod(legacyGetter);
        } catch (NoSuchMethodException ignored) {
            method = property.getClass().getMethod(recordGetter);
        }
        Object value = method.invoke(property);
        return value == null ? null : value.toString();
    }

    @Override
    public void sendRaw(Component component) {
        player.sendMessage(CrossplatFormsBungeeCord.COMPONENT_SERIALIZER.serialize(component));
    }

    @Override
    public boolean switchBackendServer(String server) {
        ServerInfo downstream = PROXY.getServerInfo(server); // find target
        if (downstream == null) {
            return false;
        }
        player.connect(downstream, ServerConnectEvent.Reason.PLUGIN);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getHandle(Class<T> asType) throws ClassCastException {
        return (T) player;
    }
}
