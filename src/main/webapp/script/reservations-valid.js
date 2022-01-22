$(document).ready(function() {
	$.ajax({
		url: "reservations",
		type: 'GET',
		data: { op: 'loadResValid' },
		success: function(data, textStatus, jqXHR) {
			remplirV(data);
		},
		error: function(data, textStatus, jqXHR) {
			console.log(textStatus);
		}
	})
	
	$.ajax({
		url: "reservations",
		type: 'GET',
		data: { op: 'loadResEnAtt' },
		success: function(data, textStatus, jqXHR) {
			remplirEV(data);
		},
		error: function(data, textStatus, jqXHR) {
			console.log(textStatus);
		}
	})
	
	$("#contentEV").on("click", ".delete", function() {
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
					url: 'reservations',
					type: 'POST',
					data: { id: id, op: "invalide" },
					success: function(data, textStatus, jqXHR) {
						Swal.fire({
							position: 'center',
							icon: 'success',
							title: 'La reservation est supprimee avec succes.',
							showConfirmButton: false,
							timer: 2000
						})
						window.location.reload();
					},
					error: function(data, textStatus, jqXHR) {
						console.log(textStatus);
					}
				})
			}
		})
	})
	
	$("#contentEV").on("click", ".update", function() {
		var id = $(this).attr("val");
		Swal.fire({
			title: 'Vous etes sur?',
			text: "Tu ne pourras pas revenir en arriere!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Oui, valider!'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url: 'reservations',
					type: 'POST',
					data: { id: id, op: "valide" },
					success: function(data, textStatus, jqXHR) {
						Swal.fire({
							position: 'center',
							icon: 'success',
							title: 'La reservation est valide avec succes.',
							showConfirmButton: false,
							timer: 2000
						})
						window.location.reload();
					},
					error: function(data, textStatus, jqXHR) {
						console.log(textStatus);
					}
				})
			}
		})
	})
	
	function remplirV(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].occupation.salle.code + " - " + data[i].occupation.salle.type + " - " + data[i].occupation.salle.capacite  + "</td><td>" + data[i].occupation.creneaux.heureDebut + " - " + data[i].occupation.creneaux.heureFin + "</td><td>" + data[i].client.nom + " " + data[i].client.prenom + "</td><td>" + data[i].date + "</td></tr>";
		}
		$("#contentV").html(ligne);
	}
	
	function remplirEV(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].occupation.salle.code + " - " + data[i].occupation.salle.type + " - " + data[i].occupation.salle.capacite  + "</td><td>" + data[i].occupation.creneaux.heureDebut + " - " + data[i].occupation.creneaux.heureFin + "</td><td>" + data[i].client.nom + " " + data[i].client.prenom + "</td><td>" + data[i].date + "</td><td><button type='button' class='delete btn btn-custon-rounded-four btn-danger' val='" + data[i].id + "'>Invalider</button><button type='button' class='update btn btn-custon-rounded-four btn-primary' val='" + data[i].id + "' style='margin-left: 10px;margin-right: 10px'>Valider</button></td></tr>";
		}
		$("#contentEV").html(ligne);
	}
})