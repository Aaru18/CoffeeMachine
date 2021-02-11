import data.Machine;
import service.PrepareDrinkService;

public class Main
{
    public static void main(String[] args)
    {
        PrepareDrinkService service = new PrepareDrinkService();
        // You can specify no of outlets of a machine while initialising
        Machine coffeeMachine = new Machine(3);
        // The no of items are fixed in the machine and this prints the initial quantities of the items
        service.printItemsList(coffeeMachine);
        // Execute all the test cases stored in the input.txt file line wise(considering every line as inputs provided at once)
        service.executeInputs(coffeeMachine);
    }
}
