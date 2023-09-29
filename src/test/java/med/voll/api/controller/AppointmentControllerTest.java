package med.voll.api.controller;

import med.voll.api.domain.appointment.BookAppointmentData;
import med.voll.api.domain.appointment.BookAppointmentService;
import med.voll.api.domain.appointment.DetailsAppointmentData;
import med.voll.api.domain.doctor.Speciality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<BookAppointmentData> bookAppointmentDataJacksonTester;
    @Autowired
    private JacksonTester<DetailsAppointmentData>  detailsAppointmentDataJacksonTester;

    @MockBean
    private BookAppointmentService bookAppointmentService;

    @Test
    @DisplayName("debe retornar estado 400 http cuando los datos ingresados sean invalidos.")
    @WithMockUser
    void bookScenary1() throws Exception {
        // given y when
        var response = mvc.perform(post("/appointments")).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("debe retornar estado 200 http cuando los datos ingresados sean validos.")
    @WithMockUser
    void bookScenary2() throws Exception {
        // given
        LocalDateTime date = LocalDateTime.now().plusHours(1);
        Speciality speciality = Speciality.CARDIOLOGIA;
        BookAppointmentData bookAppointmentData=new BookAppointmentData(null,1L,1L,date, speciality);
        DetailsAppointmentData detailsAppointmentData =new DetailsAppointmentData(null, 2L, 1L,date);
        // when
        when(bookAppointmentService.book(any())).thenReturn(detailsAppointmentData);
        var response = mvc.perform(post("/appointments").
                        contentType(MediaType.APPLICATION_JSON).content(
                                bookAppointmentDataJacksonTester.write(bookAppointmentData).getJson()
                        ))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String expectedJson = detailsAppointmentDataJacksonTester
                .write(detailsAppointmentData).getJson();
        System.out.println(response.getContentAsString());
        System.out.println(expectedJson);
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    @Test
    @DisplayName("debe retornar estado 404 http cuando se quiere consultar una cita inexistente.")
    @WithMockUser
    void bookScenary3() throws Exception {
        // given and  when
        var response = mvc.perform(get("/appointments/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}