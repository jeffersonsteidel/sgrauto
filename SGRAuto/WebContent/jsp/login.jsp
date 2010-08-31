<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SGRAuto</title>
<link rel="StyleSheet" type="text/css" href="../css/messages-style.css"
	media="screen" />
</head>
<body>
<f:view>
	<center><h:form id="form">
		<rich:panel header="SGRAUTO - SISTEMA GERENCIADOR DE REVENDAS DE AUTOMÓVEIS" style="width: 500px; position: absolute; left: 430px; top: 220px">
			<rich:messages layout="list" errorLabelClass="errorLabel" infoLabelClass="infoLabel">
				<f:facet name="infoMarker" >
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>
			<h:panelGrid columns="2">
					<h:outputText value="Login: " />
					<h:inputText value="#{acessoController.pessoaLogada.login}" size="25" maxlength="20"
					required="true" requiredMessage="Campo Login obrigatório!"></h:inputText>
					<h:outputText value="Senha: " />
					<h:inputSecret value="#{acessoController.pessoaLogada.senha}" size="25" maxlength="12"
					required="true" requiredMessage="Campo Senha obrigatório!"></h:inputSecret>
			</h:panelGrid>
			<a4j:commandButton value="Entrar" type="submit" reRender="form" action="#{acessoController.logar}"/>
		</rich:panel>
	</h:form></center>
</f:view>
</body>
</html>