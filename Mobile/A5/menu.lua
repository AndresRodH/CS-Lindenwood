--[[

menu tab

--]]

local composer = require("composer") 
local scene = composer.newScene()

function scene:create( event )
	local sceneGroup = self.view
	--The primary object in the Composer library is the scene object. 
	--This is an event listener that responds to specific events, 
	--and it contains a unique self.view property which is a reference to the display group associated with the scene. 
	--This self.view is where you should insert visual elements pertaining to the scene.
	
    background = display.newRect(0,0,display.contentWidth,display.contentHeight)	
	background:setFillColor( .13, .60, .95 )

	background.x = display.contentCenterX
	background.y = display.contentCenterY
	
    -- set up title
	local title = display.newImageRect( "title.png", 900, 400 )
    title.x = display.contentCenterX
    title.y = display.contentCenterY - 100
	
    -- load button sounds
    local playSound = audio.loadSound("coin.wav")
    local jumpSound = audio.loadSound("jump.wav")
    local appsSound = audio.loadSound("appears.wav")
    local powrSound = audio.loadSound("power.wav")

    -- set up play button
    local play = display.newImageRect( "play.png", 250, 100 )
    play.x = display.contentCenterX
    play.y = display.contentCenterY + 300

    local function goPlay( event )
        print("Game starting!")
        audio.play(playSound)
		composer.gotoScene( "game", "zoomInOutFade"  )
		--return true -- prevents other objects from receiving the event, and basically means that it worked. 
    end
	
    play:addEventListener("tap", goPlay)

    -- set up help button    
    local help = display.newImageRect( "help.png", 150, 50 )
    help.x = display.contentCenterX/2
    help.y = display.contentCenterY + 300

    local function goHelp( event )
        print("Help page")
        audio.play(appsSound)
		composer.gotoScene( "help", "slideRight"  )
		--return true -- prevents other objects from receiving the event, and basically means that it worked. 
    end

    help:addEventListener("tap", goHelp)
    
    local about = display.newImageRect( "about.png", 150, 50 )
    about.x = display.contentCenterX/2 + display.contentCenterX
    about.y = display.contentCenterY + 300

    local function goAbout( event )
        print("About page")
        audio.play(powrSound)
		composer.gotoScene( "about", "slideLeft"  )
		--return true -- prevents other objects from receiving the event, and basically means that it worked. 
    end

    about:addEventListener("tap", goAbout)

    sceneGroup:insert( background )
	sceneGroup:insert( title )
    sceneGroup:insert( play )
    sceneGroup:insert( help )
    sceneGroup:insert( about )
	
end

function scene:show( event )
	
	local phase = event.phase
	
	if "did" == phase then
			
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

end

---------------------------------------------------------------------------------

-- Listener setup
scene:addEventListener( "create", scene )
scene:addEventListener( "show", scene )
scene:addEventListener( "hide", scene )
scene:addEventListener( "destroy", scene )

---------------------------------------------------------------------------------

return scene