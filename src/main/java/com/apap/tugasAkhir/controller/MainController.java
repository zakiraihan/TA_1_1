package com.apap.tugasAkhir.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.apap.tugasAkhir.model.KamarModel;
import com.apap.tugasAkhir.model.PaviliunModel;
import com.apap.tugasAkhir.model.RequestPasienModel;
import com.apap.tugasAkhir.model.PemeriksaanModel;
import com.apap.tugasAkhir.rest.DokterAllRestModel;
import com.apap.tugasAkhir.rest.PatienAllRestModel;
import com.apap.tugasAkhir.rest.PatienRestModel;
import com.apap.tugasAkhir.service.KamarService;
import com.apap.tugasAkhir.service.PaviliunService;
import com.apap.tugasAkhir.service.PemeriksaanService;
import com.apap.tugasAkhir.service.RequestPasienService;
import com.apap.tugasAkhir.service.RestService;

@Controller
public class MainController {
	@Autowired
	private KamarService kamarService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RestService restService;
	
	@Autowired
	private PaviliunService paviliunService;

	@Autowired 
	private RequestPasienService requestPasienService;
	
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
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
	private String viewDaftarRequest(Model model) {
		List<RequestPasienModel> allRequest = requestPasienService.getAllRequest();
		model.addAttribute("allRequest", allRequest);
		
		String[] listOfIdPasien = new String[allRequest.size()];
		for (int i = 0 ; i< listOfIdPasien.length ; i++) {
			listOfIdPasien[i] = Long.toString(allRequest.get(i).getIdPasien());
		}
		
		PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
		List<PaviliunModel> selectedPaviliun = paviliunService.getActivePaviliun();
		model.addAttribute("allPaviliun", selectedPaviliun);
		model.addAttribute("allPasien", allPasienReq.getResult());	
		return "view-daftar-request";
	}
	
	@RequestMapping(value = "/daftar-request/getFromPaviliun", method= RequestMethod.GET)
	@ResponseBody
	public List<KamarModel> getKamar(@RequestParam(value = "idPaviliun", required = true) Long idPaviliun){
		PaviliunModel paviliun = paviliunService.findPaviliundById(idPaviliun).get();
		return kamarService.getActiveKamarFromPaviliun(paviliun);
	}
	
	/**
	 * TODO: Membuat algoritma assign kamar @Santos 
	 */
	@PostMapping("/daftar-request/assign")
	private RedirectView assignKamarPasien( Model model, HttpServletRequest req) {
		Long idPasien=Long.valueOf(req.getParameter("idPasien"));
		Long idRequestPasien = Long.valueOf(req.getParameter("idRequestPasien"));
		Long idKamar = Long.valueOf(req.getParameter("kamar"));
		KamarModel kamar = kamarService.getKamarById(idKamar).get();
		kamar.setIdPasien(idPasien);
		kamar.setStatus(1);
		RequestPasienModel request= requestPasienService.getReqPasienById(idRequestPasien).get();
		request.setAssign(1);
		requestPasienService.addRequestPasien(request);
		return new RedirectView("/");
		
	}
	//	return "assign-kamar-pasien";
	//}
	
	
	
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
	@PostMapping("/penanganan/insert")
	private RedirectView insertPenangananPasienSubmit(@ModelAttribute PemeriksaanModel pemeriksaan) {
		pemeriksaanService.addPemeriksaan(pemeriksaan);
		return new RedirectView("/"); 
	}
	
	@GetMapping("/penanganan/insert")
	private String insertPenangananPasien(Model model) {
		List<KamarModel> allKamar = kamarService.getActiveKamar();
		String[] listOfIdPasien = new String[allKamar.size()];
		for (int i = 0; i < listOfIdPasien.length; i++) {
			listOfIdPasien[i] = Long.toString(allKamar.get(i).getIdPasien());
		}
		PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
		DokterAllRestModel dokterAll = restService.getAllDokter();
		model.addAttribute(new PemeriksaanModel());
		model.addAttribute("allPasien", allPasienReq.getResult());
		model.addAttribute("allDokter", dokterAll.getResult());
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
	
	@GetMapping("/kamar/insert")
	private String insertDataKamar(Model model) {
		List<PaviliunModel> listOfPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute(new KamarModel());
		model.addAttribute("listOfPaviliun",listOfPaviliun);
 		return "add-kamar";
	}
	
	/**
	 * TODO: Menambah data suatu kamar
	 */
	@PostMapping("/kamar/insert")
	private RedirectView insertDataKamarSubmit(@ModelAttribute KamarModel kamar) {
		kamar.setNomorKamar(0);
		kamarService.addKamar(kamar);
		return new RedirectView("/kamar");
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
		PatienRestModel patienIdResponse = restService.getPasienById(kamar.getIdPasien());
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
	 * TODO: Menampilkan semua pasien di kamar
	 */
	@GetMapping("/daftar-ranap")
	private String viewAllRanap(Model model) {
		List<KamarModel> allKamar = kamarService.getActiveKamar();
		String[] listOfIdPasien = new String[allKamar.size()];
		for (int i = 0; i < listOfIdPasien.length; i++) {
			listOfIdPasien[i] = Long.toString(allKamar.get(i).getIdPasien());
		}
		PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
		model.addAttribute("allKamar", allKamar);
		model.addAttribute("allPasien", allPasienReq.getResult());
		return "daftar-ranap";
	}
	
	/**
	 * TODO: Mengeluarkan pasien dari kamar
	 */
	@PostMapping("/daftar-ranap/pulang/{idKamar}")
	private RedirectView deleteRanap(@PathVariable Long idKamar, HttpServletRequest req) {
		Long idPasien =  Long.valueOf(req.getParameter("idPasien"));
		KamarModel kamar = kamarService.getKamarById(idKamar).get();
		kamar.setIdPasien((long) 0);
		kamar.setStatus(0);
		kamarService.addKamar(kamar);
		return new RedirectView("/daftar-ranap");
	}
}
