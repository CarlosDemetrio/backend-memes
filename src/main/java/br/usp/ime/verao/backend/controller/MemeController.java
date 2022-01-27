package br.usp.ime.verao.backend.controller;


import br.usp.ime.verao.backend.form.MemeForm;
import br.usp.ime.verao.backend.model.Meme;
import br.usp.ime.verao.backend.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/meme")
public class MemeController {

    private final MemeService memeService;

    @Autowired
    public MemeController(MemeService memeService){
        this.memeService = memeService;
    }

    @GetMapping //os parametros vem do GET topicos?page=0&size=10&sort=id,asc
    public Page<Meme> lista(@RequestParam(required = false) String nomeMeme,
                                 @PageableDefault(sort = "id", size = 20) Pageable paginacao){

        if (nomeMeme == null) {
            Page<Meme> memes = memeService.memerepository.findAll(paginacao);
            return memes;
        } else {
            Page<Meme> memes = memeService.memerepository.findByName(nomeMeme, paginacao);
            return memes;
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Meme> detalhar(@PathVariable Long id) {
        Optional<Meme> meme = memeService.memerepository.findById(id);
        return meme.map(value -> ResponseEntity.ok(new Meme(meme.get()))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Transactional//requestBody avisa ao spring que o parametro vem do metodo post; Valid ativa validacoes
    public ResponseEntity<Meme> cadastrar(@RequestBody @Valid MemeForm form, UriComponentsBuilder uriBuilder) {

        Meme meme = form.converter();
        memeService.memerepository.save(meme);

        //necessário para devolver codigo 201 de cadastrado com sucesso
        URI uri = uriBuilder.path("/meme/{id}").buildAndExpand(meme.getId()).toUri();
        return ResponseEntity.created(uri).body(meme);//uri e o caminho do endereco do item cadastrado, o body retorna como ele foi cadastrado
    }


    @PutMapping("/{id}")
    public ResponseEntity<Meme> atualizar(@PathVariable Long id, @RequestBody @Valid MemeForm form, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Meme> optional = memeService.memerepository.findById(id);
        if (optional.isPresent()) {
            Meme meme = form.atualizar(id, memeService.memerepository);
            return ResponseEntity.ok(meme);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Meme> meme = memeService.memerepository.findById(id);
        if (meme.isPresent()) {
            memeService.memerepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
