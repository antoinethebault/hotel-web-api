package dev.hotel.dto;

import java.util.ArrayList;
import java.util.List;

public class RenvoyerMessageErreurChambre {
	List<String> message;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public RenvoyerMessageErreurChambre() {
		super();
		this.message = new ArrayList<>();
	}

	/**
	 * Getter
	 * 
	 * @return the message
	 */
	public List<String> getMessage() {
		return message;
	}

	/**
	 * Setter
	 * 
	 * @param message the message to set
	 */
	public void setMessage(List<String> message) {
		this.message = message;
	}

	/**
	 * ajouterMessage
	 * 
	 * @param message
	 */
	public void ajouterMessage(String message) {
		this.message.add(message);
	}

}
