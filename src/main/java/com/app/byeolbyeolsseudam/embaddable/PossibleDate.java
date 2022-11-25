package com.app.byeolbyeolsseudam.embaddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter @Setter
public class PossibleDate {
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
}
