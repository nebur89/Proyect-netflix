package com.everis.d4i.tutorial.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NoContentExeption;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Matchers.any;


//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
class ActorServiceImplTest {


    @Mock
    ActorRepository actorRepository;
    @InjectMocks
    private ActorServiceImpl actorServiceImpl;

    private static ActorRest actorRest1;

    private static Actor actor1, actor2, actor3, actor4,actor5;



    private static List<Actor> actorList;

    private static List<ActorRest> actorRestList;


    private static ModelMapper modelMapper= new ModelMapper();

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        actor1 = new Actor(1L, "nameActor1", (short) 31, "española", "The firstActor" );
        actor2 = new Actor(2L, "nameActor2", (short) 32, "francesa", "The firstActor" );
        actor3 = new Actor(3L, "nameActor3", (short) 33, "italiana", "The firstActor" );
        actor4 = new Actor(4L, "nameActor4", (short) 34, "alemana", "The firstActor" );
        actor5 = new Actor(5L, "nameActor5", (short) 35, "belga", "The firstActor" );

        actorList= Arrays.asList(actor1,actor2,actor3,actor4,actor5);

        actorRest1= modelMapper.map(actor1,ActorRest.class);

    }



    /*Create new Actor*/
    @Test
    void creatorNewActorTest() throws NetflixException {
    when(actorRepository.save(any(Actor.class))).thenReturn(actor1);

    assertEquals(actor1, actorRepository.save(actor1));
    assertNotNull(actorRepository.save(actor1));
    assertEquals(actor1.getActorId(),actorRepository.save(actor1).getActorId());
    }


    /*Updarte Actor*/
    @Test
    void updateActorTest()throws NetflixException {
        when(actorRepository.findById(any(Long.class))).thenReturn(Optional.of(actor1));
        when(actorRepository.save(any(Actor.class))).thenReturn(actor1);

        actorRest1.setName("newName");
        actorRest1.setAge((short) 21);
        actorRest1.setNationality("inglesa");
        actorRest1.setShortDescription("se ha modificado descripcion");

        ActorRest actorRestUpdated = actorServiceImpl.updateActor(actorRest1);

        assertNotNull(actorRestUpdated);
        assertEquals("newName",actorRestUpdated.getName());
        assertEquals(21,actorRestUpdated.getAge());
        assertEquals("inglesa",actorRestUpdated.getNationality());
        assertEquals("se ha modificado descripcion",actorRestUpdated.getShortDescription());


    }




    /*Return Actor by idActor*/

    @Test
    void findByIdTest()throws NetflixException {
    when(actorRepository.findById(any(Long.class))).thenReturn(Optional.of(actor1));

    ActorRest actorRest= actorServiceImpl.findById(1L);

    assertNotNull(actorRest);
    assertEquals(actorRest.getActorId().intValue(),1);
    assertEquals(actorRest.getName(),"nameActor1");
    assertEquals(actorRest.getAge(),31);
    assertEquals(actorRest.getNationality(),"española");
    assertEquals(actorRest.getShortDescription(),"The firstActor");

    }



    /* Delete Actor by actorId*/
    @Test
    void deleteActorTest()throws NetflixException {
        doNothing().when(actorRepository).delete(any(Actor.class));
        when(actorRepository.findById(any(Long.class))).thenReturn(Optional.of(actor1));

        actorServiceImpl.deleteActor(1L);
        verify(actorRepository, times(1)).delete(actor1);
    }



    /*Return list all Actor*/
    @Test
    void listAllActorTest()throws NetflixException {
        when(actorRepository.findAll()).thenReturn(actorList);

        List<ActorRest> actorRestList1= actorServiceImpl.listAllActor();

        assertNotNull(actorRestList1);
        assertEquals(5, actorRestList1.size());
        assertEquals("nameActor1",actorRestList1.get(0).getName());
        assertEquals(32,actorRestList1.get(1).getAge());
        assertEquals("italiana",actorRestList1.get(2).getNationality());
        assertEquals("The firstActor",actorRestList1.get(3).getShortDescription());

    }


    /*TEST FAIL*/


    /*Fail Return Actor by idActor*/

    @Test
    void findByIdTestFail()throws NetflixException {
        when(actorRepository.findById(any(Long.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> actorServiceImpl.findById(1L));

    }

    /*Create new Actor Fail*/
    @Test
    void creatorNewActorTestFail() throws NetflixException {
        when(actorRepository.save(any(Actor.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> actorServiceImpl.creatorNewActor(actorRest1));
    }



    /*Update Actor Fail*/
    @Test
    void updateActorTestFail() throws NetflixException {
        when(actorRepository.findById(any(Long.class))).thenThrow(NoContentExeption.class);
        assertThrows(NoContentExeption.class, () -> actorServiceImpl.updateActor(actorRest1));
    }


    /*Fail Delete Actor by actorId*/
    @Test
    void deleteActorTestFail()throws NetflixException {
        when(actorRepository.findById(any(Long.class))).thenThrow((NotFoundException.class));
        assertThrows(NotFoundException.class, () -> actorServiceImpl.deleteActor(1000L));
    }



}