#main:               PROCSTART
main:                    
                    pushq	%rbp
                    movq 	%rsp, %rbp
                    subq 	$192, %rsp
#                    CALL 	read
                    call 	read
#                    STRET	__T0
                    movl 	%eax, -32(%rbp)
#                    MOV  	__T0, x
                    movl 	-32(%rbp), %eax
                    movl 	%eax, -192(%rbp)
#                    MOV  	1, i
                    movl 	$1, %eax
                    movl 	%eax, -176(%rbp)
#__L0:               NOP  
__L0:                    
#                    LTE  	i, x, __T1
                    movl 	-176(%rbp), %eax
                    movl 	-192(%rbp), %ebx
                    movl 	$1, %edx
                    cmp  	%ebx, %eax
                    jle  	__L4
                    movl 	$0, %edx
__L4:                    
                    movl 	%edx, -48(%rbp)
#                    CMP  	__T1, 0
                    cmpl 	$0, -48(%rbp)
#                    BE   	__L1
                    je   	__L1
#                    MOV  	i, j
                    movl 	-176(%rbp), %eax
                    movl 	%eax, -160(%rbp)
#__L2:               NOP  
__L2:                    
#                    LTE  	j, x, __T2
                    movl 	-160(%rbp), %eax
                    movl 	-192(%rbp), %ebx
                    movl 	$1, %edx
                    cmp  	%ebx, %eax
                    jle  	__L5
                    movl 	$0, %edx
__L5:                    
                    movl 	%edx, -64(%rbp)
#                    CMP  	__T2, 0
                    cmpl 	$0, -64(%rbp)
#                    BE   	__L3
                    je   	__L3
#                    PARAM	j
                    movl 	-160(%rbp), %eax
#                    CALL 	print
                    call 	print
#                    STRET	__T3
                    movl 	%eax, -80(%rbp)
#                    ADD  	j, 1, __T4
                    movl 	-160(%rbp), %eax
                    addl 	$1, %eax
                    movl 	%eax, -96(%rbp)
#                    MOV  	__T4, j
                    movl 	-96(%rbp), %eax
                    movl 	%eax, -160(%rbp)
#                    BA   	__L2
                    jmp  	__L2
#__L3:               NOP  
__L3:                    
#                    NEG  	999999, __T5
                    movl 	$999999, %eax
                    neg  	%eax
                    movl 	%eax, -112(%rbp)
#                    PARAM	__T5
                    movl 	-112(%rbp), %eax
#                    CALL 	print
                    call 	print
#                    STRET	__T6
                    movl 	%eax, -128(%rbp)
#                    ADD  	i, 1, __T7
                    movl 	-176(%rbp), %eax
                    addl 	$1, %eax
                    movl 	%eax, -144(%rbp)
#                    MOV  	__T7, i
                    movl 	-144(%rbp), %eax
                    movl 	%eax, -176(%rbp)
#                    BA   	__L0
                    jmp  	__L0
#__L1:               NOP  
__L1:                    
#                    RET  
                    leave
                    ret  
