package dev.hotel.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@WebMvcTest(ClientsController.class)
class ClientsControllerTest {

	private static final Logger LOGGER = Logger.getLogger(ClientsControllerTest.class.getName());

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ClientRepository clientRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	// test la pagination de la recuperation des clients via un get
	@Test
	void testPagination() throws Exception {
		LOGGER.info("Etant donne qu'on retourne une liste d'utilisateurs suite a une requete");
		LOGGER.info("Lorsque le deuxieme element dont le nom fourni est Don");
		Pageable page = PageRequest.of(0, 2);
		List<Client> clients = new ArrayList<>();
		Client client1 = new Client("Odd", "Ross");
		Client client2 = new Client("Don", "Duck");
		clients.add(client1);
		clients.add(client2);
		when(clientRepository.findAll(page)).thenReturn(new PageImpl<Client>(clients));

		LOGGER.info("Alors on retrouve bien l'element en deuxieme position");
		mockMvc.perform(MockMvcRequestBuilders.get("/clients?size=2&start=0"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Don"));
	}

	// test la recuperation d'un client via son uuid dans un get en cas valide
	@Test
	void testUuidValide() throws Exception {
		LOGGER.info("Etant donne qu'on renvoie un client lie a un uuid specifique");
		String uuid = "dcf129f1-a2f9-47dc-8265-1d844244b192";
		Optional<Client> client = Optional.of(new Client("Odd", "Ross"));
		UUID uuid2 = UUID.fromString(uuid);
		when(clientRepository.findByUuid(uuid2)).thenReturn(client);

		LOGGER.info("Alors on verifie que le nom fourni est bien recupere via la requete");
		mockMvc.perform(MockMvcRequestBuilders.get("/clients/dcf129f1-a2f9-47dc-8265-1d844244b192"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Odd"));
	}

	// test la recuperation d'un client via son uuid dans un get en cas non valide
	@Test
	void testUuidInvalide() throws Exception {
		LOGGER.info("Etant donne que l'on doit renvoyer une erreur si aucun client n'est trouve");
		String uuid = "dcf129f1-a2f9-47dc-8265-1d844244b192";
		Optional<Client> client = Optional.empty();
		UUID uuid2 = UUID.fromString(uuid);
		when(clientRepository.findByUuid(uuid2)).thenReturn(client);

		LOGGER.info("Alors on verifie que l'erreur 404 est bien retournee");
		mockMvc.perform(MockMvcRequestBuilders.get("/clients/dcf129f1-a2f9-47dc-8265-1d844244b192"))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}

	// test la creation valide d'un client via un post
	@Test
	void testCreerClientValide() throws Exception {
		when(clientRepository.save(any(Client.class))).thenReturn(null);
		LOGGER.info("Etant donne que l'on cree un client qui a des elements de plus de deux caracteres");
		LOGGER.info("Lorsque l'on envoie le client au format via la methode post");
		LOGGER.info("Alors le retour doit renvoye le client avec un code 200 et les valeurs du client envoye");
		mockMvc.perform(post("/clients").param("nom", "Robert").param("prenoms", "Christian"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Robert"));
	}

}
