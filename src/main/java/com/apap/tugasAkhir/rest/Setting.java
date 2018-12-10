package com.apap.tugasAkhir.rest;

public class Setting {
	
	 //url used for retrieving all dokter data
	final public static String getAllDokterUrl = "http://si-appointment.herokuapp.com/api/1/getAllDokter";
	
	//url used to get specific dokter by id
	//example: "http://si-appointment.herokuapp.com/api/getDokter/1" used for getting dokter with id 1
	final public static String getDokterByIdUrl = "http://si-appointment.herokuapp.com/api/getDokter/";
	
	 //url used for retrieving all pasien data
	final public static String getAllPasienUrl = "http://si-appointment.herokuapp.com/api/1/getAllPasien";
		
	//url used to get specific pasien by id
	//example: "http://si-appointment.herokuapp.com/api/getPasien/1" used for getting pasien with id 1
	final public static String getPasienByIdUrl = "http://si-appointment.herokuapp.com/api/getPasien/";
	
	//url used for get all dokter assigned in rawat jalan
	//if the dokter atleast has assigned there, tolak
	final public static String getAllIUnAssignedDokterUrl = "http://sirawatjalan.herokuapp.com/rawat-jalan/poli/jadwal/dokter-available";
	
	//url for request obat
	final public static String obatRequestUrl = "";
	
	//url used for get all pasien with coresponding id in list
	//example: "http://si-appointment.herokuapp.com/api/getPasien?listId=5,10,15,908" used for getting pasien with id 5, 10, 15, 908
	final public static String getPasienByListIdUrl = "http://si-appointment.herokuapp.com/api/getPasien?listId=";

	//url used for get all dokter with coresponding id in list
	//example: "http://si-appointment.herokuapp.com/api/getDokter?listId=5,10,15,908" used for getting dokter with id 5, 10, 15, 908
	final public static String getDokterByListIdUrl = "http://si-appointment.herokuapp.com/api/getDokter?listId=";

	//url used for update patien status
	//using post method
	final public static String postPasienStatusUrl = "http://si-appointment.herokuapp.com/api/1/updatePasien";
	
	//url used to get all obat from farmawsi
	final public static String getAllObatUrl = "{{fill the url here}}";
}

