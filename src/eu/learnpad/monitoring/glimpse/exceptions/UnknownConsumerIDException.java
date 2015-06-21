 /*
  * GLIMPSE: A generic and flexible monitoring infrastructure.
  * For further information: http://labsewiki.isti.cnr.it/labse/tools/glimpse/public/main
  * 
  * Copyright (C) 2011  Software Engineering Laboratory - ISTI CNR - Pisa - Italy
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

package eu.learnpad.monitoring.glimpse.exceptions;

/**
 * This exception will be thrown when the Monitoring Manager<br />
 * is unable to find the ID of the Consumer that send a evaluation request.
 * 
 * @author Antonello Calabr&ograve;
 * @version 3.2
 */

public class UnknownConsumerIDException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnknownConsumerIDException()
	{
		System.out.println("Check request ID, may contains errors");
	}
}