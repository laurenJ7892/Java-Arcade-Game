import java.lang.Math;

public class Vehicle
{
    private String type;
    private int boostSpeed;
    private double maxFuel;
    private int maxSustainDamage;

    public Vehicle()
    {
        type = "Motorbike";
        boostSpeed = 4;
        maxFuel = 100.0;
        maxSustainDamage = 30;
    }

    public Vehicle(String type, int boostSpeed, double maxFuel, int maxSustainDamage)
    {
       this.type = type;
       this.boostSpeed = boostSpeed;
       this.maxFuel = maxFuel;
       this.maxSustainDamage = maxSustainDamage;
    }

    public void adjustVehicleFuelLimit(String userLevel)
    {
        double currentFuelLevel = this.getMaxFuel();
        double adjustMaxFuel;
        switch (userLevel)
        {
             case "medium":
                adjustMaxFuel = 0.8;
                break;

             case "hard":
                adjustMaxFuel = 0.5;
                break;
            
            default:
                adjustMaxFuel = 1;
                break;
        }
        if (adjustMaxFuel > 0)
        {
            double updatedfuelLimit = (double) Math.round(currentFuelLevel * adjustMaxFuel);
            this.setMaxFuel(updatedfuelLimit);
        }
    }

    public void display(int counter)
    {
        System.out.println("Option " + counter + " for the " + this.type + " has a maximum fuel limit of " + this.maxFuel + ", can sustain maximum damage of " + this.maxSustainDamage + " and has a boost speed of " + this.boostSpeed);
    }

    public int getBoostSpeed()
    {
        return this.boostSpeed;
    }

    public double getMaxFuel()
    {
        return this.maxFuel;
    }

    public int getMaxSustainDamage()
    {
        return this.maxSustainDamage;
    }

    public String getType()
    {
        return this.type;
    }

    public void setBoostSpeed(int newSpeed)
    {
        this.boostSpeed = newSpeed;
    }

    public void setMaxFuel(double newFuel)
    {
        this.maxFuel = newFuel;
    }

    public void setMaxSustainDamage(int newDamage)
    {
        this.maxSustainDamage = newDamage;
    }

    public void setType(String newType)
    {
        this.type = newType;
    }
}