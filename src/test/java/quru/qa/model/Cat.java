package quru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class Cat {

    public String name;
    public Integer age;
    public List<String> eat;
}
