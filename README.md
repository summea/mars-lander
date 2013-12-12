mars-lander
===========

Design Patterns Final

example output:

    Mars: Planet View
    -----------------
    0000
    0100
    0020
    0000

    Mars Lander System
    ------------------
    Robot Name: Norris

    ...............
    : initial tests :
    ```````````````

    + test init bot localhost database connection:
    % connected to server: localhost

    + test bot controller change:

    currently controlling robot: Norris

    + test list installed software extensions:

    + test adding software extensions:
    :: adding additional software...
    :: adding even more software...

    + test removing software extensions:
    :: removing additional software...

    + test list installed software extensions:
    :: even more software

    + test list installed components:
    :: camera ... status: OK
    :: mapper ... status: OK
    :: scanner ... status: OK

    + test equip camera component:
    checking if component exists...
    camera exists!
    equipping camera for use...

    + test component becomes damaged:

    + test list installed components:
    :: camera ... status: OK
    :: mapper ... status: OK
    :: scanner ... status: damaged

    + test equip damaged scanner component:
    checking if component exists...
    scanner exists!
    equipping scanner for use...
    >>ERROR<< Sorry... this component is having issues at the moment. scanner can not be equipped at this time.

    + test currently equipped component:
    camera

    - test equip NON-included flashlight component:
    checking if component exists...
    >>ERROR<< flashlight component was not included with this robot.

    + test currently equipped component:
    camera

    + test remote commands:
    getting remote command...
    receving data... 
    > test remote commands

    + test photo process:
    taking photo...
    sending back photo...


    ...............
    : pattern tests :
    ```````````````

    == PN3: ABSTRACT FACTORY PATTERN ==
    == PN2: FACTORY METHOD ==

    + test abstract factory:
    Preparing Atmos Sample Quick Scan
    checking supplies for Atmos Sample Quick Scan
    preparing test strip
    preparing test solution
    collecting a sample: 
    ||| Atmos Sample Quick Scan 

    Preparing Atmos Sample Full Scan
    checking supplies for Atmos Sample Full Scan
    preparing test strip
    preparing test solution
    collecting a sample: 
    ||| Atmos Sample Full Scan 

    Preparing Terra Sample Full Scan
    checking supplies for Terra Sample Full Scan
    preparing test strip
    preparing test solution
    collecting a sample: 
    ||| Terra Sample Full Scan 

    Preparing Terra Sample Full Scan
    checking supplies for Terra Sample Full Scan
    preparing test strip
    preparing test solution
    collecting a sample: 
    ||| Terra Sample Full Scan 


    == PN10: DECORATOR PATTERN ==

    + test camera equipment:
    camera type: Cannon

    adding camera lens...
    camera type: Cannon, lens

    adding camera filter...
    camera type: Cannon, lens, filter

    + test moves:

    moving N
    inserting current location: 1,1 data into database
    checking if component exists...
    camera exists!
    equipping camera for use...
    >> turning camera on
    >> rotating camera 30 degrees
    >> snapping...
    >> turning camera off
    >>> analyzing object...found a rock
    inserting rock data into database <<<

    moving E
    inserting current location: 2,1 data into database
    >>> there is nothing here <<<

    moving S
    inserting current location: 2,2 data into database
    checking if component exists...
    camera exists!
    equipping camera for use...
    >> turning camera on
    >> rotating camera 30 degrees
    >> snapping...
    >> turning camera off
    >>> analyzing object...found a sculpture
    inserting sculpture data into database <<<

    moving W
    inserting current location: 1,2 data into database
    >>> there is nothing here <<<

    moving N
    inserting current location: 1,1 data into database
    >>> analyzing object...found a rock ---> is already in database <<<


    locations visited history:

    1,1
    2,1
    2,2
    1,2
    1,1


    + test set controller/operator:

    + test presidential BygoneBerry Phone control:
    currently controlling robot: President


    == PN14: ADAPTER PATTERN ==

    + test software extension break-out:
    installing NASA software extension...
    installing Kaspersky software extension...
    installing Luxoft software extension...

    + test component break-out:
    installing ASUS component...
    installing Plustek component...


    == PN1: SINGLETON PATTERN ==
    % connected to server: db-server and database is specified
    [test-data-a, test-data-b, test-data-c]


    == PN4: TEMPLATE METHOD PATTERN ==
    == PN9: STRATEGY PATTERN ==

    + test initializing component frameworks...

    :: camera ... status: OK

    >> initializing...

    :: mapper ... status: OK

    >> initializing...

    :: scanner ... status: damaged

    >> initializing...
    >> recalibrating scanner
    >> recalibrating lens behavior...

    Component Status:
    :: camera ... status: OK
    :: mapper ... status: OK
    :: scanner ... status: OK


    == PN8: STATE PATTERN ==

    + test current state...
    I'm available right now...
    I'm NOT getting a command right now...
    I'm NOT processing a command right now...
    I'm NOT running a command right now...


    == PN7: OBSERVER PATTERN ==

    + test current command display...


    == PN6: COMMAND PATTERN ==

    !! robot moving north !!
    <current command:> move n

    !! robot moving northeast !!
    <current command:> move ne

    !! robot moving east !!
    <current command:> move e

    !! robot moving southeast !!
    <current command:> move se

    !! robot moving south !!
    <current command:> move s

    !! robot moving southwest !!
    <current command:> move sw

    !! robot moving west !!
    <current command:> move w

    !! robot moving northwest !!
    <current command:> move nw


    == PN11: PROXY PATTERN ==

    Logged in as: President
    Current permissions level: president
    We're sorry, that feature must be broken!

    Logged in as: Admin
    Current permissions level: admin


    == PN12: COMPOSITE PATTERN ==
    == PN5: ITERATOR PATTERN ==

    Commands Menu   
    ----------------------------------------
    1) Move SW (move robot southwest)
    2) Move S (move robot south)
    3) Move SE (move robot southeast)
    4) Move W (move robot west)
    5) Stop Move (stop robot from moving)
    6) Move E (move robot east)
    7) Move NW (move robot northwest)
    8) Move N (move robot north)
    9) Move NE (move robot northeast)
    10) Scan (use scanner)
    ----------------------------------------
    > 


    == PN13: FACADE PATTERN ==

    + test robot selfie
    prepping for robot selfie...
    >> turning projector on
    >> projecting fake MARS background
    >> turning flashlight on
    >> rotating flashlight 180 degrees
    >> turning camera on
    >> rotating camera 180 degrees
    >> snapping...
    >> sending photo...
    >> turning camera off
    >> turning flashlight off
    >> turning projector off

    + test RoboScript...

    moveNorth command running...
    moving N
    inserting current location: 1,0 data into database

    moveSouth command running...
    moving S
    inserting current location: 1,1 data into database

    moveEast command running...
    moving E
    inserting current location: 2,1 data into database

    moveWest command running...
    moving W
    inserting current location: 1,1 data into database

    selfie command running...
    prepping for robot selfie...
    >> turning projector on
    >> projecting fake MARS background
    >> turning flashlight on
    >> rotating flashlight 180 degrees
    >> turning camera on
    >> rotating camera 180 degrees
    >> snapping...
    >> sending photo...
    >> turning camera off
    >> turning flashlight off
    >> turning projector off

