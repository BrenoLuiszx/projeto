package com.itb.inf2dm.pizzariad.model.services;

import com.itb.inf2dm.pizzariad.model.entity.Pedido;
import com.itb.inf2dm.pizzariad.model.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido save(Pedido pedido) {
        pedido.setCodStatus(true);
        if (pedido.getDataHoraPedido() == null) {
            pedido.setDataHoraPedido(LocalDateTime.now());
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o id: " + id));
    }

    public Pedido update(Long id, Pedido pedido) {
        Pedido pedidoExistente = findById(id);
        pedidoExistente.setValorPedido(pedido.getValorPedido());
        pedidoExistente.setDataHoraEntrega(pedido.getDataHoraEntrega());
        pedidoExistente.setStatus(pedido.getStatus());
        pedidoExistente.setCodStatus(pedido.isCodStatus());
        return pedidoRepository.save(pedidoExistente);
    }

    public void delete(Long id) {
        Pedido pedidoExistente = findById(id);
        pedidoRepository.delete(pedidoExistente);
    }
}