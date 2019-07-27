package com.dsoccer1980.web;

import com.dsoccer1980.dto.UserNumbersDto;
import com.dsoccer1980.model.User;
import com.dsoccer1980.service.StackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StackService stackService;

    @Test
    void testMainPage() throws Exception {
        mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void testLogout() throws Exception {
        mvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void testAccept() throws Exception {
        User testUser = new User("TestUser");
        given(stackService.getOrCreateUserByName("TestUser")).willReturn(testUser);
        given(stackService.getDto(testUser)).willReturn(new UserNumbersDto("TestUser", 1, Collections.emptyList()));

        mvc.perform(get("/accept")
                .param("name", "TestUser"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TestUser")));

        verify(stackService, times(1)).getOrCreateUserByName("TestUser");
        verify(stackService, times(1)).getDto(testUser);
    }

    @Test
    void testPop() throws Exception {
        given(stackService.pop(1)).willReturn(new UserNumbersDto("TestUser", 1, Arrays.asList(1, 2)));

        mvc.perform(get("/pop")
                .param("user_id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TestUser")));

        verify(stackService, times(1)).pop(1);
    }

    @Test
    void testPush() throws Exception {
        given(stackService.push(1, "8")).willReturn(new UserNumbersDto("TestUser", 1, Arrays.asList(8, 1, 2)));

        mvc.perform(get("/push")
                .param("user_id", "1")
                .param("number", "8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<td>8</td>")));

        verify(stackService, times(1)).push(1, "8");
    }

    @Test
    void testReset() throws Exception {
        given(stackService.reset(1)).willReturn(new UserNumbersDto("TestUser", 1, Collections.emptyList()));

        mvc.perform(get("/reset")
                .param("user_id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Your stack is empty")));

        verify(stackService, times(1)).reset(1);
    }

}




