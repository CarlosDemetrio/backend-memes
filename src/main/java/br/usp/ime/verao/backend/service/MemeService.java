package br.usp.ime.verao.backend.service;


import br.usp.ime.verao.backend.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeService {

    public MemeRepository memerepository;

    @Autowired
    public MemeService(MemeRepository memeRepository){
        this.memerepository = memeRepository;
    }

}
