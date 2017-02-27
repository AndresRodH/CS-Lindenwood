local composer = require( "composer" )
local physics = require("physics")
local scene = composer.newScene()
physics.start()
--physics.setDrawMode( "hybrid" )

function scene:create( event )
	local sceneGroup = self.view
	--The primary object in the Composer library is the scene object. This is an event listener that responds to specific events, and it contains a unique self.view property which is a reference to the display group associated with the scene. This self.view is where you should insert visual elements pertaining to the scene.
	
    -- set background
    local background = display.newImageRect( "overworld_bg.png", display.contentWidth, display.contentHeight )
    background.x = display.contentWidth / 2
    background.y = display.contentHeight / 2	
    sceneGroup:insert( background )

    -- place boxes
    local boxes = {}

    for i = 1, 5 do
        for j = 1, 5 do
            boxes[i] = display.newImage( "box.png", 1400 + (i*82),display.contentHeight - 120 - (j*82) )
            boxes[i].height = 80
            boxes[i].width = 80
            physics.addBody( boxes[i], { density=0.2, friction=0.1, bounce=0.5 } )
            boxes[i]:toFront()
            sceneGroup:insert( boxes[i] )
        end
    end

    -- place triforce piramid
    local triangleShape = {0,-150,150,150,-150,150}
    local triangle1 = display.newImage("triangle.png")
    triangle1.x = 225
    triangle1.y = display.contentHeight - 300
    triangle1.width = 300
    triangle1.height = 300

    local triangle2 = display.newImage("triangle.png")
    triangle2.x = 522
    triangle2.y = display.contentHeight - 300
    triangle2.width = 300
    triangle2.height = 300

    local triangle3 = display.newImage("triangle.png")
    triangle3.x = (225 + 525)/2
    triangle3.y = display.contentHeight - 604
    triangle3.width = 300
    triangle3.height = 300

    -- it is not a perfect pyramid :( play with the numbers
    physics.addBody(triangle1, {density=1,friction=1,bounce=.5,shape=triangleShape})
    physics.addBody(triangle2, {density=1,friction=1,bounce=.5,shape=triangleShape})
    physics.addBody(triangle3, {density=0,friction=1,bounce=.1,shape=triangleShape})

    -- base of the world
    local base = display.newRect(0, display.contentHeight - 165,
                                    display.contentWidth, 100)
    base.anchorX = 0
    base.anchorY = 0
    base.alpha = 0
    physics.addBody(base, "static", {})

    -- top limit of the world
    local ceiling = display.newRect(0, -20, display.contentWidth, 20)

    ceiling.anchorX = 0
    ceiling.anchorY = 0
    physics.addBody(ceiling, "static", {})
    
    -- left limit of the world
    local lWall = display.newRect(-20,0,
                                    20, display.contentHeight)
    lWall.anchorX = 0
    lWall.anchorY = 0
    physics.addBody(lWall, "static", {})

    -- right limit of the world
    local rWall = display.newRect(display.contentWidth,0,
                                    20, display.contentHeight)
    rWall.anchorX = 0
    rWall.anchorY = 0
    physics.addBody(rWall, "static", {})
    
    -- load sounds: spawn, explosion and menu tap
    local myombSound = audio.loadSound("mparty8_bob-omb.wav")
    local byeBob = audio.loadSound("bye_bob-omb.wav")
    local pauseSound = audio.loadSound("pause.wav")

    -- setbob function
    function setbob ( event )

        if(event.phase == "began") then
            -- display bob correctly and add it to the scene
            local bob = display.newImage( "bob_omb.png", event.x,event.y )
            bob.height = 80
            bob.width = 80
            physics.addBody( bob, "dynamic", { density=0.2, friction=0.1, bounce=0.5, radius=30 })
            -- play bob-omb cry
            audio.play(myombSound)

            local omb = ""
            local explosion = ""

            -- boom!
            local function blast( event )
                -- play explosion audio
                audio.play(byeBob)
                -- set up explosion!
                omb = display.newCircle( bob.x, bob.y, 80 )
                explosion = display.newImage( "expl.png", bob.x, bob.y )
                bob:removeSelf()
                omb:setFillColor(0,0,0, 0)
                physics.addBody( omb, "static", {isSensor = true} )
                omb.myName = "omb"
                omb.collision = onLocalCollision
                omb:addEventListener( "collision", omb )
            end

            -- removes the bob-omb and the explosion graphics
            local function removeStuff( event )
                omb:removeSelf()
                explosion:removeSelf()
            end

            -- set a delay of 3 seconds for the blast!
            timer.performWithDelay(3000, blast )
            -- remove the explosion graphics
            timer.performWithDelay(3100, removeStuff)
        end
    end

    -- this generates the force the bomb will make. Play with the numbers.
    function onLocalCollision( self, event )
        if ( event.phase == "began" and self.myName == "omb" ) then
            local forcex = event.other.x-self.x
            local forcey = event.other.y-self.y-20
            if(forcex < 0) then
                forcex = 0-(80 + forcex*100)-12
            else
                forcex = 80 - forcex*100+12
            end
            event.other:applyForce( forcex, forcey, self.x, self.y )
        end
    end
	
    -- set up menu button    
    local menu = display.newImageRect( "menu.png", 150, 50 )
    menu.x = display.contentWidth - 100
    menu.y = 50

    local function goMenu( event )
        print("Menu page")
        audio.play(pauseSound)
		composer.gotoScene( "menu", "zoomInOutFade", 400  )
		--return true -- prevents other objects from receiving the event, and basically means that it worked. 
    end

    menu:addEventListener("tap", goMenu)

    -- listens to a touch so a bob-omb can be created
    background:addEventListener("touch",setbob)

    -- add the rest of the objects to the scene
    sceneGroup:insert( base )
    sceneGroup:insert( ceiling )
    sceneGroup:insert( menu )
    sceneGroup:insert( lWall )
    sceneGroup:insert( rWall )
    sceneGroup:insert( triangle1 )
    sceneGroup:insert( triangle2 )
    sceneGroup:insert( triangle3 )

end

function scene:show( event )
	
	local phase = event.phase
	
	if "did" == phase then
			
	-- remove previous scene's view
	--composer.removeScene( "scene1" )
    
	end
	
end

function scene:hide( event )
	
	local phase = event.phase
	if "will" == phase then

		-- remove touch listener for image
		-- image:removeEventListener( "touch", image )
	end
end

function scene:destroy( event )
    print( "DESTROY!!!!" )
	local stage=display.getCurrentStage()
    while stage.numChildren >0 do
    local obj=stage[1]
    print("removed ".. stage.numChildren .. " from green.")
    obj:removeSelf()
    obj=nil
    end
	
end

---------------------------------------------------------------------------------

-- Listener setup
scene:addEventListener( "create", scene )
scene:addEventListener( "show", scene )
scene:addEventListener( "hide", scene )
scene:addEventListener( "destroy", scene )

---------------------------------------------------------------------------------

return scene