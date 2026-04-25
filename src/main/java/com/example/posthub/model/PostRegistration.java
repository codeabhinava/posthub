package com.example.posthub.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PostRegistration {

    private ContentType contentType;
    private String content;
    private String authorUsername;
}
