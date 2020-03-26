 package com.douglasmariano.gerenciadorFinanceiro.api.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.douglasmariano.gerenciadorFinanceiro.api.model.Lancamento;
import com.douglasmariano.gerenciadorFinanceiro.api.model.Pessoa;
import com.douglasmariano.gerenciadorFinanceiro.api.repository.LancamentoRepository;
import com.douglasmariano.gerenciadorFinanceiro.api.repository.PessoaRepository;
import com.douglasmariano.gerenciadorFinanceiro.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	
	@Autowired
	private LancamentoRepository lancamentoRespository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscaLancamentoPeloCodigo(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoSalvo,"codigo");
		return lancamentoRespository.save(lancamentoSalvo);
	}

	private Lancamento buscaLancamentoPeloCodigo(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRespository.findById(codigo).orElse(null);
		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoSalvo;
	}

	public Lancamento salvar(Lancamento lancamento) {
			Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);
			if (pessoa == null || pessoa.isInativo()) {
				throw new PessoaInexistenteOuInativaException();
			}
			return lancamentoRespository.save(lancamento);
	}
	
	
}
