-----------------------------------------------------------------------------------------
--
-- main.lua
--
-----------------------------------------------------------------------------------------
local centerX = display.contentWidth/2
local centerY = display.contentHeight/2
black = {1,1,1}

-- *******************  ASSEMBLING CROSSBONES  ***********************
-- left bone
lBone = display.newRect(centerX,centerY-50, 30, 330)
lULTip = display.newCircle(centerX-13,centerY-225, 20)
lURTip = display.newCircle(centerX+11,centerY-225, 20)
lBLTip = display.newCircle(centerX-13,centerY+130, 20)
lBRTip = display.newCircle(centerX+11,centerY+130, 22)
leftBone = display.newGroup()
leftBone:insert(lBone)
leftBone:insert(lULTip)
leftBone:insert(lURTip)
leftBone:insert(lBLTip)
leftBone:insert(lBRTip)
leftBone.rotation = 45
leftBone.x = 180
leftBone.y = -10
-- right bone
rBone = display.newRect(centerX,centerY-50, 30, 330)
rULTip = display.newCircle(centerX-13,centerY-225, 20)
rURTip = display.newCircle(centerX+11,centerY-225, 20)
rBLTip = display.newCircle(centerX-13,centerY+130, 20)
rBRTip = display.newCircle(centerX+11,centerY+130, 22)
rightBone = display.newGroup()
rightBone:insert(rBone)
rightBone:insert(rULTip)
rightBone:insert(rURTip)
rightBone:insert(rBLTip)
rightBone:insert(rBRTip)
rightBone.rotation = -45
rightBone.x = -90
rightBone.y = 220
-- group both bones
crossbones = display.newGroup()
crossbones:insert(leftBone)
crossbones:insert(rightBone)

-- ******************* ASSEMBLING THE HEAD  *******************************
jaw = display.newCircle(centerX-2, centerY+73, 48)
jaw:setStrokeColor(black)
jaw.strokeWidth = 4
-- teeth UPPER PART
uT1 = display.newRect(centerX-37,280,26,29)
uT1:setStrokeColor(black)
uT1.strokeWidth = 4

uT2 = display.newRect(centerX-12,280,24,29)
uT2:setStrokeColor(black)
uT2.strokeWidth = 4

uT3 = display.newRect(centerX+12,280,24,29)
uT3:setStrokeColor(black)
uT3.strokeWidth = 4

uT4 = display.newRect(centerX+35,280,23,29)
uT4:setStrokeColor(black)
uT4.strokeWidth = 4

-- teeth BOTTOM PART
bT1 = display.newRect(centerX-37,305,26,25)
bT1:setStrokeColor(black)
bT1.strokeWidth = 4

bT2 = display.newRect(centerX-12,305,24,25)
bT2:setStrokeColor(black)
bT2.strokeWidth = 4

bT3 = display.newRect(centerX+12,305,24,25)
bT3:setStrokeColor(black)
bT3.strokeWidth = 4

bT4 = display.newRect(centerX+35,305,23,25)
bT4:setStrokeColor(black)
bT4.strokeWidth = 4

-- face
head = display.newCircle(centerX, centerY-50, 90)
leftEye = display.newCircle(centerX-35, centerY-20,25)
rightEye = display.newCircle(centerX+35, centerY-20,25)
nose = display.newCircle(centerX,centerY+15, 10)
leftEye:setFillColor(black)
rightEye:setFillColor(black)
nose:setFillColor(black)

-- head coloring
gradient = {type = "gradient", color1 = {1, .7, 0}, color2 = {1,1,1}, direction = "down"}
head.fill = gradient
head:setStrokeColor(black)
head.strokeWidth = 4

-- "straw hat" band
band = display.newRoundedRect(centerX,centerY-68,175,30,15)
band:setFillColor(1,0,0)
band:setStrokeColor(black)
band.strokeWidth = 4

-- "straw hat" border
lowerHat = display.newRoundedRect(centerX,centerY-55,230,12,5)
lowerHat:setFillColor(1,.7,0)
lowerHat:setStrokeColor(black)
lowerHat.strokeWidth = 4

-- "straw hat" lines LEFTMOST -> RIGHTMOST
line1 = display.newLine(95,147,102,138,112,128)
line1:setStrokeColor(black)
line1.strokeWidth = 4

line2 = display.newLine(108,150,112,143,120,133)
line2:setStrokeColor(black)
line2.strokeWidth = 4

line3 = display.newLine(120,146,127,138)
line3:setStrokeColor(black)
line3.strokeWidth = 4

line4 = display.newLine(202,150,201,146,196,138)
line4:setStrokeColor(black)
line4.strokeWidth = 4

line5 = display.newLine(215,150,216,148,212,138,210,135,205,130)
line5:setStrokeColor(black)
line5.strokeWidth = 4

-- ********************  MOCKUP IMAGE  ******************************
o = display.newImageRect("strawhat.png",100,100)
o.x = centerX
o.y = centerY+200
