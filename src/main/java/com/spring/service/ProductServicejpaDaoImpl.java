package com.spring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring.domain.Product;

@Service
@Profile("jpadao")
public class ProductServicejpaDaoImpl implements ProductService {

	private EntityManagerFactory emf;

	@Override
	public List<Product> listAllProducts() {
		EntityManager entityManager = emf.createEntityManager();
		return entityManager.createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public Product getProductById(Integer id) {
		EntityManager entityManager = emf.createEntityManager();
		return entityManager.find(Product.class, id);
	}

	@Override
	public Product saveProduct(Product product) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		Product p = entityManager.merge(product);
		entityManager.getTransaction().commit();
		return p;
	}

	@Override
	public void deleteProduct(Integer id) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(Product.class, id));
		entityManager.getTransaction().commit();
	}

	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

}
