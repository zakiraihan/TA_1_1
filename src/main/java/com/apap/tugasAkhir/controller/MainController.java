package com.apap.tugasAkhir.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import com.apap.tugasAkhir.model.JadwalJagaModel;
import com.apap.tugasAkhir.model.KamarModel;
import com.apap.tugasAkhir.model.PaviliunModel;
import com.apap.tugasAkhir.model.PemeriksaanModel;
import com.apap.tugasAkhir.model.RequestObatModel;
import com.apap.tugasAkhir.model.RequestPasienModel;
import com.apap.tugasAkhir.rest.DokterAllRestMapModel;
import com.apap.tugasAkhir.rest.DokterAllRestModel;
import com.apap.tugasAkhir.rest.DokterModel;
import com.apap.tugasAkhir.rest.DokterRestModel;
import com.apap.tugasAkhir.rest.ObatAllRestModel;
import com.apap.tugasAkhir.rest.ObatModel;
import com.apap.tugasAkhir.rest.PatienAllRestModel;
import com.apap.tugasAkhir.rest.PatienRestModel;
import com.apap.tugasAkhir.rest.Setting;
import com.apap.tugasAkhir.rest.StatusModel;
import com.apap.tugasAkhir.service.JadwalJagaService;
import com.apap.tugasAkhir.service.KamarService;
import com.apap.tugasAkhir.service.PaviliunService;
import com.apap.tugasAkhir.service.PemeriksaanService;
import com.apap.tugasAkhir.service.RequestObatService;
import com.apap.tugasAkhir.service.RequestPasienService;
import com.apap.tugasAkhir.service.RestService;

@Controller
public class MainController {
	@Autowired
	private KamarService kamarService;
	
	@Autowired
	private RequestObatService obatService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RestService restService;
	
	@Autowired
	private PaviliunService paviliunService;

	@Autowired 
	private RequestPasienService requestPasienService;
	
	@Autowired
	private JadwalJagaService jadwalJagaService;
	
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	@Autowired
	private RequestObatService requestObatService;
	
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
		if (!allRequest.isEmpty()) {
			model.addAttribute("allRequest", allRequest);
			
			String[] listOfIdPasien = new String[allRequest.size()];
			for (int i = 0 ; i< listOfIdPasien.length ; i++) {
				listOfIdPasien[i] = Long.toString(allRequest.get(i).getIdPasien());
			}
			
			PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
			List<PaviliunModel> selectedPaviliun = paviliunService.getActivePaviliun();
			model.addAttribute("allPaviliun", selectedPaviliun);
			model.addAttribute("allPasien", allPasienReq.getResult());
			model.addAttribute("listRequestToggle", 1);
		}	
		else {
			model.addAttribute("listRequestToggle", 0);
		}
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
		PatienRestModel patienIdResponse = restService.getPasienById(idPasien);
		if (patienIdResponse.getStatus() == 200) {
			System.out.println(patienIdResponse.getResult().getNama());
			StatusModel status = new StatusModel();
			status.setId((long)5);
			status.setJenis("Berada di Rawat Inap");
			patienIdResponse.getResult().setStatusPasien(status);
			String result = restService.postPasienStatus(patienIdResponse.getResult());
			System.out.println(result);
		}
		RequestPasienModel request= requestPasienService.getReqPasienById(idRequestPasien).get();
		request.setAssign(1);
		requestPasienService.addRequestPasien(request);
		return new RedirectView("/daftar-ranap");
		
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
	@PostMapping("/penanganan/insert")
	private RedirectView insertPenangananPasienSubmit(@ModelAttribute PemeriksaanModel pemeriksaan, Model model, @RequestParam("waktuPemeriksaan") String waktu) {
		
		String[] waktuSplit = waktu.split("T");
		System.out.println(waktuSplit[1]);
		System.out.println((int)Double.parseDouble(waktuSplit[1].split(":")[1]));
		//1990 format new timestamp
		Timestamp dateTime = new Timestamp(Integer.parseInt(waktuSplit[0].split("-")[0])-1900,Integer.parseInt(waktuSplit[0].split("-")[1])-1,Integer.parseInt(waktuSplit[0].split("-")[2])-1,Integer.parseInt(waktuSplit[1].split(":")[0])-1,Integer.parseInt(waktuSplit[1].split(":")[1])-1,0,0);
		pemeriksaan.setWaktu(dateTime);
		if (pemeriksaan.getListObat().size() != 0) {
			for (RequestObatModel obat : pemeriksaan.getListObat()) {
				obat.setIdPasien(pemeriksaan.getIdPasien());
				obat.setStatusObat(0);
				obat.setPemeriksaan(pemeriksaan);
			}
		}
		pemeriksaanService.addPemeriksaan(pemeriksaan);
		model.addAttribute("pemeriksaan", pemeriksaan);
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
		List<JadwalJagaModel> allJadwalJaga = jadwalJagaService.viewAll();
		
		String[] listOfIdDokter = new String[allJadwalJaga.size()];
		for (int i = 0; i < listOfIdDokter.length; i++) {
			listOfIdDokter[i] = Long.toString(allJadwalJaga.get(i).getIdDokter());
		}
		
		System.out.println(allJadwalJaga.size());
		DokterAllRestMapModel allDokterReq = restService.getListOfDokter(listOfIdDokter);
		model.addAttribute("allDokterReq", allDokterReq.getResult());
		
		model.addAttribute("allJadwal", allJadwalJaga);
		ObatAllRestModel obatAll = restService.getAllObat();
		model.addAttribute("allObat", obatAll.getResult());
		model.addAttribute("pemeriksaan", new PemeriksaanModel());
		model.addAttribute("allPasien", allPasienReq.getResult());
		model.addAttribute("allKamar", allKamar);
		return "add-penanganan-pasien";
	}
	
