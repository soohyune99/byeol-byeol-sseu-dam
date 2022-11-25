package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_FILE_BOARD")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileBoard extends Period {
    @Id @GeneratedValue
    private Long fileBoardId;
    private String fileBoardName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
