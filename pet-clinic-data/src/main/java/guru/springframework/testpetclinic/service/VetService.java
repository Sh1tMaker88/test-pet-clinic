package guru.springframework.testpetclinic.service;

import guru.springframework.testpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;


public interface VetService extends CrudRepository<Vet, Long> {

}
