package com.cinema.dto.movie;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {

    private Long nNum;
    private String nTitle;
    private String nContent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate nDate;
}

