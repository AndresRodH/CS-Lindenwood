-----------------------------------------------------------------------------------------
--
-- main.lua
--
-- Andres Rodriguez
-- A7:
-- Sprites, Icons and animation
-----------------------------------------------------------------------------------------

-- set dark status bar
display.setStatusBar( display.DarkStatusBar )
-- set white background
display.setDefault("background", 1, 1, 1)

-- trying new things: widget
local widget = require( "widget" )

----------- initialize sheet ------------
-- options
local options = {
    frames = {
        {   -- Iddle 1
            x = 0,
            y = 0,
            width = 863,
            height = 1500,
        },
        {   -- Iddle 2
            x = 3356,
            y = 1500,
            width = 973,
            height = 1500,
        },
        {   -- Iddle 3
            x = 4329,
            y = 1500,
            width = 1013,
            height = 1500,
        },
        {   -- Iddle 4
            x = 5342,
            y = 1500,
            width = 973,
            height = 1500,
        },
        {   -- Crouch 1
            x = 863,
            y = 0,
            width = 863,
            height = 1500,
        },
        {   -- Crouch 2
            x = 2589,
            y = 0,
            width = 1013,
            height = 1500,
        },
        {   -- Crouch 3
            x = 3602,
            y = 0,
            width = 1221,
            height = 1500,
        },
        {   -- Crouch 4
            x = 4823,
            y = 0,
            width = 1122,
            height = 1481,
        },
        {   -- Crouch 5
            x = 5945,
            y = 0,
            width = 1122,
            height = 1500,
        },
        {   -- Crouch 6
            x = 7067,
            y = 0,
            width = 1122,
            height = 1479,
        },
        {   -- Crouch 7
            x = 0,
            y = 1500,
            width = 1122,
            height = 1500,
        },
        {   -- Crouch 8
            x = 1122,
            y = 1500,
            width = 1221,
            height = 1500,
        },
        {   -- Crouch 9
            x = 2343,
            y = 1500,
            width = 1013,
            height = 1500,
        },
        {   -- Crouch 10
            x = 1726,
            y = 0,
            width = 863,
            height = 1500,
        },
        {   -- Jump 1
            x = 6315,
            y = 1500,
            width = 891,
            height = 1500,
        },
        {   -- Jump 2
            x = 0,
            y = 3000,
            width = 920,
            height = 1500,
        },
        {   -- Jump 3
            x = 920,
            y = 3000,
            width = 1106,
            height = 1500,
        },
        {   -- Jump 4
            x = 2026,
            y = 3000,
            width = 1111,
            height = 1500,
        },
        {   -- Jump 5
            x = 3137,
            y = 3000,
            width = 1233,
            height = 1500,
        },
        {   -- Jump 6
            x = 4370,
            y = 3000,
            width = 873,
            height = 1500,
        },
        {   -- Jump 7
            x = 5243,
            y = 3000,
            width = 886,
            height = 1500,
        },
        {   -- Jump 8
            x = 6129,
            y = 3000,
            width = 1180,
            height = 1500,
        },
        {   -- Jump 9
            x = 0,
            y = 4500,
            width = 1073,
            height = 1500,
        },
        {   -- Jump 10
            x = 7206,
            y = 1500,
            width = 863,
            height = 1500,
        },
    }
}
local imageSheet = graphics.newImageSheet( "Sprite.png", options )

local sequenceData =
{
    {
        -- idle pose
        name = "idle",
        start = 1,
        count = 4,
        time = 1000,
        loopCount = 0,
    },
    {
        -- crouch action
        name = "crouch",
        start = 5,
        count = 10,
        time = 1000,
        loopCount = 0,
    },
    {
        -- make it jump
        name = "jump",
        start = 16,
        count = 9,
        time = 1500,
        loopCount = 0,
    },
}
 
-- initialize sprite sheet animation
local character = display.newSprite( imageSheet, sequenceData )

-- put it in the middle of the screen
character.x = display.contentWidth/2
character.y = display.contentHeight/2

-- scale it, it is way to big
character:scale(0.5,0.5)

-- set idle to be the default and play it
character:setSequence("idle")
character:play()
 
-------- functions to handle button events:  ---------

-- idle button
local function goIdle( event )
 
    if ( "ended" == event.phase ) then
        character:setSequence("idle")
        character:play()
    end
end

-- crouch button
local function goCrouch( event )
 
    if ( "ended" == event.phase ) then
        character:setSequence("crouch")
        character:play()
    end
end

-- jump button
local function goJump( event )
 
    if ( "ended" == event.phase ) then
        character:setSequence("jump")
        character:play()
    end
end
 
-- create idle button
local idleButton = widget.newButton(
    {
        label = "Idle",
        onEvent = goIdle,
        emboss = false,
        -- rounded rect widget button
        shape = "roundedRect",
        width = 200,
        height = 40,
        cornerRadius = 2,
        fillColor = { default={1,0,0,1}, over={1,0.1,0.7,0.4} },
        strokeColor = { default={1,0.4,0,1}, over={0.8,0.8,1,1} },
        strokeWidth = 4,
        labelColor = { default={ 1, 1, 1 }, over={ 1, 1, 1 } }
    }
)

-- create crouch button
local crouchButton = widget.newButton(
    {
        label = "Crouch",
        onEvent = goCrouch,
        emboss = true,
        -- cicle button
        shape = "circle",
        width = 200,
        height = 40,
        cornerRadius = 2,
        fillColor = { default={ 1, 0.2, 0.5, 0.7 }, over={ 1, 0.2, 0.5, 1 } },
        strokeColor = { default={ 0, 0, 0 }, over={ 0.4, 0.1, 0.2 } },
        strokeWidth = 4,
        labelColor = { default={ 1, 1, 1 }, over={ 1, 1, 1 } }
    }
)

-- create crouch button
local jumpButton = widget.newButton(
    {
        label = "Jump",
        onEvent = goJump,
        emboss = true,
        -- polygon button
        shape = "polygon",
        width = 600,
        height = 200,
        vertices = { 0,-110, 27,-35, 105,-35, 43,16, 65,90, 0,45, -65,90, -43,15, -105,-35, -27,-35, },
        fillColor = { default={ 0.2, 1, 0.7, 0.7 }, over={ 1, 0.9, 1, 0.6 } },
        strokeColor = { default={ 0, 0, 0 }, over={ 0.4, 0.1, 0.2 } },
        strokeWidth = 2,
        labelColor = { default={ 1, 1, 1 }, over={ 1, 1, 1 } }
    }
)

-- jump button position
jumpButton.x = display.contentCenterX + 200
jumpButton.y = display.contentHeight

-- crouch button position
crouchButton.x = display.contentCenterX
crouchButton.y = display.contentHeight

-- idle button position
idleButton.x = display.contentCenterX - 200
idleButton.y = display.contentHeight

