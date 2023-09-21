package com.bestrongkids.chikingauthorizationserver.controller;

import com.bestrongkids.chikingauthorizationserver.dto.UserDto;
import com.bestrongkids.chikingauthorizationserver.entities.User;
import com.bestrongkids.chikingauthorizationserver.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void givenUser_whenRequestingRegisterUser_thenReturnSuccessMessage() throws Exception{
        // Given
        UserDto userDto = new UserDto("test1", "test1@test.com", "12345");
        String body = mapper.writeValueAsString(userDto);

        // When
        MvcResult result = mvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // Then
        String responseContent = result.getResponse().getContentAsString();
        // 예상되는 응답 내용을 검증
        assertThat(responseContent).contains("save");

        // 데이터베이스에서 사용자를 조회하여 검증
        Optional<User> registeredUser = userRepository.findUserByEmail(userDto.email());
        assertTrue(registeredUser.isPresent());
        assertEquals(userDto.name(), registeredUser.get().getName());
        // 암호화된 패스워드가 데이터베이스에 저장되었는지 확인
        assertNotEquals(userDto.password(), registeredUser.get().getPassword());
    }
}