package br.usp.ime.verao.backend.form;


import br.usp.ime.verao.backend.model.Meme;
import br.usp.ime.verao.backend.repository.MemeRepository;

import javax.persistence.ElementCollection;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class MemeForm {
    @NotEmpty @NotNull @Max(value = 60)
    private String name;
    @ElementCollection
    private List<String> keywords = new ArrayList<>();
    @NotEmpty @NotNull @Max(value = 255)
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Meme converter(){
        return new Meme(this.name, this.keywords, this.url);
    }

    public Meme atualizar(Long id, MemeRepository memeRepository) {
        Meme meme = memeRepository.getOne(id);
        meme.setName(this.name);
        meme.setUrl(this.url);
        meme.setKeywords(this.keywords);
        return meme;
    }
}
