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
		<rich:panel header="Pessoa - Cliente"
			style="width: 680px; position: absolute; left: 345px; top: auto;">
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
				<h:outputText value="Nome: " />
				<h:inputText value="#{pessoaController.pessoa.nome}" size="50"
					maxlength="100" required="true"
					requiredMessage="Campo Nome obrigatório!"></h:inputText>
				<h:outputText value="RG: " />
				<h:inputText value="#{pessoaController.pessoa.rg}" size="16"
					maxlength="11" required="true"
					requiredMessage="Campo RG obrigatório!"></h:inputText>
				<h:outputText value="CPF: " />
				<h:inputText id="cpf" value="#{pessoaController.pessoa.cpf}"
					size="20" maxlength="14" required="true"
					requiredMessage="Campo CPF obrigatório!"
					validatorMessage="Campo CPF deve ter 14 caracteres!"
					onkeypress="mascara(this,cpf);">
					<f:validateLength minimum="14" />
				</h:inputText>
				<h:outputText value="CNH: " />
				<h:inputText value="#{pessoaController.pessoa.cnh}" size="20"
					maxlength="20"></h:inputText>
				<h:outputText value="Data Nascimento: " />
				<rich:calendar value="#{pessoaController.pessoa.dataNascimento}"
					locale="" popup="true" datePattern="dd/MM/yyyy" showApplyButton="#"
					cellWidth="24px" cellHeight="22px" style="width:200px"
					required="true"
					requiredMessage="Campo Data Nascimento obrigatório!" />
				<h:outputText value="Sexo: " />
				<h:selectOneMenu value="#{pessoaController.pessoa.sexo}"
					required="true" requiredMessage="Campo Sexo obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItem itemLabel="FEMININO" itemValue="F" />
					<f:selectItem itemLabel="MASCULINO" itemValue="M" />
				</h:selectOneMenu>
				<h:outputText value="Cargo: " />
				<h:inputText value="#{pessoaController.pessoa.cargo}" size="40"
					maxlength="60" required="true"
					requiredMessage="Campo Cargo obrigatório!"></h:inputText>
				<h:outputText value="Remuneração: " />
				<h:inputText converterMessage="Campo Remuneração inválido"
					onkeyup="mascara(this,moeda);"
					value="#{pessoaController.pessoa.remuneracao}" size="20"
					maxlength="13"></h:inputText>
				<h:outputText value="Estado: " />
				<h:selectOneMenu required="true"
					value="#{pessoaController.pessoa.cidade.estado.codigo}"
					requiredMessage="Campo Estado obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{pessoaController.estados}" />
					<a4j:support event="onchange"
						action="#{pessoaController.listarCidades}" ajaxSingle="true"
						reRender="cidades" />
				</h:selectOneMenu>
				<h:outputText value="Cidade: " />
				<h:selectOneMenu required="true" id="cidades"
					value="#{pessoaController.pessoa.cidade.codigo}"
					requiredMessage="Campo Cidade obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{pessoaController.cidades}" />
				</h:selectOneMenu>
				<h:outputText value="Endereço: " />
				<h:inputText value="#{pessoaController.pessoa.endereco}" size="50"
					required="true" requiredMessage="Campo Endereço obrigatório!"
					maxlength="200"></h:inputText>
				<h:outputText value="Bairro: " />
				<h:inputText value="#{pessoaController.pessoa.bairro}" size="30"
					required="true" requiredMessage="Campo Bairro obrigatório!"
					maxlength="60"></h:inputText>
				<h:outputText value="CEP: " />
				<h:inputText value="#{pessoaController.pessoa.cep}" size="18"
					required="true" requiredMessage="Campo CEP obrigatório!"
					maxlength="9" validatorMessage="Campo CEP deve ter 9 caracteres!"
					onkeypress="mascara(this,cep);">
					<f:validateLength minimum="9" />
				</h:inputText>
				<h:outputText value="Telefone: " />
				<h:inputText required="true"
					value="#{pessoaController.pessoa.telefone}" size="18"
					maxlength="13"
					validatorMessage="Campo Telefone deve ter 13 caracteres!"
					onkeypress="mascara(this,telefone);"
					requiredMessage="Campo Telefone obrigatório!">
					<f:validateLength minimum="13" />
				</h:inputText>
				<h:outputText value="E-mail: " />
				<h:inputText value="#{pessoaController.pessoa.email}" size="50"
					maxlength="100"></h:inputText>
				<h:outputText value="Celular: " />
				<h:inputText value="#{pessoaController.pessoa.celular}" size="18"
					maxlength="13"
					validatorMessage="Campo Celular deve ter 13 caracteres!"
					onkeypress="mascara(this,telefone);">
					<f:validateLength minimum="13" />
				</h:inputText>
				<h:outputText value="Filial: " />
				<h:selectOneMenu required="true"
					value="#{pessoaController.pessoa.filial.codigo}"
					requiredMessage="Campo Filial obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{pessoaController.filiais}" />
				</h:selectOneMenu>
			</h:panelGrid>
			<a4j:commandButton value="Salvar" action="#{pessoaController.salvar}"
				reRender="form" />
		</rich:panel>
	</a4j:form></center>
</f:view>
</body>
</html>