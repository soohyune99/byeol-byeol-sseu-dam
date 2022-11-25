package com.app.byeolbyeolsseudam.embaddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Recyclable {
    private int petCount;
    private int glassCount;
}
