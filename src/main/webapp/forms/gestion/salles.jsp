<div id="PrimaryModalalert"
	class="modal modal-edu-general default-popup-PrimaryModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-close-area modal-close-df">
				<a class="close" data-dismiss="modal" href="#"><i
					class="fa fa-close"></i></a>
			</div>
			<div class="modal-body" style="padding: 20px;">
				<h2 style="margin: 0">Gestion des salles</h2>
			</div>
			<hr style="margin: 0; color: #006DF0; margin-bottom: 20px;">

			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="all-form-element-inner">
							<input type="hidden" id="idSalle" value="0">
							<div class="form-group-inner">
								<div class="row">
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
										<label class="login2 pull-right pull-right-pro">Code</label>
									</div>
									<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
										<input type="text" name="code" class="form-control"
															id="code" required />
									</div>
								</div>
							</div>

							<div class="form-group-inner">
								<div class="row">
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
										<label class="login2 pull-right pull-right-pro">Capacité</label>
									</div>
									<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
										<input type="number" name="capacite" class="form-control"
															id="capacite" required/>
									</div>
								</div>
							</div>

							<div class="form-group-inner">
								<div class="row">
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
										<label class="login2 pull-right pull-right-pro">Type</label>
									</div>
									<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
										<div class="bt-df-checkbox pull-left">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
													<div class="i-checks pull-left">
														<label class="">
															<div class="iradio_square-green"
																style="position: relative;">
																<input type="radio" checked value="théorique" name="type" 
																	style="position: absolute; opacity: 0;">
																<ins class="iCheck-helper"
																	style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
															</div> <i></i> Théorique
														</label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
													<div class="i-checks pull-left">
														<label class="">
															<div class="iradio_square-green checked"
																style="position: relative;">
																<input type="radio" value="pratique" name="type"
																	style="position: absolute; opacity: 0;">
																<ins class="iCheck-helper"
																	style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
															</div> <i></i> Pratique
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-custon-rounded-four btn-primary" data-dismiss="modal" style="background: #006DF0;padding: 10px 20px;outline: none;">Annuler</button> 
				<button type="button" class="btn btn-custon-rounded-four btn-primary" id="envoyer" style="background: #006DF0;padding: 10px 20px;outline: none;">Envoyer</button>
			</div>
		</div>
	</div>
</div>