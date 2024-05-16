package br.edu.vzincoder.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.vzincoder.crud.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
    
}
