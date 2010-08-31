<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="StyleSheet" type="text/css" href="../css/messages-style.css"
	media="screen" />
<style>
.rich-panel-header {
	font-size: 12px;
	font: bold;
}
</style>
<body onload="verificarAutenticacao()">
<div id="non-printable"><f:subview id="Menu">
	<center><rich:panel
		header="SGRAUTO - SISTEMA GERENCIADOR DE REVENDAS DE AUTOMÓVEIS"
		style="height:65px;">
		<a4j:form>
			 <a4j:loadScript src="../js/script.js"  /> 
			<a4j:jsFunction action="#{acessoController.isAutenticado}"
				name="verificarAutenticacao" />
			<rich:toolBar style="text-align: center;">
				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Automóvel" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{automovelController.abrir}">
					</rich:menuItem>
					<rich:menuGroup value="Pesquisar">
						<rich:menuItem submitMode="ajax" value="Em Estoque"
							action="#{automovelController.listarByEstoque}" />
						<rich:menuItem submitMode="ajax" value="Todos"
							action="#{automovelController.listar}"
							rendered="#{acessoController.administrador}" />
						<rich:menuItem submitMode="ajax" value="Vendidos"
							action="#{automovelController.listarByVendidos}" />
					</rich:menuGroup>
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Cidade" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{cidadeController.abrir}">
					</rich:menuItem>
					<rich:menuItem submitMode="ajax" value="Pesquisar"
						action="#{cidadeController.listar}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Cliente" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{pessoaController.abrirCliente}" />
					<rich:menuItem submitMode="ajax" value="Pesquisar"
						action="#{pessoaController.listarClientes}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Empresa" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{empresaController.abrir}">
					</rich:menuItem>
					<rich:menuItem submitMode="ajax" value="Pesquisar"
						action="#{empresaController.listar}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Estado" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{estadoController.abrir}">
					</rich:menuItem>
					<rich:menuItem submitMode="ajax" value="Pesquisar"
						action="#{estadoController.listar}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu rendered="#{acessoController.administrador}">
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Funcionário" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{pessoaController.abrirFuncionario}"
						rendered="#{acessoController.administrador}" />
					<rich:menuItem submitMode="ajax" value="Pesquisar"
						action="#{pessoaController.listarFuncionarios}"
						rendered="#{acessoController.administrador}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Marca" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{marcaController.abrir}">
					</rich:menuItem>
					<rich:menuItem submitMode="ajax" value="Pesquisar"
						action="#{marcaController.listar}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Modelo" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{modeloController.abrir}">
					</rich:menuItem>
					<rich:menuItem submitMode="ajax" value="Pesquisar"
						action="#{modeloController.listar}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Venda" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Cadastrar"
						action="#{vendaController.abrir}">
					</rich:menuItem>
					<rich:menuItem submitMode="ajax" value="Pesquisar"
						action="#{vendaController.listar}">
					</rich:menuItem>
				</rich:dropDownMenu>

				<rich:dropDownMenu rendered="#{acessoController.administrador}">
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Relatório" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Estoque"
						rendered="#{acessoController.administrador}"
						action="#{automovelController.abrirPorFiltro}">
					</rich:menuItem>
					<rich:menuItem submitMode="ajax" value="Vendas"
						rendered="#{acessoController.administrador}"
						action="#{vendaController.abrirPorFiltro}">
					</rich:menuItem>
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Trocar Senha" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Trocar Senha"
						action="#{acessoController.abrirTrocarSenha}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:outputText value="Sair" />
						</h:panelGroup>
					</f:facet>
					<rich:menuItem submitMode="ajax" value="Sair"
						action="#{acessoController.fechar}" />
				</rich:dropDownMenu>

				<rich:dropDownMenu>
					<f:facet name="label">
						<h:panelGroup>
							<h:graphicImage value="../images/boneco.gif" style="border:0"
								width="16" height="14" />
							<h:outputText style="font-weight: bold;"
								value="#{acessoController.pessoaLogada.nome}" />
						</h:panelGroup>
					</f:facet>
				</rich:dropDownMenu>
			</rich:toolBar>
		</a4j:form>
	</rich:panel></center>
</f:subview></div>
</body>

