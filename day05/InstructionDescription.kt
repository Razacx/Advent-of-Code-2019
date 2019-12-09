package day5

enum class ParameterMode {
    POSITION,
    IMMEDIATE;

    companion object {
        fun parseParameterMode(i: Int) = when (i) {
            0 -> POSITION
            1 -> IMMEDIATE
            else -> throw IllegalArgumentException("$i is not a valid day5.ParameterMode")
        }
    }
}

data class InstructionDescription(val opcode: Int, val modes: List<ParameterMode>)

fun readInstructionDescription(opcode: Int): InstructionDescription {
    val opcodeStr = opcode.toString()
    return if (opcodeStr.length <= 2) {
        InstructionDescription(
            opcode,
            listOf(ParameterMode.POSITION, ParameterMode.POSITION, ParameterMode.POSITION)
        )
    } else {
        val parsedOpcode = opcodeStr
            .subSequence(opcodeStr.length - 2, opcodeStr.length)
            .toString()
            .toInt()
        val parameterModes = opcodeStr
            .subSequence(0, opcodeStr.length - 2)
            .reversed()
            .padEnd(3, '0')
            .map { ParameterMode.parseParameterMode(it.toString().toInt()) }
        InstructionDescription(parsedOpcode, parameterModes)
    }
}
