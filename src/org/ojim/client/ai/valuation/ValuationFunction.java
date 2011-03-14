/*  Copyright (C) 2010 - 2011  Fabian Neundorf, Philip Caroli,
 *  Maximilian Madlung,	Usman Ghani Ahmed, Jeremias Mechler
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ojim.client.ai.valuation;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.ojim.log.OJIMLogger;
import org.ojim.logic.Logic;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.GameState;

import edu.kit.iti.pse.iface.IServer;

/**
 * 
 * @author Jeremias Mechler
 * 
 */
public abstract class ValuationFunction {

	private Logic logic;
	protected Logger logger;
	protected IServer server;
	protected ValuationParameters parameters;
	

	/**
	 * Number of valuation functions
	 */
	public static final int COUNT = 6;

	/**
	 * Constructor
	 */
	protected ValuationFunction() {
	}

	private static Map<Class<? extends ValuationFunction>, ValuationFunction> instances = new HashMap<Class<? extends ValuationFunction>, ValuationFunction>(
			ValuationFunction.COUNT);

	/**
	 * 
	 * Gets you an instance of clazz
	 * 
	 * @param clazz
	 *            Class
	 * @param <T>
	 *            type
	 * @return Instance
	 * 
	 */
	public static <T extends ValuationFunction> T getInstance(Class<T> clazz) {
		return ValuationFunction.getInstance(false, clazz);
	}

	/**
	 * Gets you an instance
	 * 
	 * @param forceNew
	 *            Do you need a new instance?
	 * @param clazz
	 *            Class
	 * @param <T>
	 *            type
	 * @return Instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ValuationFunction> T getInstance(boolean forceNew, Class<T> clazz) {
		T instance = null;
		if (!forceNew) {
			instance = (T) ValuationFunction.instances.get(clazz);
		}
		if (instance == null) {
			try {
				instance = clazz.newInstance();
				ValuationFunction.instances.put(clazz, instance);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (T) instance;
	}

	/**
	 * Sets logic and rules
	 * 
	 * @param logic
	 *            logic
	 */
	public void setParameters(ValuationParameters parameters, Logic logic) {
		this.parameters = parameters;
		this.logic = logic;
	}

	/**
	 * Get Logic
	 * 
	 * @return reference to logic
	 */
	protected final Logic getGameLogic() {
		return this.logic;
	}

	/**
	 * Get Rules
	 * 
	 * @return reference to rules
	 */
	protected final GameRules getGameRules() {
		return this.logic.getGameRules();
	}

	/**
	 * Get State
	 * 
	 * @return reference to state
	 */
	protected final GameState getGameState() {
		return this.logic.getGameState();
	}

	/**
	 * Returns valuation
	 * 
	 * @return Valuation as double
	 */
	public abstract double returnValuation(int playerID, int argument);

	/**
	 * Sets a logger
	 */
	protected final void getLogger() {
		if (logger == null) {
			logger = OJIMLogger.getLogger(this.getClass().toString());
		}
	}

	/**
	 * gets logic
	 * 
	 * @return reference to logic
	 */
	protected final Logic getLogic() {
		return logic;
	}

	/**
	 * sets server
	 * 
	 * @param server
	 *            reference to server
	 */
	protected final void setServer(IServer server) {
		this.server = server;
	}

	/**
	 * gets server
	 * 
	 * @return reference to server
	 */
	protected final IServer getServer() {
		assert (server != null);
		return server;
	}

}