	/**
	 * TODO: Melihat data penanganan yang telah dilakukan
	 */
	@GetMapping("/penanganan/{idPasien}")
	private String viewPenangananPasien(@PathVariable("idPasien") long idPasien, Model model) {
		//ambil pemeriksaan yang pasiennya idPasien
		PatienRestModel pasien = restService.getPasienById(idPasien);
		List<PemeriksaanModel> pemeriksaanPasiens = pemeriksaanService.getPemeriksaanByIdPasien(idPasien);
		ArrayList<DokterModel> dokters = new ArrayList<DokterModel>();
		//reqObat per pemeriksaan
		ArrayList<List<RequestObatModel>> reqObats = new ArrayList<List<RequestObatModel>>();
		
		for (PemeriksaanModel a: pemeriksaanPasiens) {
			DokterModel dokter = restService.getDokterById(a.getIdDokter()).getResult();
			List<RequestObatModel> reqObatTemp = obatService.findAll();
			List<RequestObatModel> reqObat = new ArrayList<RequestObatModel>();
			for (RequestObatModel x: reqObatTemp) {
				if (x.getPemeriksaan().getId().equals(a.getId())) {
					reqObat.add(x);
				}
			}
			dokters.add(dokter);
			reqObats.add(reqObat);
		}
		System.out.println(reqObats);
		model.addAttribute("count", pemeriksaanPasiens.size());
		model.addAttribute("pasien", pasien.getResult());
		model.addAttribute("dokters", dokters);
		model.addAttribute("pemeriksaans", pemeriksaanPasiens);
		model.addAttribute("obats", reqObats);
		return "view-penanganan-pasien";
	}
	
