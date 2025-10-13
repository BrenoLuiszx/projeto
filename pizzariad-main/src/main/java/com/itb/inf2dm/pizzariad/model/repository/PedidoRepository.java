package com.itb.inf2dm.pizzariad.model.repository;


import com.itb.inf2dm.pizzariad.model.entity.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
