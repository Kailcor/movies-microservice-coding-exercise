package com.veact.recruitment.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "movies")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {

    @Indexed @Id
    public String title;
    public Integer year;
    public String genre;
    public String Description;
    public Boolean adult_content;

}
