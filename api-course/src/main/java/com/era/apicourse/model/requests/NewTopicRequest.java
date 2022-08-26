package com.era.courseapi.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTopicRequest {

    private String name;

    private String description;

    private Integer difficultRate;

    private String courseName;

}
