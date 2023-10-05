package be.ugent.securityservice.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.securityservice.adapters.messaging.MessageGateway;
import be.ugent.securityservice.adapters.messaging.VisitorIdMessage;

@Service
public class CheckIncidentSaga {
	private final MessageGateway gateway;
	
	@Autowired
	public CheckIncidentSaga(MessageGateway gateway) {
		this.gateway = gateway;
	}
	
	public void checkIncidents(List<Incident> incidents, String visitorId) {
		if(incidents.size() == 1) {
			if(incidents.get(0).getSeverity() == Severity.MAJOR) {
				gateway.banVisitor(new VisitorIdMessage(Integer.valueOf(visitorId)));
			}			
		} else {
			gateway.banVisitor(new VisitorIdMessage(Integer.valueOf(visitorId)));
		}
	}

}
