package com.delivery.springbootproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeVO {

    private int noti_no;
    private LocalDate noti_regdate;
    private String noti_title;
    private String noti_content;
    private String noti_writer;
    private String noti_enddate;




}
