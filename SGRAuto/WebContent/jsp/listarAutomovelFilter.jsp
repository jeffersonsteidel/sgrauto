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
		<rich:panel header="Relatório de Automóveis em Estoque"
			style="width: 1100px; position: absolute; left: 10px; top: auto;">
			<div id="non-printable"><rich:messages layout="list"
				errorLabelClass="errorLabel" infoLabelClass="infoLabel">
				<f:facet name="infoMarker">
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages> <h:panelGrid columns="10">
				<h:outputText value="Filial: " />
				<h:selectOneMenu
					value="#{automovelController.automovel.filial.codigo}">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{automovelController.filiais}" />
				</h:selectOneMenu>

				<h:outputText value="Ano entre: " />
				<h:inputText onkeypress="mascara(this,soNumeros);"
					value="#{automovelController.anoInicial}" size="8"
					maxlength="4" validatorMessage="Campo Ano deve ter 4 caracteres!">
					<f:validateLength minimum="4" />
				</h:inputText>
				<h:outputText value="a " />
				<h:inputText onkeypress="mascara(this,soNumeros);"
					value="#{automovelController.anoFinal}" size="8"
					maxlength="4" validatorMessage="Campo Ano deve ter 4 caracteres!">
					<f:validateLength minimum="4" />
				</h:inputText>

				<h:outputText value="Valor de Venda: " />
				<h:inputText
					value="#{automovelController.precoVendaInicial}"
					size="15" maxlength="13"
					converterMessage="Campo Valor de Venda inválido"
					onkeyup="mascara(this,moeda);">
				</h:inputText>
				<h:outputText value="a: " />
				<h:inputText
					value="#{automovelController.precoVendaFinal}" size="15"
					maxlength="13" converterMessage="Campo Valor de Venda inválido"
					onkeyup="mascara(this,moeda);">
				</h:inputText>

				<h:outputText value="Data Entrada: " />
				<h:panelGrid columns="3">
				<rich:calendar
					value="#{automovelController.dataEntradaInicial}"
					locale="" popup="true" datePattern="dd/MM/yyyy" showApplyButton="#"
					cellWidth="16px" cellHeight="22px" style="width:200px" />
				<h:outputText value="a" />
				<rich:calendar
					value="#{automovelController.dataEntradaFinal}" locale=""
					popup="true" datePattern="dd/MM/yyyy" showApplyButton="#"
					cellWidth="16px" cellHeight="22px" style="width:200px" />
				</h:panelGrid>	
			</h:panelGrid> <a4j:commandButton value="Pesquisar"
				action="#{automovelController.listarPorFiltro}" reRender="form" /></div>

			<h:panelGrid
				rendered="#{not empty automovelController.automovelList}">
				<div id="printable"><rich:dataTable id="listaAutomovel"
					value="#{automovelController.automovelList}" var="list"
					width="980px" columnClasses="center">
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Placa" />
						</f:facet>
						<h:outputText value="#{list.placa}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Marca" />
						</f:facet>
						<h:outputText value="#{list.modelo.marca.descricao}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Modelo" />
						</f:facet>
						<h:outputText value="#{list.modelo.descricao} - #{list.descricao}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Ano / Ano Modelo" />
						</f:facet>
						<h:outputText value="#{list.ano} - #{list.anoModelo}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Combustível" />
						</f:facet>
						<h:outputText value="#{list.combustivel}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Cor" />
						</f:facet>
						<h:outputText value="#{list.cor}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Valor" />
						</f:facet>
						<h:outputText value="#{list.valorDeVenda}">
							<f:convertNumber pattern="########.00" />
						</h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Filial" />
						</f:facet>
						<h:outputText value="#{list.filial.razaoSocial}" />
					</rich:column>
				</rich:dataTable></div>
				<h:panelGroup id="buttonRelatorio">
					<center><a4j:commandButton value="Gerar Relatório"
						onclick="imprimir();" reRender="form" /></center>
				</h:panelGroup>
			</h:panelGrid>
		</rich:panel>
	</a4j:form></center>
</f:view>
</body>
</html>