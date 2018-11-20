package com.apap.tugasAkhir.rest;

public class Setting {
	
	 //url used for retrieving all dokter data
	final public static String getAllDokterUrl = "http://si-appointment.herokuapp.com/api/getAllDokter";
	
	//url used to get specific dokter by id
	//example: "http://si-appointment.herokuapp.com/api/getDokter/1" used for getting dokter with id 1
	final public static String getDokterByIdUrl = "http://si-appointment.herokuapp.com/api/getDokter/";
	
	 //url used for retrieving all pasien data
	final public static String getAllPasienUrl = "http://si-appointment.herokuapp.com/api/1/getAllPasien";
		
	//url used to get specific pasien by id
	//example: "http://si-appointment.herokuapp.com/api/getPasien/1" used for getting pasien with id 1
	final public static String getPasienByIdUrl = "http://si-appointment.herokuapp.com/api/getPasien/";
	
	//url used for update pasien status by it's id
	//don't forget to use POST method
	final public static String updateStatusPasienByIdUrl = "http://si-appointment.herokuapp.com/api/updatePasien";
	
	//url used for get all dokter assigned in rawat jalan
	//if the dokter atleast has assigned there, tolak
	final public static String getAllAssignedDokterUrl = "{{fill the url here}}";
}

