<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SGRAuto</title>
</head>
<body>
<f:view>
	<jsp:directive.include file="menus.jsp" />
	<center><a4j:form id="form">
		<rich:panel header="Venda"
			style="width: 760px; position: absolute; left: 310px; top: auto;">
			<rich:messages layout="list" errorLabelClass="errorLabel"
				infoLabelClass="infoLabel">
				<f:facet name="infoMarker">
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>

			<h:panelGrid columns="4">

				<h:outputText value="Loja: " />
				<h:selectOneMenu value="#{vendaController.venda.filial.codigo}"
					required="true" requiredMessage="Campo Loja obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{vendaController.filiais}" />
					<a4j:support event="onchange"
						action="#{vendaController.listarVendedores}" ajaxSingle="true"
						reRender="vendedor, estado, cidade, cliente, clienteJuridico, automovel" />
				</h:selectOneMenu>

				<h:outputText value="Vendedor: " />
				<h:selectOneMenu id="vendedor"
					value="#{vendaController.venda.funcionario.codigo}" required="true"
					requiredMessage="Campo Vendedor obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{vendaController.vendedores}" />
				</h:selectOneMenu>

				<h:outputText value="Estado: " />
				<h:outputText id="estado"
					value="#{vendaController.venda.filial.cidade.estado.descricao}" />
				<h:outputText value="Cidade: " />
				<h:outputText id="cidade"
					value="#{vendaController.venda.filial.cidade.descricao}" />

				<h:outputText value="CPF do Cliente: " />
				<h:inputText id="cpf" value="#{vendaController.venda.cliente.cpf}"
					size="20" maxlength="14" required="true"
					requiredMessage="Campo CPF obrigatório!"
					validatorMessage="Campo CPF deve ter 14 caracteres!"
					onkeypress="mascara(this,cpf);">
					<f:validateLength minimum="14" />
					<a4j:support event="onchange"
						action="#{vendaController.carregarCliente}" ajaxSingle="true"
						reRender="clienteText" />
				</h:inputText>

				<h:outputText value="Cliente: " />
				<h:outputText id="clienteText"
					value="#{vendaController.venda.cliente.nome}" />

				<h:outputText value="Automóvel Placa: " />
				<h:inputText id="placa"
					value="#{vendaController.venda.automovel.placa}" size="10"
					maxlength="8" required="true"
					requiredMessage="Campo Placa obrigatório!"
					validatorMessage="Campo Automóvel Placa deve ter 8 caracteres!">
					<f:validateLength minimum="8" />
					<a4j:support event="onchange"
						action="#{vendaController.carregarAutomovel}" ajaxSingle="true"
						reRender="placa, marca, modelo, ano, cor, combustivel, valorvista" />
				</h:inputText>

				<h:outputText value="Marca: " />
				<h:outputText id="marca"
					value="#{vendaController.venda.automovel.modelo.marca.descricao}" />
				<h:outputText value="Modelo: " />
				<h:outputText id="modelo"
					value="#{vendaController.venda.automovel.modelo.descricao} #{vendaController.venda.automovel.descricao}" />
				<h:outputText value="Ano/ Ano Modelo: " />
				<h:outputText id="ano"
					value="#{vendaController.venda.automovel.ano}  #{vendaController.venda.automovel.anoModelo}" />
				<h:outputText value="Cor: " />
				<h:outputText id="cor"
					value="#{vendaController.venda.automovel.cor}" />
				<h:outputText value="Combustível: " />
				<h:outputText id="combustivel"
					value="#{vendaController.venda.automovel.combustivel}" />
				<h:outputText value="Valor: " />
				<h:outputText id="valorvista"
					value="#{vendaController.venda.valorVista}">
					<f:convertNumber pattern="########.00" maxFractionDigits="2"
						minFractionDigits="2" />
				</h:outputText>


				<h:outputText value="Data: " />
				<rich:calendar value="#{vendaController.venda.data}" locale=""
					popup="true" datePattern="dd/MM/yyyy" showApplyButton="#"
					cellWidth="16px" cellHeight="22px" style="width:200px"
					required="true" requiredMessage="Campo Data obrigatório!" />

				<h:outputText value="Forma de Pagamento: " />
				<h:selectOneMenu value="#{vendaController.formaPagamento}"
					required="true"
					requiredMessage="Campo Forma Pagamento obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItem itemLabel="A PRAZO" itemValue="PRAZO" />
					<f:selectItem itemLabel="À VISTA" itemValue="VISTA" />
					<a4j:support event="onchange"
						action="#{vendaController.escolherFormaPagamento}"
						ajaxSingle="true" reRender="pagamento" />
				</h:selectOneMenu>
			</h:panelGrid>

			<h:panelGrid id="pagamento" columns="4">

				<h:outputText value="Financeira: "
					rendered="#{vendaController.venda.vendaPrazo}" />
				<h:selectOneMenu value="#{vendaController.venda.financeira.codigo}"
					rendered="#{vendaController.venda.vendaPrazo}" required="true"
					requiredMessage="Campo Financeira obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{vendaController.financeiras}" />
				</h:selectOneMenu>

				<h:outputText value="Desconto:"
					rendered="#{vendaController.venda.vendaPrazo}" />
				<h:inputText value="#{vendaController.venda.desconto}" size="12"
					rendered="#{vendaController.venda.vendaPrazo}" maxlength="8"
					required="true" requiredMessage="Campo Desconto obrigatório!"
					converterMessage="Campo Desconto inválido"
					onkeyup="mascara(this,moeda);">
					<a4j:support event="onchange"
						action="#{vendaController.calcularFinanciamento}"
						ajaxSingle="true"
						reRender="valorPrestCampo, valorTPrazoCampo,  valorFDesconto" />
				</h:inputText>


				<h:outputText value="Desconto:"
					rendered="#{vendaController.venda.vendaVista}" />
				<h:inputText value="#{vendaController.venda.desconto}" size="12"
					rendered="#{vendaController.venda.vendaVista}" maxlength="8"
					required="true" requiredMessage="Campo Desconto obrigatório!"
					converterMessage="Campo Desconto inválido"
					onkeyup="mascara(this,moeda);">
					<f:selectItem itemLabel="À VISTA" itemValue="VISTA" />
					<a4j:support event="onchange"
						action="#{vendaController.calcularDesconto}" ajaxSingle="true"
						reRender="valorFinal" />
				</h:inputText>

				<h:outputText value="Valor Final: "
					rendered="#{vendaController.venda.vendaVista}" />
				<h:outputText id="valorFinal"
					rendered="#{vendaController.venda.vendaVista}"
					value="#{vendaController.venda.valorTotal}">
					<f:convertNumber maxFractionDigits="2" minFractionDigits="2" />
				</h:outputText>

				<h:outputText value="Valor Com Desconto: "
					rendered="#{vendaController.venda.vendaPrazo}" />
				<h:outputText id="valorFDesconto"
					rendered="#{vendaController.venda.vendaPrazo}"
					value="#{vendaController.venda.valorTotal}">
					<f:convertNumber maxFractionDigits="2" minFractionDigits="2" />
				</h:outputText>


				<h:outputText id="entradaTexto" value="Valor da Entrada: "
					rendered="#{vendaController.venda.vendaPrazo}" />
				<h:inputText id="entradaCampo"
					value="#{vendaController.venda.valorEntrada}" size="12"
					maxlength="13" rendered="#{vendaController.venda.vendaPrazo}"
					required="true" requiredMessage="Campo Valor da Entrada obrigatório!"
					converterMessage="Campo Valor da Entrada inválido"
					onkeyup="mascara(this,moeda);">
					<a4j:support event="onchange"
						action="#{vendaController.calcularFinanciamento}"
						ajaxSingle="true"
						reRender="valorPrestCampo, valorFDesconto, valorTPrazoCampo" />
				</h:inputText>

				<h:outputText id="jurosTexto" value="Taxa de Juros: "
					rendered="#{vendaController.venda.vendaPrazo}" />
				<h:inputText id="jurosCampo"
					value="#{vendaController.venda.taxaJuros}" size="6"
					rendered="#{vendaController.venda.vendaPrazo}" maxlength="6"
					required="true" requiredMessage="Campo Taxa de Juros obrigatório!"
					converterMessage="Campo Taxa de Juros inválido"
					onkeyup="mascara(this,moeda);">
					<a4j:support event="onchange"
						action="#{vendaController.calcularFinanciamento}"
						ajaxSingle="true"
						reRender="valorPrestCampo, valorFDesconto, valorTPrazoCampo" />
				</h:inputText>

				<h:outputText id="qtdPrestTexto" value="Quantidade de Prestações: "
					rendered="#{vendaController.venda.vendaPrazo}" />
				<h:inputText id="qtdPrestCampo"
					rendered="#{vendaController.venda.vendaPrazo}"
					value="#{vendaController.venda.qtdePrestacoes}"
					onkeypress="mascara(this,soNumeros);" size="4" maxlength="2"
					required="true"
					requiredMessage="Campo Quantidade de Prestações obrigatório!">
					<a4j:support event="onchange"
						action="#{vendaController.calcularFinanciamento}"
						ajaxSingle="true"
						reRender="valorPrestCampo, valorFDesconto, valorTPrazoCampo" />
				</h:inputText>

				<h:outputText id="valorPrestTexto" value="Valor da Prestação: "
					rendered="#{vendaController.venda.vendaPrazo}" />
				<h:outputText id="valorPrestCampo"
					rendered="#{vendaController.venda.vendaPrazo}"
					value="#{vendaController.venda.valorPrestacao}">
					<f:convertNumber maxFractionDigits="2" minFractionDigits="2" />
				</h:outputText>

				<h:outputText value="Valor Total a Prazo: " id="valorTPrazoTexto"
					rendered="#{vendaController.venda.vendaPrazo}" />
				<h:outputText id="valorTPrazoCampo"
					rendered="#{vendaController.venda.vendaPrazo}"
					value="#{vendaController.venda.valorPrazo}">
					<f:convertNumber maxFractionDigits="2" minFractionDigits="2" />
				</h:outputText>

			</h:panelGrid>
			<a4j:commandButton value="Salvar" action="#{vendaController.salvar}"
				reRender="form" />
		</rich:panel>
	</a4j:form></center>
</f:view>
</body>
</html>