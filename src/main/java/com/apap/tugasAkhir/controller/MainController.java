package com.apap.tugasAkhir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasAkhir.model.KamarModel;
import com.apap.tugasAkhir.rest.PatienRestModel;
import com.apap.tugasAkhir.rest.Setting;
import com.apap.tugasAkhir.service.KamarService;

@Controller
public class MainController {
	@Autowired
	private KamarService kamarService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate RestTemplate() {
		return new RestTemplate();
	}
	
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
	private String viewAllKamar(Model model) {
		List<KamarModel> allKamar = kamarService.getAllKamar();
		model.addAttribute("allKamar", allKamar);
		return "view-all-kamar";
	}
	
	/**
	 * TODO: Menampilkan data suatu kamar
	 */
	@GetMapping("/kamar/{idKamar}")
	private String viewKamar(@PathVariable ("idKamar") long idKamar, Model model) {
		KamarModel kamar = kamarService.getKamarById(idKamar).get();
		model.addAttribute("kamar", kamar);
		String pathPasien = Setting.getPasienByIdUrl + kamar.getIdPasien();
		PatienRestModel patienIdResponse = restTemplate.getForObject(pathPasien, PatienRestModel.class);
		if (patienIdResponse.getStatus() == 200) {
			System.out.println(patienIdResponse.getResult().getNama());
			model.addAttribute("pasien", patienIdResponse.getResult());
		}
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
