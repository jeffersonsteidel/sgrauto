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
	<a4j:form id="form">
		<center><rich:panel>
			<rich:messages layout="list" errorLabelClass="errorLabel"
				infoLabelClass="infoLabel">
				<f:facet name="infoMarker">
					<h:graphicImage value="../images/passed.gif" />
				</f:facet>
				<f:facet name="errorMarker">
					<h:graphicImage value="../images/error.gif" />
				</f:facet>
			</rich:messages>

			<h:panelGrid id="fotos">
				<rich:dataGrid value="#{automovelController.automovelFotosList}"
					var="file" rowKeyVar="row">
					<center><h:outputText
						value="Placa: #{automovelController.automovelFotos.automovel.placa} Marca: #{automovelController.automovelFotos.automovel.modelo.marca.descricao} Modelo: #{automovelController.automovelFotos.automovel.modelo.descricao} / #{automovelController.automovelFotos.automovel.descricao} Ano: #{automovelController.automovelFotos.automovel.ano} / #{automovelController.automovelFotos.automovel.anoModelo}" />
					</center>
					<h:panelGrid columns="1">
						<a4j:mediaOutput element="img"
							createContent="#{automovelController.paint}" value="#{row}"
							style="width:640px; height:480px; overflow:auto;"
							cacheable="false">
						</a4j:mediaOutput>
					</h:panelGrid>
					<center><h:panelGrid columns="1">
						<h:outputText />
						<a4j:commandLink ajaxSingle="true" id="delete"
							oncomplete="#{rich:component('deletePanel')}.show()">
							<h:graphicImage id="excluir" value="../images/delete.gif"
								style="border:0" />
							<f:setPropertyActionListener value="#{file}"
								target="#{automovelController.automovelFotos}" />
						</a4j:commandLink>
						<rich:toolTip for="excluir" value="Excluir" />
					</h:panelGrid></center>
				</rich:dataGrid>
			</h:panelGrid>

			<h:panelGrid columns="2">
				<rich:fileUpload addControlLabel="Adicionar Foto"
					fileUploadListener="#{automovelController.listener}" id="upload"
					immediate="false" allowFlash="false" clearAllControlLabel="Limpar"
					clearControlLabel="" cancelEntryControlLabel=""
					doneLabel="Finalizada" stopButtonClassDisabled="true"
					transferErrorLabel="Falha Ao realizar Transferência"
					doneLabelClass="Finalizada" autoclear="true"
					acceptedTypes="jpg, gif, png, bmp" maxFilesQuantity="1"
					listWidth="270px" stopControlLabel="Parar" stopEntryControlLabel=""
					sizeErrorLabel="Foto muito grande" uploadControlLabel="Carregar"
					listHeight="70px">
					<a4j:support event="onuploadcomplete" reRender="fotos, upload" />
				</rich:fileUpload>
			</h:panelGrid>

			<a4j:commandButton value="Voltar"
				action="#{automovelController.listar}" reRender="form" />

		</rich:panel></center>
	</a4j:form>
	<rich:modalPanel id="deletePanel" autosized="true" width="200">
		<f:facet name="header">
			<h:outputText value="Deseja realmente deletar este item?"
				style="padding-right:15px;" />
		</f:facet>
		<h:form>
			<center><a4j:status forceId="true">
				<f:facet name="start">
					<h:graphicImage value="../images/ajax-loader.gif" />
				</f:facet>
			</a4j:status></center>
			<table width="100%">
				<tbody>
					<tr>
						<td align="center" width="50%"><a4j:commandButton value="Sim"
							ajaxSingle="true" action="#{automovelController.excluirFotos}"
							oncomplete="#{rich:component('deletePanel')}.hide();"
							reRender="fotos" /></td>
						<td align="center" width="50%"><a4j:commandButton value="Não"
							onclick="#{rich:component('deletePanel')}.hide();return false;" />
						</td>
					</tr>
				</tbody>
			</table>
		</h:form>
	</rich:modalPanel>
</f:view>
</body>
</html>