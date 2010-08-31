package  br.com.sgrauto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String nome;
	private String rg;
	private String cpf;
	private String cnh;
	private Date dataNascimento;
	private String sexo;
	private Empresa filial;
	private String cargo;
	private BigDecimal remuneracao;
	private Cidade cidade;
	private String endereco;
	private String bairro;
	private String cep;
	private String telefone;
	private String celular;
	private String email;
	private Boolean indCliente;
	private Boolean indFuncionario;
	private Date dataEntrada;
	private Date dataSaida;
	private String login;
	private String senha;
	private Boolean indGerente = false;
	private Boolean indDesat;

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public BigDecimal getRemuneracao() {
		if (remuneracao != null) {
			remuneracao = remuneracao.setScale(2, RoundingMode.HALF_EVEN);
		}
		return remuneracao;
	}
	public void setRemuneracao(BigDecimal remuneracao) {
		this.remuneracao = remuneracao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIndCliente() {
		return indCliente;
	}
	public void setIndCliente(Boolean indCliente) {
		this.indCliente = indCliente;
	}
	public Boolean getIndFuncionario() {
		return indFuncionario;
	}
	public void setIndFuncionario(Boolean indFuncionario) {
		this.indFuncionario = indFuncionario;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getIndDesat() {
		return indDesat;
	}
	public void setIndDesat(Boolean indDesat) {
		this.indDesat = indDesat;
	}
	public Boolean getIndGerente() {
		return indGerente;
	}
	public void setIndGerente(Boolean indGerente) {
		this.indGerente = indGerente;
	}
	
	public Empresa getFilial() {
		return filial;
	}
	public void setFilial(Empresa filial) {
		this.filial = filial;
	}

	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
