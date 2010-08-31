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
		<rich:panel header="Trocar Senha"
			style="width: 320px; position: absolute; left: 520px; top: auto; ">
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
				<h:outputText value="Senha Atual: " />
				<h:inputSecret value="#{acessoController.senhaAtual}" size="20" maxlength="12"
				required="true" requiredMessage="Campo Senha Atual obrigatório!"></h:inputSecret>
				<h:outputText value="Nova Senha: " />
				<h:inputSecret value="#{acessoController.novaSenha}"  size="20" maxlength="12"
				required="true" requiredMessage="Campo Nova Senha obrigatório!"></h:inputSecret>
				<h:outputText value="Confirmar Nova Senha: " />
				<h:inputSecret value="#{acessoController.confirmaSenha}"  size="20" maxlength="12"
				required="true" requiredMessage="Campo Confirmar Nova Senha obrigatório!"></h:inputSecret>
			</h:panelGrid>
			<a4j:commandButton value="Salvar" action="#{acessoController.trocarSenha}" reRender="form" />
		</rich:panel>
	</a4j:form></center>
</f:view>
</body>
</html>