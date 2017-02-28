#Compiler Constuction
This is my final product for the CSC49001 class in Lindenwood University under Phd. Dr. Stephen Blythe's supervision.
This compiler was built in Java and BYACCJ in a MacOSX machine. There are two preambles.s files that would later be used to run assembly code.
 
* For **OSX** use the "*osx_preamble.s*" file 
* For **Windows/Linux** use the "*preamble.s*" file

#NOTE THAT **_".myl"_** FILES ARE INPUT FILES!

##Instructions
- Run > 'Make'
- Run > 'java Parser **_'your_test_file.myl'_**. This will spit out assembly code.
- There are many ways you could do this. Simplest would be creating a new .s file and copying the preamble in it (like "**out.s**", which is already provided). Below the preamble, copy what the Parser spit out and paste it.
- Run > './a.out'. Now your program should be running right now.

##Feel free to submit a PR or address any issues!

Special thanks to Dr. Blythe who taught this course.
