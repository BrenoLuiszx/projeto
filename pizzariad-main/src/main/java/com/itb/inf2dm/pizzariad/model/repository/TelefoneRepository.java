package com.itb.inf2dm.pizzariad.model.repository;



import com.itb.inf2dm.pizzariad.model.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
