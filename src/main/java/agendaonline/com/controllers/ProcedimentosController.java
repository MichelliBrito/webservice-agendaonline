package agendaonline.com.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import agendaonline.com.models.Procedimento;
import agendaonline.com.repositories.ProcedimentoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Procedimentos Resource") //Diz ao Swagger que esse é um endpoint e REST deve ser documentado
@RestController
@RequestMapping("/agendaonline/procedimentos")
public class ProcedimentosController {

	@Autowired
	private ProcedimentoRepository pr;
	
	@ApiOperation(value = "Mostra lista de procedimentos" ) //Diz ao Swagger que essa operação REST deve ser documentado
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Procedimento> listaProcedimentos(){
		Iterable<Procedimento> listaProcedimentos = pr.findAll();	
		return listaProcedimentos;
	}
	
	@ApiOperation(value = "Salva procedimento" ) //Diz ao Swagger que essa operação REST deve ser documentado
	@PostMapping()
	public ResponseEntity<?> cadastroProcedimento(@RequestBody @Valid Procedimento procedimento, BindingResult result){
		 if (result.hasErrors()) {
			 return ResponseEntity.badRequest().body(result.getFieldError());
		 }
		pr.save(procedimento);
		return ResponseEntity.ok(procedimento);
	}

	@ApiOperation(value = "Deleta procedimento" ) //Diz ao Swagger que essa operação REST deve ser documentado
	@DeleteMapping()
	public Procedimento deletaProcedimento(@RequestBody Procedimento procedimento){
		pr.delete(procedimento);
		return procedimento;
	}
}
