<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<div class="product-payment-inner-st">
		<ul id="myTabedu1" class="tab-review-design">
			<li class="active"><a href="#description">Gestion</a></li>
			<li><a href="#reviews"> Liste</a></li>
		</ul>
		<div id="myTabContent" class="tab-content custom-product-edit">
			<div class="product-tab-list tab-pane fade active in"
				id="description">
				<div id="dropzone1" class="pro-ad">
					<div class="review-content-section" style="margin-top: 0;">
						<div class="sparkline9-list mg-t-30"
							style="margin-top: 0; padding-left: 100px; padding-right: 100px;">
							<div class="sparkline9-graph">
								<%@include file="gestion/clients.jsp"%>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="product-tab-list tab-pane fade" id="reviews">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="review-content-section" style="padding-left: 100px; padding-right: 100px;">
							<div class="devit-card-custom">
								<div class="product-status-wrap drp-lst" style="padding: 0;">
									<%@include file="listes/clients.jsp"%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>