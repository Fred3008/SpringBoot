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

import fr.diginamic.resttp05.exception.OperationNotFoundException;
import fr.diginamic.resttp05.model.Operation;
import fr.diginamic.resttp05.repository.iCrudOperation;

@RestController
@RequestMapping("api/Operation")
public class ControllerOperation {

	@Autowired
	iCrudOperation ca;


	@GetMapping("all")
	public Iterable<Operation> getOperations() {
		return ca.findAll();
	}

	@GetMapping("{id}")
	public Optional<Operation> getOperation(@PathVariable("id") Integer pid) throws OperationNotFoundException {
		if (ca.findById(pid).isEmpty()) {
			String s = "Operation non trouvé, id: " + pid + " !!";
			throw new OperationNotFoundException(s);
		}
		return ca.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteOperation(@PathVariable("id") Integer pid) throws OperationNotFoundException {
		if (ca.findById(pid).isEmpty()) {
			String s = "Operation non trouvé, id: " + pid + " !!";
			throw new OperationNotFoundException(s);
		}
		ca.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Operation suprimé !");

	}

	@PutMapping("{id}")
	public Operation updateOperation(@PathVariable("id") Integer pid, @RequestBody Operation Operation)
			throws OperationNotFoundException {
		if (pid != Operation.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " !!";
			throw new OperationNotFoundException(s);
		}
		if (ca.findById(pid).isEmpty()) {

			String s = "Operation non trouvé, id: " + pid + " !!";
			throw new OperationNotFoundException(s);
		}

		return ca.save(Operation);

	}

	@PostMapping
	public Operation addOperation(@RequestBody Operation Operation) {
		return ca.save(Operation);
	}

}
