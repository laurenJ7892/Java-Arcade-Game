public class Obstacle
{
    private String obType;
    private char obLetter;
    private double obProb;
    private int obDamage; 

    public Obstacle()
    {
        // allow for new obstacles to be added. Not used in Game
        obType = "Rocks";
        obLetter = 'R';
        obProb = 0.3;
        obDamage = 75;
    }

    public Obstacle(String obstacleType, char obstacleLetter, double obstacleProb, int obstacleDamage)
    {
        obType = obstacleType;
        obLetter = obstacleLetter;
        obProb = obstacleProb;
        obDamage = obstacleDamage;
    }

    public int getObDamage()
    {
        return this.obDamage;
    }

    public char getObLetter()
    {
        return this.obLetter;
    }

    public double getObProb()
    {
        return this.obProb;
    }

    public String getObType()
    {
        return this.obType;
    }

    public void setObDamage(int newDamage)
    {
        this.obDamage = newDamage;
    }

    public void setObLetter(char newLetter)
    {
        this.obLetter = newLetter;
    }

    public void setObProb(double newProb)
    {
        this.obProb = newProb;
    }

    public void setObType(String newType)
    {
        this.obType = newType;
    }
}