package br.com.projetofaturamento.Faturamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetofaturamento.Faturamento.modelo.FaturamentoModelo;
import br.com.projetofaturamento.Faturamento.modelo.ClienteModelo;
import br.com.projetofaturamento.Faturamento.repository.FaturamentoModeloRepository;
import br.com.projetofaturamento.Faturamento.repository.ClienteModeloRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faturamentos")
public class FaturamentoController {

    @Autowired
    private FaturamentoModeloRepository faturamentoModeloRepository;

    @Autowired
    private ClienteModeloRepository clienteModeloRepository;

    // Recupera todos os faturamentos
    @GetMapping
    public List<FaturamentoModelo> getAllFaturamentos() {
        return faturamentoModeloRepository.findAll();
    }

    // Recupera um faturamento específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<FaturamentoModelo> getFaturamentoById(@PathVariable Long id) {
        Optional<FaturamentoModelo> faturamento = faturamentoModeloRepository.findById(id);
        if (faturamento.isPresent()) {
            return new ResponseEntity<>(faturamento.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Cria um novo faturamento
    @PostMapping
    public ResponseEntity<FaturamentoModelo> createFaturamento(@RequestBody FaturamentoModelo faturamentoModelo) {
        if (faturamentoModelo.getClienteModelo() != null && faturamentoModelo.getClienteModelo().getId() != null) {
            Optional<ClienteModelo> cliente = clienteModeloRepository.findById(faturamentoModelo.getClienteModelo().getId());
            if (cliente.isPresent()) {
                faturamentoModelo.setClienteModelo(cliente.get());
                FaturamentoModelo savedFaturamento = faturamentoModeloRepository.save(faturamentoModelo);
                return new ResponseEntity<>(savedFaturamento, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Atualiza um faturamento existente
    @PutMapping("/{id}")
    public ResponseEntity<FaturamentoModelo> updateFaturamento(@PathVariable Long id, @RequestBody FaturamentoModelo faturamentoModelo) {
        if (faturamentoModeloRepository.existsById(id)) {
            faturamentoModelo.setId(id); // Certifique-se de que o ID está correto para atualização

            // Verifica se o cliente associado existe
            if (faturamentoModelo.getClienteModelo() != null && faturamentoModelo.getClienteModelo().getId() != null) {
                Optional<ClienteModelo> cliente = clienteModeloRepository.findById(faturamentoModelo.getClienteModelo().getId());
                if (cliente.isPresent()) {
                    faturamentoModelo.setClienteModelo(cliente.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

            FaturamentoModelo updatedFaturamento = faturamentoModeloRepository.save(faturamentoModelo);
            return new ResponseEntity<>(updatedFaturamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Remove um faturamento existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaturamento(@PathVariable Long id) {
        if (faturamentoModeloRepository.existsById(id)) {
            faturamentoModeloRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
