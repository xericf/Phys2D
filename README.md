# Physics Simulator

## What will the application do?

This application will be able to simulate various **2 dimensional objects based on newton's  
three laws of motion**. It will be able to model and showcase different types of physics  
phenomenon through pre-made scenarios designed to highlight a specific aspect of the physics  
engine. One such design may be an interactive demo of a ball bouncing on a user-controlled  
paddle.

## Who will use it?

*Game developers* will be the main audience for this application. They should be easily able to  
see an example and use case of how this physics engine works, and should also be able to  
implement my code into their programs too. *Physics enthusiasts and scientists* are hopefully  
my second target audience, where they may use my program to play around with experiments.

## Why is this project of interest to you?

I believe this project will be extremely rewarding to take on because it will challenge me to  
learn more about physics and about the real-world in general. Also, I hope to learn a lot about  
the practices and thought process behind designing game engines as I know it will help me solve  
difficult problems in the future.


## User stories



As a *user*, I want to:
- Be able to add a ball to the list of physical objects in a World by pressing " t " (lowercase)
- Be able to control a player's movement with the " a " and " d " keys (lowercase, make sure not CAPS locked)
- Be able to quit the game when " q " is pressed
- Be able to clear the balls on the screen when " f " is pressed
- Reverse the velocity of my player when I hit the screen's border

Phase 2
- Be able to save my World's information about its properties and each physical object's instantaneous velocity, position, and acceleration onto a file by pressing " i ".
- Be able to load a World's information from a file by pressing " o ".

Phase 3
- GUI integration: Be able to add a ball to the list of physical objects in a World by pressing " t " (lowercase)  
  which then shows up on the screen as a circle. Other two related events:
  - " f " to clear the balls on the screen
  - Able to click and drag on the balls to move them.
- GUI integration: As a user I want to be able to save and load world information on a file with " i " and " o "


**Phase 4: Task 2**  

Fri Apr 01 01:55:14 PDT 2022
New ball has been added at: x = 438.0, y = 431.0, with an initial velocity of dx = -37.964462, dy = 0.0

Fri Apr 01 01:55:14 PDT 2022
Threw ball from starting point: x = 438.0, y = 431.0, to: x = 438.0, y = 431.0, in 0.066005 seconds

Fri Apr 01 01:55:16 PDT 2022
Threw ball from starting point: x = 433.0, y = 515.0, to: x = 857.0, y = 365.0, in 0.1250194 seconds

Fri Apr 01 01:55:16 PDT 2022
All balls have been cleared

**Phase 4: Task 3**

Overall, I think that I am fairly satisfied with the design of this physics engine because each of the assosciations and
aggregations were very logical, and it feels like most of the relationship of the classes seemed very necessary to be 
structured like that. For example: It makes sense that Colliders and RigidBodies should be different from eachother,
and there must be different types of colliders to satisfy each subtype of RigidBody for correct collision detection.  

Also, I generally like to keep the amount of associations between different classes minimal, and allow for classes
to see only what they need to know. 

In the UML diagram, there are some classes that are sitting by themselves such as the ColliderPoints,
EventLog, Logger, and JsonReader classes. These generally all use static methods to do what they are
required to do, which is why they don't need to be associated with any other classes. Except for ColliderPoints, I chose
to not have any classes associated with that class since I feel like it would be unnecessary. Intead, it is used as a temporary
data structure class used for storing valuable information about collisions between colliders to be later used in a Solver
class which is responsible for handling reactions of RigidBodies to collisions.

There are some improvements that I think I could make with the design as I move forward with this project.
One such improvement is by making the Player extend Rect, because they are basically the same thing, but one just has 
input functionality. 

