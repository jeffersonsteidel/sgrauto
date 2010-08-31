function mascara(o, f) {
	v_obj = o;
	v_fun = f;
	setTimeout("execmascara()", 0);
}

function execmascara() {
	v_obj.value = v_fun(v_obj.value);
}

function soNumeros(v) {
	return v.replace(/\D/g, "");
}

function telefone(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que no dgito
	v = v.replace(/^(\d\d)(\d)/g, "($1)$2"); // Coloca parnteses em volta dos
												// dois primeiros dgitos
	v = v.replace(/(\d{4})(\d)/, "$1-$2"); // Coloca hfen entre o quarto e o
											// quinto dgitos
	return v;
}

function cpf(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que no dgito
	v = v.replace(/(\d{3})(\d)/, "$1.$2"); // Coloca um ponto entre o terceiro
											// e o quarto dgitos
	v = v.replace(/(\d{3})(\d)/, "$1.$2"); // Coloca um ponto entre o terceiro
											// e o quarto dgitos
	v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2"); // Coloca um hfen entre o
													// terceiro e o quarto
													// dgitos
	return v;
}

function cep(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que no dgito
	v = v.replace(/(\d{5})(\d)/, "$1-$2");
	return v;
}

function cnpj(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que no dgito
	v = v.replace(/^(\d{2})(\d)/, "$1.$2"); // Coloca ponto entre o segundo e o
											// terceiro dgitos
	v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3"); // Coloca ponto entre o
														// quinto e o sexto
														// dgitos
	v = v.replace(/\.(\d{3})(\d)/, ".$1/$2"); // Coloca uma barra entre o
												// oitavo e o nono dgitos
	v = v.replace(/(\d{4})(\d)/, "$1-$2"); // Coloca um hfen depois do bloco de
											// quatro dgitos
	return v;
}

function moeda(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que não é dígito
	v = v.replace(/(\d{2})$/, ".$1"); // Coloca o ponto
	v = v.replace(/^(0)(\d)/g, "$2");
	return v;
}

function moeda2(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que não é dígito
	v = v.replace(/(\d{2})$/, ",$1"); // Coloca a virgula
	v = v.replace(/(\d+)(\d{3},\d{2})$/g, "$1.$2"); // Coloca o primeiro ponto
	var qtdLoop = (v.length - 3) / 3;
	var count = 0;
	while (qtdLoop > count) {
		count++;
		v = v.replace(/(\d+)(\d{3}.*)/, "$1.$2"); // Coloca o resto dos pontos
	}
	v = v.replace(/^(0)(\d)/g, "$2"); // Coloca hífen entre o quarto e o
	return v;
}
