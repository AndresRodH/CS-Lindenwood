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
#main:               PROCSTART
main:                    
                    pushq	%rbp
                    movq 	%rsp, %rbp
                    subq 	$96, %rsp
#                    CALL 	read
                    call 	read
#                    STRET	__T0
                    movl 	%eax, -8(%rbp)
#                    MOV  	__T0, x
                    movl 	-8(%rbp), %eax
                    movl 	%eax, -24(%rbp)
#                    LT   	x, 0, __T1
                    movl 	-24(%rbp), %eax
                    movl 	$0, %ebx
                    movl 	$1, %edx
                    cmp  	%ebx, %eax
                    jl   	__L2
                    movl 	$0, %edx
__L2:                    
                    movl 	%edx, -12(%rbp)
#                    CMP  	__T1, 0
                    cmpl 	$0, -12(%rbp)
#                    BE   	__L0
                    je   	__L0
#                    NEG  	x, __T2
                    movl 	-24(%rbp), %eax
                    neg  	%eax
                    movl 	%eax, -16(%rbp)
#                    MOV  	__T2, x
                    movl 	-16(%rbp), %eax
                    movl 	%eax, -24(%rbp)
#                    BA   	__L1
                    jmp  	__L1
#__L0:               NOP  
__L0:                    
#__L1:               NOP  
__L1:                    
#                    PARAM	x
                    movl 	-24(%rbp), %eax
#                    CALL 	print
                    call 	print
#                    STRET	__T3
                    movl 	%eax, -20(%rbp)
#                    RET  
                    leave
                    ret  
