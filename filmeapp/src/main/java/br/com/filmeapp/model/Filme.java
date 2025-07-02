package br.com.filmeapp.model;

public class Filme {
	private int id;
    private String nome;

    public Filme(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
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
    
    public Filme() {}
    
    
}
