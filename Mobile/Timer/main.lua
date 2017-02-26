local outText = display.newText("###", display.contentWidth/2,
                      display.contentHeight/2, "agency fb", 150)
function doSomething(event)

  print("Timer has fired")
  print(event.name)
  print(event.source)
  print(event.time)
  print(event.count)
  outText.text = event.count

end

myTimer = timer.performWithDelay(500, doSomething, 0)

local yellow = display.newCircle(200,200,20)
yellow:setFillColor(1,1,0)

local red = display.newCircle(140,200,20)
red:setFillColor(1,0,0)

local green = display.newCircle(260,200,20)
green:setFillColor(0,1,0)

local count = 0
function increaseCount(event)
  count = count+1
  print("count ".. count)
end

red:addEventListener("tap",increaseCount)

function moveObject(event)
  print("object moved")
  if (red.x <= 140) then
    transition.to(red, {time=500, x=600, onComplete=moveObject})
  else
    transition.to(red, {time=500, x=0, onComplete=moveObject})
  end
end

transition.to(yellow, {time=2000, y=600, x=100, xScale=3,
                      rotation=95, alpha=.5})
transition.to(green, {time=1000, y=800, delay=2500})
transition.to(green, {time=2000, x=600, delay=0})
transition.to(red, {time=5000, y=900, onComplete=moveObject})
