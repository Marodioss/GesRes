document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'dayGridMonth,timeGridWeek,listWeek'
		},
		editable: true
	});
	calendar.setOption('locale', 'fr');
	calendar.render();

	calendar.on('dateClick', function(info) {
		var date = info.dateStr;
		$.ajax({
			url: 'salles',
			type: 'GET',
			data: { op: 'loadDisponibilite', date: date },
			success: function(data, textStatus, jqXHR) {
				remplir(data);
				$("#PrimaryModalalert").modal("show");
			},
			error: function(data, textStatus, jqXHR) {
				console.log(textStatus);
			}
		})
	});

	function remplir(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].code + "</td><td>" + data[i].type + "</td><td>" + data[i].capacite + "</td>";
		}
		$("#content").html(ligne);
	}
});