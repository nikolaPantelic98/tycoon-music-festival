package com.db.tycoonmusicfestivalus.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationUserPermission {
    ARTIST_READ("artist:read"),
    ARTIST_WRITE("artist:write"),
    ALBUM_READ("album:read"),
    ALBUM_WRITE("album:write"),
    LABEL_READ("label:read"),
    LABEL_WRITE("label:write"),
    PRODUCER_READ("producer:read"),
    PRODUCER_WRITE("producer:write"),
    SONG_READ("song:read"),
    SONG_WRITE("song:write");

    private final String permission;
}
