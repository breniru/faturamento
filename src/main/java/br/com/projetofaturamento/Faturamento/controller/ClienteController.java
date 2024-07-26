package br.com.projetofaturamento.Faturamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projetofaturamento.Faturamento.modelo.ClienteModelo;
import br.com.projetofaturamento.Faturamento.repository.ClienteModeloRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cadastro/cliente")
public class ClienteController {

    @Autowired
    private ClienteModeloRepository clienteModeloRepository;

    // Recupera todos os clientes
    @GetMapping
    public List<ClienteModelo> getAllClientes() {
        return clienteModeloRepository.findAll();
    }

    // Recupera um cliente específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModelo> getClienteById(@PathVariable Long id) {
        Optional<ClienteModelo> cliente = clienteModeloRepository.findById(id);
        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Cria um novo cliente
    @PostMapping
    public ResponseEntity<ClienteModelo> createCliente(@RequestBody ClienteModelo clienteModelo) {
        ClienteModelo savedCliente = clienteModeloRepository.save(clienteModelo);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    // Atualiza um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModelo> updateCliente(@PathVariable Long id, @RequestBody ClienteModelo clienteModelo) {
        if (clienteModeloRepository.existsById(id)) {
            clienteModelo.setId(id); // Certifique-se de que o ID está correto para atualização
            ClienteModelo updatedCliente = clienteModeloRepository.save(clienteModelo);
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Remove um cliente existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteModeloRepository.existsById(id)) {
            clienteModeloRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
