 /*
  * GLIMPSE: A generic and flexible monitoring infrastructure.

  * For further information: http://labsewiki.isti.cnr.it/labse/tools/glimpse/public/main
  * 
  * Copyright (C) 2015  Software Engineering Laboratory - ISTI CNR - Pisa - Italy
  * 
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * 
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * 
*/

package eu.learnpad.simulator.mon.example;

import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Vector;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.sim.rest.event.AbstractEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.event.GlimpseBaseEvent;
import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;
import eu.learnpad.simulator.mon.probe.GlimpseAbstractProbe;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import eu.learnpad.simulator.mon.utils.Manager;

public class MyGlimpseProbe_BPMN_LearnPAd extends GlimpseAbstractProbe {

	/**
	 * This class provides an example of how to send messages (events) to Glimpse CEP.
	 * @author Antonello Calabr&ograve;
	 * @version 3.3.2
	 *
	 */
	
	public static int sendingInterval = 10000;
	
	public MyGlimpseProbe_BPMN_LearnPAd(Properties settings) {
		super(settings);
	}

	public static void main(String[] args) throws UnknownHostException {

		DebugMessages.line();
		DebugMessages.println(TimeStamp.getCurrentTime(), MyGlimpseProbe_BPMN_LearnPAd.class.getName(),
				"\nONE EVENT CONTAINING BPMN SIMULATED PARAMETER WILL BE SENT EACH 10 SECONDS\n"
				+ "TO SPECIFY A DIFFERENT RATE, PROVIDE AN ARG IN MILLISECONDS\n"
				+ "USAGE: java -jar MyGlimpseProbe_BPMN.jar [amountOfMilliseconds]");
		DebugMessages.line();
		try {
			if (args.length > 0 && Integer.parseInt(args[0])>0) {
				sendingInterval = Integer.parseInt(args[0]);
			}	
		} catch (IndexOutOfBoundsException e) {
		}
		
		MyGlimpseProbe_BPMN_LearnPAd aGenericProbe = new
				MyGlimpseProbe_BPMN_LearnPAd(Manager.createProbeSettingsPropertiesObject(
								"org.apache.activemq.jndi.ActiveMQInitialContextFactory",
								"tcp://atlantis.isti.cnr.it:61616",
								"system", "manager",
								"TopicCF", "jms.probeTopic",
								false,
								"probeName", "probeTopic"));
		
		DebugMessages.println(TimeStamp.getCurrentTime(), 
				MyGlimpseProbe_BPMN_LearnPAd.class.getName(),
				"Starting infinite loop");
		
		aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload(
				"Activity_"+ System.currentTimeMillis(),sendingInterval);
	}
	
	@Override
	public void sendMessage(GlimpseBaseEvent<?> event, boolean debug) {		
	}
	
	private void generateAndSendExample_GlimpseBaseEvents_StringPayload(String data, int sendingInterval) throws UnknownHostException {
		DebugMessages.ok();
		DebugMessages.print(TimeStamp.getCurrentTime(), MyGlimpseProbe_BPMN_LearnPAd.class.getName(),"Creating GlimpseBaseEventBPMN message");
		GlimpseBaseEventBPMN<String> message;
		DebugMessages.ok();
		DebugMessages.line();
		try {
			
			AbstractEvent theEvent = new SessionScoreUpdateEvent();
			
			for (int i = 0; i<1000;i++) {
				Vector<Learner> usersInvolved = new Vector<>();
				usersInvolved.add(new Learner(1,12,"testName1", "testSurname1"));
				usersInvolved.add(new Learner(2,24,"testName2", "testSurname2"));
				
				message = new GlimpseBaseEventBPMN<String>(
						data,
						"aGenericProbe",
						System.currentTimeMillis(),
						"EventGeneratedFromActivitiEngineSimulated",
						false,
						"ExtraField", theEvent						
						);
				
				this.sendEventMessage(message, false);
				DebugMessages.println(TimeStamp.getCurrentTime(), 
						MyGlimpseProbe_BPMN_LearnPAd.class.getName(),
						"GlimpseBaseEventBPMN message sent: {\n"
								+ "eventName: " + message.getEventName() + "\n"
								+ "eventData: " + message.getEventData() + "\n"
								+ "timestamp: " + message.getTimeStamp() + "\n"
								+ "extraField: " + message.getExtraDataField() + "\n"
								+ "sessionID_Field: " + message.event.simulationsessionid + "\n"
								+ "usersInvolvedSize: " + message.event.involvedusers+ "\n"
								+ "eventType: " + message.event.type
								+"}");
				DebugMessages.line();
				Thread.sleep(sendingInterval);
			}
		} catch (JMSException e1) {
			e1.printStackTrace();
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
