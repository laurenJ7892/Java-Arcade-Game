import java.lang.Math;

public class ObstacleGame
{
    private static int numObstacles;
    private static char[] obstaclesByType;
    private static Obstacle[] obstacles;
    public static double currentFuel;
    public static int currentDamage;

    public static void adjustNumberObstacles(String userLevel)
    {
        int numObstaclesOnHighway;
        switch (userLevel)
        {
            case "medium":
                numObstaclesOnHighway = 24;
                break;

            case "hard":
                numObstaclesOnHighway = 45;
                break;
            
            default:
                numObstaclesOnHighway = 12;
                break;
        }

        if (numObstaclesOnHighway > 0)
        {
            ObstacleGame.setNumObstacles(numObstaclesOnHighway);
            ObstacleGame.distributeObstaclesbyType(numObstaclesOnHighway);
        }
    }

    public static void createObstaclesArray()
    {
        Obstacle[] obstacles = new Obstacle[4];
        ObstacleGame.setObstacles(obstacles);
        ObstacleGame.setSpecificObstacle(0,"Fuel", 'F', 0.3, 10);
        ObstacleGame.setSpecificObstacle(1,"RoadBlock", 'B', 0.4, -20);
        ObstacleGame.setSpecificObstacle(2,"Manhole", 'O', 0.1, -50);
        ObstacleGame.setSpecificObstacle(3,"Spikes", 'S', 0.2, -45);
    }

    public static void distributeObstaclesbyType(int numTotalObstacles)
    {
        Obstacle obFuel = ObstacleGame.getSpecificObstacle(0);
        Obstacle obRoadBlock = ObstacleGame.getSpecificObstacle(1);
        Obstacle obManhole = ObstacleGame.getSpecificObstacle(2);
        Obstacle obSpikes = ObstacleGame.getSpecificObstacle(3);

        int numberFuel = (int) Math.round(numTotalObstacles * obFuel.getObProb());
        int numberSpikes = (int) Math.round(numTotalObstacles * obSpikes.getObProb());
        int numberRoadBlocks = (int) Math.round(numTotalObstacles * obRoadBlock.getObProb());
        int numberManhole = (int) Math.round(numTotalObstacles * obManhole.getObProb());

        int maxLengthObstacleArray = Highway.calculateMaxObstacleArrayLength();
        char[] obstacleLetters = new char[(maxLengthObstacleArray)];
        System.out.println("You will have " + numTotalObstacles + " obstacles on the Highway!");
        // Add each obstacle character into the array 
        char fuelChar = obFuel.getObLetter();
        char spikesChar = obSpikes.getObLetter();
        char blocksChar = obRoadBlock.getObLetter();
        char manholeChar = obManhole.getObLetter();
        int i = 0;
        int j = numberFuel;
        int k = (numberFuel + numberSpikes);
        int l = (numberFuel + numberSpikes + numberRoadBlocks);
        int m  = (numberFuel + numberSpikes + numberRoadBlocks + numberManhole);
        int emptyPlaceholder = maxLengthObstacleArray  - numTotalObstacles;
        do
        {
            obstacleLetters[i] = fuelChar;
            i++;
        } while (i <= j);
        do
        {
            obstacleLetters[j] = spikesChar;
            j++;
        } while (j <= k);
        do
        {
            obstacleLetters[k] = blocksChar;
            k++;
        } while ( k <= l);
        do
        {
            obstacleLetters[l] = manholeChar;
            l++;
        } while ( l <= m);
        do
        {
            obstacleLetters[emptyPlaceholder] = '_';
            emptyPlaceholder++;
        } while ( emptyPlaceholder <= maxLengthObstacleArray -1);
        ObstacleGame.setObstaclesByType(obstacleLetters);
    }

    public static int getCurrentDamage()
    {
        return currentDamage;
    }

    public static double getCurrentFuel()
    {
        return currentFuel;
    }


    public static int getNumObstacles()
    {
        return numObstacles;
    }

    public static Obstacle[] getObstacles()
    {
        return obstacles;
    }

    public static int getObstacleDamage(char inputChar)
    {
        // Obstacle Fuel = 0; RoadBlock = 1; Manhole = 2; Spikes = 3;
        
        int obstacleDamage;
        switch (inputChar)
        {
            case 'F':
                Obstacle obFuel = ObstacleGame.getSpecificObstacle(0);
                obstacleDamage = obFuel.getObDamage();
                break;
            
            case 'B':
                Obstacle obRoadBlock = ObstacleGame.getSpecificObstacle(1);
                obstacleDamage = obRoadBlock.getObDamage();
                break;

            case 'O':
                Obstacle obManhole = ObstacleGame.getSpecificObstacle(2);
                obstacleDamage = obManhole.getObDamage();
                break;

            case 'S':
                Obstacle obSpikes = ObstacleGame.getSpecificObstacle(3);
                obstacleDamage = obSpikes.getObDamage();
                break;
            
            default:
                obstacleDamage = 0;
                break;
        }
        return obstacleDamage;
    }

    public static char[] getObstaclesByType()
    {
        return obstaclesByType;
    }

    public static Obstacle getSpecificObstacle(int specificIndex)
    {
        return obstacles[specificIndex];
    }

    public static void setCurrentDamage(int newDamage)
    {
        currentDamage = newDamage;
    }

    public static void setCurrentFuel(double newFuel)
    {
        currentFuel = newFuel;
    }

    public static void setNumObstacles(int newNumObstacles)
    {
        numObstacles = newNumObstacles;
    }

    public static void setSpecificObstacle(int newIndex, String obstacleType, char obstacleLetter, double obstacleProb, int obstacleDamage)
    {
        obstacles[newIndex] = new Obstacle(obstacleType, obstacleLetter, obstacleProb, obstacleDamage);
    }

    public static void setObstacles(Obstacle[] newObstacles)
    {
        obstacles = newObstacles;
    }

    public static void setObstaclesByType(char[] newObstacleList)
    {
        obstaclesByType = newObstacleList;
    }
}