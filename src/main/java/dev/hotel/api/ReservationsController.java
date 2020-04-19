package dev.hotel.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.CreerReservationDto;
import dev.hotel.dto.RenvoyerMessageErreurChambre;
import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationsController {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ChambreRepository chambreRepository;

	@Autowired
	ReservationRepository reservationRepository;

	// POST reservations/ ----- creer une reservation si le client et la chambre
	// existent
	@PostMapping
	public ResponseEntity<Object> reservations(@RequestBody CreerReservationDto reservationDto) {
		RenvoyerMessageErreurChambre erreurs = new RenvoyerMessageErreurChambre();
		boolean erreur = false;
		String body = "";
		Optional<Chambre> optionChambre;
		List<Chambre> chambres = new ArrayList<>();

		UUID uuidClient = reservationDto.getClientId();

		// le message d'erreur si le client n'a pas ete trouve
		Optional<Client> optionClient = clientRepository.findByUuid(uuidClient);
		if (optionClient.isEmpty()) {
			body = "{\"messages\" : [\"uuid client non trouvé\"]}";
			return ResponseEntity.status(400).body(body);
		}

		// les messages d'erreurs si des chambres n'ont pas ete trouves
		for (UUID uuidChambre : reservationDto.getChambres()) {
			optionChambre = chambreRepository.findByUuid(uuidChambre);
			if (optionChambre.isEmpty()) {
				erreur = true;
				erreurs.ajouterMessage("\"la chambre " + uuidChambre.toString() + " n'existe pas\"");
			}
		}
		if (erreur == true) {
			return ResponseEntity.status(400).body(erreurs);
		}

		// si aucune erreur n'a ete trouvee on cree la reservation
		Reservation reservation = new Reservation();
		reservation.setDateDebut(reservationDto.getDateDebut());
		reservation.setDateFin(reservationDto.getDateFin());
		reservation.setClient(clientRepository.findByUuid(uuidClient).get());
		for (UUID uuidChambre : reservationDto.getChambres()) {
			optionChambre = chambreRepository.findByUuid(uuidChambre);
			chambres.add(optionChambre.get());
		}
		reservation.setChambres(chambres);

		// on enregistre la reservation et on retourne la reservation cree
		reservationRepository.save(reservation);

		return ResponseEntity.status(200).body(reservation);

	}

}
