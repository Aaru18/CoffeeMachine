package data;

import java.util.HashMap;
import java.util.Map;

/**
 * The model for Machine
 */
public class Machine
{
    public static final String HOT_WATER    = "hot_water";
    public static final String HOT_MILK     = "hot_milk";
    public static final String GINGER_SYRUP = "ginger_syrup";
    public static final String SUGAR_SYRUP  = "sugar_syrup";
    public static final String TEA_LEAVES   = "tea_leaves";
    public static final String HOT_TEA      = "hot_tea";
    public static final String HOT_COFFEE   = "hot_coffee";
    public static final String BLACK_TEA    = "black_tea";
    public static final String GREEN_TEA    = "green_tea";

    // No of outlets -- indicating how many beverages can be served in one time
    private int                               _outlets;
    // Map representing every items quantity which is currently available
    private Map<String, Integer>              _total_quantities;
    // Map representing the map of all the beverages and the items that it will need
    private Map<String, Map<String, Integer>> _beverages;

    public Machine(final int outlets)
    {
        _outlets = outlets;
        _total_quantities = new HashMap<>();
        _beverages = new HashMap<>();
        refillMachine();

        final Map<String, Integer> hotTeaMap = new HashMap<>();
        hotTeaMap.put(HOT_WATER, 200);
        hotTeaMap.put(HOT_MILK, 100);
        hotTeaMap.put(GINGER_SYRUP, 10);
        hotTeaMap.put(SUGAR_SYRUP, 10);
        hotTeaMap.put(TEA_LEAVES, 30);

        final Map<String, Integer> hotCoffeeMap = new HashMap<>();
        hotCoffeeMap.put(HOT_WATER, 100);
        hotCoffeeMap.put(HOT_MILK, 400);
        hotCoffeeMap.put(GINGER_SYRUP, 30);
        hotCoffeeMap.put(SUGAR_SYRUP, 50);
        hotCoffeeMap.put(TEA_LEAVES, 30);

        final Map<String, Integer> blackTeaMap = new HashMap<>();
        blackTeaMap.put(HOT_WATER, 300);
        blackTeaMap.put(GINGER_SYRUP, 30);
        blackTeaMap.put(SUGAR_SYRUP, 50);
        blackTeaMap.put(TEA_LEAVES, 30);

        final Map<String, Integer> greenTeaMap = new HashMap<>();
        greenTeaMap.put(HOT_WATER, 100);
        greenTeaMap.put(GINGER_SYRUP, 30);
        greenTeaMap.put(SUGAR_SYRUP, 50);
        greenTeaMap.put(TEA_LEAVES, 30);

        addBeverages(HOT_COFFEE, hotCoffeeMap);
        addBeverages(HOT_TEA, hotTeaMap);
        addBeverages(GREEN_TEA, greenTeaMap);
        addBeverages(BLACK_TEA, blackTeaMap);
    }

    public void refillMachine()
    {
        _total_quantities.put(HOT_WATER, 500);
        _total_quantities.put(HOT_MILK, 500);
        _total_quantities.put(GINGER_SYRUP, 100);
        _total_quantities.put(SUGAR_SYRUP, 100);
        _total_quantities.put(TEA_LEAVES, 100);
    }

    /**
     * The machine by default support hot_tea, hot_coffee, black_tea and green_tea
     * Also you can ask for hot_water or hot_milk if you need
     * This function facilitates to further add any beverage in case of machine update
     *
     * @param name        Name of the beverage
     * @param ingredients Ingredient required for the beverage
     */
    public void addBeverages(final String name, final Map<String, Integer> ingredients)
    {
        _beverages.put(name, ingredients);
    }

    public int get_outlets()
    {
        return _outlets;
    }

    public void set_outlets(int outlets)
    {
        _outlets = outlets;
    }

    public Map<String, Integer> get_total_quantities()
    {
        return _total_quantities;
    }

    public void set_total_quantities(Map<String, Integer> total_quantities)
    {
        _total_quantities = total_quantities;
    }

    public Map<String, Map<String, Integer>> get_beverages()
    {
        return _beverages;
    }

    public void set_beverages(Map<String, Map<String, Integer>> beverages)
    {
        _beverages = beverages;
    }
}
