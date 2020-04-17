package dev.hotel.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.ReservationDto;
import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationsController {

	@Autowired
	ClientRepository clientRepository;

	// POST reservations/ ----- creer une reservation si le client et la chambre
	// existent
	@PostMapping
	public ResponseEntity<Object> reservations(@RequestBody ReservationDto reservationDto) {

		Optional<Client> optionClient = clientRepository.findByUuid(reservation.getUuid());
		if (optionClient.isEmpty()) {
			return ResponseEntity.status(400).body("{\"messages\" : [\"Erreur, le client n'a pas ete trouve\"]}");
		} else {
			String erreur;
			for (Chambre chambre : reservation.getChambres()) {

			}
		}

		return null;

	}

}
