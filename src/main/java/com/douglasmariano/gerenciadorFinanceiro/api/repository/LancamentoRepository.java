package com.douglasmariano.gerenciadorFinanceiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglasmariano.gerenciadorFinanceiro.api.model.Lancamento;
import com.douglasmariano.gerenciadorFinanceiro.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
