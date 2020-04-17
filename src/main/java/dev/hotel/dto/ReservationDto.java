package dev.hotel.dto;

/**
 * @author antoinethebault ReservationDto classe utilisee dans
 *         ReservationsController pour creer une reservation via un POST
 */
public class ReservationDto {
	String dateDebut;
	String dateFin;
	String clientId;
	String[] chambres;
}
