package agendaonline.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import agendaonline.com.models.Procedimento;
import agendaonline.com.repositories.ProcedimentoRepository;

@RestController
public class ProcedimentosController {

	@Autowired
	private ProcedimentoRepository pr;
	
	@GetMapping(value="/procedimentos", produces="application/json")
	public @ResponseBody Iterable<Procedimento> listaProcedimentos(){
		Iterable<Procedimento> listaProcedimentos = pr.findAll();	
		return listaProcedimentos;
	}
	
	@PostMapping(value="/procedimentos")
	public Procedimento cadastroProcedimento(@RequestBody Procedimento procedimento){
		return pr.save(procedimento);
	}
}
