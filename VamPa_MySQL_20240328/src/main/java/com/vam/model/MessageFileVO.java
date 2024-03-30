package com.vam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageFileVO {

    private Integer fileId;
    private Integer contentsId;
    private Integer messagesId;
//    @NotBlank(message ="少なくとも一つファイルをアップロードしてください。" )
    private Integer fileSourceName;
    private Integer fileHostName;
    private Integer cntDownload;
    private Integer filesize;
    private Integer width;
    private Integer height;
    private Date regDate;
    private Date updDate;
}
