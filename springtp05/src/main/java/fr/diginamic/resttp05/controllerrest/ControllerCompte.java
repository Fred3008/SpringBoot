package fr.diginamic.resttp05.controllerrest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.resttp05.exception.CompteNotFoundException;
import fr.diginamic.resttp05.model.Compte;
import fr.diginamic.resttp05.model.CompteCourant;
import fr.diginamic.resttp05.repository.iCrudCompte;

@RestController
@RequestMapping("api/compte")
public class ControllerCompte  extends ControllerCpt<CompteCourant> {

	


	@Override
	public Iterable <CompteCourant> getComptes() {
		CompteCourant c1 = new CompteCourant();
		c1.setNumero("65325");
		c1.setSolde(2333.22);
		cc.save(c1);
		return cc.getAllComptes();
	}
	
	@Override
	public CompteCourant getCompteId( @PathVariable("id")int pid) throws CompteNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "compte non trouvé, id: " + pid + " !!";
			throw new CompteNotFoundException(s);
		}
		return (CompteCourant) cc.findById(pid).get();	}
	
	

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "compte non trouvé, id: " + pid + " !!";
			throw new CompteNotFoundException(s);
		}
		cc.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Compte suprimé !");

	}

	@PutMapping("{id}")
	public Compte updateCompte(@PathVariable("id") Integer pid, @RequestBody Compte compte)
			throws CompteNotFoundException {
		if (pid != compte.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " !!";
			throw new CompteNotFoundException(s);
		}
		if (cc.findById(pid).isEmpty()) {

			String s = "compte non trouvé, id: " + pid + " !!";
			throw new CompteNotFoundException(s);
		}

		return cc.save(compte);

	}

	@PostMapping
	public Compte addCompte(@RequestBody Compte compte) {
		return cc.save(compte);
	}

	

}
