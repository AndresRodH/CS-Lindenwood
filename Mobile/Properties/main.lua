-----------------------------------------------------------------------------------------
--
-- main.lua
--
-----------------------------------------------------------------------------------------
local centerX = display.contentWidth/2
local centerY = display.contentHeight/2

-- drawing
myRectangle = display.newRect(centerX,centerY,200,50)
myRoundRectangle = display.newRoundedRect(centerX,centerY+150,200,150,30)
myCirc = display.newCircle(centerX,centerY-200,50)

-- fill color
myCirc:setFillColor(.118,.329,.306, .5)
-- rotate
myRectangle.rotation = 15

-- gradient
myGradient = {type="gradient", color1={1,0,0},color2={1,.7,0},direction='right'}
myRoundRectangle:setFillColor(myGradient)

-- drawing lines
someShape = display.newLine(50,60,300,300)
someShape:setStrokeColor(0,1,0)
someShape.strokeWidth = 10
someShape:append(50,500, 100, 350, 50, 60)

-- ************************  DAY TWO  **********************************

yellowSpot = display.newCircle( 200, 200, 100 )
yellowSpot:setFillColor(1,1,0)

myImage = display.newImage( "SkullPepper.png", 200,200)
myImage:scale(.25,.25)

yellowSpot.alpha = .7

myGroup = display.newGroup()
myGroup:insert(yellowSpot)
myGroup:insert(myImage)
myGroup.rotation = 15

title = display.newText( "Suh Dude",centerX ,centerY-200,"arial",60 )
-- 33.3, 1.6, 0
someColor = {.333,.16,0}
title.fill = someColor

myOtherRect = display.newRect(display.contentWidth/2, display.contentHeight/2, display.contentWidth/2, display.contentHeight/2)
myOtherRect:toBack()
