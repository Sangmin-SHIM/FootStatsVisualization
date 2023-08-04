package com.example.FootballStats.customization;

import com.example.FootballStats.aggregation.IPlayersByClubCount;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayersByClubCount {
    private List<IPlayersByClubCount> items;
    private long total_count;
}
