import java.util.ArrayList;

public class UserInput
{
    private ArrayList<Vehicle> vehicles;
    private String name;
    private String level;
    private static Vehicle chosenVehicle;

    public UserInput()
    {
        vehicles = new ArrayList<Vehicle>();
        name = "unknown";
        level = "easy";
    }

    public UserInput(ArrayList vehicles, String userName, String userLevel)
    {
        this.vehicles = vehicles;
        this.name = userName;
        this.level = userLevel;
    }

    public void addVehicles(Vehicle newVehicle)
    {
        this.vehicles.add(newVehicle);
    }

    public void displayVehiclesToUser()
    {
        System.out.println("Please select a vehicle from the following options");
        int counter = 0;
        for (Vehicle v: this.vehicles)
        {
            v.display(counter);
            counter++;
        };
    }

    public static Vehicle getChosenVehicle()
    {
        return chosenVehicle;
    }

    public String getLevel()
    {
        return this.level;
    }

    public String getName()
    {
        return this.name;
    }

    public Vehicle getSpecificVehicle(int specificVehicle)
    {
        return this.vehicles.get(specificVehicle);
    }

    public ArrayList<Vehicle> getVehicles()
    {
        return this.vehicles;
    }

    public int getVehicleSize()
    {
        return this.vehicles.size();
    }

    public void removeVehicle(int vehicleToRemove)
    {
       this.vehicles.remove(vehicleToRemove);
    }

    public static void setChosenVehicle(Vehicle newVehicle)
    {
        chosenVehicle = newVehicle;
    }

    public void setLevel(String inputLevel)
    {
        this.level = inputLevel;
    }

    public void setName(String inputName)
    {
        this.name = inputName;
    }

    public void setSpecificVehicle(int indexVehicle, Vehicle newVehicle)
    {
        this.vehicles.add(indexVehicle, newVehicle);
    }

    public void setVehicles(ArrayList<Vehicle> inputVehicles)
    {
        this.vehicles = inputVehicles;
    }
}