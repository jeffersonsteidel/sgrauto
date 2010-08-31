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
		<rich:panel header="Estado"
			style="width: 570px;  position: absolute; left: 400px; top: auto;">
			<rich:messages layout="list" errorLabelClass="errorLabel"
				infoLabelClass="infoLabel">
				<f:facet name="infoMarker">
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>
				<rich:dataTable id="listaEstado" 
					value="#{estadoController.estadoList}" var="list"
					width="450px" columnClasses="center" rows="10" reRender="ds">
					<rich:column sortBy="#{list.descricao}"
						filterBy="#{list.descricao}" filterEvent="onkeyup">
						<f:facet name="header">
							<h:outputText value="Estado" />
						</f:facet>
						<h:outputText value="#{list.descricao}" />
					</rich:column>
					<rich:column sortBy="#{list.uf}" filterBy="#{list.uf}"
						filterEvent="onkeyup">
						<f:facet name="header">
							<h:outputText value="UF" />
						</f:facet>
						<h:outputText value="#{list.uf}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Editar" />
						</f:facet>
						<a4j:commandLink action="#{estadoController.carregar}"
							ajaxSingle="true" id="editar">
							<h:graphicImage value="../images/edit.gif" style="border:0" />
							<f:setPropertyActionListener value="#{list.codigo}"
								target="#{estadoController.estado.codigo}" />
						</a4j:commandLink>
						<rich:toolTip for="editar" value="Editar" />
					</rich:column>
					<rich:column rendered="#{acessoController.administrador}"> 
						<f:facet name="header">
							<h:outputText value="Excluir" />
						</f:facet>
						<a4j:commandLink ajaxSingle="true" id="delete"
							oncomplete="#{rich:component('deletePanel')}.show()">
							<h:graphicImage id="excluir" value="../images/delete.gif"
								style="border:0" />
							<f:setPropertyActionListener value="#{list.codigo}"
								target="#{estadoController.estado.codigo}" />
						</a4j:commandLink>
						<rich:toolTip for="excluir" value="Excluir" />
					</rich:column>
					<f:facet name="footer">
						<rich:datascroller id="ds"></rich:datascroller>
					</f:facet>
				</rich:dataTable>
		</rich:panel>
	</a4j:form> <rich:modalPanel id="deletePanel" autosized="true" width="200">
		<f:facet name="header">
			<h:outputText value="Deseja realmente deletar este item?"
				style="padding-right:15px;" />
		</f:facet>
		<h:form>
			<table width="100%">
				<tbody>
					<tr>
						<td align="center" width="50%"><a4j:commandButton value="Sim"
							ajaxSingle="true" action="#{estadoController.excluir}"
							oncomplete="#{rich:component('deletePanel')}.hide();"
							reRender="listaEstado, form" /></td>
						<td align="center" width="50%"><a4j:commandButton value="Não"
							onclick="#{rich:component('deletePanel')}.hide();return false;" />
						</td>
					</tr>
				</tbody>
			</table>
		</h:form>
	</rich:modalPanel></center>
</f:view>
</body>
</html>