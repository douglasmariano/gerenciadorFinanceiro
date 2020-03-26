package com.douglasmariano.gerenciadorFinanceiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglasmariano.gerenciadorFinanceiro.api.model.Pessoa;
import com.douglasmariano.gerenciadorFinanceiro.api.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{

}
