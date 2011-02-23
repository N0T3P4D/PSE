package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.ojim.iface.Rules;
import org.ojim.logic.ServerLogic;
import org.ojim.logic.actions.ActionFactory;
import org.ojim.logic.actions.ActionPayForBuildings;
import org.ojim.logic.actions.ActionTransferMoneyToPlayers;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.Card;
import org.ojim.logic.state.CardStack;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.GetOutOfJailCard;
import org.ojim.logic.state.ServerGameState;
import org.ojim.logic.state.ServerPlayer;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.CardField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.FieldGroup;
import org.ojim.logic.state.fields.FreeParking;
import org.ojim.logic.state.fields.GoField;
import org.ojim.logic.state.fields.GoToJail;
import org.ojim.logic.state.fields.InfrastructureField;
import org.ojim.logic.state.fields.InfrastructureFieldGroup;
import org.ojim.logic.state.fields.Jail;
import org.ojim.logic.state.fields.Station;
import org.ojim.logic.state.fields.StationFieldGroup;
import org.ojim.logic.state.fields.Street;
import org.ojim.logic.state.fields.StreetFieldGroup;
import org.ojim.logic.state.fields.TaxField;
import org.ojim.server.OjimServer;
import org.ojim.server.TestClient;

public class testServerLogic {
	OjimServer server;
	ServerLogic logic;
	ServerGameState state;
	ServerPlayer player1, player2;

	class TestClass {
		public void test(int x, int y, int z, int h, Field f, boolean b,
				boolean s) {
		}
	}

	@Before
	public void setUp() throws Exception {
		server = new OjimServer("testServer");
		player1 = new ServerPlayer("test1", 0, 0, 0, 0, new TestClient(server));
		player2 = new ServerPlayer("test2", 0, 0, 1, 1, new TestClient(server));
		state = new ServerGameState();
		logic = new ServerLogic(state, new GameRules(state, new Rules()));
		Field[] fields = new Field[state.getNumberOfFields()];
		this.loadDefaultGameStateFields(fields);
		for (Field field : fields) {
			state.setFieldAt(field, field.getPosition());
		}
		state.setPlayer(player1);
		state.setPlayer(player2);

		logic.startGame();
	}

	private void testParameters(TestClass testcase) {
		for (int x = 0; x < 40; x++) {
			player1.setPosition(x);
			for (int y = -10000; y < 10000; y += 1000) {
				player1.setBalance(y);
				for (int z = 0; z < 40; z++) {
					player2.setPosition(z);
					for (int h = 0; h < 40; h++) {
						Field field = state.getFieldAt(h);
						if (field instanceof BuyableField) {
							((BuyableField) field).buy(player1);
						}
						testcase.test(x, y, z, h, state.getFieldAt(h),
								(state.getFieldAt(h) instanceof BuyableField),
								(state.getFieldAt(h) instanceof Street));
						if (field instanceof BuyableField) {
							((BuyableField) field).buy(null);
						}
					}
				}
			}
		}
	}

	@Test
	public void testBuyStreet() {
		testParameters(new TestClass() {
			public void test(int x, int y, int z, int h, Field f, boolean b,
					boolean s) {

				if (state.getFieldAt(x) instanceof BuyableField && h != x && y >= ((BuyableField) state.getFieldAt(x)).getPrice()) {
					logic.buyStreet();
					if (!((BuyableField) state.getFieldAt(x)).getOwner()
							.equals(player1)) {
						fail("pos:" + x + " bal:" + y);
					}
				}
			}
		});

	}

	@Test
	public void testToggleMortgage() {
		testParameters(new TestClass() {
			public void test(int x, int y, int z, int h, Field f, boolean b,
					boolean s) {

				if (b && y > 8000) {
					boolean wasMortgaged = ((BuyableField)f).isMortgaged();
					logic.toggleMortgage((BuyableField) f);
					if (player1.getBalance() != y + ((BuyableField)f).getMortgagePrice() * (wasMortgaged ? 1 : -1)
						|| ((BuyableField)f).isMortgaged() == wasMortgaged)
						fail("field:" + f.getName() + " pre bal:" + y + "pos bal:" + player1.getBalance());
				}
			}
		});
	}

	@Test
	public void testExchangeMoney() {
		int[] test = {0,1,-1,100,-100,1000, -1000,10000};
		for(int i : test) {
			int old1 = player1.getBalance();
			int old2 = player2.getBalance();
			logic.exchangeMoney(player1, player2, i);
			if(old1 - i != player1.getBalance() ||
					old2 + i != player2.getBalance()) {
				fail("pl1 old:" + old1 + " pl2 old:" + old2 +
						" pl1 new:" + player1.getBalance() + " pl2 new:" + player2.getBalance());
			}
		}
	}

