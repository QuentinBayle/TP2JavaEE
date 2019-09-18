package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        // S3
	public void MontantInsuffifantPasDeTicket(){
            machine.insertMoney(PRICE-1);
            assertFalse(machine.printTicket());
	}
        
        @Test
        //S4
        public void MontantSuffisantTicket(){
            machine.insertMoney(PRICE);
            assertTrue(machine.printTicket());
        }
        
        @Test
        //S5
        public void BalanceDecrementeePrix(){
            machine.insertMoney(60);
            machine.printTicket();
            assertEquals("La balance n'est pas correctement décrémentée", 60-PRICE, machine.getBalance());
        }
         
        @Test
        //S6
	public void MontantCollecte() {
		assertEquals(0, machine.getTotal());
                machine.insertMoney(PRICE);
                assertEquals(0, machine.getTotal());
		machine.printTicket();
		assertEquals(PRICE , machine.getTotal());
	}
            
	@Test
        //S7
	public void RendMonnaie() {
		int montant = 10;
		machine.insertMoney(montant);
		assertEquals(montant, machine.refund());
	}
        
	@Test
        //S8
	public void BalanceZero() {
		machine.insertMoney(60);
		machine.printTicket();
		assertEquals(40 + 20 - PRICE, machine.refund());
		assertEquals(0, machine.getBalance());
	}	
        
	@Test
        //S9
	public void MontantNegatifErreur() {
		try {
			machine.insertMoney(-1);
			fail("Erreur");
		} catch (IllegalArgumentException e) {
		}
	}
      

	@Test 
        //S10
	public void TicketPrixNegatif() {
		try {
			new TicketMachine(0);
			fail("Le ticket est positif ce n'est pas normal");
		} catch (IllegalArgumentException e) {
		}
	}	
}
