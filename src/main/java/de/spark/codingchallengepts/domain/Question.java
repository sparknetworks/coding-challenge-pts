package de.spark.codingchallengepts.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question
{
    public String key;
    public boolean required;
    public String type;
    public String hintKey;
    public Constraints constraints;
    public List<String> answers = null;
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Constraints
{
    public Integer min;
    public Integer max;

    @JsonProperty(value = "default")
    public Integer _default;
}