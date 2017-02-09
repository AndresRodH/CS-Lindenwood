-----------------------------------------------------------------------------------------
--
-- main.lua
--
-- Author: Andres Rodriguez
-- Version: 0.1
--
-- Assignment #1: Simple app showing a "Hello World" message and other stuff
-- ...
-- ...
-- ... and cool transitions
-----------------------------------------------------------------------------------------

-- set up center location variables
local centerX = display.contentCenterX
local centerY = display.contentCenterY

-- set up background
local background = display.newImageRect("back.png", display.contentWidth+100, display.contentHeight+100)
background.x = centerX
background.y = centerY

-- set up logo
local logo = display.newImageRect("scribble.png", 100,200)
logo.x = centerX + 50
logo.y = centerY + 170
logo.alpha = 0
transition.fadeIn(logo, {time = 2000, delay = 3000})

-- Hello World
local hello = display.newText("Hello World", centerX-30, centerY-80, "verdana", 30)
hello.alpha = 0
transition.fadeIn(hello, {time = 1000})

-- by Andres Rodriguez
local auth = display.newText("by Andres Rodriguez", centerX-10, centerY-50, "verdana", 20)
auth.alpha = 0
transition.fadeIn(auth, {time = 1000})

-- short text... with even more fade ins
local a1 = display.newText("Assignment #1", 100, centerY, "verdana", 20)
local line1 = display.newText("La razon no tiene voto", 110, centerY+30, "verdana", 15)
local line2 = display.newText("Y la conciencia esta sin voz", 130, centerY+45, "verdana", 15)
local line3 = display.newText("- Rawayana", 165, centerY+70, "verdana", 15)

a1.alpha = 0
line1.alpha = 0
line2.alpha = 0
line3.alpha = 0

transition.fadeIn(a1, {time = 1000, delay = 1000})
transition.fadeIn(line1, {time = 2000, delay = 1500})
transition.fadeIn(line2, {time = 2000, delay = 1500})
transition.fadeIn(line3, {time = 2000, delay = 1500})