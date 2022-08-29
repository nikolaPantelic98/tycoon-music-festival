package com.db.tycoonmusicfestivalus.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.db.tycoonmusicfestivalus.security.ApplicationUserPermission.*;

@Getter
public enum ApplicationUserRole {
    ADMINTRAINEE(Sets.newHashSet(ARTIST_READ, ALBUM_READ, LABEL_READ, PRODUCER_READ, SONG_READ)),
    ADMIN(Sets.newHashSet(ARTIST_READ, ARTIST_WRITE, ALBUM_READ, ALBUM_WRITE, LABEL_READ, LABEL_WRITE,
            PRODUCER_READ, PRODUCER_WRITE, SONG_READ, SONG_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
