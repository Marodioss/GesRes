$(document).ready(function() {
	$.ajax({
		url: 'creneaux',
		type: 'GET',
		data: { op: 'load' },
		success: function(data, textStatus, jqXHR) {
			remplirData(data);

		},
		error: function(data, textStatus, jqXHR) {
			console.log(textStatus);
		}
	})

	$("#envoyerC").click(function() {
		var id = $("#idCreneaux").val();
		var heureDebut = $("#heureD").val();
		var heureFin = $("#heureF").val();
		$.ajax({
			url: 'creneaux',
			type: 'POST',
			data: { id: id, heureDebut: heureDebut, heureFin: heureFin },
			success: function(data, textStatus, jqXHR) {
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: 'Operation faite avec succes.',
					showConfirmButton: false,
					timer: 3000
				})
				remplirData(data);
				$("#idCreneau").val(0);
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
						title: 'Crenenau existe.',
						showConfirmButton: false,
						timer: 2000
					})
				}
				console.log(textStatus);
			}
		})
	})

	$("#contentC").on("click", ".delete", function() {
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
					url: 'creneaux',
					type: 'POST',
					data: { id: id, op: "delete" },
					success: function(data, textStatus, jqXHR) {
						Swal.fire(
							'Suprimee!',
							'Le creneau est supprimee avec succes.',
							'success'
						)
						remplirData(data);
					},
					error: function(data, textStatus, jqXHR) {
						if (data.status == 400) {
							Swal.fire({
								position: 'center',
								icon: 'error',
								title: 'Impossible de supprimer le creneau',
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

	$("#contentC").on("click", ".update", function() {
		var id = $(this).attr("val");
		$.ajax({
			url: 'creneaux',
			type: 'GET',
			data: { id: id ,op: 'update' },
			success: function(data, textStatus, jqXHR) {
				$("#heureD").val(formatTime(data.heureDebut));
				$("#heureF").val(formatTime(data.heureFin));
				$("#idCreneaux").val(data.id);
			},
			error: function(data, textStatus, jqXHR) {
				console.log(textStatus);
			}
		})
	})

	function remplirData(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].heureDebut + "</td><td>" + data[i].heureFin + "</td>" + "<td><button type='button' class='delete btn btn-custon-rounded-four btn-danger'val ='" + data[i].id + "'>Supprimer</button><button style='margin-left:5px' type='button' class='update btn btn-custon-rounded-four btn-primary' val ='" + data[i].id + "'>Modifier</button></td></tr>";
		}
		$("#contentC").html(ligne);
	}

	function formatTime(heure) {
		var hMinSec = heure.split(':');
		var pmAm = hMinSec[2].split(' ');
		if (pmAm[1] === "PM") {
			if (parseInt(hMinSec[0]) == 12)
				var nvHeure = "12:" + hMinSec[1];
			else
				var nvHeure = (parseInt(hMinSec[0]) + 12) + ":" + hMinSec[1];
		}
		else {
			if (parseInt(hMinSec[0]) == 12)
				var nvHeure = "00:" + hMinSec[1];
			else
				var nvHeure = hMinSec[0] + ":" + hMinSec[1];
		}

		return nvHeure;
	}
})