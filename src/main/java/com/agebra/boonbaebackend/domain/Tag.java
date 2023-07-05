package com.agebra.boonbaebackend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pk")
    private Long pk;

    @NotNull
    @Column(name = "tag_name")
    private String name;

    public static Tag makeTag(String name)
    {
        Tag tag = new Tag();
        tag.name = name;
        return tag;
    }
}
