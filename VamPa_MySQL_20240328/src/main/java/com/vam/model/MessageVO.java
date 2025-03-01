package com.vam.model;

import com.vam.validator.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageVO {

    private Integer messagesId;
    private Integer contentsId;
    @NotBlank(message ="メッセージのタイトルを入力してください。" )
    @Size(max = 200, message = "200以内文字を入力してください。")
    @Code(message = "メッセージのタイトルを入力してください")
    private String subject;
    @NotBlank(message ="メッセージの本文を入力してください。" )
    @Code(message = "メッセージの本文を入力してください。")
    private String body;

    private Integer viewCount;

    private String category;
    private String wk_1;
    private String wk_2;
    private String wk_3;
    private String wk_4;
    private String wk_5;
    private String wk_6;
    private String wk_7;
    private String wk_8;
    private String wk_9;
    private String wk_10;
    private String wk_11;
    private String wk_12;
    private String wk_13;
    private String wk_14;
    private String wk_15;
    private String wk_16;
    private String wk_17;
    private String wk_18;
    private String wk_19;
    private String wk_20;
    private String wk_21;
    private String wk_22;
    private String wk_23;
    private String wk_24;
    private String wk_25;
    private String wk_26;
    private String wk_27;
    private String wk_28;
    private String wk_29;
    private String wk_30;
    private String memberId;
    private Date regDate;
    private Date updDate;
}
