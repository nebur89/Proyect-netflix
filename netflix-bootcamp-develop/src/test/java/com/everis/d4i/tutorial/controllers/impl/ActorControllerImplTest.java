package com.everis.d4i.tutorial.controllers.impl;


import com.everis.d4i.tutorial.exceptions.AlreadyReportedExecption;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NoContentExeption;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;

import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.impl.ActorServiceImpl;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;

//, properties = {"spring.main.allow-bean-definition-overriding = true" }

@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorControllerImplTest {


    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private  ObjectMapper objectMapper;

    @Mock
    private  ActorServiceImpl actorService;

    @MockBean
    private  static ModelMapper modelMapper;

    private static ActorRest actorRest1, actorRest2, actorRest3, actorRestUpdate, actorRestInexistent;
    private static List<ActorRest> actorRestList= new ArrayList<>();

    private static NetflixResponse<List<ActorRest>> netflixResponseList;
    private static NetflixResponse<ActorRest> netflixResponse;
    private static NetflixResponse<Void> netflixResponseNoConten;

    private static String pathControllerActor = RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_ACTOR;

    private static String pathMethodFindById = pathControllerActor + RestConstants.RESOURCE_ACTOR_ID;
    private static String pathMethodDeleteActor = pathControllerActor + RestConstants.RESOURCE_ACTOR_ID;
    private static String pathMethodListAllActor = pathControllerActor + RestConstants.RESOURCE_ACTOR_LIST;


    @BeforeEach
    public void setUp() {
         //MockitoAnnotations.initMocks(this);

        actorRest1 = new ActorRest(1L,"nameActor1", (short) 31, "española", "The firstActor");
        actorRest2 = new ActorRest(2L,"nameActor2", (short) 32, "francesa", "The firstActor");
        actorRest3 = new ActorRest(3L,"nameActor3", (short) 33, "italiana", "The firstActor");

        actorRestUpdate = new ActorRest(1L ,"nameActor1Update", (short) 41, "Español", "The firstActor updated");
        actorRestInexistent = new ActorRest(1L ,"nameActor1Update", (short) 41, "Español", "The firstActor updated");

        actorRestList = Arrays.asList(actorRest1, actorRest2, actorRest3);

        netflixResponse = new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),
                CommonConstants.OK, actorRest1);

        netflixResponseList = new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),
                CommonConstants.OK, actorRestList);

        netflixResponseNoConten = new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT),
                CommonConstants.OK);
    }

    /*TEST OK*/

    /*Test List all Actor*/
    @Test
    void listAllActorTest()throws Exception {

        when(actorService.listAllActor()).thenReturn(actorRestList);

       mockMvc.perform(get(pathMethodListAllActor).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))

                .andExpect(content().json(objectMapper.writeValueAsString(netflixResponseList)))
                .andExpect(content().json("{\"status\":\"Success\",\"code\":\"200\"}",false))
                .andExpect(jsonPath("$.code",is(String.valueOf(HttpStatus.OK))))
                .andExpect(jsonPath("$.message",is(CommonConstants.OK)))
                .andExpect(jsonPath("$.status",is(CommonConstants.SUCCESS)))
                .andDo(print());

        verify(actorService, only()).listAllActor();

    }


    /*Test create new Actor*/
    @Test
    public void createNewActorTest() throws Exception {
        when(actorService.creatorNewActor(any(ActorRest.class))).thenReturn(actorRest1);
        mockMvc.perform(post(pathControllerActor).content(String.valueOf(actorRest1))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"status\":\"Success\",\"code\":\"201\"}", false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.CREATED))))
                .andExpect(jsonPath("$.message", is(CommonConstants.OK)))
                .andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS))).andDo(print());

        verify(actorService, only()).creatorNewActor(any(ActorRest.class));
    }


    /*Test delete actor*/
    @Test
    public void testDeleteActor() throws Exception {
        doNothing().when(actorService).deleteActor(any(long.class));
         mockMvc.perform(delete(pathMethodDeleteActor, 1L))
                .andExpect(content().json(objectMapper.writeValueAsString(netflixResponseNoConten)))
                .andExpect(status().isNoContent())
                .andExpect(content().json("{\"status\":\"Success\",\"code\":\"204\"}", false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.NO_CONTENT))))
                .andExpect(jsonPath("$.message", is(CommonConstants.OK)))
                .andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS))).andDo(print());

        verify(actorService, only()).deleteActor(1L);
    }


    /*Test Update Actor*/
    @Test
    public void updateActorTest() throws Exception {
        when(actorService.updateActor(any(ActorRest.class))).thenReturn(actorRest1);
        mockMvc.perform(put(pathControllerActor, String.valueOf(actorRestUpdate))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(netflixResponse)))
                .andExpect(content().json("{\"status\":\"Success\",\"code\":\"200\"}", false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.OK))))
                .andExpect(jsonPath("$.message", is(CommonConstants.OK)))
                .andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS))).andDo(print());

        verify(actorService, only()).updateActor(actorRestUpdate);
    }

    /*TEST FAIL*/

    /*Test createNewActor Actor*/
    @Test
    void createNewActorTestFail()throws Exception {
        when(actorService.creatorNewActor(any(ActorRest.class)))
                .thenThrow(new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR));
        mockMvc.perform(post(pathControllerActor)
                        .content(String.valueOf(actorRest1))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{\"status\":\"ERROR\",\"code\":\"500\"}", false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))))
                .andExpect(jsonPath("$.message", is(ExceptionConstants.INTERNAL_SERVER_ERROR)))
                .andExpect(jsonPath("$.status", is(ExceptionConstants.ERROR))).andDo(print());

        verify(actorService, only()).creatorNewActor(any(ActorRest.class));

    }


    /*Test Update Actor Fail*/

    @Test
    public void updateActorTestFail() throws Exception {
        when(actorService.updateActor(any(ActorRest.class))).thenThrow(new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));
        mockMvc.perform(put(pathControllerActor, String.valueOf(actorRestInexistent)).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"status\":\"ERROR\",\"code\":\"404\"}", false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.NOT_FOUND))))
                .andExpect(jsonPath("$.message", is(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR)))
                .andExpect(jsonPath("$.status", is(ExceptionConstants.ERROR))).andDo(print());

        verify(actorService, only()).updateActor(actorRestInexistent);
    }


    /*Test delete Actor Fail*/
    @Test
    void testDeleteActorFail() throws Exception {
        mockMvc.perform(delete(pathMethodDeleteActor,-1L))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":\"ERROR\",\"code\":\"404\"}", false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.NOT_FOUND))))
                .andExpect(jsonPath("$.message", is(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR)))
                .andExpect(jsonPath("$.status", is(ExceptionConstants.ERROR))).andDo(print());

        verify(actorService, only()).deleteActor(-1L);

    }





}

