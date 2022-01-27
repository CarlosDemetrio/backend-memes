package br.usp.ime.verao.backend.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meme {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection
    private List<String> keywords = new ArrayList<>();
    private String url;

    public Meme(String name, List<String> keywords, String url) {
        this.name = name;
        this.keywords = keywords;
        this.url = url;
    }

    public Meme() {

    }

    public Meme(Long id, String name, List<String> keywords, String url) {
        this.id = id;
        this.name = name;
        this.keywords = keywords;
        this.url = url;
    }

    public Meme(Meme meme) {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Meme other = (Meme) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