	private void loadDefaultGameStateFields(Field[] fields) {

		// Initialise field groups
		StationFieldGroup stations = new StationFieldGroup();
		StreetFieldGroup[] streets = new StreetFieldGroup[8];
		streets[0] = new StreetFieldGroup(0, "Dagobah", 1000);
		streets[1] = new StreetFieldGroup(1, "Hoth", 1000);
		streets[2] = new StreetFieldGroup(2, "Tatooine", 2000);
		streets[3] = new StreetFieldGroup(3, "Yavin&nbsp;Vier", 2000);
		streets[4] = new StreetFieldGroup(4, "Wolkenstadt", 3000);
		streets[5] = new StreetFieldGroup(5, "Todesstern", 3000);
		streets[6] = new StreetFieldGroup(6, "Endor", 4000);
		streets[7] = new StreetFieldGroup(7, "Coruscant", 4000);
		InfrastructureFieldGroup infrastructures = new InfrastructureFieldGroup();
		FieldGroup taxGroups = new FieldGroup(FieldGroup.TAX);
		FieldGroup go = new FieldGroup(FieldGroup.GO);
		FieldGroup communityCards = new FieldGroup(FieldGroup.COMMUNITY);
		FieldGroup eventCards = new FieldGroup(FieldGroup.EVENT);
		FieldGroup jails = new FieldGroup(FieldGroup.JAIL);
		FieldGroup freeParkings = new FieldGroup(FieldGroup.FREE_PARKING);
		FieldGroup goToJail = new FieldGroup(FieldGroup.GO_TO_JAIL);

		FreeParking freeParking;

		// Add Streets
		fields[0] = go.addField(new GoField("Los", 0, this.logic));
		fields[1] = streets[0].addField(new Street("Sumpf", 1, new int[] { 40,
				200, 600, 1800, 3200, 5000 }, 0, 1200, logic));
		fields[2] = this.newEventCardField(2, eventCards);
		fields[3] = streets[0].addField(new Street("Jodas Hütte", 3, new int[] {
				80, 400, 1200, 3600, 6400, 9000 }, 0, 1200, logic));
		fields[4] = taxGroups.addField(new TaxField("Landungs- steuer", 4,
				4000, this.logic));
		fields[5] = stations.addField(new Station("TIE-Fighter", 5, 4000));
		fields[6] = streets[1].addField(new Street("Echo-Basis", 6, new int[] {
				120, 600, 1800, 5400, 8000, 11000 }, 0, 2000, logic));
		fields[7] = this.newCommunityCardField(7, communityCards);
		fields[8] = streets[1].addField(new Street("Eis-Steppen", 8, new int[] {
				120, 600, 1800, 5400, 8000, 11000 }, 0, 2000, logic));
		fields[9] = streets[1].addField(new Street("Nordgebirge", 9, new int[] {
				160, 800, 2000, 6000, 9000, 12000 }, 0, 2400, logic));
		fields[10] = jails.addField(new Jail("Gefängnis", 10, 1000, 3));
		fields[11] = streets[2].addField(new Street("Lars Heimstatt", 11,
				new int[] { 200, 1000, 3000, 9000, 12500, 15000 }, 0, 2800,
				logic));
		fields[12] = infrastructures.addField(new InfrastructureField(
				"Kern-Reaktor", 12, 3000, this.logic));
		fields[13] = streets[2].addField(new Street("Mos Eisley", 13,
				new int[] { 200, 1000, 3000, 9000, 12500, 15000 }, 0, 2800,
				logic));
		fields[14] = streets[2].addField(new Street("Jabbas Palast", 14,
				new int[] { 240, 1200, 3600, 10000, 14000, 18000 }, 0, 3200,
				logic));
		fields[15] = stations.addField(new Station("Millenium Falke", 15, 4000,
				this.logic));
		fields[16] = streets[3].addField(new Street("Kommandozentrale", 16,
				new int[] { 280, 1400, 4000, 11000, 15000, 19000 }, 0, 3600,
				logic));
		fields[17] = this.newEventCardField(17, eventCards);
		fields[18] = streets[3].addField(new Street("Massassi Tempel", 18,
				new int[] { 280, 1400, 4000, 11000, 15000, 19000 }, 0, 3600,
				logic));
		fields[19] = streets[3].addField(new Street("Tempel-Thronsaal", 19,
				new int[] { 320, 1600, 4400, 12000, 16000, 20000 }, 0, 4000,
				logic));
		freeParking = new FreeParking("Frei Parken", 20, this.logic);
		fields[20] = freeParkings.addField(freeParking);
		fields[21] = streets[4].addField(new Street("Andockbucht", 21,
				new int[] { 360, 1800, 5000, 14000, 17500, 21000 }, 0, 4400,
				logic));
		fields[22] = this.newCommunityCardField(22, communityCards);
		fields[23] = streets[4].addField(new Street("Karbon- Gefrierkammer",
				23, new int[] { 360, 1800, 5000, 14000, 17500, 21000 }, 0,
				4400, logic));
		fields[24] = streets[4].addField(new Street("Reaktor- Kontrollraum",
				24, new int[] { 400, 2000, 6000, 15000, 18500, 22000 }, 0,
				4800, logic));
		fields[25] = stations.addField(new Station("X-Wing Fighter", 25, 4000,
				this.logic));
		fields[26] = streets[5].addField(new Street("Lande-Deck", 26,
				new int[] { 440, 2200, 6600, 16000, 19500, 23000 }, 0, 5200,
				logic));
		fields[27] = streets[5].addField(new Street("Thronsaal", 27, new int[] {
				440, 2200, 6600, 16000, 19500, 23000 }, 0, 5200, logic));
		fields[28] = infrastructures.addField(new InfrastructureField(
				"Wasser- Farm", 28, 3000, this.logic));
		fields[29] = streets[5].addField(new Street("Hauptreaktor", 29,
				new int[] { 480, 2400, 7200, 17000, 20500, 24000 }, 0, 5600,
				logic));
		fields[30] = goToJail.addField(new GoToJail("Gehe ins Gefängnis", 30,
				this.logic, (Jail) fields[10]));
		// Fabians Strange gelbe Karte wurde hiermit hoch offiziell von Max
		// entfernt!
		// fields[30] = streets[5].addField(new Street("foobar", 30, new int[] {
		// 480, 2400, 7200, 17000, 20500, 240()00 }, 0, 5600, logic));
		fields[31] = streets[6].addField(new Street("Wald", 31, new int[] {
				520, 2600, 7800, 18000, 22000, 25500 }, 0, 6000, logic));
		fields[32] = streets[6].addField(new Street("Schildgenerator", 32,
				new int[] { 520, 2600, 7800, 18000, 22000, 25500 }, 0, 6000,
				logic));
		fields[33] = this.newEventCardField(33, eventCards);
		fields[34] = streets[6].addField(new Street("Ewok-Dorf", 34, new int[] {
				560, 3000, 9000, 20000, 24000, 28000 }, 0, 6400, logic));
		fields[35] = stations
				.addField(new Station("Stern-Zerstörer", 35, 4000));
		fields[36] = this.newCommunityCardField(36, communityCards);
		fields[37] = streets[7].addField(new Street("Platz des Volkes", 37,
				new int[] { 700, 3500, 10000, 22000, 16000, 30000 }, 0, 7000,
				logic));
		fields[38] = taxGroups.addField(new TaxField("Kopf-Geld Prämie", 38,
				2000));
		fields[39] = streets[7].addField(new Street("Imperialer Palast", 39,
				new int[] { 1000, 4000, 12000, 28000, 34000, 40000 }, 0, 8000,
				logic));

		stations.setRent(new int[] { 500, 1000, 2000, 4000 });
		infrastructures.setFactors(new int[] { 80, 200 });

		// Add Cards
		CardStack comm = ((ServerGameState) this.logic.getGameState())
				.getCommunityCards();
		comm.add(new GetOutOfJailCard("comm jail", comm, this.logic));
		comm.add(Card.newNormalCard("comm +100 money", comm,
				ActionFactory.newTransferMoneyToBank(this.logic, -100)));
		comm.add(Card.newNormalCard("comm 100 m > free", comm, ActionFactory
				.newTransferMoneyToFreeParking(this.logic, 100, freeParking)));
		comm.add(Card.newNormalCard("comm 100 m (p. Hou) 1000 (p. Hot)", comm,
				new ActionPayForBuildings(this.logic, 100, 1000, this.logic
						.getGameState().getBank())));
		comm.add(Card.newNormalCard("comm +100 m (p. Ply)", comm,
				new ActionTransferMoneyToPlayers(this.logic, 100)));

		CardStack even = ((ServerGameState) this.logic.getGameState())
				.getEventCards();
		even.add(new GetOutOfJailCard("even jail", even, this.logic));
		even.add(Card.newNormalCard("even +100 money", even,
				ActionFactory.newTransferMoneyToBank(this.logic, -100)));
		even.add(Card.newNormalCard("even 100 m > free", even, ActionFactory
				.newTransferMoneyToFreeParking(this.logic, 100, freeParking)));
		even.add(Card.newNormalCard("even 100 m (p. Hou) 1000 (p. Hot)", even,
				new ActionPayForBuildings(this.logic, 100, 1000, this.logic
						.getGameState().getBank())));
		even.add(Card.newNormalCard("even +100 m (p. Ply)", even,
				new ActionTransferMoneyToPlayers(this.logic, 100)));
	}

	private synchronized Field newEventCardField(int position, FieldGroup group) {
		return group.addField(new CardField("Ereignis- karte", position, false,
				this.logic));
	}

	private synchronized Field newCommunityCardField(int position,
			FieldGroup group) {
		return group.addField(new CardField("Gemein- schafts- karte", position,
				true, this.logic));
	}
}
