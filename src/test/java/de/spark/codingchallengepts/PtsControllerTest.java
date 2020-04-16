package de.spark.codingchallengepts;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PtsControllerTest
{
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void thatQuestionnaireEndpointWorks() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/questionnaire")).andExpect(status().isOk());
    }


    @Test
    public void thatQuestionnaireEndpointReturnsTheQuestionnaire() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/questionnaire"))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$", Matchers.hasSize(4)))
            .andExpect(jsonPath("$[2].answers", Matchers.hasSize(5)))
            .andExpect(jsonPath("$[0].key", Matchers.is("UNAME")))
            .andExpect(jsonPath("$[0].required", Matchers.is(true)))
            .andExpect(jsonPath("$[0].type", Matchers.is("TEXT_SINGLE")))
            .andExpect(jsonPath("$[0].hintKey", Matchers.is("UNAME_HINT")))
            .andExpect(jsonPath("$[0].constraints.min", Matchers.is(2)))
            .andExpect(jsonPath("$[0].constraints.max", Matchers.is(40)))
            .andExpect(jsonPath("$[0].constraints.default").doesNotExist())
            .andExpect(jsonPath("$[0].answers").doesNotExist())
            .andExpect(jsonPath("$[1].constraints.default", Matchers.is(180)))
            .andExpect(jsonPath("$[2].answers[0]", Matchers.is("PAINTING")));
    }


    @Test
    public void thatSubmittingQuestionnaireEndpointAcceptsAnyPayload() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/answers")
            .content("doesn't even have to be a json")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
