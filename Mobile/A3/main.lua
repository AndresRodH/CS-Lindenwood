--[[
Assignment #3

Author: Andres Rodriguez
Version: 1.1

** 02/06/2017. Initial release
** 02/06/2017. Added animations

--]]

local values = {"A", 2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K"}
local suits = {"Spades", "Clubs", "Hearts", "Diamonds"}
local hand = {}
local cenX = display.contentWidth/2
local cenY = display.contentHeight/2

display.newText("Tap the screen to", cenX, cenY-60, "helvetica", 100)
display.newText("start drawing cards!", cenX, cenY+60, "helvetica", 100)

function draw(event)
  --[[
  Receives + an event (screen tap)
  Task + draws 5 cards and place them on the screen
  Returns + nothing
  --]]

  -- card counter
  local n = 1

  -- clear entire display
  local stage = display.getCurrentStage()
    while stage.numChildren > 0 do
      local obj = stage[1]
      obj:removeSelf()
      obj = nil
    end

  -- draw until 5 DIFFERENT cards are in the hand
  while table.maxn(hand) < 5 do

    drawVal = values[math.random(1,13)]  -- get value for the card
    drawSuit = suits[math.random(1,4)]   -- and the suit
    card = drawVal..drawSuit             -- get the card

    -- and card to the hand if it is not duplicate
    if (table.indexOf( hand, card ) == nil) then
      table.insert(hand, card)
      print(drawVal.." of "..drawSuit)

      -- build the graphics for the card
      card = makeCard(drawSuit, drawVal)
      toX = 0
      toY = 0
      -- set is the card going to be placed on the screen
      if (n == 1) then
        toX = 0
        toY = 10
      elseif (n == 2) then
        toX = 320
        toY = 630
      elseif (n == 3) then
        toX = -320
        toY = 630
      elseif (n == 4) then
        toX = -320
        toY = -610
      elseif (n == 5) then
        toX = 320
        toY = -610
      end
      -- animate the card
      transition.to(card, {time = 300, x = toX, y = toY, alpha = 1})
      -- update counter
      n = n + 1
    else
      print("Duplicate! "..drawVal.." of "..drawSuit)
    end
  end

  -- clear the current hand
  clearHand()
end

function clearHand()
  --[[
  Receives + nothing
  Task + removes every card from the hand
  Returns + nothing
  --]]

  for i = #hand,1,-1 do
    table.remove(hand,i)
  end
end

function makeCard(suit, val)
  --[[
  Receives + a string (the suit of the card)
           + a 1-character-long string for A, J, Q, K or a number from 2 to 10
             (value of the card)
  Task + builds the whole graphics for the card the input
         describes
  Returns + a group (the card)
  --]]

  -- initialize card group
  card = display.newGroup()

  -- generate background
  back = display.newRoundedRect(cenX, cenY, 400,600, 30)
  card:insert(back)

  -- upper suit mark
  uSuit = display.newImageRect(suit..".png", 40,40)
  uSuit.x = cenX - 170
  uSuit.y = cenY - 230
  card:insert(uSuit)

  -- bottom suit mark
  bSuit = display.newImageRect(suit..".png", 40,40)
  bSuit.x = cenX + 170
  bSuit.y = cenY + 230
  bSuit.rotation = 180
  card:insert(bSuit)

  -- upper value mark
  uVal = display.newText(val, cenX - 170, cenY - 270, "helvetica", 40)

  -- bottom value mark
  bVal = display.newText(val, cenX + 170, cenY + 270, "helvetica", 40)
  bVal.rotation = 180

  -- set appropriate color for the value marks
  if (suit == "Spades" or suit == "Clubs") then
    uVal:setFillColor(0,0,0)
    bVal:setFillColor(0,0,0)
  else
    uVal:setFillColor(1,0,0)
    bVal:setFillColor(1,0,0)
  end
  card:insert(uVal)
  card:insert(bVal)

  -- FUN PART! POPULATE CARDS!
  -- for value A
  if (val == "A") then
    o = display.newImageRect(suit..".png",250,250)
    o.x = cenX
    o.y = cenY
    card:insert(o)

  -- for value 2
  elseif (val == 2) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX
    o1.y = cenY - 230
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX
    o2.y = cenY + 230
    o2.rotation = 180
    card:insert(o2)

  -- for value 3
  elseif (val == 3) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX
    o1.y = cenY - 230
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX
    o2.y = cenY + 230
    o2.rotation = 180
    card:insert(o2)

    o3 = display.newImageRect(suit..".png",80,80)
    o3.x = cenX
    o3.y = cenY
    card:insert(o3)

  -- for value 4
  elseif (val == 4) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX - 100
    o1.y = cenY - 210
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX + 100
    o2.y = cenY - 210
    card:insert(o2)

    o3 = display.newImageRect(suit..".png",80,80)
    o3.x = cenX + 100
    o3.y = cenY + 210
    o3.rotation = 180
    card:insert(o3)

    o4 = display.newImageRect(suit..".png",80,80)
    o4.x = cenX - 100
    o4.y = cenY + 210
    o4.rotation = 180
    card:insert(o4)

  -- for value 5
  elseif (val == 5) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX - 100
    o1.y = cenY - 210
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX + 100
    o2.y = cenY - 210
    card:insert(o2)

    o3 = display.newImageRect(suit..".png",80,80)
    o3.x = cenX + 100
    o3.y = cenY + 210
    o3.rotation = 180
    card:insert(o3)

    o4 = display.newImageRect(suit..".png",80,80)
    o4.x = cenX - 100
    o4.y = cenY + 210
    o4.rotation = 180
    card:insert(o4)

    o5 = display.newImageRect(suit..".png",80,80)
    o5.x = cenX
    o5.y = cenY
    card:insert(o5)

  -- for value 6
  elseif (val == 6) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX - 100
    o1.y = cenY - 210
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX + 100
    o2.y = cenY - 210
    card:insert(o2)

    o3 = display.newImageRect(suit..".png",80,80)
    o3.x = cenX + 100
    o3.y = cenY + 210
    o3.rotation = 180
    card:insert(o3)

    o4 = display.newImageRect(suit..".png",80,80)
    o4.x = cenX - 100
    o4.y = cenY + 210
    o4.rotation = 180
    card:insert(o4)

    o5 = display.newImageRect(suit..".png",80,80)
    o5.x = cenX + 100
    o5.y = cenY
    card:insert(o5)

    o6 = display.newImageRect(suit..".png",80,80)
    o6.x = cenX - 100
    o6.y = cenY
    card:insert(o6)

  -- for value 7
  elseif (val == 7) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX - 100
    o1.y = cenY - 210
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX + 100
    o2.y = cenY - 210
    card:insert(o2)

    o3 = display.newImageRect(suit..".png",80,80)
    o3.x = cenX + 100
    o3.y = cenY + 210
    o3.rotation = 180
    card:insert(o3)

    o4 = display.newImageRect(suit..".png",80,80)
    o4.x = cenX - 100
    o4.y = cenY + 210
    o4.rotation = 180
    card:insert(o4)

    o5 = display.newImageRect(suit..".png",80,80)
    o5.x = cenX + 100
    o5.y = cenY
    card:insert(o5)

    o6 = display.newImageRect(suit..".png",80,80)
    o6.x = cenX - 100
    o6.y = cenY
    card:insert(o6)

    o7 = display.newImageRect(suit..".png",80,80)
    o7.x = cenX
    o7.y = cenY - 105
    card:insert(o7)

  -- for value 8
  elseif (val == 8) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX - 100
    o1.y = cenY - 210
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX + 100
    o2.y = cenY - 210
    card:insert(o2)

    o3 = display.newImageRect(suit..".png",80,80)
    o3.x = cenX + 100
    o3.y = cenY + 210
    o3.rotation = 180
    card:insert(o3)

    o4 = display.newImageRect(suit..".png",80,80)
    o4.x = cenX - 100
    o4.y = cenY + 210
    o4.rotation = 180
    card:insert(o4)

    o5 = display.newImageRect(suit..".png",80,80)
    o5.x = cenX + 100
    o5.y = cenY
    card:insert(o5)

    o6 = display.newImageRect(suit..".png",80,80)
    o6.x = cenX - 100
    o6.y = cenY
    card:insert(o6)

    o7 = display.newImageRect(suit..".png",80,80)
    o7.x = cenX
    o7.y = cenY - 105
    card:insert(o7)

    o8 = display.newImageRect(suit..".png",80,80)
    o8.x = cenX
    o8.y = cenY + 105
    o8.rotation = 180
    card:insert(o8)

  -- for value 9
  elseif (val == 9) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX - 100
    o1.y = cenY - 210
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX + 100
    o2.y = cenY - 210
    card:insert(o2)

    o3 = display.newImageRect(suit..".png",80,80)
    o3.x = cenX + 100
    o3.y = cenY + 210
    o3.rotation = 180
    card:insert(o3)

    o4 = display.newImageRect(suit..".png",80,80)
    o4.x = cenX - 100
    o4.y = cenY + 210
    o4.rotation = 180
    card:insert(o4)

    o5 = display.newImageRect(suit..".png",80,80)
    o5.x = cenX + 100
    o5.y = cenY + 75
    o5.rotation = 180
    card:insert(o5)

    o6 = display.newImageRect(suit..".png",80,80)
    o6.x = cenX - 100
    o6.y = cenY + 75
    o6.rotation = 180
    card:insert(o6)

    o7 = display.newImageRect(suit..".png",80,80)
    o7.x = cenX - 100
    o7.y = cenY - 75
    card:insert(o7)

    o8 = display.newImageRect(suit..".png",80,80)
    o8.x = cenX + 100
    o8.y = cenY - 75
    card:insert(o8)

    o9 = display.newImageRect(suit..".png",80,80)
    o9.x = cenX
    o9.y = cenY
    card:insert(o9)

  -- for value 10
  elseif (val == 10) then
    o1 = display.newImageRect(suit..".png",80,80)
    o1.x = cenX - 100
    o1.y = cenY - 210
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",80,80)
    o2.x = cenX + 100
    o2.y = cenY - 210
    card:insert(o2)

    o3 = display.newImageRect(suit..".png",80,80)
    o3.x = cenX + 100
    o3.y = cenY + 210
    o3.rotation = 180
    card:insert(o3)

    o4 = display.newImageRect(suit..".png",80,80)
    o4.x = cenX - 100
    o4.y = cenY + 210
    o4.rotation = 180
    card:insert(o4)

    o5 = display.newImageRect(suit..".png",80,80)
    o5.x = cenX + 100
    o5.y = cenY + 75
    o5.rotation = 180
    card:insert(o5)

    o6 = display.newImageRect(suit..".png",80,80)
    o6.x = cenX - 100
    o6.y = cenY + 75
    o6.rotation = 180
    card:insert(o6)

    o7 = display.newImageRect(suit..".png",80,80)
    o7.x = cenX - 100
    o7.y = cenY - 75
    card:insert(o7)

    o8 = display.newImageRect(suit..".png",80,80)
    o8.x = cenX + 100
    o8.y = cenY - 75
    card:insert(o8)

    o9 = display.newImageRect(suit..".png",80,80)
    o9.x = cenX
    o9.y = cenY - 140
    card:insert(o9)

    o10 = display.newImageRect(suit..".png",80,80)
    o10.x = cenX
    o10.y = cenY + 140
    o10.rotation = 180
    card:insert(o10)

  -- for value J
  elseif (val == "J") then
    o = display.newImageRect(val..".png",250,515)
    o.x = cenX
    o.y = cenY
    card:insert(o)

    o1 = display.newImageRect(suit..".png",60,60)
    o1.x = cenX - 140
    o1.y = cenY - 85
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",60,60)
    o2.x = cenX + 140
    o2.y = cenY + 85
    o2.rotation = 180
    card:insert(o2)

  -- for value Q
  elseif (val == "Q") then
    o = display.newImageRect(val..".png",250,515)
    o.x = cenX
    o.y = cenY
    card:insert(o)

    o1 = display.newImageRect(suit..".png",60,60)
    o1.x = cenX - 140
    o1.y = cenY - 85
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",60,60)
    o2.x = cenX + 140
    o2.y = cenY + 85
    o2.rotation = 180
    card:insert(o2)

  -- for value K
  elseif (val == "K") then
    o = display.newImageRect(val..".png",250,515)
    o.x = cenX
    o.y = cenY
    card:insert(o)

    o1 = display.newImageRect(suit..".png",60,60)
    o1.x = cenX - 140
    o1.y = cenY - 85
    card:insert(o1)

    o2 = display.newImageRect(suit..".png",60,60)
    o2.x = cenX + 140
    o2.y = cenY + 85
    o2.rotation = 180
    card:insert(o2)

  end

  return card
end

Runtime:addEventListener("tap", draw)
