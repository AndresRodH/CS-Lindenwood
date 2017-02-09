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
