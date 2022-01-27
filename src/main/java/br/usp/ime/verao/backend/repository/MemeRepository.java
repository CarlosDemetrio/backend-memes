package br.usp.ime.verao.backend.repository;

import br.usp.ime.verao.backend.model.Meme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemeRepository  extends JpaRepository<Meme, Long> {
    Page<Meme> findByName(String name, Pageable paginacao);
}
