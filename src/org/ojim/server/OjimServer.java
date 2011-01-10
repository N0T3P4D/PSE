package org.ojim.server;

import java.util.LinkedList;
import java.util.Scanner;

import org.ojim.client.ai.AIClient;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;

import edu.kit.iti.pse.iface.IServer;
import edu.kit.iti.pse.iface.IServerAuction;
import edu.kit.iti.pse.iface.IServerTrade;

public class OjimServer implements IServer, IServerAuction, IServerTrade {

	private String name;
	private boolean isOpen = false;
	private int connectedClients;
	private int maxClients;
	private LinkedList<IClient> clients;

	private GameState state;
	
	public OjimServer(String name) {
		this.name = name;
		
		System.out.println("Server started");
		boolean running = true;
		
		System.out.println("\nAdministrative Functions can be executed here, type help for an overview of existing calls");
		
		String input;
		Scanner in = new Scanner(System.in);
		while(running)
		{
			//read input
			input = in.nextLine();
			
			if(input.equals("help")) {
				System.out.println("help            shows this dialog");
				System.out.println("info            shows some information");
				System.out.println("exit            ends Ojim");
				System.out.println("start [1] [2]   starts a new game");
				System.out.println("                [1] PlayerCount");
				System.out.println("                [2] AICount");
				System.out.println("                currently its start 2 1");
				System.out.println("end             ends the current game");
			} else if(input.equals("info")) {
				System.out.println("Servername:     " + this.name);
				System.out.println("Status:         " + (isOpen ? "open": "closed"));
				System.out.println("Players:        " + connectedClients + "/" + maxClients);
				System.out.println("AI:             " + "-");
			} else if(input.equals("exit")) {
				if(isOpen) {
					System.out.println("Ending current game");
					endGame();
					System.out.println("Current game Ended!");
				}
				running = false;
			} else if(input.startsWith("start")) {
				if(isOpen) {
					System.out.println("There is already a Game running, end it first before starting a new one!");
				} else {
					System.out.println("Initializing Game");
					initGame(2,1);
					System.out.println("Game initialized!");
				}
			} else if(input.equals("end")) {
				if(!isOpen) {
					System.out.println("There is no Game running!");
				} else {
					System.out.println("Ending current game");
					endGame();
					System.out.println("Current game Ended!");
				}
			}
		}

	}

	private boolean initGame(int playerCount, int aiCount) {
		
		//Make sure no negative numbers appear and there are players at all
		if(playerCount < 0 || aiCount < 0 || playerCount + aiCount == 0) {
			display("Player- and AICount must not be negative and one of them must be positive");
			return false;
		}
		
		//Initializing Fields
		this.connectedClients = 0;
		this.maxClients = playerCount + aiCount;
		clients = new LinkedList<IClient>();
		
		//Add AIClients to the Game
		for(int i = 0; i < aiCount; i++) {
			addAIClient();
		}
				
		//Open the Game
		isOpen = true;
		return true;
	}

	private void addAIClient() {
		clients.add(new AIClient());
		connectedClients++;
	}

	private boolean endGame() {
		
		//Closing the Game
		isOpen = false;
		
		//Disconnecting all Clients
		for(IClient client : clients) {
			if(client.getName().equals("AIClient")) {
				client = null;
			} else {
				disconnect(client);
				client = null;
			}
		}
		
		//Setting the Fields to the Standard Values
		this.connectedClients = 0;
		this.maxClients = 0;
		clients = null;
		
		return true;
	}
	
	private void disconnect(IClient client) {
		// TODO Auto-generated method stub
		
	}

	private void display(String string) {
		System.out.println(string);
	}

	@Override
	public boolean initTrade(int actingPlayer, int partnerPlayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTradeState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPartner() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean offerCash(int playerID, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerGetOutOfJailCard(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerEstate(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requireCash(int playerID, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requireGetOutOfJailCard(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requireEstate(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getOfferedEstates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOfferedCash() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfOfferedGetOutOfJailCards() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getRequiredEstates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRequiredCash() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfRequiredGetOutOfJailCards() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean cancelTrade(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean proposeTrade(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAuctionState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAuctionedEstate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHighestBid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBidder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean placeBid(int playerID, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPlayerPiecePosition(int playerID) {
		if(playerID >= this.connectedClients) {
			return -1;
		}
		return ;
	}

	@Override
	public int addPlayer(IClient client) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPlayerReady(int player) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPlayerName(int player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerColor(int player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Rules getRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEstateName(int position) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public void sendMessage(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendPrivateMessage(String text, int reciever) {
		// TODO Auto-generated method stub

	}

}