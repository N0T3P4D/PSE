package rmi.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Klasse verwaltet alle Methoden die ueber das Netzwerk aufgerufen werden koennen
 * @author Usman Ghani Ahmed
 *
 */
public class ImplBuffer extends UnicastRemoteObject implements NetOjim {

	
	
	protected ImplBuffer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerPiecePosition(int playerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEstateColorGroup(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEstateHouses(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEstatePrice(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEstateRent(int position, int houses) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getGameStatusMessage(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMortgaged(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getOwner(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDiceValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getDiceValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerCash(int playerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlayerOnTurn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfGetOutOfJailCards(int playerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfHousesLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfHotelsLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean rollDice(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean decline(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean endTurn(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean declareBankruptcy(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean construct(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deconstruct(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toggleMortgage(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}


}
