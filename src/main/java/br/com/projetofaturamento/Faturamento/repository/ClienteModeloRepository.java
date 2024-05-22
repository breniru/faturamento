package br.com.projetofaturamento.Faturamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofaturamento.Faturamento.modelo.ClienteModelo;

public interface ClienteModeloRepository extends JpaRepository<ClienteModelo, Long> {

}