	/**
	 * TODO: Update penanganan yang diterima oleh pasien
	 */
	@GetMapping("/penanganan/{idPasien}/{idPenanganan}")
	//Masih salah nih mestinya nge get id Pasien bukan id Penanganan
	private String updatePenangananPasien(
			@PathVariable("idPenanganan") long idPenanganan, 
			@PathVariable("idPasien") long idPasien, 
			Model model) {
		
		PemeriksaanModel pemeriksaanPasien = pemeriksaanService.getPemeriksaanByIdPemeriksaan(idPenanganan); 
		PatienRestModel pasien = restService.getPasienById(idPasien);
		DokterRestModel dokter = restService.getDokterById(pemeriksaanPasien.getIdDokter());
		
		
		//ini belom diganti ke dokter yang ada di idPenanganan
		List<DokterModel> dokters = restService.getAllDokter().getResult(); 
		
		//Mengambil req obat 
		List<RequestObatModel> reqObat = pemeriksaanPasien.getListObat();
		ObatAllRestModel obatAll = restService.getAllObat();
		model.addAttribute("allObat", obatAll.getResult());
		model.addAttribute("pasien", pasien.getResult());
		model.addAttribute("dokter", dokter.getResult());
		model.addAttribute("pemeriksaanPasien", pemeriksaanPasien);
		model.addAttribute("obat", reqObat);
		model.addAttribute("dokters", dokters);
		return "update-penanganan-pasien"; 
	}
	
	@PostMapping("/penanganan/{idPasien}/{idPenanganan}")
	private String updatePenangananPasienPost(@PathVariable("idPasien") long idPasien,
			@ModelAttribute PemeriksaanModel pemeriksaan, Model model, BindingResult bindingResult) {
		if(pemeriksaan.getListObat() == null) {
			pemeriksaan.setListObat(new ArrayList());
		}
		
		RequestObatModel requestObat = new RequestObatModel();
		requestObat.setIdPasien(pemeriksaan.getIdPasien());
		requestObat.setStatusObat(0);
		requestObat.setPemeriksaan(pemeriksaan);
		pemeriksaan.getListObat().add(requestObat);
		
		List<KamarModel> allKamar = kamarService.getActiveKamar();
		String[] listOfIdPasien = new String[allKamar.size()];
		for (int i = 0; i < listOfIdPasien.length; i++) {
			listOfIdPasien[i] = Long.toString(allKamar.get(i).getIdPasien());
		}
		PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
		DokterAllRestModel dokterAll = restService.getAllDokter();
		List<JadwalJagaModel> allJadwalJaga = jadwalJagaService.viewAll();
		ObatAllRestModel obatAll = restService.getAllObat();
		model.addAttribute("allObat", obatAll. getResult());
		model.addAttribute("pemeriksaan", pemeriksaan);
		model.addAttribute("allPasien", allPasienReq.getResult());
		model.addAttribute("allDokter", dokterAll.getResult());
		model.addAttribute("allKamar", allKamar);
		
		List<ObatModel> allObat = restService.getAllObat().getResult();
		model.addAttribute("listObat", allObat);
		return "redirect:/penanganan/"+ pemeriksaan.getIdPasien();
		
//			@PathVariable("idPasien") long idPasien,
//			@RequestParam("id") long idPenanganan,
//			@RequestParam("dokter") long idDokter,
//			@RequestParam("deskripsi") String deskripsi,
//			@RequestParam("waktu") String waktu,
//			Model model
//			) {
//
//		String[] waktuSplit = waktu.split("T");
//		System.out.println(waktu);
//		System.out.println((int)Double.parseDouble(waktuSplit[1].split(":")[2]));
//		Timestamp dateTime = new Timestamp(Integer.parseInt(waktuSplit[0].split("-")[0])-1900,Integer.parseInt(waktuSplit[0].split("-")[1])-1,Integer.parseInt(waktuSplit[0].split("-")[2])-1,Integer.parseInt(waktuSplit[1].split(":")[0])-1,Integer.parseInt(waktuSplit[1].split(":")[1])-1,(int)Double.parseDouble(waktuSplit[1].split(":")[2])-1,0);
//		PemeriksaanModel pemeriksaanPasien = pemeriksaanService.getPemeriksaanByIdPemeriksaan(idPenanganan); 
//		pemeriksaanPasien.setIdDokter(idDokter);
//		pemeriksaanPasien.setPemeriksaan(deskripsi);
//		pemeriksaanPasien.setWaktu(dateTime);
//		pemeriksaanService.addPemeriksaan(pemeriksaanPasien);
//		return "redirect:/penanganan/"+idPasien;
	}
	
