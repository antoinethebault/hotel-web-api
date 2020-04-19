package dev.hotel.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;

@RestController
@RequestMapping(value = "/chambres")
public class ChambresController {
	@Autowired
	ChambreRepository chambreRepository;

	@GetMapping
	public List<Chambre> chambre() {
		List<Chambre> chambres;

		chambres = chambreRepository.findAll();

		return chambres;
	}
}
