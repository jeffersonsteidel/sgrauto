<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SGRAuto</title>
<style>
#printable {
	display: none;
}

@media print {
	#non-printable {
		display: none;
	}
	#printable {
		display: block;
	}
}
</style>
<script>
function imprimir(){
	window.print();
}
</script>
</head>
<body>
<f:view>
	<jsp:directive.include file="menus.jsp" />
	<center><a4j:form id="form">
		<rich:panel header="Relatório de Vendas"
			style="width: 1150px; position: absolute; left: 10px; top: auto;">
			<div id="non-printable"><rich:messages layout="list"
				errorLabelClass="errorLabel" infoLabelClass="infoLabel">
				<f:facet name="infoMarker">
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages> <h:panelGrid columns="8">
				<h:outputText value="Loja: " />
				<h:selectOneMenu value="#{vendaController.venda.filial.codigo}">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{vendaController.filiais}" />
					<a4j:support event="onchange"
						action="#{vendaController.listarVendedores}" ajaxSingle="true"
						reRender="vendedor, estado, cidade, cliente, clienteJuridico, automovel" />
				</h:selectOneMenu>

				<h:outputText value="Vendedor: " />
				<h:selectOneMenu id="vendedor"
					value="#{vendaController.venda.funcionario.codigo}">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{vendaController.vendedores}" />
				</h:selectOneMenu>

				<h:outputText value="Financeira: " />
				<h:selectOneMenu value="#{vendaController.venda.financeira.codigo}">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{vendaController.financeiras}" />
				</h:selectOneMenu>

				<h:outputText value="Forma de Pagamento: " />
				<h:selectOneMenu value="#{vendaController.formaPagamento}">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItem itemLabel="A PRAZO" itemValue="PRAZO" />
					<f:selectItem itemLabel="À VISTA" itemValue="VISTA" />
					<a4j:support event="onchange"
						action="#{vendaController.escolherFormaPagamento}"
						ajaxSingle="true" reRender="pagamento" />
				</h:selectOneMenu>

				<h:outputText value="Periodo: " />
				<rich:calendar value="#{vendaController.dataInicial}"
					locale="" popup="true" datePattern="dd/MM/yyyy" showApplyButton="#"
					cellWidth="16px" cellHeight="22px" style="width:200px" />
				<h:outputText value="a" />
				<rich:calendar value="#{vendaController.dataFinal}" locale=""
					popup="true" datePattern="dd/MM/yyyy" showApplyButton="#"
					cellWidth="16px" cellHeight="22px" style="width:200px" />
			</h:panelGrid> <a4j:commandButton value="Pesquisar"
				action="#{vendaController.listarPorFiltro}" reRender="form" /></div>

			<h:panelGrid rendered="#{not empty vendaController.vendaList}">
				<div id="printable"><rich:dataTable id="listaVenda"
					value="#{vendaController.vendaList}" var="list"
					width="1030px" columnClasses="center">
					<rich:column width="80px">
						<f:facet name="header">
							<h:outputText value="Placa" />
						</f:facet>
						<h:outputText value="#{list.automovel.placa}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Marca" />
						</f:facet>
						<h:outputText value="#{list.automovel.modelo.marca.descricao}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Modelo" />
						</f:facet>
						<h:outputText
							value="#{list.automovel.modelo.descricao} - #{list.automovel.descricao}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Filial" />
						</f:facet>
						<h:outputText value="#{list.filial.razaoSocial}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Vendedor" />
						</f:facet>
						<h:outputText value="#{list.funcionario.nome}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Cliente" />
						</f:facet>
						<h:outputText value="#{list.cliente.nome}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Data">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.data}">
							<f:convertDateTime pattern="dd/MM/yyyy" />
						</h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Valor" />
						</f:facet>
						<h:outputText value="#{list.valorVista}">
							<f:convertNumber pattern="########.00" />
						</h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Valor Com Desconto">
								<f:convertNumber pattern="########.00" />
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.valorVista - list.desconto}">
							<f:convertNumber pattern="########.00" />
						</h:outputText>
					</rich:column>
				</rich:dataTable></div>
				<h:panelGroup
					id="buttonRelatorio">
					<center><a4j:commandButton value="Gerar Relatório"
						onclick="imprimir();" reRender="form" /></center>
				</h:panelGroup>
			</h:panelGrid>
		</rich:panel>
	</a4j:form></center>
</f:view>
</body>
</html>