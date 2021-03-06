package ic.org.arm.instr

import arrow.core.None
import ic.org.arm.ARMCondSInstr
import ic.org.arm.Flag
import ic.org.arm.ImmOperand2
import ic.org.arm.Immed_8r_bs
import ic.org.arm.Operand2
import ic.org.arm.RegOperand2
import ic.org.arm.Register

interface ArithmeticInstr {
  val s: Boolean
}

data class ADDInstr(
  override val cond: Flag = None,
  override val s: Boolean,
  val rd: Register,
  val rn: Register,
  val op2: Operand2
) : ARMCondSInstr(), ArithmeticInstr {
  constructor(
    cond: Flag = None,
    s: Boolean = true,
    rd: Register,
    rn: Register,
    op2: Register
  ) : this(cond, s, rd, rn, RegOperand2(op2))

  constructor(
    cond: Flag = None,
    s: Boolean = true,
    rd: Register,
    rn: Register,
    int8b: Int
  ) : this(cond, s, rd, rn, ImmOperand2(Immed_8r_bs(int8b.toByte(), 0)))

  override val code = "${opcode("ADD")} ${rd.code}, ${rn.code}, ${op2.code}"
}

data class ADCInstr(
  override val s: Boolean,
  val rd: Register,
  val rn: Register,
  val op2: Operand2,
  override val cond: Flag = None
) : ARMCondSInstr(), ArithmeticInstr {
  override val code = "${opcode("ADC")} ${rd.code}, ${rn.code}, ${op2.code}"
}

data class SUBInstr(
  override val cond: Flag = None,
  override val s: Boolean,
  val rd: Register,
  val rn: Register,
  val op2: Operand2
) : ARMCondSInstr(), ArithmeticInstr {
  constructor(
    cond: Flag = None,
    s: Boolean = true,
    rd: Register,
    rn: Register,
    op2: Register
  ) : this(cond, s, rd, rn, RegOperand2(op2))

  constructor(
    cond: Flag = None,
    s: Boolean = true,
    rd: Register,
    rn: Register,
    int8b: Int
  ) : this(cond, s, rd, rn, ImmOperand2(Immed_8r_bs(int8b.toByte(), 0)))

  override val code = "${opcode("SUB")} ${rd.code}, ${rn.code}, ${op2.code}"
}

data class SBCInstr(
  override val cond: Flag,
  override val s: Boolean,
  val rd: Register,
  val rn: Register,
  val op2: Operand2
) : ARMCondSInstr(), ArithmeticInstr {
  override val code = "${opcode("SBC")} ${rd.code}, ${rn.code}, ${op2.code}"
}

data class RSBInstr(
  override val cond: Flag,
  override val s: Boolean,
  val rd: Register,
  val rn: Register,
  val op2: Operand2
) : ARMCondSInstr(), ArithmeticInstr {
  override val code = "${opcode("RSB")} ${rd.code}, ${rn.code}, ${op2.code}"
}

data class RSCInstr(
  override val cond: Flag,
  override val s: Boolean,
  val rd: Register,
  val rn: Register,
  val op2: Operand2
) : ARMCondSInstr() {
  override val code = "${opcode("RSC")} ${rd.code}, ${rn.code}, ${op2.code}"
}

data class MULInstr(
  override val cond: Flag,
  override val s: Boolean,
  val rd: Register,
  val rm: Register,
  val rs: Register
) : ARMCondSInstr(), ArithmeticInstr {
  override val code = "${opcode("MUL")} ${rd.code}, ${rm.code}, ${rs.code}"
}

data class MLAInstr(
  override val cond: Flag,
  override val s: Boolean,
  val rd: Register,
  val rm: Register,
  val rs: Register,
  val rn: Register
) : ARMCondSInstr(), ArithmeticInstr {
  override val code = "${opcode("MLA")} ${rd.code}, ${rm.code}, ${rs.code}, ${rn.code}"
}

data class SMULLInstr(
  override val cond: Flag,
  override val s: Boolean,
  val rdlo: Register,
  val rdhi: Register,
  val rm: Register,
  val rs: Register
) : ARMCondSInstr() {
  override val code = "${opcode("SMULL")} ${rdlo.code}, ${rdhi.code}, ${rm.code}, ${rs.code}"
}
