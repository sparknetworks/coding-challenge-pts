package de.spark.codingchallengepts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.spark.codingchallengepts.domain.Question;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuestionnaireBuilder
{
    public static final String QUESTIONS_JSON = "/questions.json";

    @Getter
    private List<Question> questions;


    @Autowired
    public QuestionnaireBuilder()
    {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Question>> typeReference = new TypeReference<>()
        {
        };

        InputStream inputStream = TypeReference.class.getResourceAsStream(QUESTIONS_JSON);

        try
        {
            questions = mapper.readValue(inputStream, typeReference);
        }
        catch (IOException e)
        {
            log.error("File not found: " + QUESTIONS_JSON);
        }
    }
}
