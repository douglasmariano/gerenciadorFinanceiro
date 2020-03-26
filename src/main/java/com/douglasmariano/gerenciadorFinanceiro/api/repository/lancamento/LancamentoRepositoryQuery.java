package com.douglasmariano.gerenciadorFinanceiro.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.douglasmariano.gerenciadorFinanceiro.api.model.Lancamento;
import com.douglasmariano.gerenciadorFinanceiro.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
