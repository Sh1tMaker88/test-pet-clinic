package guru.springframework.testpetclinic.service;

import guru.springframework.testpetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
