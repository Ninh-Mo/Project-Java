package com.vam.model;

import com.vam.request.AddMessageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageCommonVO {

    @Valid
    private MessageVO messageVO;
    @Valid
    private MessageFileVO messageFileVO;
    @Valid
    private AddMessageRequest messageReq;
}
