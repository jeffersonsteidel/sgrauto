package br.com.sgrauto.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validator {

	public static boolean validarPlaca(String placa) {
		String formato = "^(([A-Z]{3})\\-(([1-9][0-9][0-9][0-9])|([0][1-9][0-9][0-9])|([0]{2}[1-9][0-9])|([0]{3}[1-9])))$";
		if ((placa == null) || (placa.length() != 8)
				|| (!placa.matches(formato)))
			return false;
		else
			return true;
	}

	public static boolean validarCep(String cep) {
		String formato = "[0-9]{5}?\\-[0-9]{3}?";
		if ((cep == null) || (cep.length() != 9) || (!cep.matches(formato)))
			return false;
		else
			return true;
	}

	public static boolean validarTelefone(String tel) {
		String formato = "\\([0-9]{2}?\\)[0-9]{4}?\\-[0-9]{4}?";
		if ((tel == null) || (tel.length() != 13) || (!tel.matches(formato)))
			return false;
		else
			return true;
	}

	public static boolean validarCNPJCPF(String CNPJ_CPF) {
		CNPJ_CPF = CNPJ_CPF.replace("-", "").replace(".", "").replace("/", "");
		if (CNPJ_CPF.length() == 11) { // CPF
			int acumulador1 = 0;
			int acumulador2 = 0;
			int resto = 0;
			StringBuffer resultado = new StringBuffer();
			String digitoVerificadorCPF = CNPJ_CPF.substring(
					(CNPJ_CPF.length() - 2), CNPJ_CPF.length());
			for (int i = 1; i < (CNPJ_CPF.length() - 1); i++) {
				acumulador1 += (11 - i)
						* Integer.valueOf(CNPJ_CPF.substring((i - 1), i));
				acumulador2 += (12 - i)
						* Integer.valueOf(CNPJ_CPF.substring((i - 1), i));
			}
			resto = acumulador1 % 11;
			if (resto < 2) {
				acumulador2 += 2;
				resultado.append(2);
			} else {
				acumulador2 += 2 * (11 - resto);
				resultado.append((11 - resto));
			}
			resto = acumulador2 % 11;
			if (resto < 2) {
				resultado.append(0);
			} else {
				resultado.append((11 - resto));
			}
			return resultado.toString().equals(digitoVerificadorCPF);
		} else if (CNPJ_CPF.length() == 14) { // CNPJ
			int acumulador = 0;
			int digito = 0;
			StringBuffer CNPJ = new StringBuffer();
			char[] CNPJCharArray = CNPJ_CPF.toCharArray();
			CNPJ.append(CNPJ_CPF.substring(0, 12));
			for (int i = 0; i < 4; i++) {
				if (((CNPJCharArray[i] - 48) >= 0)
						&& ((CNPJCharArray[i] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i] - 48) * (6 - (i + 1));
				}
			}
			for (int i = 0; i < 8; i++) {
				if (((CNPJCharArray[i + 4] - 48) >= 0)
						&& ((CNPJCharArray[i + 4] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i + 4] - 48) * (10 - (i + 1));
				}
			}
			digito = 11 - (acumulador % 11);
			CNPJ.append((digito == 10 || digito == 11) ? "0" : digito);
			acumulador = 0;
			for (int i = 0; i < 5; i++) {
				if (((CNPJCharArray[i] - 48) >= 0)
						&& ((CNPJCharArray[i] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i] - 48) * (7 - (i + 1));
				}
			}
			for (int i = 0; i < 8; i++) {
				if (((CNPJCharArray[i + 5] - 48) >= 0)
						&& ((CNPJCharArray[i + 5] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i + 5] - 48) * (10 - (i + 1));
				}
			}
			digito = 11 - (acumulador % 11);
			CNPJ.append((digito == 10 || digito == 11) ? "0" : digito);
			return CNPJ.toString().equals(CNPJ_CPF);
		}
		return false;
	}

	public static boolean validarEmail(String email) {
		if (email == null || email == "") {
			return true;
		}
		Pattern p = Pattern.compile(".+@.+\\.[A-Z]+");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		if (matchFound)
			return true;
		else
			return false;
	}

	/*
	 * realiza a formatação do valor de acordo com a mascara enviada
	 */
	public static String formatar(String valor, String mascara) {

		String dado = "";
		// remove caracteres nao numericos
		for (int i = 0; i < valor.length(); i++) {
			char c = valor.charAt(i);
			if (Character.isDigit(c)) {
				dado += c;
			}
		}

		int indMascara = mascara.length();
		int indCampo = dado.length();

		for (; indCampo > 0 && indMascara > 0;) {
			if (mascara.charAt(--indMascara) == '#') {
				indCampo--;
			}
		}

		String saida = "";
		for (; indMascara < mascara.length(); indMascara++) {
			saida += ((mascara.charAt(indMascara) == '#') ? dado
					.charAt(indCampo++) : mascara.charAt(indMascara));
		}
		return saida;
	}

	public static String formatarCpf(String cpf) {
		while (cpf.length() < 11) {
			cpf = "0" + cpf;
		}
		return formatar(cpf, "###.###.###-##");
	}

	public static String formatarCnpj(String cnpj) {
		while (cnpj.length() < 14) {
			cnpj = "0" + cnpj;
		}
		return formatar(cnpj, "##.###.###/####-##");
	}
}
