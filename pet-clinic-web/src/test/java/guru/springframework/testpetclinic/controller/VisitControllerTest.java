package guru.springframework.testpetclinic.controller;

import guru.springframework.testpetclinic.model.Owner;
import guru.springframework.testpetclinic.model.Pet;
import guru.springframework.testpetclinic.model.PetType;
import guru.springframework.testpetclinic.service.PetService;
import guru.springframework.testpetclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

    private static final String PETS_CREATE_OR_UPDATE = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNERS = "redirect:/owners/{ownerId}";
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";

    @Mock
    PetService petService;
    @Mock
    VisitService visitService;
    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;

    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;

    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long ownerId = 1L;
        Owner owner = Owner.builder().id(ownerId).firstName("Joe").lastName("Doe").build();
        PetType dog = PetType.builder().name("Dog").build();
        Pet pet = Pet.builder().id(petId).birthDate(LocalDate.of(2020, 11, 13)).name("Cutie")
                .visits(new HashSet<>()).owner(owner).petType(dog).build();
        when(petService.findById(anyLong())).thenReturn(pet);

        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post(visitsUri)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("date", "2020-11-13")
                    .param("description", YET_ANOTHER_VISIT_DESCRIPTION)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS))
                .andExpect(model().attributeExists("visit"));
    }
}
