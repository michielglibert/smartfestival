import React, { Component } from "react";

export class Home extends Component {
	render() {
		return (
			<div>
				<h1>Een kort overzicht van alles functies</h1>
				<div className="block">
					<h2>Security</h2>
					<p>
						Hier kan je incidenten aanmaken met verschillende opties. Onderaan
						wordt een overzicht getoond van alle ingediende incidenten. Bij 2
						MINOR incidenten wordt een visitor ook gebanned. Bij 1 MAJOR
						incident wordt een visitor direct gebanned. Men ziet hier ook een
						overzicht van alle incidenten.
					</p>
				</div>
				<div className="block">
					<h2>Products</h2>
					<p>
						Op deze pagina kan de balans van een festivalganger worden opgeladen
						en geraadpleegd. Daarnaast is het hier mogelijk voor een verkoper om
						orders aan te maken en hieraan verschillende producten toe te
						voegen. Vervolgens kan deze order dan nog betaald worden. Men ziet
						een overzicht van alle orders + balansen.
					</p>
				</div>
				<div className="block">
					<h2>Tickets</h2>
					<p>
						Hier kan je tickets reserveren en betalen. Je ziet een overzicht van
						alle geserveerde en betaalde tickets.
					</p>
				</div>
				<div className="block">
					<h2>Visitor</h2>
					<p>
						Hier kan je tickets valideren en valideren of een visitor geldig is.
						En ook alle visitors die een geldig tickets hebben ingescanned zien.
					</p>
				</div>
				<div className="block">
					<h2>Display</h2>
					<p>
						Op deze plaats zie je berichten zoals lineupchanges of berichten
						verstuurd vanaf de notification service. Deze zouden normaal op het
						echte festival, op beeldschermen verschijnen.
					</p>
				</div>
				<div className="block">
					<h2>Lineup</h2>
					<p>
						Hier kunnen aanpassingen worden gemaakt aan de lineup. De timetable
						wordt hier ook getoond.
					</p>
				</div>
				<div className="block">
					<h2>Notifications</h2>
					<p>
						Hier kunnen security notifications worden gemeld en hierop kan dan
						ook worden geantwoord. Daarnaast kunnen hier berichten voor de
						displayservice worden verstuurt. Alle notificaties worden hier ook
						getoond.
					</p>
				</div>
			</div>
		);
	}
}

export default Home;
