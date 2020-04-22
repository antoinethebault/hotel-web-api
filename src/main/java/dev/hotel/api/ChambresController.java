package dev.hotel.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ReservationRepository;

@RestController
@RequestMapping(value = "/chambres")
public class ChambresController {
	@Autowired
	ChambreRepository chambreRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@GetMapping
	public List<Chambre> chambre() {
		List<Chambre> chambres;

		chambres = chambreRepository.findAll();

		return chambres;
	}

	// GET /reservations?chambre=x
	// verifie la disponibilite d'une chambre
	@GetMapping
	@RequestMapping(value = "/chambreDispo")
	public ResponseEntity<Object> disponibiliteChambre(@RequestParam String numero) {
		// on verifie que le numero de chambre existe
		List<Chambre> chambres = chambreRepository.findAll();
		Boolean chambreTrouve = false;
		for (Chambre chambre : chambres) {
			if (chambre.getNumero().equals(numero))
				chambreTrouve = true;
		}
		if (chambreTrouve == false)
			return ResponseEntity.status(404).body("Erreur : la chambre n'existe pas");

		// on verifie si la chambre est liee a une reservation
		List<Reservation> reservations = reservationRepository.findAll();
		for (Reservation reservation : reservations) {
			chambres = reservation.getChambres();
			for (Chambre chambre : chambres) {
				if (chambre.getNumero().equals(numero))
					return ResponseEntity.status(200).body("La chambre est reservee");
			}
		}

		return ResponseEntity.status(200).body("La chambre est disponible");
	}
}
