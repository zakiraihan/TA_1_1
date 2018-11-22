package com.apap.tugasAkhir.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasAkhir.model.PaviliunModel;
import com.apap.tugasAkhir.repository.PaviliunDb;

import java.util.List;

@Service
@Transactional
public class PaviliunServiceImpl implements PaviliunService{
	@Autowired
	PaviliunDb paviliunDb;
	
	@Override
	public List<PaviliunModel> getAllPaviliun(){
		List<PaviliunModel> listOfPaviliun = paviliunDb.findAll();
		return listOfPaviliun;
	}
}