	@RequestMapping(value="/penanganan/insert", method = RequestMethod.POST, params={"removeRow"})
	public String removeRow(@ModelAttribute PemeriksaanModel pemeriksaan, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    pemeriksaan.getListObat().remove(rowId.intValue());
	    List<KamarModel> allKamar = kamarService.getActiveKamar();
		String[] listOfIdPasien = new String[allKamar.size()];
		for (int i = 0; i < listOfIdPasien.length; i++) {
			listOfIdPasien[i] = Long.toString(allKamar.get(i).getIdPasien());
		}
		PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
		DokterAllRestModel dokterAll = restService.getAllDokter();
		List<JadwalJagaModel> allJadwalJaga = jadwalJagaService.viewAll();
		
		String[] listOfIdDokter = new String[allJadwalJaga.size()];
		for (int i = 0; i < listOfIdDokter.length; i++) {
			listOfIdDokter[i] = Long.toString(allJadwalJaga.get(i).getIdDokter());
		}
		DokterAllRestMapModel allDokterReq = restService.getListOfDokter(listOfIdDokter);
		model.addAttribute("allDokterReq", allDokterReq.getResult());
		
		model.addAttribute("allJadwal", allJadwalJaga);
		ObatAllRestModel obatAll = restService.getAllObat();
		model.addAttribute("allObat", obatAll.getResult());
		model.addAttribute("allPasien", allPasienReq.getResult());
		model.addAttribute("allKamar", allKamar);
	    model.addAttribute("pemeriksaan", pemeriksaan);
	    return "add-penanganan-pasien";
	}
	
	@RequestMapping(value = "/penanganan/insert", method = RequestMethod.POST, params= {"addRow"})
	private String addRow(@ModelAttribute PemeriksaanModel pemeriksaan, Model model, BindingResult bindingResult) {
		if(pemeriksaan.getListObat() == null) {
			pemeriksaan.setListObat(new ArrayList());
		}
		
		RequestObatModel requestnya = new RequestObatModel();
		requestnya.setIdPasien(pemeriksaan.getIdPasien());
		requestnya.setStatusObat(0);
		requestnya.setPemeriksaan(pemeriksaan);
		pemeriksaan.getListObat().add(requestnya);
		List<KamarModel> allKamar = kamarService.getActiveKamar();
		String[] listOfIdPasien = new String[allKamar.size()];
		for (int i = 0; i < listOfIdPasien.length; i++) {
			listOfIdPasien[i] = Long.toString(allKamar.get(i).getIdPasien());
		}
		PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
		DokterAllRestModel dokterAll = restService.getAllDokter();
		List<JadwalJagaModel> allJadwalJaga = jadwalJagaService.viewAll();
		
		String[] listOfIdDokter = new String[allJadwalJaga.size()];
		for (int i = 0; i < listOfIdDokter.length; i++) {
			listOfIdDokter[i] = Long.toString(allJadwalJaga.get(i).getIdDokter());
		}
		DokterAllRestMapModel allDokterReq = restService.getListOfDokter(listOfIdDokter);
		model.addAttribute("allDokterReq", allDokterReq.getResult());
		
		model.addAttribute("allJadwal", allJadwalJaga);
		ObatAllRestModel obatAll = restService.getAllObat();
		model.addAttribute("allObat", obatAll.getResult());
		model.addAttribute("allPasien", allPasienReq.getResult());
		model.addAttribute("allKamar", allKamar);
	    
	    model.addAttribute("pemeriksaan", pemeriksaan);
	    return "add-penanganan-pasien";
	}
	
