package br.com.zup.comics.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FormatDataNascimento {

	public Date formata(String dataNascimento) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse(dataNascimento); 
		return dataFormatada;
	}
}
