package com.vam.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageListRequest {
    private String category;
    private Integer contentsId;
    private Integer fileMaxCount;
    private Integer fileMaxSize;
    private String tableName;
}
