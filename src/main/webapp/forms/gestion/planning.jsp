<%@page import="beans.Salle"%>
<%@page import="beans.Creneaux"%>
<%@page import="service.SalleService"%>
<%@page import="service.CreneauService"%>
<div class="all-form-element-inner">
	<input type="hidden" id="idPlanning" value="0">
	<div class="form-group-inner">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
				<label class="login2 pull-right pull-right-pro">Salle :</label>
			</div>
			<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
				<div class="form-select-list">
					<select class="form-control custom-select-value" name="idSalle" id ="idSalle">
						<%
						SalleService ss = new SalleService();
						for (Salle s : ss.findAll()) {
						%>
						<option value="<%=s.getId()%>"><%=s.getCode() + " - " + s.getType() + " - " + s.getCapacite()%></option>
						<%
						}
						%>
					</select>
				</div>
			</div>
		</div>
	</div>

	<div class="form-group-inner">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
				<label class="login2 pull-right pull-right-pro">Creneau :</label>
			</div>
			<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
				<div class="form-select-list">
					<select class="form-control custom-select-value" name="idCreneau" id ="idCreneau">
						<%
						CreneauService cs = new CreneauService();
						for (Creneaux c : cs.findAll()) {
						%>
						<option value="<%=c.getId()%>"><%=c.getHeureDebut() + " - " + c.getHeureFin()%></option>
						<%
						}
						%>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="form-group row showcase_row_area">
		<div class="col-md-3 showcase_text_area"></div>
		<div class="col-md-9 showcase_content_area">
			<button type="submit" id="envoyerP"
				class="btn btn-sm btn-primary col-md-5"
				style="background: #006DF0; padding: 8px 20px; outline: none; font-size: 14px; width: 200px;">Envoyer</button>
		</div>
	</div>
</div>