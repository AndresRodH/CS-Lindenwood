	.section	__TEXT,__text,regular,pure_instructions
	.macosx_version_min 10, 12
	.globl	_main
	.align	4, 0x90
_main:                                  ## @main
	.cfi_startproc
## BB#0:
	pushq	%rbp
Ltmp0:
	.cfi_def_cfa_offset 16
Ltmp1:
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
Ltmp2:
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	leaq	L_.str(%rip), %rdi
	leaq	-8(%rbp), %rsi
	movl	$0, -4(%rbp)
	movb	$0, %al
	callq	_scanf
	cmpl	$0, -8(%rbp)
	movl	%eax, -12(%rbp)         ## 4-byte Spill
	jge	LBB0_2
## BB#1:
	xorl	%eax, %eax
	subl	-8(%rbp), %eax
	movl	%eax, -8(%rbp)
LBB0_2:
	leaq	L_.str(%rip), %rdi
	movl	-8(%rbp), %esi
	movb	$0, %al
	callq	_printf
	movl	-4(%rbp), %esi
	movl	%eax, -16(%rbp)         ## 4-byte Spill
	movl	%esi, %eax
	addq	$16, %rsp
	popq	%rbp
	retq
	.cfi_endproc

	.section	__TEXT,__cstring,cstring_literals
L_.str:                                 ## @.str
	.asciz	"%d"


.subsections_via_symbols
