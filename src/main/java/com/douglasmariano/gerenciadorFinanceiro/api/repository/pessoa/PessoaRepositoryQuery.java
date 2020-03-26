package com.douglasmariano.gerenciadorFinanceiro.api.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.douglasmariano.gerenciadorFinanceiro.api.model.Pessoa;
import com.douglasmariano.gerenciadorFinanceiro.api.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	
	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageble);

}
