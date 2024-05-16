package br.edu.vzincoder.crud.controller;

import org.springframework.web.bind.annotation.RestController;
import br.edu.vzincoder.crud.model.Contato;
import br.edu.vzincoder.crud.repository.ContatoRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("contato")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarContatoPorId(@PathVariable("id") int id) {
        Optional<Contato> contato = contatoRepository.findById(id);
        return contato.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public List<Contato> buscarContatos() {
        return contatoRepository.findAll();
    }

    @GetMapping("/nome/{nome}")
    public List<Contato> buscarContatoPorNome(@PathVariable("nome") String nome) {
        return contatoRepository.findByNome(nome);
    }

    @GetMapping("/numero/{numero}")
    public List<Contato> buscarContatoPorNumero(@PathVariable("numero") String numero) {
        return contatoRepository.findByNumero(numero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarContato(@PathVariable("id") int id, @RequestBody Contato novoContato) {
        Optional<Contato> optionalContato = contatoRepository.findById(id);
        if (optionalContato.isPresent()) {
            Contato contatoExistente = optionalContato.get();
            contatoExistente.setNome(novoContato.getNome());
            contatoExistente.setNumero(novoContato.getNumero());
            contatoRepository.save(contatoExistente);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarContato(@RequestBody Contato contato) {
        contatoRepository.save(contato);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
