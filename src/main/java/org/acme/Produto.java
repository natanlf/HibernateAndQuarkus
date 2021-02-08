package org.acme;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "PRODUTO")
public class Produto {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
    public String nome;

    public String descricao;

    public double valor;

    @CreationTimestamp
    public Date dataCriacao;

    @UpdateTimestamp
    public Date dataAtualizacao;

}