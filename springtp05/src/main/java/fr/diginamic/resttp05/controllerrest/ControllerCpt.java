package fr.diginamic.resttp05.controllerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.resttp05.exception.CompteNotFoundException;
import fr.diginamic.resttp05.model.Compte;
import fr.diginamic.resttp05.repository.iCrudCompte;
@RestController
public abstract class ControllerCpt <T extends Compte> {

	@Autowired
	protected iCrudCompte cc;
	
	@GetMapping("all")
	public abstract Iterable<T> getComptes();
	
	@GetMapping("{id}")
	public abstract T getCompteId(@PathVariable("id") int pid) throws CompteNotFoundException;

	
}
