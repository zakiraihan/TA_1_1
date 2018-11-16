package com.apap.tugasAkhir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
	
	@GetMapping("/login")
	private String login() {
		return "login";
	}
	
	@GetMapping("/")
	private String homepage() {
		return "home";
	}
	
	/**
	 * TODO: Menampilkan semua request pasien yang ingin masuk ke rawat inap
	 */
	@GetMapping("/daftar-request")
	private String viewDaftarRequest() {
		return "view-daftar-request";
	}
	
	/**
	 * TODO: Membuat algoritma assign kamar @Santos 
	 */
	@PostMapping("/daftar-request/assign/{idPasien}")
	private String assignKamarPasien(@PathVariable("idPasien") long idPasien, Model model) {
		return "assign-kamar-pasien";
	}
	
	/**
	 * TODO: Menampilkan semua penanganan yang ada di rawat inap
	 */
	@GetMapping("/penanganan")
	private String viewAllPenanganan() {	
		return "view-all-penanganan";
	}
	
	/**
	 * TODO: Melakukan insert data penanganan harian rawat jalan 	
	 */
	@PostMapping("/penanganan/{idPasien}/insert")
	private String insertPenangananPasien(@PathVariable("idPasien") long idPasien, Model model) {
		return "insert-penanganan-pasien"; 
	}
	
	/**
	 * TODO: Melihat data penanganan yang telah dilakukan
	 */
	@GetMapping("/penanganan/{idPasien}")
	private String viewPenangananPasien(@PathVariable("idPasien") long idPasien, Model model) {
		return "view-penanganan-pasien";
	}
	
	/**
	 * TODO: Update penanganan yang diterima oleh pasien
	 */
	@PostMapping("/penanganan/{idPasien}/{idPenanganan}")
	private String updatePenangananPasien(@PathVariable("idPasien") long idPasien, Model model) {
		return "update-penanganan-pasien";
	}
	
	/**
	 * TODO: Menambah data suatu kamar
	 */
	@PostMapping("/kamar/insert")
	private String insertDataKamar() {
		return "view-all-kamar";
	}
	
	/**
	 * TODO: Menampilkan semua kamar yang ada di rawat inap
	 */
	@GetMapping("/kamar")
	private String viewAllKamar() {
		return "view-all-kamar";
	}
	
	/**
	 * TODO: Menampilkan data suatu kamar
	 */
	@GetMapping("/kamar/{idKamar}")
	private String viewKamar(@PathVariable ("idKamar") long idKamar, Model model) {
		return "view-kamar";
	}
	
	/**
	 * TODO: Update data suatu kamar
	 */
	@PostMapping("/kamar/{idKamar}")
	private String updateKamar(@PathVariable ("idKamar") long idKamar, Model model) {
		return "view-all-kamar";
	}
	
	/**
	 * TODO: Mengeluarkan pasien dari kamar
	 */
	@PostMapping("/daftar-ranap/pulang/{idKamar}")
	private String deleteRanap() {
		return "daftar-ranap";
	}

}
