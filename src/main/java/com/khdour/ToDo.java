package com.khdour;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ToDo {
    private String id;
    private String title;
    private String description;
    private Boolean done;
    private ZonedDateTime createdOn;
    private ZonedDateTime updatedOn;
    private String ISBN;

}