	@GetMapping("/kamar/insert")
	private String insertDataKamar(Model model) {
		List<PaviliunModel> listOfPaviliun = paviliunService.getActivePaviliun();
		model.addAttribute(new KamarModel());
		model.addAttribute("listOfPaviliun",listOfPaviliun);
 		return "add-kamar";
	}
	
	/**
	 * TODO: Menambah data suatu kamar
	 */
	@PostMapping("/kamar/insert")
	private RedirectView insertDataKamarSubmit(@ModelAttribute KamarModel kamar) {
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
			model.addAttribute("pasien", patienIdResponse.getResult());
		}
		return "view-kamar";
	}
	
	@GetMapping("/kamar/update/{idKamar}")
	private String updateFormKamar(@PathVariable ("idKamar") long idKamar, Model model) {
		KamarModel kamar = kamarService.getKamarById(idKamar).get();
		model.addAttribute("kamar", kamar);
		
		List<RequestPasienModel> listOfPending =requestPasienService.getPendingPasien();
		model.addAttribute("listOfPending", listOfPending);
		
		String[] listOfIdPasien = new String[listOfPending.size()];
		for (int i = 0 ; i< listOfIdPasien.length ; i++) {
			listOfIdPasien[i] = Long.toString(listOfPending.get(i).getIdPasien());
		}
		
		PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
		model.addAttribute("allPendingPasien", allPasienReq.getResult());
		
		List<PaviliunModel> listOfPaviliun = paviliunService.getActivePaviliun();
		model.addAttribute("listOfPaviliun",listOfPaviliun);
		
		if (kamar.getIdPasien() != 0) {
			PatienRestModel patienIdResponse = restService.getPasienById(kamar.getIdPasien());
			if (patienIdResponse.getStatus() == 200) {
				model.addAttribute("pasien", patienIdResponse.getResult());		
			}
		}
		return "update-form-kamar";
	}
	
	/**
	 * TODO: Update data suatu kamar
	 */
	@PostMapping("/kamar/{idKamar}")
	private RedirectView updateKamar(@PathVariable ("idKamar") long idKamar, Model model, HttpServletRequest req) {
		Long idPaviliun=Long.valueOf(req.getParameter("paviliunKamar"));
		Long idPasienAwal=Long.valueOf(req.getParameter("pasienAwal"));
		Long idPasienBaru=Long.valueOf(req.getParameter("idPasien"));
		Integer status=Integer.valueOf(req.getParameter("status"));
		
		KamarModel kamar = kamarService.getKamarById(idKamar).get();
		
		kamar.setPaviliunKamar(paviliunService.findPaviliundById(idPaviliun).get());
		if (idPasienAwal != idPasienBaru) {
			if (idPasienAwal != 0) {
				RequestPasienModel requestPasien = requestPasienService.getReqByIdPasien(idPasienAwal);
				requestPasien.setAssign(2);
				kamar.setIdPasien(idPasienBaru);
				PatienRestModel patienIdResponse = restService.getPasienById(idPasienAwal);
				if (patienIdResponse.getStatus() == 200) {
					System.out.println(patienIdResponse.getResult().getNama());
					StatusModel statusPasien = new StatusModel();
					statusPasien.setId((long)6);
					statusPasien.setJenis("Selesai di Rawat Inap");
					patienIdResponse.getResult().setStatusPasien(statusPasien);
					String result = restService.postPasienStatus(patienIdResponse.getResult());
					System.out.println(result);
				}
			}
			if (idPasienBaru != 0) {
				RequestPasienModel requestPasienBaru = requestPasienService.getReqByIdPasien(idPasienBaru);
				requestPasienBaru.setAssign(1);
				PatienRestModel patienIdResponseBaru = restService.getPasienById(idPasienBaru);
				if (patienIdResponseBaru.getStatus() == 200) {
					System.out.println(patienIdResponseBaru.getResult().getNama());
					StatusModel statusPasien = new StatusModel();
					statusPasien.setId((long)5);
					statusPasien.setJenis("Berada di Rawat Inap");
					patienIdResponseBaru.getResult().setStatusPasien(statusPasien);
					String result = restService.postPasienStatus(patienIdResponseBaru.getResult());
					System.out.println(result);
				}
				kamar.setIdPasien(idPasienBaru);
				kamar.setStatus(1);
			}
			if (idPasienBaru == 0) {
				kamar.setStatus(0);
				kamar.setIdPasien(idPasienBaru);
			}
		}
		kamarService.addKamar(kamar);
		return new RedirectView("/kamar");
	}
	
	/**
	 * TODO: Menampilkan semua pasien di kamar
	 */
	@GetMapping("/daftar-ranap")
	private String viewAllRanap(Model model) {
		List<KamarModel> allKamar = kamarService.getActiveKamar();
		if (!allKamar.isEmpty()) {
			String[] listOfIdPasien = new String[allKamar.size()];
			for (int i = 0; i < listOfIdPasien.length; i++) {
				listOfIdPasien[i] = Long.toString(allKamar.get(i).getIdPasien());
			}
			PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
			model.addAttribute("allKamar", allKamar);
			model.addAttribute("allPasien", allPasienReq.getResult());
			model.addAttribute("listPasienToggle", 1);
		}
		else {
			model.addAttribute("listPasienToggle", 0);
		}
		return "daftar-ranap";
	}
	
	/**
	 * TODO: Mengeluarkan pasien dari kamar
	 */
	@PostMapping("/daftar-ranap/pulang/{idKamar}")
	private RedirectView deleteRanap(@PathVariable Long idKamar, HttpServletRequest req) {
		Long idPasien =  Long.valueOf(req.getParameter("idPasien"));
		System.out.println(idPasien);
		KamarModel kamar = kamarService.getKamarById(idKamar).get();
		PatienRestModel patienIdResponse = restService.getPasienById(kamar.getIdPasien());
		if (patienIdResponse.getStatus() == 200) {
			System.out.println(patienIdResponse.getResult().getNama());
			StatusModel status = new StatusModel();
			status.setId((long)6);
			status.setJenis("Selesai di Rawat Inap");
			patienIdResponse.getResult().setStatusPasien(status);
			String result = restService.postPasienStatus(patienIdResponse.getResult());
			System.out.println(result);
		}
		requestPasienService.getReqByIdPasien(idPasien).setAssign(2);
		kamar.setIdPasien((long) 0);
		kamar.setStatus(0);
		kamarService.addKamar(kamar);
		return new RedirectView("/daftar-ranap");
	}
	
	/**
	 * TODO: Insert jadwal jaga
	 */	
	@GetMapping(value = "/jadwal-jaga/insert")
	private String addJadwalJaga(Model model) {
		//model.addAttribute(new JadwalJagaModel());
		DokterAllRestModel allDokter = restService.getAllDokter();
		model.addAttribute("jadwalJaga", new JadwalJagaModel());
		model.addAttribute("allDokter", allDokter.getResult());
		model.addAttribute("allPaviliun", paviliunService.getActivePaviliun());
		return "add-jadwal-jaga";
	}
	
	@PostMapping(value = "/jadwal-jaga/insert")
	private RedirectView addJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga) {
		jadwalJagaService.addJadwalJaga(jadwalJaga);
		return new RedirectView("/jadwal-jaga");
	}

	/**
	 * TODO: Filter list dokter yang paling available dari jadwal rawat jalan
	 */
	
	/**
	 * TODO: Update jadwal jaga
	 */
	@GetMapping(value = "jadwal-jaga/update/{jadwalJagaId}")
	private String updateJadwalJaga(@PathVariable(value = "jadwalJagaId") Long jadwalJagaId, Model model) {
		JadwalJagaModel jadwalJaga = jadwalJagaService.findById(jadwalJagaId).get();
		DokterAllRestModel allDokter = restService.getAllDokter();
		model.addAttribute("jadwalJaga", jadwalJaga);
		model.addAttribute("allDokter", allDokter.getResult());
		model.addAttribute("allPaviliun", paviliunService.getActivePaviliun());
		return "update-jadwal-jaga";
	}
	
	@PostMapping(value = "/jadwal-jaga/update/{jadwalJagaId}")
	private RedirectView updateJadwalJagaSubmit(
			@PathVariable(value = "jadwalJagaId") Long jadwalJagaId,
			@RequestParam("statusDokter") String statusDokter,
			@RequestParam("daftarHariJaga") String daftarHariJaga,
			@RequestParam("idDokter") Long idDokter,
			@RequestParam("paviliunJaga") Long paviliunJaga){
				JadwalJagaModel jadwalJaga = (JadwalJagaModel) jadwalJagaService.findById(jadwalJagaId).get();
				jadwalJaga.setStatusDokter(statusDokter);
				jadwalJaga.setDaftarHariJaga(daftarHariJaga);
				jadwalJaga.setIdDokter(idDokter);
				PaviliunModel paviliun = paviliunService.findPaviliundById(paviliunJaga).get();
				jadwalJaga.setPaviliunJaga(paviliun);
				jadwalJagaService.addJadwalJaga(jadwalJaga);
				return new RedirectView("/jadwal-jaga");
	}
	
	/**
	 * TODO: View jadwal jaga
	 */
	@GetMapping(value = "/jadwal-jaga")
	private String viewJadwalJaga(Model model){
		List<JadwalJagaModel> allJadwalJaga = jadwalJagaService.viewAll();
		
		if (!allJadwalJaga.isEmpty()) {
			model.addAttribute("allJadwalJaga", allJadwalJaga);
			
			String[] listOfIdDokter = new String[allJadwalJaga.size()];
			for (int i = 0; i < listOfIdDokter.length; i++) {
				listOfIdDokter[i] = Long.toString(allJadwalJaga.get(i).getIdDokter());
			}
			
			DokterAllRestMapModel allDokterReq = restService.getListOfDokter(listOfIdDokter);
			model.addAttribute("allDokterReq", allDokterReq.getResult());
			
			List<PaviliunModel> allListPaviliun = paviliunService.getAllPaviliun();
			
			HashMap<Long, PaviliunModel> mapOfPaviliun = new HashMap<Long, PaviliunModel>();
			for (PaviliunModel paviliun : allListPaviliun) {
				mapOfPaviliun.put(paviliun.getId(), paviliun);
			}
			
			
			model.addAttribute("allPaviliun", mapOfPaviliun);
			model.addAttribute("listJadwalToggle", 1);
		}
		else {
			model.addAttribute("listJadwalToggle", 0);
		}
		
		return "view-all-jadwal-jaga";
	}
	
	/**
	 * TODO: View request obat
	 */
	@GetMapping(value = "/obat/request")
	private String viewAllRequestObat(Model model) {
		List<RequestObatModel> allRequestObat = requestObatService.findAll();
		
		if (!allRequestObat.isEmpty()) {
			model.addAttribute("allRequestObat", allRequestObat);
			
			String[] listOfIdPasien = new String[allRequestObat.size()];
			for (int i = 0; i < listOfIdPasien.length; i++) {
				listOfIdPasien[i] = Long.toString(allRequestObat.get(i).getIdPasien());
			}
			
			PatienAllRestModel allPasienReq = restService.getListOfPasien(listOfIdPasien);
			model.addAttribute("allPasienReq", allPasienReq.getResult());
			
			model.addAttribute("listObatToggle", 1);
		}
		else {
			model.addAttribute("listObatToggle", 0);
		}
		
		return "view-all-request-obat";
	}
	

}
