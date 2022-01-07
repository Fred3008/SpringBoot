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

import fr.diginamic.resttp05.exception.LivretANotFoundException;
import fr.diginamic.resttp05.model.LivretA;
import fr.diginamic.resttp05.repository.iCrudLivretA;

@RestController
@RequestMapping("api/livreta")
public class ControllerLvretA {

	@Autowired
	iCrudLivretA ca;

	

	@GetMapping("all")
	public Iterable<LivretA> getLivretAs() {
		return ca.findAll();
	}
	@GetMapping("test")
	public void test() {
		LivretA la = new LivretA();
		ca.save(la);
	}

	@GetMapping("{id}")
	public Optional<LivretA> getLivretA(@PathVariable("id") Integer pid) throws LivretANotFoundException {
		if (ca.findById(pid).isEmpty()) {
			String s = "LivretA non trouvé, id: " + pid + " !!";
			throw new LivretANotFoundException(s);
		}
		return ca.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteLivretA(@PathVariable("id") Integer pid) throws LivretANotFoundException {
		if (ca.findById(pid).isEmpty()) {
			String s = "LivretA non trouvé, id: " + pid + " !!";
			throw new LivretANotFoundException(s);
		}
		ca.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("LivretA suprimé !");

	}

	@PutMapping("{id}")
	public LivretA updateLivretA(@PathVariable("id") Integer pid, @RequestBody LivretA livreta)
			throws LivretANotFoundException {
		if (pid != livreta.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " !!";
			throw new LivretANotFoundException(s);
		}
		if (ca.findById(pid).isEmpty()) {

			String s = "LivretA non trouvé, id: " + pid + " !!";
			throw new LivretANotFoundException(s);
		}

		return ca.save(livreta);

	}

	@PostMapping
	public LivretA addLivretA(@RequestBody LivretA LivretA) {
		return ca.save(LivretA);
	}

}
