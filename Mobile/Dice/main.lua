local text = display.newText("Rollem",185,70,"verdana",80)
function roll(event)

  for i=1,10,1 do

    local num1 = math.random(1,6)
    local num2 = math.random(1,6)
    local total = num1+num2

    print(num1.." + "..num2.." = "..total)

    if (total==7 or total==11) then
      print ("you win")
      local text = display.newText("You Win!",185,180*i,"verdana",40)
    elseif (total==2) then
      print ("snake eyes")
      local text = display.newText("Snake Eyes!",185,180*i,"verdana",40)
    else
      print("you lose")
      local text = display.newText("You Lose!",185,180*i,"verdana",40)
    end

  end

end

Runtime:addEventListener("tap",roll)
