package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {
    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "longDescription", column = @Column(name = "ITEM_LONG_DESC")),
            @AttributeOverride(name = "shortDescription", column = @Column(name = "ITEM_SHORT_DESC"))
    })
    private Description description;

    public Item(Description description) {
        this.description = description;
    }
}
