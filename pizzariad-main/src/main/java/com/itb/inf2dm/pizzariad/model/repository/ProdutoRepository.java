package com.itb.inf2dm.pizzariad.model.repository;


import com.itb.inf2dm.pizzariad.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
