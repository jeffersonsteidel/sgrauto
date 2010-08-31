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
		<rich:panel header="Automóvel"
			style="width: 670px;  position: absolute; left: 350px; top: auto;">

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
				<h:outputText value="Placa: " />
				<h:inputText value="#{automovelController.automovel.placa}"
					size="10" maxlength="8" required="true"
					requiredMessage="Campo Placa obrigatório!"
					validatorMessage="Campo Placa deve ter 8 caracteres!">
					<f:validateLength minimum="8" />
				</h:inputText>
				<h:outputText value="Marca: " />
				<h:selectOneMenu
					value="#{automovelController.automovel.modelo.marca.codigo}"
					required="true" requiredMessage="Campo Marca obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{automovelController.marcas}" />
					<a4j:support event="onchange"
						action="#{automovelController.listarModelos}" ajaxSingle="true"
						reRender="modelos" />
				</h:selectOneMenu>
				<h:outputText value="Modelo: " />
				<h:selectOneMenu id="modelos"
					value="#{automovelController.automovel.modelo.codigo}"
					required="true" requiredMessage="Campo Modelo obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{automovelController.modelos}" />
				</h:selectOneMenu>
				<h:outputText value="Filial: " />
				<h:selectOneMenu
					value="#{automovelController.automovel.filial.codigo}"
					required="true" requiredMessage="Campo Filial obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItems value="#{automovelController.filiais}" />
				</h:selectOneMenu>
				<h:outputText value="Descrição: " />
				<h:inputText id="descricao"
					value="#{automovelController.automovel.descricao}" size="30"
					maxlength="40" required="true"
					requiredMessage="Campo Descrição obrigatório!">
					<rich:toolTip for="descricao" value="versão-motor-válvulas-portas" />
				</h:inputText>
				<h:outputText value="Chassi: " />
				<h:inputText value="#{automovelController.automovel.chassi}"
					size="30" maxlength="100"></h:inputText>
				<h:outputText value="Ano: " />
				<h:inputText onkeypress="mascara(this,soNumeros);"
					value="#{automovelController.automovel.ano}" size="8" maxlength="4"
					required="true" requiredMessage="Campo Ano obrigatório!"
					validatorMessage="Campo Ano deve ter 4 caracteres!">
					<f:validateLength minimum="4" />
				</h:inputText>
				<h:outputText value="Cor: " />
				<h:inputText value="#{automovelController.automovel.cor}" size="30"
					maxlength="60" required="true"
					requiredMessage="Campo Cor obrigatório!"></h:inputText>
				<h:outputText value="Ano Modelo: " />
				<h:inputText onkeypress="mascara(this,soNumeros);"
					value="#{automovelController.automovel.anoModelo}" size="8"
					maxlength="4" required="true"
					requiredMessage="Campo Ano Modelo obrigatório!"
					validatorMessage="Campo Ano Modelo deve ter 4 caracteres!">
					<f:validateLength minimum="4" />
				</h:inputText>
				<h:outputText value="Combustível: " />
				<h:selectOneMenu
					value="#{automovelController.automovel.combustivel}"
					required="true" requiredMessage="Campo Combustível obrigatório!">
					<f:selectItem itemLabel="SELECIONE" itemValue="" />
					<f:selectItem itemLabel="DIESEL" itemValue="DIESEL" />
					<f:selectItem itemLabel="ETANOL" itemValue="ETANOL" />
					<f:selectItem itemLabel="FLEX" itemValue="FLEX" />
					<f:selectItem itemLabel="GASOLINA" itemValue="GASOLINA" />
				</h:selectOneMenu>
				<h:outputText value="Km Rodado: " />
				<h:inputText maxlength="7"
					value="#{automovelController.automovel.kmRodados}" size="8"
					onkeypress="mascara(this,soNumeros);"></h:inputText>
				<h:outputText value="Valor de Venda: " />
				<h:inputText value="#{automovelController.automovel.valorDeVenda}"
					size="15" maxlength="13" required="true"
					converterMessage="Campo Valor de Venda inválido"
					requiredMessage="Campo Valor de Venda obrigatório!"
					onkeyup="mascara(this,moeda);">
				</h:inputText>
			</h:panelGrid>
			<h:panelGrid columns="8">
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.kitGas}"></h:selectBooleanCheckbox>
				<h:outputText value="Kit Gás. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.arCondicionado}"></h:selectBooleanCheckbox>
				<h:outputText value="Ar Condicionado. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.arQuente}"></h:selectBooleanCheckbox>
				<h:outputText value="Ar Quente. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.arFrio}"></h:selectBooleanCheckbox>
				<h:outputText value="Ar Frio. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.vidroEletrico}"></h:selectBooleanCheckbox>
				<h:outputText value="Vidro Elétrico. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.vidroVerde}"></h:selectBooleanCheckbox>
				<h:outputText value="Vidro Verde. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.isulfilm}"></h:selectBooleanCheckbox>
				<h:outputText value="Isulfilm. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.airBag}"></h:selectBooleanCheckbox>
				<h:outputText value="Air Bag. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.abs}"></h:selectBooleanCheckbox>
				<h:outputText value="ABS. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.travaEletrica}"></h:selectBooleanCheckbox>
				<h:outputText value="Trava Elétrica. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.alarme}"></h:selectBooleanCheckbox>
				<h:outputText value="Alarme. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.cdPlayer}"></h:selectBooleanCheckbox>
				<h:outputText value="CD Player. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.desembacador}"></h:selectBooleanCheckbox>
				<h:outputText value="Desembaçador Traseiro. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.limpadorTraseiro}"></h:selectBooleanCheckbox>
				<h:outputText value="Limpador Traseiro. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.direcaoHidraulica}"></h:selectBooleanCheckbox>
				<h:outputText value="Direção Hidráulica. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.rodaLigaLeve}"></h:selectBooleanCheckbox>
				<h:outputText value="Roda de Liga Leve. " />
				<h:selectBooleanCheckbox
					value="#{automovelController.automovel.cambioAutomatico}"></h:selectBooleanCheckbox>
				<h:outputText value="Câmbio Autmomático. " />
			</h:panelGrid>
			<h:panelGrid columns="2">
				<h:outputText value="Outros: " />
				<h:inputTextarea value="#{automovelController.automovel.outros}"
					rows="4" cols="30"></h:inputTextarea>
			</h:panelGrid>
			<h:panelGrid columns="4">
				<h:outputText value="Valor de Compra: " />
				<h:inputText value="#{automovelController.automovel.valorDeCompra}"
					size="15" maxlength="13" required="true"
					converterMessage="Campo Valor de Compra inválido"
					onkeyup="mascara(this,moeda);" 
					requiredMessage="Campo Valor de Compra obrigatório!">
				</h:inputText>
				<h:outputText value="Data Entrada: " />
				<rich:calendar value="#{automovelController.automovel.dataEntrada}"
					locale="pt_BR" popup="true" datePattern="dd/MM/yyyy"
					showApplyButton="#" cellWidth="24px" cellHeight="22px"
					style="width:200px" required="true"
					requiredMessage="Campo Data Entrada obrigatório!" />
				<h:outputText value="Data Saída: "
					rendered="#{automovelController.automovel.dataSaida != null}" />
				<rich:calendar value="#{automovelController.automovel.dataSaida}"
					locale="pt_BR" popup="true" datePattern="dd/MM/yyyy"
					showApplyButton="#" cellWidth="24px" cellHeight="22px"
					style="width:200px" required="true"
					requiredMessage="Campo Data Saída obrigatório!"
					rendered="#{automovelController.automovel.dataSaida != null}" />
			</h:panelGrid>
			<a4j:commandButton value="Salvar"
				action="#{automovelController.salvar}" reRender="form" />
		</rich:panel>
	</a4j:form></center>
</f:view>
</body>
</html>