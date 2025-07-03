package br.com.filmeapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Filme {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String nome;
    private Double nota;

    public Filme(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
    
    public Double getNota() {
        return nota;
    }

    public String getNome() {
        return nome;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setNota(Double nota) {
        this.nota = nota;
    }
    
    public Filme() {}
    
    
}
