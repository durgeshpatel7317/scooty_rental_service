package com.scootyrental.webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEnvelope {
    private Object data;
    private Boolean success;
    private String error;
}
