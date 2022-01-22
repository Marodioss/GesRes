$(document).ready(function() {
	$.ajax({
		url: "salles",
		type: 'GET',
		data: { op: 'load' },
		success: function(data, textStatus, jqXHR) {
			remplir(data);
		},
		error: function(data, textStatus, jqXHR) {
			console.log(textStatus);
		}
	})

	$("#envoyer").click(function() {
		var id = $("#idSalle").val();
		var code = $("#code").val();
		var capacite = $("#capacite").val();
		var type = $("input[name='type']:checked").val();
		$.ajax({
			url: "salles",
			type: 'POST',
			data: { id: id, code: code, capacite: capacite, type: type },
			success: function(data, textStatus, jqXHR) {
				$("#idSalle").val(0 + "");
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: 'Operation faite avec succes.',
					showConfirmButton: false,
					timer: 2000
				})
				$("#code").val("");
				$("#capacite").val("");
				remplir(data);
			},
			error: function(data, textStatus, jqXHR) {
				if (data.status == 401) {
					Swal.fire({
						position: 'center',
						icon: 'error',
						title: 'Veuillez entrer tous les champs.',
						showConfirmButton: false,
						timer: 2000
					})
				} else if (data.status == 402) {
					Swal.fire({
						position: 'center',
						icon: 'error',
						title: 'Salle existe.',
						showConfirmButton: false,
						timer: 2000
					})
				}
				console.log(textStatus);
			}
		})
	})

	$("#content").on("click", ".update", function() {
		var id = $(this).attr("val");
		$.ajax({
			url: "salles",
			type: 'GET',
			data: { id: id, op: 'update' },
			success: function(data, textStatus, jqXHR) {
				$("#code").val(data.code);
				$("#capacite").val(data.capacite);
				$("#idSalle").val(data.id);
			},
			error: function(data, textStatus, jqXHR) {
				console.log(textStatus);
			}
		})
	})

	$("#content").on("click", ".delete", function() {
		var id = $(this).attr("val");
		Swal.fire({
			title: 'Vous etes sur?',
			text: "Tu ne pourras pas revenir en arriere!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Oui, supprimer!'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url: 'salles',
					type: 'POST',
					data: { id: id, op: "delete" },
					success: function(data, textStatus, jqXHR) {
						Swal.fire({
							position: 'center',
							icon: 'success',
							title: 'La salle est supprimee avec succes.',
							showConfirmButton: false,
							timer: 2000
						})
						remplir(data);
					},
					error: function(data, textStatus, jqXHR) {
						if (data.status == 400) {
							Swal.fire({
								position: 'center',
								icon: 'error',
								title: 'Impossible de supprimer la salle',
								showConfirmButton: false,
								timer: 2000
							})
						}
						console.log(textStatus);
					}
				})
			}
		})
	})

	function remplir(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].code + "</td><td>" + data[i].type + "</td><td>" + data[i].capacite + "</td><td><button type='button' class='delete btn btn-custon-rounded-four btn-danger' val='" + data[i].id + "'>Supprimer</button><button type='button' class='update btn btn-custon-rounded-four btn-primary' val='" + data[i].id + "' style='margin-left: 10px;margin-right: 10px'  data-toggle='modal' data-target='#PrimaryModalalert'>Modifier</button></td></tr>";
		}
		$("#content").html(ligne);
	}
})