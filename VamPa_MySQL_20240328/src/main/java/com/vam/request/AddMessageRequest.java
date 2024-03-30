package com.vam.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMessageRequest {
    private String categoryContent;
    private Integer messagesId;
    private Integer contentsId;
    private String subject;
    private String body;
    private String fileSourceName;
    private String fileHostName;
    private Integer filesize;
    private Integer width;
    private Integer height;
    private Integer fileMaxCount;
    private Integer fileMaxSize;
    private String tableName;


}
