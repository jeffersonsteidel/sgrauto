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
		<rich:panel header="Empresa"
			style="width: 515px; position: absolute; left: 425px; top: auto;">
			<rich:messages layout="list" errorLabelClass="errorLabel"
				infoLabelClass="infoLabel">
				<f:facet name="infoMarker">
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>

			<h:panelGrid columns="2">
				<h:outputText value="CNPJ: " />
				<h:inputText id="cnpj" value="#{empresaController.empresa.cnpj}"
					size="20" maxlength="18" required="true"
					requiredMessage="Campo CNPJ obrigatório!"
					validatorMessage="Campo CNPJ deve ter 18 caracteres!"
					onkeypress="mascara(this,cnpj)">
					<f:validateLength minimum="18" />
				</h:inputText>
				<h:outputText value="Razão Social: " />
				<h:inputText value="#{empresaController.empresa.razaoSocial}"
					size="50" maxlength="100" required="true"
					requiredMessage="Campo Razão Social obrigatório!">
				</h:inputText>
				<h:outputText value="Nome Fantasia: " />
				<h:inputText value="#{empresaController.empresa.nomeFantasia}"
					size="50" maxlength="80">
				</h:inputText>
				<h:outputText value="Tipo Empresa: " />
				<h:selectOneMenu required="true"
					value="#{empresaController.empresa.tipoEmpresa}"
					requiredMessage="Campo Tipo Empresa obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItem itemLabel="AUTO PEÇAS" itemValue="AUTO PEÇAS" />
					<f:selectItem itemLabel="DESPACHANTE" itemValue="DESPACHANTE" />
					<f:selectItem itemLabel="FINANCEIRA" itemValue="FINANCEIRA" />
					<f:selectItem itemLabel="FILIAL" itemValue="FILIAL" />
					<f:selectItem itemLabel="LAVA CAR" itemValue="LAVA CAR" />
					<f:selectItem itemLabel="OFICINA MECANICA" itemValue="OFICINA MECANICA" />
					<f:selectItem itemLabel="OUTROS" itemValue="OUTROS" />
				</h:selectOneMenu>
				<h:outputText value="Estado: " />
				<h:selectOneMenu required="true"
					value="#{empresaController.empresa.cidade.estado.codigo}"
					requiredMessage="Campo Estado obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{empresaController.estados}" />
					<a4j:support event="onchange"
						action="#{empresaController.listarCidades}" ajaxSingle="true"
						reRender="cidades" />
				</h:selectOneMenu>
				<h:outputText value="Cidade: " />
				<h:selectOneMenu required="true" id="cidades"
					value="#{empresaController.empresa.cidade.codigo}"
					requiredMessage="Campo Cidade obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{empresaController.cidades}" />
				</h:selectOneMenu>
				<h:outputText value="Endereço: " />
				<h:inputText value="#{empresaController.empresa.endereco}" size="50"
					maxlength="200"></h:inputText>
				<h:outputText value="Bairro: " />
				<h:inputText value="#{empresaController.empresa.bairro}" size="35"
					maxlength="60"></h:inputText>
				<h:outputText value="CEP: " />
				<h:inputText value="#{empresaController.empresa.cep}" size="18"
					maxlength="9" validatorMessage="Campo CEP deve ter 9 caracteres!"
					onkeypress="mascara(this,cep);">
					<f:validateLength minimum="9" />
				</h:inputText>
				<h:outputText value="Contato: " />
				<h:inputText value="#{empresaController.empresa.contato}" size="50"
					maxlength="100"></h:inputText>
				<h:outputText value="Telefone: " />
				<h:inputText value="#{empresaController.empresa.telefone}" size="18"
					maxlength="13" required="true"
					requiredMessage="Campo Telefone obrigatório!"
					validatorMessage="Campo Telefone deve ter 13 caracteres!"
					onkeypress="mascara(this,telefone);">
					<f:validateLength minimum="13" />
				</h:inputText>
			</h:panelGrid>
			<a4j:commandButton value="Salvar"
				action="#{empresaController.salvar}" reRender="form" />
		</rich:panel>
	</a4j:form></center>
</f:view>
</body>
</html>