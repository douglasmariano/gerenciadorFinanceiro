package com.douglasmariano.gerenciadorFinanceiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglasmariano.gerenciadorFinanceiro.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
}
