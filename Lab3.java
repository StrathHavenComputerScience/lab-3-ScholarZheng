
// To experiment with lists in SwapAll
import java.util.*;

public class Lab3
{
    //// HELPER METHODS ARE AT THE BOTTOM, THANK YOU :) ////
    public static void testLightCandles1()
    {
        Robot.load("candles1.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }
    
    public static void testLightCandles2()
    {
        Robot.load("candles2.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }
    
    public static void lightCandles()
    {
        //NEW CODE: "Angel Lighter" Method: Infinite Candles and Height (For Fun :) )
        
        int currentVertical = 0; //Tracks how far the robot descends.
        int currentHorizontal = 0; //Tracks the current position of the robot.
        int totalHorizontal = 0; //Tracks the total amount of horizontal blocks there are.
        boolean runTime = true; //Stops the program when it is equal to false.
        
        //This brings the Robot to the top of the map.
        Robot.turnLeft();
        while (Robot.frontIsClear() == true) {
            Robot.move();
        }
        Lab3.turnRight();
        
        //This calculates how long, horizontally, the map is.
        while (Robot.frontIsClear() == true) {
           Robot.move();
           currentHorizontal++;
        }
        Lab3.turnAround(); 
        totalHorizontal = currentHorizontal;
        
        //This returns the robot back to where it started before calculating
        // how long the map is, horizontally.
        while (currentHorizontal > 0) {
            Robot.move();
            currentHorizontal--;
        }
        Lab3.turnAround();
        
        /*This makes the robot light candles. The robot first moves to 
         * the block over the candle, then returns it to where it was
         * before it lit the candle. Finally, the robot moves onto the
         * next candle and repeats the process until it reaches the end 
         * of the map.
         */ 
        while (currentHorizontal < totalHorizontal && runTime == true) {
            if (Robot.frontIsClear() == true) {
                Robot.move();
                Lab3.turnRight();
                
                while (Robot.frontIsClear() == true) {
                    Robot.move();
                    currentVertical++;
                }
                Lab3.changeToDark();
                Lab3.turnAround();
                while (currentVertical > 0) {
                    Robot.move();  
                    currentVertical--;
                }
                Lab3.turnRight();
                Robot.move();
                
            } else {
                runTime = false;
            }    
            
        }
        
        // OLD CODE : "Candle Climber" Method: Only works for the assignment's conditions.
        /*
        for (int candles=1; candles<=10; candles++) {
            int spacesUp = 0;
            
            // Tests if a candle part is in front of robot, and calculates how tall it is.
            while (Robot.frontIsClear() == false) {
                Robot.turnLeft();
                Lab3.moveWithCondition();
                spacesUp = spacesUp + 1;
                Lab3.turnRight();
            }
            
            // Light candle and move to next candle's base. 
            // This can be used for an infinately tall candle.
            if (Robot.frontIsClear() == true) {
                Lab3.moveWithCondition();
                Lab3.changeToDark();
                Lab3.moveWithCondition();
                Lab3.turnRight();
                
               while (spacesUp != 0 || spacesUp < 0) {
                    Lab3.moveWithCondition();
                    spacesUp = spacesUp - 1;
                }
                Robot.turnLeft();
            };

        }

        */ 
    
    };
    
    //Run this method to test completeRoom on map room1.txt
    public static void testCompleteRoom1()
    {
        Robot.load("room1.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }
    
    //Run this method to test completeRoom on map room2.txt
    public static void testCompleteRoom2()
    {
        Robot.load("room2.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }
    
    //Complete this method.  You will need to write additional helper methods.
    public static void completeRoom()
    {
        /* Pre-Code Ideas :
        * Only write the code for placing a dark spot, if there is a dark spot, and loop this
        * until it is done. Outside of the testing of there is no block in front of it, make it
        * move foward and check the next one. After looping it for the amount of times,
        * make it move into the next corner, place a block, and then go back out but face
        * the new direction properly. Loop this entire thing four times to cycle through
        * the entire box.
        */
       
       // This larger loop repeats a total of four times (once for each side)
       for (int rotation = 0; rotation <= 3; rotation++) {
           // This smaller loop repeats a total of four times (once for each block)
           for (int timesPerSide = 0; timesPerSide <= 3; timesPerSide++) {
               Robot.turnLeft();
               //The robot checks if there is an open block.
               // Regardless of condition, the robot will be at the next block.
               if (Robot.frontIsClear() == true) {
                   Robot.move();
                   Lab3.changeToDark();
                   Lab3.turnAround();
                   Robot.move();
                   Robot.turnLeft();
                   Lab3.moveWithCondition();
                   
                   } else if (Robot.frontIsClear() != true) {
                     // If the block is not open.
                     Lab3.turnRight();
                     Lab3.moveWithCondition();
                }
               //If the block is open, the robot will go into 
               //the block and make it dark. Then it will move out
               // and move onto the next block
            }
            Robot.turnLeft();
            
            //The Robot should be at the corner of the room.
            // This is a special method to get those pesky corner blocks.
            if (Robot.frontIsClear() == true) {
                // If the block next to the corner is open.
                Robot.move();
                Lab3.changeToDark();
                Lab3.turnAround();
                Robot.move();
            } else if (Robot.frontIsClear() != true) {
                // If the block next to the corner is closed.
                Lab3.turnAround();
            } 
            //The Robot should be ready to complete the next side of the room.
            //It is parallel to the side.
        }
    }
    
        //Run this method to test swapAll on map swap1.txt
    public static void testSwapAll1()
    {
        Robot.load("swap1.txt");
        Robot.setDelay(0.05);
        swapAll();
    }
    
    //Run this method to test swapAll on map swap2.txt
    public static void testSwapAll2()
    {
        Robot.load("swap2.txt");
        Robot.setDelay(0.05);
        swapAll();
    }

    //Complete this method.  You will need to write additional helper methods.
    public static void swapAll()
    {  
          int position = 0; 
          // Keeps track of the robot's location as a key.
          int heightOfMap = 0;
          // These lists keep track of each column's lights and darks.
          List leftColumn = new ArrayList<>();
          List rightColumn = new ArrayList<>();
         
          Lab3.turnRight();
          Robot.move();
          Robot.turnLeft();
          
          // Gets the height of the map.
          while (Robot.frontIsClear()) {
              Robot.move();
              heightOfMap++;
            } 
          Lab3.turnAround();
          while (Robot.frontIsClear()) {
              Robot.move();
            }
          Lab3.turnAround();
            
          // Scans the two columns and tests if each block is dark. It will 
          // input a boolean value in an index determined by the robot's position.
          for (int Fcolumn = 1; Fcolumn <= 2; Fcolumn++) {
              while (position <= heightOfMap) {
                  if (Fcolumn == 1 && Robot.onDark()) {
                      rightColumn.add(position, true);
                    } else if (Fcolumn == 1 && Robot.onDark() == false) {
                      rightColumn.add(position, false);
                    } else if (Fcolumn == 2 && Robot.onDark()) {
                      leftColumn.add(position, true);
                    } else if (Fcolumn == 2 && Robot.onDark() == false) {
                      leftColumn.add(position, false);
                    }
                  Lab3.moveWithCondition();
                  position++;
                }
              position = 0;
              Robot.turnLeft();
              Robot.move();
              Robot.move();
              Robot.turnLeft();
        } 
        
        Robot.turnLeft();
        Robot.move();
        Robot.move();
        Lab3.turnRight();
        position = 0;
        
        // This loop swaps the two columns by iterating through the lists
        // using the position varialbe to keep track of the index and Robot.
        for (int Lcolumn = 1; Lcolumn <= 2; Lcolumn++) {
            while (position <= heightOfMap) {
                if (Lcolumn == 1) {
                   if (rightColumn.get(position).equals(true)) {
                        Lab3.changeToDark();
                   } else {
                        Lab3.changeToLight();
                   }
                } else if (Lcolumn == 2) {
                   if (leftColumn.get(position).equals(true)) {
                        Lab3.changeToDark();
                   } else {
                        Lab3.changeToLight();
                   }
                } 
                Lab3.moveWithCondition();
                position++;
            }
            position = 0;
            Lab3.turnRight();
            Robot.move();
            Robot.move();
            Lab3.turnRight();
        }
        
        // OLD Pre-Code Ideas : 
          //Take one side, store the value, move it to the other side, check if it is matching color
          //if matching color, proceed to next line, if different, place line, move back to old square,
          //change old square. This code is built specifically for the assignment.

       /*   
        boolean toChange = false;
        // This variable check if the robot needs to swap colors.

        for (int move=0; move <10; move++) {
             Robot.turnLeft();
             Lab3.moveWithCondition();
             toChange = false;
             
             // Tests if Left is Dark or Light
             if (Robot.onDark()) {
                 toChange = true;
                 Lab3.turnAround();
                 Lab3.moveWithCondition();
                 Lab3.moveWithCondition(); 
                 
                 } else {
                     Lab3.turnAround();
                     Lab3.moveWithCondition();
                     Lab3.moveWithCondition();
                     
             }
             
             // If Left is Dark and...
             if (toChange == true) {
                // if Right is Dark, it brings the robot to the next row.
                if (Robot.onDark()) {
                     Lab3.turnAround();
                     Lab3.moveWithCondition();
                     Lab3.turnRight();
                     
                     // if Right is Light, it switches, then it brings the robot to the next row.
                    } else {
                        Lab3.changeToDark();
                        Lab3.turnAround();
                        Lab3.moveWithCondition();
                        Lab3.moveWithCondition(); 
                        Lab3.changeToLight();
                        Lab3.turnAround();
                        Lab3.moveWithCondition();
                        Robot.turnLeft();
                }
            }
             
             // If Left is Light and...
            if (toChange == false) {
                 // if Right is Dark, it switches, then it brings the robot to the next row.
                 if (Robot.onDark()) {
                     Lab3.changeToLight();
                     Lab3.turnAround();
                     Lab3.moveWithCondition();
                     Lab3.moveWithCondition();
                     Lab3.changeToDark();
                     Lab3.turnAround();
                     Lab3.moveWithCondition();
                     Robot.turnLeft();
                     
                     // If Right is Light, it brings the robot to the next row.
                    } else {
                        Lab3.turnAround();
                        Lab3.moveWithCondition();
                        Lab3.turnRight();
                    
                }
            }
                Lab3.moveWithCondition();
        }*/
    }

  //Don't run these. I will!
  public static void testLightCandles3()
  {
    Robot.load("candles3.txt");
    Robot.setDelay(0.05);
    lightCandles();
  }
  
  public static void testLightCandles4()
  {
    Robot.load("candles4.txt");
    Robot.setDelay(0.05);
    lightCandles();
  }
  public static void testCompleteRoom3()
  {
     Robot.load("room3.txt");
     Robot.setDelay(0.05);
     completeRoom();
  }
  
  public static void testCompleteRoom4()
  {
    Robot.load("room4.txt");
    Robot.setDelay(0.05);
    completeRoom();
  }
  
  public static void testSwapAll3()
  {
    Robot.load("swap3.txt");
    Robot.setDelay(0.05);
    swapAll();
  }
  
  //Run this method to test swapAll on map swap2.txt
  public static void testSwapAll4()
  {
    Robot.load("swap4.txt");
    Robot.setDelay(0.05);
    swapAll();
  }
  
  
  ////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////HELPER METHODS//////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////
  public static void turnRight() 
    {
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }
  public static void changeToLight() 
    {
        if (Robot.onDark() == true) {
            Robot.makeLight();
        }
    } 
  public static void changeToDark() 
    {
        if (Robot.onDark() != true) {
            Robot.makeDark();
        } 
    } 
  public static void moveWithCondition()
    { 
        if (Robot.frontIsClear() == true) {
            Robot.move();
        }
    }
  public static void turnAround()
    {
       Robot.turnLeft();
       Robot.turnLeft(); 
    }

  ////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////




}
