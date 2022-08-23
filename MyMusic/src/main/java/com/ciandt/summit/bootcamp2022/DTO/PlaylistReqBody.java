package com.ciandt.summit.bootcamp2022.DTO;

import com.ciandt.summit.bootcamp2022.entity.Music;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistReqBody {
    private List<Music> data;
}
