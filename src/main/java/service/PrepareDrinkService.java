package service;

import data.Machine;

import java.util.Map;
import java.util.Scanner;

/**
 * Service class for preparing drinks from the machine
 */
public class PrepareDrinkService
{
    public void printItemsList(Machine machine)
    {
        System.out.println("Following List of Items are available with there quantity");
        machine.get_total_quantities().entrySet()
               .stream()
               .sorted(Map.Entry.comparingByKey())
               .forEach(System.out::println);
        System.out.println();
    }

    /**
     * Executing line by line inputs stored in Input.txt file and refilling after every cycle to the max capacity of the
     * machine for a better experience
     *
     * @param machine
     */
    public void executeInputs(Machine machine)
    {
        Scanner sc = new Scanner(Machine.class.getClassLoader().getResourceAsStream("Input.txt"));

        Map<String, Map<String, Integer>> beverages = machine.get_beverages();
        while (sc.hasNextLine())
        {
            String[] arrayInput = sc.nextLine().split(",");
            Map<String, Integer> items = machine.get_total_quantities();
            int count = 0;
            for (String input : arrayInput)
            {
                if ((input.equals(Machine.HOT_WATER) || input.equals(Machine.HOT_MILK))
                    && count < machine.get_outlets())
                {
                    if (items.containsKey(input) && items.get(input) > 50)
                    {
                        items.put(input, items.get(input) - 50);
                        System.out.println(input + " is served");
                        count++;
                    }
                    else
                    {
                        System.out.println(input + " cannot be served " + input + " is not available");
                    }
                }
                else if (beverages.containsKey(input) && count < machine.get_outlets())
                {
                    boolean available = checkAvailability(items, beverages.get(input), input);
                    if (available)
                    {
                        System.out.println(input + " is prepared");
                        machine.set_total_quantities(reduceQuantity(items, beverages.get(input)));
                        count++;
                    }
                }
                else if (!beverages.containsKey(input))
                {
                    System.out.println(
                        input + " cannot be prepared because we only have " + beverages.keySet().toString());
                }
                else if (count > machine.get_outlets())
                {
                    System.out.println("Only " + machine.get_outlets() + " can be served at once");
                }
            }
            System.out.println("Refilling....");
            machine.refillMachine();
        }

        sc.close();

    }

    private boolean checkAvailability(final Map<String, Integer> available,
                                      final Map<String, Integer> required,
                                      final String beverageName)
    {
        for (Map.Entry<String, Integer> requiredEntry : required.entrySet())
        {
            if (available.get(requiredEntry.getKey()) < requiredEntry.getValue())
            {
                System.out.println(
                    beverageName + " cannot be prepared because " + requiredEntry.getKey() + " is not available as required");
                return false;
            }
        }
        return true;
    }

    private Map<String, Integer> reduceQuantity(final Map<String, Integer> available,
                                                final Map<String, Integer> required)
    {
        for (Map.Entry<String, Integer> entry : required.entrySet())
        {
            available.put(entry.getKey(), available.get(entry.getKey()) - entry.getValue());
        }

        return available;
    }
}
