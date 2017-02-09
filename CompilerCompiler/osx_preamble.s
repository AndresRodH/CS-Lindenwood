#### OS/X PREAMBLE - include globals and print, read ####
#
#  make sure to allocate space o the stack in multiples of 16
#     ... OS/X requires such. 
#
	.section	__TEXT,__cstring
	.align 16
_printL:.asciz "%d\n"
_readL:	.asciz "%d"

	
	.section	__TEXT,__text
	.globl	_main
	.align	4
######  tricK OS/X's gcc _main into using our main method ...
_main:
	jmp	main  ## requires you to code your own main method ...
###### "built in" print function
print:
	pushq	%rbp       #save old base pointer
	movq	%rsp, %rbp  #new base pointer is top of stack

	xor	%rsi, %rsi     #clear out all of si register.
	movl	%eax, %esi     #print() input is 2nd param to printf
	leaq	_printL(%rip), %rdi #address of format string is first param
	movl	$0, %eax       #printf requires eax of 0 
	callq	_printf         #call C library printf()

	leave              #restore old base pointer
	ret                #return where you were called from

###### "built in" read function
read:
	pushq	%rbp       #save old base pointer
	movq	%rsp, %rbp  #new base pointer is top of stack
	subq	$16, %rsp    #make space for local variable

	leaq	-4(%rbp), %rsi #var to read is  2nd param to scanf
	leaq	_readL(%rip), %rdi  #address of format string is first param
	movl	$0, %eax       #scanf requires eax of 0 
	callq	_scanf          #call C library scanf()
	movl	-4(%rbp), %eax #store scanf return val into eax

	leave              #restore old base pointer
	ret                #return where you were called from
#### END PREAMBLE ####
#
#  don't forget, allocate space on the stack inmultiples of 16 bytes!
#
