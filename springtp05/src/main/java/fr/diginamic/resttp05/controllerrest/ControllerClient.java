package fr.diginamic.resttp05.controllerrest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.resttp05.exception.ClientNotFoundException;
import fr.diginamic.resttp05.model.Client;
import fr.diginamic.resttp05.repository.iCrudClient;

@RestController
@RequestMapping("api/client")
public class ControllerClient {

	@Autowired
	iCrudClient cc;


	@GetMapping("all")
	public Iterable<Client> getClients() {
		return cc.findAll();
	}

	@GetMapping("{id}")
	public Optional<Client> getClient(@PathVariable("id") Integer pid) throws ClientNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "client non trouvé, id: " + pid + " !!";
			throw new ClientNotFoundException(s);
		}
		return cc.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id") Integer pid) throws ClientNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "client non trouvé, id: " + pid + " !!";
			throw new ClientNotFoundException(s);
		}
		cc.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Client suprimé !");

	}

	@PutMapping("{id}")
	public Client updateClient(@PathVariable("id") Integer pid,@Valid @RequestBody Client client)
			throws ClientNotFoundException {
		if (pid != client.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " !!";
			throw new ClientNotFoundException(s);
		}
		if (cc.findById(pid).isEmpty()) {

			String s = "client non trouvé, id: " + pid + " !!";
			throw new ClientNotFoundException(s);
		}

		return cc.save(client);

	}

	@PostMapping
    public Client addClient(@Valid @RequestBody Client client,
            BindingResult result) throws ClientNotFoundException {
        if(result.hasErrors())
        {
            String s = result.toString();
            throw new ClientNotFoundException(s);
        }
        return cc.save(client);
    }

}
