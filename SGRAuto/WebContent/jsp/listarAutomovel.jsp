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
		<rich:panel header="Automoveis #{automovelController.opcao}"
			style="width: 1320px;  position: absolute; left: 20px; top: auto;">
			<rich:messages layout="list" errorLabelClass="errorLabel"
				infoLabelClass="infoLabel">
				<f:facet name="infoMarker">
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>
			<a4j:status>
				<f:facet name="start">
					<h:graphicImage value="../images/ajax-loader.gif" />
				</f:facet>
			</a4j:status>
			<rich:dataTable id="listaAutomovel" 
				value="#{automovelController.automovelList}" var="list"
				width="980px" columnClasses="center" rows="10" reRender="ds">
				<rich:column sortBy="#{list.placa}" filterBy="#{list.placa}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Placa" />
					</f:facet>
					<h:outputText value="#{list.placa}" />
				</rich:column>
				<rich:column sortBy="#{list.modelo.marca.descricao}"
					filterBy="#{list.modelo.marca.descricao}" filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Marca" />
					</f:facet>
					<h:outputText value="#{list.modelo.marca.descricao}" />
				</rich:column>
				<rich:column sortBy="#{list.modelo.descricao}" width="10px"
					filterBy="#{list.modelo.descricao}" filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Modelo" />
					</f:facet>
					<h:outputText value="#{list.modelo.descricao} - #{list.descricao}" />
				</rich:column>
				<rich:column sortBy="#{list.ano}" filterBy="#{list.ano}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Ano / Ano Modelo" />
					</f:facet>
					<h:outputText value="#{list.ano} - #{list.anoModelo}" />
				</rich:column>
				<rich:column sortBy="#{list.combustivel}" filterBy="#{list.combustivel}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Combustível" />
					</f:facet>
					<h:outputText value="#{list.combustivel}" />
				</rich:column>
				<rich:column sortBy="#{list.cor}" filterBy="#{list.cor}"
					filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Cor" />
					</f:facet>
					<h:outputText value="#{list.cor}" />
				</rich:column>
				<rich:column sortBy="#{list.valorDeVenda}"
					filterBy="#{list.valorDeVenda}" filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Valor" />
					</f:facet>
					<h:outputText value="#{list.valorDeVenda}">
						<f:convertNumber pattern="########.00" />
					</h:outputText>
				</rich:column>
				<rich:column sortBy="#{list.filial.razaoSocial}"
					filterBy="#{list.filial.razaoSocial}" filterEvent="onkeyup">
					<f:facet name="header">
						<h:outputText value="Filial" />
					</f:facet>
					<h:outputText value="#{list.filial.razaoSocial}" />
				</rich:column>
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Fotos" />
					</f:facet>
					<a4j:commandLink action="#{automovelController.abrirFotos}"
						reRender="listaAutomovel" ajaxSingle="true" eventsQueue="foo">
						<h:graphicImage value="../images/fotos.jpg" style="border:0"
							width="20" height="18" id="fotos" />
						<f:setPropertyActionListener value="#{list.codigo}"
							target="#{automovelController.automovel.codigo}" />
					</a4j:commandLink>
					<rich:toolTip for="fotos" value="Ver Fotos" />
				</rich:column>
				<rich:column>
					<f:facet name="header">
						<h:outputText value="Editar" />
					</f:facet>
					<a4j:commandLink action="#{automovelController.carregar}"
						reRender="listaAutomovel" ajaxSingle="true">
						<h:graphicImage value="../images/edit.gif" style="border:0"
							width="20" height="18" id="editar" />
						<f:setPropertyActionListener value="#{list.codigo}"
							target="#{automovelController.automovel.codigo}" />
					</a4j:commandLink>
					<rich:toolTip for="editar" value="Editar" />
				</rich:column>
				<rich:column rendered="#{acessoController.administrador && automovelController.btExcluir}" >
					<f:facet name="header">
						<h:outputText value="Excluir" />
					</f:facet>
					<a4j:commandLink ajaxSingle="true" id="delete"
						oncomplete="#{rich:component('deletePanel')}.show()">
						<h:graphicImage id="excluir" value="../images/delete.gif"
							style="border:0" />
						<f:setPropertyActionListener value="#{list.codigo}"
							target="#{automovelController.automovel.codigo}" />
					</a4j:commandLink>
					<rich:toolTip for="excluir" value="Excluir" />
				</rich:column>
				<rich:column sortBy="#{list.indVendido}" rendered="#{automovelController.opcao == null}">
					<f:facet name="header">
						<h:outputText value="Vendido" />
					</f:facet>
					<a4j:commandLink rendered="#{!list.indVendido}" reRender="form"
						ajaxSingle="true">
						<h:graphicImage id="nvendido" value="../images/ico_martelo.gif"
							style="border:0" width="20" height="18" />
					</a4j:commandLink>
					<rich:toolTip for="nvendido" value="Em Estoque" />
					<a4j:commandLink rendered="#{list.indVendido}" reRender="form"
						ajaxSingle="true">
						<h:graphicImage id="vendido" value="../images/ico_vendido.gif"
							style="border:0" width="20" height="18" />
					</a4j:commandLink>
					<rich:toolTip for="vendido" value="Vendido" />
				</rich:column>
				<f:facet name="footer">
					<rich:datascroller id="ds"></rich:datascroller>
				</f:facet>
			</rich:dataTable>
		</rich:panel>
	</a4j:form>
	<rich:modalPanel autosized="true">
		
	</rich:modalPanel>
	<rich:modalPanel id="deletePanel" autosized="true" width="200">
		<f:facet name="header">
			<h:outputText value="Deseja realmente deletar este item?"
				style="padding-right:15px;" />
		</f:facet>
		<h:form>
			<table width="100%">
				<tbody>
					<tr>
						<td align="center" width="50%"><a4j:commandButton value="Sim"
							ajaxSingle="true" action="#{automovelController.excluir}"
							oncomplete="#{rich:component('deletePanel')}.hide();"
							reRender="listaAutomovel, form" /></td>
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