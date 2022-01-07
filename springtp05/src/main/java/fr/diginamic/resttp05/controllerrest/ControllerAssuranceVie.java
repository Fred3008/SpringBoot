package fr.diginamic.resttp05.controllerrest;


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



import fr.diginamic.resttp05.exception.AssuranceVieNotFoundException;
import fr.diginamic.resttp05.model.AssuranceVie;


@RestController
@RequestMapping("api/assurancevie")
public abstract class ControllerAssuranceVie extends ControllerCpt<AssuranceVie> {

	

	@Override
	public Iterable<AssuranceVie> getComptes() {
		AssuranceVie c1 = new AssuranceVie();
		c1.setNumero("3422");
		c1.setSolde(154.43);
		c1.setTaux(13.1);
		cc.save(c1);
		return cc.getAllAssuranceVies();
	}
	@Override
	public AssuranceVie getCompteId(@PathVariable("id")int pid) {
		
		return (AssuranceVie) cc.findById(pid).get();
	}

	@GetMapping("all")
	public Iterable<AssuranceVie> getAssuranceVies() {
		return cc.getAllAssuranceVies();
	}

	@GetMapping("{id}")
	public AssuranceVie getAssuranceVie(@PathVariable("id") Integer pid) throws AssuranceVieNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "assuranceVie non trouvé, id: " + pid + " !!";
			throw new AssuranceVieNotFoundException(s);
		}
		return (AssuranceVie)cc.findById(pid).get();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAssuranceVie(@PathVariable("id") Integer pid) throws AssuranceVieNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "AssuranceVie non trouvé, id: " + pid + " !!";
			throw new AssuranceVieNotFoundException(s);
		}
		cc.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("AssuranceVie suprimé !");

	}

	@PutMapping("{id}")
	public AssuranceVie updateAssuranceVie(@PathVariable("id") Integer pid, @RequestBody AssuranceVie assuranceVie)
			throws AssuranceVieNotFoundException {
		if (pid != assuranceVie.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " !!";
			throw new AssuranceVieNotFoundException(s);
		}
		if (cc.findById(pid).isEmpty()) {

			String s = "assuranceVie non trouvé, id: " + pid + " !!";
			throw new AssuranceVieNotFoundException(s);
		}

		return cc.save(assuranceVie);

	}

	@PostMapping
	public AssuranceVie addAssuranceVie(@RequestBody AssuranceVie assuranceVie) {
		return cc.save(assuranceVie);
	}

}
