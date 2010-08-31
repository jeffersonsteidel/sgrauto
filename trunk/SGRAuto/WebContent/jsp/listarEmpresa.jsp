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
			style="width: 750px;  position: absolute; left: 300px; top: auto;">
			<rich:messages layout="list">
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>
			<rich:dataTable id="listaEmpresa"
				value="#{empresaController.empresaList}" var="list"
				width="700px" columnClasses="center" rows="10" reRender="ds">
				<rich:column sortBy="#{list.cnpj}" filterBy="#{list.cnpj}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="CNPJ" />
					</f:facet>
					<h:outputText value="#{list.cnpj}" />
				</rich:column>
				<rich:column sortBy="#{list.razaoSocial}" filterBy="#{list.razaoSocial}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Razão Social" />
					</f:facet>
					<h:outputText value="#{list.razaoSocial}" />
				</rich:column>
				<rich:column sortBy="#{list.tipoEmpresa}" filterBy="#{list.tipoEmpresa}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Tipo Empresa" />
					</f:facet>
					<h:outputText value="#{list.tipoEmpresa}" />
				</rich:column>
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Telefone" />
					</f:facet>
					<h:outputText value="#{list.telefone}" />
				</rich:column>
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Editar" />
					</f:facet>
					<a4j:commandLink action="#{empresaController.carregar}"
						reRender="listaEmpresa" ajaxSingle="true">
						<h:graphicImage value="../images/edit.gif" style="border:0"
							width="20" height="18" id="editar"/>
						<f:setPropertyActionListener value="#{list.codigo}"
							target="#{empresaController.empresa.codigo}" />
					</a4j:commandLink>
					<rich:toolTip for="editar" value="Editar" />
				</rich:column>
				<rich:column sortBy="#{list.indDesat}" rendered="#{acessoController.administrador}">
					<f:facet name="header">
						<h:outputText value="Ativado" />
					</f:facet>
					<a4j:commandLink action="#{empresaController.desativar}"
						rendered="#{!list.indDesat}" reRender="form" ajaxSingle="true">
						<h:graphicImage id="ativar" value="../images/ativar.gif"
							style="border:0" width="20" height="18" />
						<f:setPropertyActionListener value="#{list.codigo}"
							target="#{empresaController.empresa.codigo}" />
					</a4j:commandLink>
					<rich:toolTip for="ativar" value="Desativar" />
					<a4j:commandLink action="#{empresaController.ativar}"
						rendered="#{list.indDesat}" reRender="form" ajaxSingle="true">
						<h:graphicImage id="desativar" value="../images/desativar.gif"
							style="border:0" width="20" height="18" />
						<f:setPropertyActionListener value="#{list.codigo}"
							target="#{empresaController.empresa.codigo}" />
					</a4j:commandLink>
					<rich:toolTip for="desativar" value="Ativar" />
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