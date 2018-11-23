package com.apap.tugasAkhir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugasAkhir.model.JadwalJagaModel;
import com.apap.tugasAkhir.model.PaviliunModel;
import com.apap.tugasAkhir.model.RequestObatModel;
import com.apap.tugasAkhir.rest.Setting;
import com.apap.tugasAkhir.service.JadwalJagaService;
import com.apap.tugasAkhir.service.RequestObatService;

public class ApiController {
	
	@Autowired
	private RequestObatService requestObatService;
	
	
	/*	
	@RequestMapping(value="/pegawai/tambah", params={"addRow"}, method = RequestMethod.POST)
	public String addRow(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		pegawai.getListJabatan().add(new JabatanModel());
	    model.addAttribute("pegawai", pegawai);
	    
	    DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Date date = new Date();
	    model.addAttribute("allProvinsi", provinsiService.viewAll());
		model.addAttribute("allInstansi", instansiService.viewByProvinsi(pegawai.getInstansi().getProvinsi()));
		model.addAttribute("allJabatan", jabatanService.viewAll());
		model.addAttribute("tanggalLahir", dateFormat.format(date));
	    return "addPegawai";
	}
	
	@InitBinder
	 protected void initBinder(WebDataBinder binder) {
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(
	             dateFormat, false));
	 }
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"deleteRow"})
	private String deleteRow(@ModelAttribute PegawaiModel pegawai, Model model,final BindingResult bindingResult, final HttpServletRequest req) {
		Integer rowId =  Integer.valueOf(req.getParameter("deleteRow"));
		pegawai.getListJabatan().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
	    model.addAttribute("allProvinsi", provinsiService.viewAll());
		model.addAttribute("allInstansi", instansiService.viewByProvinsi(pegawai.getInstansi().getProvinsi()));
		model.addAttribute("allJabatan", jabatanService.viewAll());
		
		return "addPegawai";
	}
	*/
	
    /*@RequestMapping(value = "/jadwal-jaga/insert", method = RequestMethod.POST)
    private String addJadwalSubmit(@ModelAttribute JadwalJagaModel jadwalJaga) {
        jadwalJagaService.addJadwalJaga(jadwalJaga);
        return "add";
    }*/
	
	
    /*@RequestMapping(value = "/jadwal-jaga/update/{jadwalJagaId}", method = RequestMethod.GET)
    private String updateJadwalJaga(@PathVariable(value = "jadwalJagaId") Long jadwalJagaId, Model model) {
    	JadwalJagaModel jadwalJaga = jadwalJagaService.findById(jadwalJagaId).get();
		model.addAttribute("jadwalJaga",jadwalJaga);
		return "update-jadwal-jaga";
    }

    @RequestMapping(value = "/jadwal-jaga/update/{jadwalJagaId}", method = RequestMethod.POST)
    private @ResponseBody JadwalJagaModel updateJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga, Model model) {
        jadwalJagaService.addJadwalJaga(jadwalJaga);
        return jadwalJaga;
    }*/
    
	
	
    /**
	 * TODO: Request obat ke Farmasi IS
	 */
    @PostMapping(value = "/obat/request/{requestObatId}")
	private String postRequest(@PathVariable ("requestObatId") Long requestObatId) throws Exception{
		String path = Setting.obatRequestUrl;
		RequestObatModel requestObat = requestObatService.findById(requestObatId).get();
		//DealerDetail detail = restTemplate.postForObject(path,dealer, DealerDetail.class);
		return "request-success";
	}
	
    /*
    @GetMapping(value = "/full/{dealerId}")
	private DealerDetail postStatus(@PathVariable ("dealerId") Long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer";
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		DealerDetail detail = restTemplate.postForObject(path,dealer, DealerDetail.class);
		return detail;
	}
	*/
}
