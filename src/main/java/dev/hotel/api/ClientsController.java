package dev.hotel.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientsController {

	@Autowired
	EntityManager entityManager;

	@GetMapping
	public List<Client> clients(@RequestParam int start, @RequestParam int size) {
		String query = "SELECT c FROM Client c";
		TypedQuery<Client> typedQuery = entityManager.createQuery(query, Client.class);
		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(size);
		List<Client> clients = typedQuery.getResultList();
		return clients;
	}

}
