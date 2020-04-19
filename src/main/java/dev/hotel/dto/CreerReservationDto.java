package dev.hotel.dto;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author antoinethebault ReservationDto classe utilisee dans
 *         ReservationsController pour creer une reservation via un POST
 */
public class CreerReservationDto {
	LocalDate dateDebut;
	LocalDate dateFin;
	UUID clientId;
	UUID[] chambres;

	/**
	 * Constructor
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @param clientId
	 * @param chambres
	 */
	public CreerReservationDto(LocalDate dateDebut, LocalDate dateFin, UUID clientId, UUID[] chambres) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.clientId = clientId;
		this.chambres = chambres;
	}

	/**
	 * Getter
	 * 
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * Setter
	 * 
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Getter
	 * 
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * Setter
	 * 
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Getter
	 * 
	 * @return the clientId
	 */
	public UUID getClientId() {
		return clientId;
	}

	/**
	 * Setter
	 * 
	 * @param clientId the clientId to set
	 */
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	/**
	 * Getter
	 * 
	 * @return the chambres
	 */
	public UUID[] getChambres() {
		return chambres;
	}

	/**
	 * Setter
	 * 
	 * @param chambres the chambres to set
	 */
	public void setChambres(UUID[] chambres) {
		this.chambres = chambres;
	}

}
