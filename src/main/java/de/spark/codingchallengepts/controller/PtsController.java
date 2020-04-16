package de.spark.codingchallengepts.controller;

import de.spark.codingchallengepts.QuestionnaireBuilder;
import de.spark.codingchallengepts.domain.Question;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PtsController
{
    private final QuestionnaireBuilder questionnaireBuilder;


    @GetMapping(value = "/questionnaire", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Question> getQuestionnaire()
    {
        return questionnaireBuilder.getQuestions();
    }


    @PostMapping(value = "/answers")
    public ResponseEntity<String> submitAnswers()
    {
        return ResponseEntity.ok().build();
    }

}
