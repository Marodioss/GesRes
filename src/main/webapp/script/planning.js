$(document).ready(function() {
	$.ajax({
		url: 'planning',
		type: 'GET',
		data: { op: 'load' },
		success: function(data, textStatus, jqXHR) {
			remplirData(data);
		},
		error: function(data, textStatus, jqXHR) {
			console.log(textStatus);
		}
	})

	$("#envoyerP").click(function() {
		var id = $("#idPlanning").val();
		var idCreneaux = $("#idCreneau").val();
		var idSalle = $("#idSalle").val();
		console.log(idCreneaux); console.log(idSalle);
		$.ajax({
			url: 'planning',
			type: 'POST',
			data: { id: id, idCreneaux: idCreneaux, idSalle: idSalle },
			success: function(data, textStatus, jqXHR) {
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: 'Operation faite avec succes.',
					showConfirmButton: false,
					timer: 2000
				})
				remplirData(data);
				$("#idPlanning").val(0);
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
						title: 'Salle est deja affecte au creneau.',
						showConfirmButton: false,
						timer: 2000
					})
				}
				console.log(textStatus);
			}
		})
	})

	$("#contentP").on("click", ".update", function() {
		var id = $(this).attr("val");
		$("#idPlanning").val(id);
	})

	$("#contentP").on("click", ".delete", function() {
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
					url: 'planning',
					type: 'POST',
					data: { id: id, op: "delete" },
					success: function(data, textStatus, jqXHR) {
						Swal.fire(
							'Suprimee!',
							'Ligne choisi est supprimee avec succes.',
							'success'
						)
						remplirData(data);
					},
					error: function(data, textStatus, jqXHR) {
						if (data.status == 400) {
							Swal.fire({
								position: 'center',
								icon: 'error',
								title: 'Impossible de supprimer ce ligne',
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

	function remplirData(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].salle.code + "</td><td>" + data[i].creneaux.heureDebut + " - " + data[i].creneaux.heureFin + "</td>" + "<td><button type='button' class='delete btn btn-custon-rounded-four btn-danger'val ='" + data[i].id + "'>Supprimer</button><button style='margin-left:5px' type='button' class='update btn btn-custon-rounded-four btn-primary' val ='" + data[i].id + "'>Modifier</button></td></tr>";
		}
		$("#contentP").html(ligne);
	}
})