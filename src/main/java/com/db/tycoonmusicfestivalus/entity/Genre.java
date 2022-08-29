package com.db.tycoonmusicfestivalus.entity;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@AttributeOverrides(
        @AttributeOverride(
                name = "name",
                column = @Column(name = "genre")
        )
)
public class Genre {

    private String name;
}
