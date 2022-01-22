$(document).ready(function() {
	$.ajax({
		url: "clients",
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
		var nom = $("#nom").val();
		var prenom = $("#prenom").val();
		var login = $("#login").val();
		var email = $("#email").val();
		var password = $("#password").val();
		console.log(nom + prenom + login + email + password);
		$.ajax({
			url: 'clients',
			type: 'POST',
			data: { nom: nom, prenom: prenom, login: login, email: email, password: password },
			success: function(data, textStatus, jqXHR) {
				$("#idClient").val(0);
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: 'Operation faite avec succes.',
					showConfirmButton: false,
					timer: 2000
				})
				remplir(data);
				$("#nom").val("");
				$("#prenom").val("");
				$("#login").val("");
				$("#email").val("");
				$("#password").val("");
			},
			error: function(data, textStatus, jqXHR) {
				if (data.status == 400) {
					Swal.fire({
						position: 'center',
						icon: 'error',
						title: 'Client existe',
						showConfirmButton: false,
						timer: 2000
					})
					$("#nom").val("");
					$("#prenom").val("");
					$("#login").val("");
					$("#email").val("");
					$("#password").val("");
				}
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
					url: 'clients',
					type: 'POST',
					data: { id: id, op: "delete" },
					success: function(data, textStatus, jqXHR) {
						Swal.fire(
							'Suprimee!',
							'Le client est supprimee avec succes.',
							'success'
						)
						remplir(data);
					},
					error: function(data, textStatus, jqXHR) {
						if (data.status == 401) {
							Swal.fire({
								position: 'center',
								icon: 'error',
								title: 'Impossible de supprimer le client',
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
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].nom + "</td><td>" + data[i].prenom + "</td><td>" + data[i].login + "</td><td>" + data[i].email + "</td><td><button type='button' class='delete btn btn-custon-rounded-four btn-danger' val='" + data[i].id + "'>Supprimer</button></td></tr>";
		}
		$("#content").html(ligne);
	}
})