package org.acme.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.acme.domain.Categoria;

@ApplicationScoped
public class CategoriaService {
	
    @Inject
    EntityManager em;
    
    @Transactional
    public String insert(String nome) {
    	try {
    		String query = "insert into categoria values(null, ?)";

        	int n = em.createNativeQuery(query, Categoria.class)
        	   .setParameter(1, nome)
        	   .executeUpdate();
        	
        	System.out.println("ID CATEGORIA= "+n);
        	System.out.println("ID CATEGORIA= "+em.getClass());
        	
        	return n == 1 ? "ok": null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CODE = " + e.hashCode() + ", Title = "+ e.getCause() + ", MESSAGE = " + e.getMessage());
			return e.getMessage();
		}
    }
	
	public List<Categoria> getAll () {
		List<Categoria> list = em.createNativeQuery("select id as Id, nome as Nome from categoria",Categoria.class).getResultList();
		return list;
	}
	
	public Categoria findById(Integer id) {
		String query = "select id as Id, nome as Nome from categoria where id = ?";
		Categoria cat = (Categoria) em.createNativeQuery(query, Categoria.class)
				.setParameter(1, id).getSingleResult();
		return cat;
	}

}
