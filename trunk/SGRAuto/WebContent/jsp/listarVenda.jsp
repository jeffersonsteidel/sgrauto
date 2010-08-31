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
			style="width: 1300px;  position: absolute; left: 30px; top: auto;">
			<rich:messages layout="list" errorLabelClass="errorLabel"
				infoLabelClass="infoLabel">
				<f:facet name="infoMarker">
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>
			<rich:dataTable id="listaVenda"
				value="#{vendaController.vendaList}" var="list" width="1200px"
				columnClasses="center" rows="7" reRender="ds">
				<rich:column sortBy="#{list.automovel.placa}"
					filterBy="#{list.automovel.placa}" filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Placa" />
					</f:facet>
					<h:outputText value="#{list.automovel.placa}" />
				</rich:column>
				<rich:column sortBy="#{list.automovel.modelo.marca.descricao}"
					filterBy="#{list.automovel.modelo.marca.descricao}" filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Marca" />
					</f:facet>
					<h:outputText value="#{list.automovel.modelo.marca.descricao}" />
				</rich:column>
				<rich:column sortBy="#{list.automovel.modelo.descricao}"
					filterBy="#{list.automovel.modelo.descricao}" filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Modelo" />
					</f:facet>
					<h:outputText
						value="#{list.automovel.modelo.descricao} - #{list.automovel.descricao}" />
				</rich:column>
				<rich:column sortBy="#{list.filial.razaoSocial}"
					filterBy="#{list.filial.razaoSocial}" filterEvent="onkeyup" width="450px">
					<f:facet name="header">
						<h:outputText value="Filial" />
					</f:facet>
					<h:outputText value="#{list.filial.razaoSocial}" />
				</rich:column>
				<rich:column sortBy="#{list.funcionario.nome}"
					filterBy="#{list.funcionario.nome}" filterEvent="onkeyup" width="450px">
					<f:facet name="header">
						<h:outputText value="Vendedor" />
					</f:facet>
					<h:outputText value="#{list.funcionario.nome}" />
				</rich:column>
				<rich:column sortBy="#{list.cliente.nome}"
					filterBy="#{list.cliente.nome}" filterEvent="onkeyup" width="450px">
					<f:facet name="header">
						<h:outputText value="Cliente" />
					</f:facet>
					<h:outputText value="#{list.cliente.nome}" />
				</rich:column>
				<rich:column sortBy="#{list.data}" >
					<f:facet name="header">
						<h:outputText value="Data">
						</h:outputText>
					</f:facet>
					<h:outputText value="#{list.data}">
						<f:convertDateTime locale="pt_BR" pattern="dd/MM/yyyy" />
					</h:outputText>
				</rich:column>
				<rich:column sortBy="#{list.valorVista}" >
					<f:facet name="header">
						<h:outputText value="Valor" />
					</f:facet>
					<h:outputText value="#{list.valorVista}">
						<f:convertNumber pattern="########.00" />
					</h:outputText>
				</rich:column>
				<rich:column sortBy="#{list.valorVista - list.desconto}">
					<f:facet name="header">
						<h:outputText value="Valor Com Desconto">
							<f:convertNumber pattern="########.00" />
						</h:outputText>
					</f:facet>
					<h:outputText value="#{list.valorVista - list.desconto}">
						<f:convertNumber pattern="########.00" />
					</h:outputText>
				</rich:column>
				<rich:column rendered="#{acessoController.administrador}">
					<f:facet name="header">
						<h:outputText value="Editar" />
					</f:facet>
					<a4j:commandLink action="#{vendaController.carregar}"
						reRender="listaMarca" ajaxSingle="true">
						<h:graphicImage value="../images/edit.gif" style="border:0"
							id="editar" />
						<f:setPropertyActionListener value="#{list.codigo}"
							target="#{vendaController.venda.codigo}" />
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
