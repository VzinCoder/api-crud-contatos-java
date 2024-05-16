package br.edu.vzincoder.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.vzincoder.crud.model.Contato;
import java.util.List;


public interface ContatoRepository extends JpaRepository<Contato, Integer> {
    
    List<Contato> findByNome(String nome);
    List<Contato> findByNumero(String numero);
}
