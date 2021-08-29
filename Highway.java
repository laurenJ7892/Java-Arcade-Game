import java.util.Random;

public class Highway
{
    public static final int LANES_HIGHWAY = 3;
    public static final char USER_OUTPUT_LABEL = '@';
    private static final int HIGHWAY_LENGTH_VISIBLE = 10;
    private static int highwayLength;
    private static int currentUserRow;
    private static int currentUserColumn;
    private static char[][] highwayTable;
    
    public static void calculateHighwayLength(String userLevel)
    {
        int userHighwaylength;
        switch (userLevel)
        {
            case "medium":
                userHighwaylength = (int)(15 + (Math.random() * 30));
                break;

            case "hard":
                userHighwaylength = (int)(30 + (Math.random() * 50));
                break;
            
            default:
                userHighwaylength = (int)(10 + (Math.random() * 15));
                break;
        }
        Highway.setHighwayLength(userHighwaylength);
    }

    public static void calculateHighwayMove(int userMove)
    {
        Vehicle userVehicle = UserInput.getChosenVehicle();
        int boostSections = userVehicle.getBoostSpeed();
        double fuelLimit = userVehicle.getMaxFuel();

        int damageLimit = userVehicle.getMaxSustainDamage();
        int userColumn = Highway.getCurrentColumn();
        int userRow = Highway.getCurrentRow();

        double currentFuel = ObstacleGame.getCurrentFuel();
        int currentDamage = ObstacleGame.getCurrentDamage();
        int highwayLength = Highway.getHighwayLength();

        // create copy to compare to other values
        double fuelPoints = 0;
        int newColumn = 0;
        int newRow = 0;
        
        switch(userMove)
        {
            case 1:
                newColumn = userColumn + 1;
                newRow = userRow;
                if (newColumn < highwayLength)
                {
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                else
                {
                    newColumn = highwayLength - 1;
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                fuelPoints = -1;
                break;

            case 2:
                newColumn = userColumn + boostSections;
                newRow = userRow;
                if (newColumn < highwayLength)
                {
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                else
                {
                    newColumn = highwayLength-1;
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                fuelPoints = boostSections * -3;
                break;
            
            case 3:
                newColumn = userColumn + 1;
                newRow = userRow + 1;
                if (newColumn < highwayLength && userRow < 2)
                {
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                else if (newColumn < highwayLength && userRow >= 2)
                {
                    newRow = userRow;
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                else if (newColumn >= highwayLength && userRow < 2)
                {
                    newColumn = highwayLength;
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                else
                {
                    newRow = userRow;
                    newColumn = highwayLength - 1;
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                fuelPoints = -2;
                break;
            
            case 4:
                newColumn = userColumn + 1;
                newRow = userRow - 1;
                if (newColumn < highwayLength && userRow > 0)
                {
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                else if (newColumn < highwayLength && userRow == 0)
                {
                    newRow = userRow;
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                    
                }
                else if (newColumn >= highwayLength && userRow > 0)
                {
                    newColumn = highwayLength;
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                else
                {
                    newRow = userRow;
                    newColumn = highwayLength;
                    Highway.setCurrentRow(newRow);
                    Highway.setCurrentColumn(newColumn);
                }
                fuelPoints = -2;
                break;
            
            default:
                System.out.println("Please enter the provided numbers only");
                break;
        }
       
        char newPositionhasObstacle = Highway.getPositionObstacle(newRow, newColumn);
        int damageFromObstacle = ObstacleGame.getObstacleDamage(newPositionhasObstacle);
        double addFuel;

        if (newPositionhasObstacle == 'F')
        {
            addFuel = (currentFuel + damageFromObstacle + fuelPoints) > fuelLimit ? fuelLimit : (currentFuel + damageFromObstacle + fuelPoints);
        }
        else
        {
            addFuel = (currentFuel + fuelPoints);
            ObstacleGame.setCurrentDamage(damageFromObstacle + currentDamage); 
        }
        ObstacleGame.setCurrentFuel(addFuel);

        if (ObstacleGame.getCurrentFuel() <= 0  ||  ObstacleGame.getCurrentDamage() <= 0)
        {
            Game.gameOver("You got caught! GAME OVER!!");
        }
        else if (newColumn == highwayLength -1)
        {
            Game.gameOver("You escaped! CONGRATULATIONS!!");
        }
        else
        {
            Highway.displayHighway(newRow, newColumn);
            Game.inputMove();
        }
    }

    public static int calculateMaxObstacleArrayLength()
    {
        // remove 9 for 3 lanes * 3 sections with no obstacles
        int maxLength = (Highway.getHighwayLength() * LANES_HIGHWAY) - 9;
        return maxLength;
    }

    public static void displayHighway(int currentUserRow, int currentUserColumn)
    {
        char[][] highwayArray = Highway.getHighwayTable();
        int highwayLength = Highway.getHighwayLength();
        int visibleHighwaySections =  currentUserColumn + Highway.HIGHWAY_LENGTH_VISIBLE;
        int remainingHighwaySections = Math.min(visibleHighwaySections, highwayLength);

         // Show all three lanes but only 10 columns at a time
        for (int i = 0; i < highwayArray.length; i++)
        {
            for (int j = (currentUserColumn == 0 ? 0 :  currentUserColumn - 1); j < remainingHighwaySections; j++)
            {
                highwayArray[currentUserRow][currentUserColumn] = Highway.USER_OUTPUT_LABEL;
                System.out.print("|_" + highwayArray[i][j] + "_|");
            }
            System.out.println();
        }
    }

    public static int getCurrentColumn()
    {
        return currentUserColumn;
    }

    public static int getCurrentRow()
    {
        return currentUserRow;
    }

    public static int getHighwayLength()
    {
        return highwayLength;
    }

    public static char[][] getHighwayTable()
    {
        return highwayTable;
    }

    public static char getPositionObstacle(int newRow, int newColumn)
    {
        char[][] highwayArray = Highway.getHighwayTable();
        char obstaclePresent = highwayArray[newRow][newColumn];
        return obstaclePresent;
    }

    public static char pluckValueFromObstacleArray()
    {
        Random random = new Random();
        char[] obstaclesToWrite = ObstacleGame.getObstaclesByType();
        char[] newArray =  new char[obstaclesToWrite.length - 1];
        int randIndex = random.nextInt(obstaclesToWrite.length);
        char valueFromArray = obstaclesToWrite[randIndex];

        for (int i = 0, j = 0; i < obstaclesToWrite.length; i++)
        {
            if (i != randIndex)
            {
                 newArray[j++] = obstaclesToWrite[i];
            }
        }
        ObstacleGame.setObstaclesByType(newArray);
        return valueFromArray;
    }

    public static void setCurrentColumn(int newColumn)
    {
        currentUserColumn = newColumn;
    }

    public static void setCurrentRow(int newRow)
    {
        currentUserRow = newRow;
    }

    public static void setHighwayLength(int newLength)
    {
        highwayLength = newLength;
    }

    public static void setHighwayTable(char[][] newTable)
    {
        highwayTable = newTable;
    }

    public static void writeObstaclesToHighway()
    {
        int totalObstacles = ObstacleGame.getNumObstacles();
        // based on user level
        int highwayLength = Highway.getHighwayLength();
        // output table length == highway length & rows = highway lanes
        char[][] table = new char[LANES_HIGHWAY][highwayLength];
        char cell_value;
        // No obstacles in first three sections
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[i].length; j++)
            {
                if (j < 3)
                {
                    cell_value = '_';
                }
                else
                {
                    cell_value = Highway.pluckValueFromObstacleArray();
                    if (cell_value != 'B' && cell_value != 'F' && cell_value != 'S' && cell_value != 'O' && cell_value != '_')
                    {
                        cell_value = '_';
                    }
                }
                table[i][j] = cell_value;
            }
        }
        Highway.setHighwayTable(table);
    }
}