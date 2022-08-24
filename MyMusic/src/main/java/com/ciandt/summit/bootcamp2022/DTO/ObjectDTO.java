package com.ciandt.summit.bootcamp2022.DTO;

import com.ciandt.summit.bootcamp2022.entity.Music;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ObjectDTO {
    private List<Music> data;
}
