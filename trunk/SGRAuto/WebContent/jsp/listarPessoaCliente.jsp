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
			style="width: 980px;  position: absolute; left: 190px; top: auto;">
			<rich:messages layout="list">
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>
			<rich:dataTable id="listaPessoa"
				value="#{pessoaController.pessoaList}" var="list"
				width="900px" columnClasses="center" rows="10" reRender="ds">
				<rich:column sortBy="#{list.nome}" filterBy="#{list.nome}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Nome" />
					</f:facet>
					<h:outputText value="#{list.nome}" />
				</rich:column>
				<rich:column sortBy="#{list.rg}" filterBy="#{list.rg}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="RG" />
					</f:facet>
					<h:outputText value="#{list.rg}" />
				</rich:column>
				<rich:column sortBy="#{list.cpf}" filterBy="#{list.cpf}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="CPF" />
					</f:facet>
					<h:outputText value="#{list.cpf}" />
				</rich:column>
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Telefone" />
					</f:facet>
					<h:outputText value="#{list.telefone}" />
				</rich:column>
				<rich:column sortBy="#{list.filial.razaoSocial}" filterBy="#{list.filial.razaoSocial}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Filial" />
					</f:facet>
					<h:outputText value="#{list.filial.razaoSocial}" />
				</rich:column>
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Editar" />
					</f:facet>
					<a4j:commandLink action="#{pessoaController.carregarCliente}"
						reRender="listaPessoa" ajaxSingle="true">
						<h:graphicImage value="../images/edit.gif" style="border:0"
							width="20" height="18" id="editar"/>
						<f:setPropertyActionListener value="#{list.codigo}"
							target="#{pessoaController.pessoa.codigo}" />
					</a4j:commandLink>
					<rich:toolTip for="editar" value="Editar" />
				</rich:column>
				<f:facet name="footer">
					<rich:datascroller id="ds"></rich:datascroller>
				</f:facet>
			</rich:dataTable>
		</rich:panel>
	</a4j:form></center>
</f:view>
</body>
</html>