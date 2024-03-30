package com.vam.model;

import com.vam.utils.Constant;
import com.vam.validator.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.hibernate.validator.constraints.Length;

//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentVO {

    private Integer contentsId;

    @NotBlank(message ="コンテンツコードを入力してください。")
    @Size(max = 10, message = "10以内文字列を入力してください。")
    @Pattern(regexp = Constant.CODE_PARTTERN,message = "少なくとも1つの英文字を含める必要があります。")
    @Pattern(regexp = Constant.CODE_PARTTERN2,message = "特殊文字を含まなく文字列を入力してください。")
    private  String code;

    @NotBlank(message ="タイトルを入力してください。" )
    @Size(max = 100, message = "100文字以内で入力してください。")
    @Code
    private String title;

    @Min(value = 1,message = "1~100数字を入力してください。")
    @Max(value = 100, message = "1~100数字を入力してください。")
    @NotNull(message ="数字を入力してください。" )
    private Integer pageRows;

    @Min(value = 1,message = "1~100数字を入力してください。")
    @Max(value = 100, message = "1~100数字を入力してください。")
    @NotNull(message ="数字を入力してください。" )
    private Integer fileMaxCount;

    @Min(value = 1,message = "1~51200数字を入力してください。")
    @Max(value = 51200, message = "1~51200数字を入力してください。")
    @NotNull(message ="数字を入力してください。" )
    private Integer fileMaxSize;

    @NotBlank(message ="カテゴリーを入力してください。" )
    @Size(max = 255, message = "255以内文字を入力してください。")
    @Code
    private String category;

    @Size(max = 50)
    private String skinName;

    private Integer displayFlag;

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
    private Date regDate;

    private Date updDate;



}
