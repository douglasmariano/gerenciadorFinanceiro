package com.douglasmariano.gerenciadorFinanceiro.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.douglasmariano.gerenciadorFinanceiro.api.exceptionhandler.GerenciadorFinanceiroExceptionHandler.Erro;
import com.douglasmariano.gerenciadorFinanceiro.api.service.exception.PessoaInexistenteOuInativaException;

@ControllerAdvice
public class GerenciadorFinanceiroExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemDoUsuario = messageSource().getMessage("mensagem.invalida",  null, LocaleContextHolder.getLocale());
		String mensagemDoDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemDoUsuario, mensagemDoDesenvolvedor));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

			List<Erro> erros = criarListaErros(ex.getBindingResult());
			return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException( EmptyResultDataAccessException ex, WebRequest request) {
		String mensagemDoUsuario = messageSource().getMessage("recurso.nao-encontrado",  null, LocaleContextHolder.getLocale());
		String mensagemDoDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemDoUsuario, mensagemDoDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		String mensagemDoUsuario = messageSource().getMessage("recurso.operacao-nao-permitida",  null, LocaleContextHolder.getLocale());
		String mensagemDoDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemDoUsuario, mensagemDoDesenvolvedor));		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String mensagemDoUsuario = messageSource().getMessage("pessoa.inexistente-ou-inativo",  null, LocaleContextHolder.getLocale());
		String mensagemDoDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemDoUsuario, mensagemDoDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);

		
	}
	
	private List<Erro> criarListaErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			
			String mensagemUsuario = messageSource().getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
			}
		
		return erros;
	}
	
	public static class Erro {
		
		private String mensagemDoUsuario;
		private String mensagemDoDesenvolvedor;
		public Erro(String mensagemDoUsuario, String mensagemDoDesenvolvedor) {			
			this.mensagemDoUsuario = mensagemDoUsuario;
			this.mensagemDoDesenvolvedor = mensagemDoDesenvolvedor;
		}
		public String getMensagemDoUsuario() {
			return mensagemDoUsuario;
		}
		public void setMensagemDoUsuario(String mensagemDoUsuario) {
			this.mensagemDoUsuario = mensagemDoUsuario;
		}
		public String getMensagemDoDesenvolvedor() {
			return mensagemDoDesenvolvedor;
		}
		public void setMensagemDoDesenvolvedor(String mensagemDoDesenvolvedor) {
			this.mensagemDoDesenvolvedor = mensagemDoDesenvolvedor;
		}
		
		
	}
	
	
	

}
