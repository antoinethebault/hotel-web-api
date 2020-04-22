package dev.hotel.api;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ReservationRepository;

@WebMvcTest(ChambresController.class)
public class ChambresControllerTest {

	private static final Logger LOGGER = Logger.getLogger(ClientsControllerTest.class.getName());

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ChambreRepository chambreRepository;

	@MockBean
	ReservationRepository reservationRepository;

	@Test
	void testChambreDisponible() throws Exception {
		LOGGER.info("Etant donne qu'on a une chambre sans reservation");
		List<Chambre> chambres = new ArrayList<>();
		chambres.add(new Chambre("P1", null, null));
		when(chambreRepository.findAll()).thenReturn(chambres);

		LOGGER.info("Alors la chambre doit etre disponible");
		mockMvc.perform(MockMvcRequestBuilders.get("/chambres/chambreDispo?numero=P1"))
				.andExpect(MockMvcResultMatchers.content().string("La chambre est disponible"));
	}

	@Test
	void testChambreReserve() throws Exception {
		LOGGER.info("Etant donne qu'on a une chambre");
		List<Chambre> chambres = new ArrayList<>();
		chambres.add(new Chambre("P1", null, null));
		when(chambreRepository.findAll()).thenReturn(chambres);

		LOGGER.info("Lorsque on attache cette chambre a une reservation");
		List<Reservation> reservations = new ArrayList<>();
		reservations.add(new Reservation(null, null, null, chambres));
		when(reservationRepository.findAll()).thenReturn(reservations);

		LOGGER.info("Alors la chambre doit etre reserve");
		mockMvc.perform(MockMvcRequestBuilders.get("/chambres/chambreDispo?numero=P1"))
				.andExpect(MockMvcResultMatchers.content().string("La chambre est reservee"));
	}
}
