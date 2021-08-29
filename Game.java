import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class Game
{
    public static void gameOver(String userOutcome)
    {
        System.out.println(userOutcome);
        int highwayLength = Highway.getHighwayLength();
        int currentColumn = Highway.getCurrentColumn() +1;
        String printString = userOutcome + ". You made " + currentColumn + " of the " + highwayLength + " sections on the Highway\n";
        ReadWriteFile.writeFile(printString);
    }

    public static Vehicle inputCar(UserInput objUserInput)
    {
        Vehicle objVehicle;
        try 
        {
            objUserInput.displayVehiclesToUser();
            int numOptions = objUserInput.getVehicleSize() - 1;
            int userVehicleChoice = 99;
            boolean flag = true;
            String userOptionResponse;
            do
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Please select a vehicle from the options provided");
                userOptionResponse = sc.nextLine();
                try
                {
                    userVehicleChoice = Integer.parseInt(userOptionResponse.trim());
                    flag = false;
                }
                catch (Exception e)
                {
                    System.out.println("Please enter an integer Only");
                    continue;
                }
                //sc.close();
            } while (
                Validation.isBlank(userOptionResponse) == true ||
                Validation.isSpecialCharacter(userOptionResponse.trim()) == true ||
                Validation.vehicleOptionCheck(numOptions, userVehicleChoice) == true ||
                flag == true
            );
            objVehicle = objUserInput.getSpecificVehicle(userVehicleChoice);
            UserInput.setChosenVehicle(objVehicle);
        }
        catch (Exception e)
        {
            System.out.println("Error reading Vehicle Options. Applying Motorbike.");
            objVehicle = new Vehicle();
        }
        // Adjust max Fuel for Vehicle based on user level
        objVehicle.adjustVehicleFuelLimit(objUserInput.getLevel());
        return objVehicle;
    }

    public static void inputLevel(UserInput objUserInput)
    {
        Scanner sc = new Scanner(System.in);
        String userLevel;
        do 
        {
            System.out.println("Please Choose your Level: easy, medium or hard");
            userLevel = sc.nextLine();
        } while (Validation.isNumber(userLevel) == true || 
                 Validation.levelCheck(userLevel) == false ||
                 Validation.isSpecialCharacter(userLevel) == true);
        try
        {
            objUserInput.setLevel(userLevel);
            // Set Highway Length & Num Obstacles for Game
            Highway.calculateHighwayLength(userLevel);
            ObstacleGame.adjustNumberObstacles(userLevel);
        }
        catch (Exception e)
        {
            System.out.println("Error setting user Level. Using easy as default");
        }
        //sc.close();
    }

    public static void inputMove()
    {
        int userColumn = Highway.getCurrentColumn();
        int userRow = Highway.getCurrentRow();
        int highwayLength = Highway.getHighwayLength();
        Scanner sc = new Scanner(System.in);
        Vehicle userVehicle = UserInput.getChosenVehicle();
        int vBoost = userVehicle.getBoostSpeed();

        String displayString = "Enter 1 to move 1 section to the Left, 2 to Move " + vBoost + " sections to the left";
        String userMoveResponse;
        int userMove = 0;
        boolean userInputValid = false;
        do
        {
            switch(userRow)
            {
                case 0:
                    displayString += " or 3 to move down 1 lane and to the 1 section to the left";
                    break;

                case 1:
                    displayString += " or 3 to move down 1 lane and to the left or 4 to move up 1 lane and to the left";
                    break;
                
                default:
                    displayString += " or 4 to move up 1 lane and to the left";
                    break;
            }
            System.out.println(displayString);
            userMoveResponse = sc.nextLine();
            try
            {
                userMove = Integer.parseInt(userMoveResponse.trim());
                userInputValid = true;
            }
            catch (Exception e)
            {
                System.out.println("Please enter an integer Only");
                continue;
            }
        } while (userInputValid == false);
        Highway.calculateHighwayMove(userMove);
        //sc.close();
    }


    public static void inputName(UserInput objUserInput)
    {
        Scanner sc = new Scanner(System.in);
        String userName;
        do
        {
            System.out.println("Please enter your name");
            userName = sc.nextLine();
        } while (Validation.stringLengthCheck(userName) == false || 
                 Validation.isBlank(userName) == true ||
                 Validation.isSpecialCharacter(userName) == true ||
                 Validation.isNumber(userName) == true);
        try
        {
            objUserInput.setName(userName);
        }
        catch (Exception e)
        {
            System.out.println("Error setting username. Applying Unknown as default");
        }
        //sc.close();
    }

    public static void startGame()
    {
        UserInput objUserInput = new UserInput();
        Game.readFile(objUserInput);
        ObstacleGame.createObstaclesArray();
        //User Input
        Game.inputName(objUserInput);
        Game.inputLevel(objUserInput);
        Vehicle userVehicle = Game.inputCar(objUserInput);
        
        int boostSections = userVehicle.getBoostSpeed();
        double vehicleFuel = userVehicle.getMaxFuel();
        int vehicleDamage = userVehicle.getMaxSustainDamage();
        // Adjust Damage and Fuel based on User Input
        ObstacleGame.setCurrentDamage(vehicleDamage);
        ObstacleGame.setCurrentFuel(vehicleFuel);
        System.out.println("Get Set! You have got " + Highway.getHighwayLength() + " highway sections to avoid obstacles and " + userVehicle.getMaxFuel() + " fuel units to reach the end/refuel points.");
        
        // Random Start row for User & set positions on Highway
        int randomUserRow = (int)(Math.random() * 2);
        Highway.setCurrentRow(randomUserRow);
        Highway.setCurrentColumn(0);
        
        // Write Obstacle to Highway and then Display. MUST BE IN THIS ORDER
        Highway.writeObstaclesToHighway();
        Highway.displayHighway(randomUserRow, 0);
        Game.inputMove();
    }

    public static void main(String[] args)
    {
        startGame();
    }

    public static void readFile(UserInput objUserInput)
    {
        ArrayList<Vehicle> vehiclesFromFile = new ArrayList<>();
        String fileContents = ReadWriteFile.readInputFile();
        String[] fileLines = fileContents.split("\\|");
        for (int b = 0; b < fileLines.length; b++)
        {
            String fileLine = fileLines[b];
            String[] lineInput = fileLine.split(",");
            if (lineInput.length == 4)
            {
                String vehicleType = lineInput[0];
                int vehicleSpeed = 0;
                int vehicleDamage = 0;
                try
                {
                    vehicleSpeed = Integer.parseInt(lineInput[1]);
                    vehicleDamage = Integer.parseInt(lineInput[3]);
                }
                catch (Exception e)
                {
                    System.out.println("Hmm we have an issue with Speed And Damage Sustained. Setting to 0");
                }
                double vehicleFuel = 0.0;
                try
                {
                    vehicleFuel = Double.parseDouble(lineInput[2]);
                }
                catch (Exception e)
                {
                    System.out.println("Hmm we have an issue with Vehicle Fuel. Setting to 0");
                }
                vehiclesFromFile.add(new Vehicle(vehicleType, vehicleSpeed, vehicleFuel, vehicleDamage));
            }
            else
            {
                System.out.println("Vehicle does not have the right inputs!");
            }
        }
        objUserInput.setVehicles(vehiclesFromFile);
    }
}