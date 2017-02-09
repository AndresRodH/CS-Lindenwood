local physics = require("physics")
physics.start()
--physics.setDrawMode( "hybrid" )

local Yellow = display.newCircle(200,200,80)
local Blue = display.newCircle(300,400,50)
local Red = display.newCircle(800,150,100)
local spinner = display.newRect(display.contentWidth/2,1000,
                                100, 100)
Blue:setFillColor(0,0,1)
Yellow:setFillColor(1,1,0)
Red:setFillColor(1,0,0)

physics.addBody(Red, "dynamic", {bounce = 1, friction = .5,
                                    density = 1, radius = 100})
physics.addBody(Blue, "dynamic", {bounce = 1, friction = .5,
                                    density = .7, radius = 50})
physics.addBody(Yellow, "dynamic", {bounce = 1, friction = .5,
                                    density = 1, radius = 80})
physics.addBody(spinner, "kinematic", {})

local base = display.newRect(0, display.contentHeight - 50,
                                display.contentWidth, 20)
base.anchorX = 0
base.anchorY = 0
physics.addBody(base, "static", {})

local ceiling = display.newRect(0, 50, display.contentWidth, 20)

ceiling.anchorX = 0
ceiling.anchorY = 0
physics.addBody(ceiling, "static", {})

local lWall = display.newRect(50,0,
                                20, display.contentHeight)
lWall.anchorX = 0
lWall.anchorY = 0
physics.addBody(lWall, "static", {})

local rWall = display.newRect(display.contentWidth-50,0,
                                20, display.contentHeight)
rWall.anchorX = 0
rWall.anchorY = 0
physics.addBody(rWall, "static", {})

local level1 = display.newRect(0, display.contentHeight/3,
                                display.contentWidth/2, 20)
level1.anchorX = 0
level1.anchorY = 0
level1.rotation = 15
physics.addBody(level1, "static", {})

local level2 = display.newRect(display.contentWidth, display.contentHeight/2,
                                display.contentWidth/2, 20)
physics.addBody(level2, "static", {})

local level3 = display.newRect(400,1300,600,20)
level3.rotation = 40
physics.addBody(level3, "static", {})

function spinIt(event)
  spinner.rotation = spinner.rotation+5
end

function moveToTarget(event)
  print("touched")
  transition.to(Yellow, {time = 500, x = event.x, y = event.y})
end

local triangle = display.newImage("triangle.png")
triangle.x = display.contentWidth/2
triangle.y = 500
triangle.width = 50
triangle.height = 50
local triangleShape = {0,-25,25,25,-25,25}
physics.addBody(triangle, {density=0,friction=.5,bounce=.5,shape=triangleShape})

local mySound = audio.loadSound("BoingWav.wav")

function Boing(event)
  print("Boing!")
  audio.play(mySound)
end

triangle:addEventListener("collision", Boing)
Runtime:addEventListener("enterFrame", spinIt)
Runtime:addEventListener("tap",moveToTarget)
-- spinner:addEventListener("tap",moveToTarget)
