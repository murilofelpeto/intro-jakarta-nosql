package com.felpeto.jakarta;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface Confraria extends CrudRepository<Developer, String> {

}
