package day5

class Memory(private val memory: List<Int>, val ptr: Int = 0) : List<Int> by memory {

    operator fun get(parameter: Parameter): Int = when (parameter.mode) {
        ParameterMode.POSITION -> memory[parameter.value]
        ParameterMode.IMMEDIATE -> parameter.value
    }

    fun withElementAtIndex(element: Int, index: Int): Memory {
        val list = toMutableList()
        list[index] = element
        return Memory(list.toList(), ptr)
    }

    fun withPtr(ptr: Int): Memory {
        return Memory(this.memory, ptr)
    }

    fun getInstructionAt(ptr: Int): Instruction {
        val instructionDescription = readInstructionDescription(this[ptr])
        return when (instructionDescription.opcode) {
            1 -> AddInstruction(
                Parameter(this[ptr + 1], instructionDescription.modes[0]),
                Parameter(this[ptr + 2], instructionDescription.modes[1]),
                this[ptr + 3]
            )
            2 -> MultiplyInstruction(
                Parameter(this[ptr + 1], instructionDescription.modes[0]),
                Parameter(this[ptr + 2], instructionDescription.modes[1]),
                this[ptr + 3]
            )
            3 -> StoreInstruction(this[ptr + 1])
            4 -> PrintInstruction(Parameter(this[ptr + 1], instructionDescription.modes[0]))
            5 -> JumpIfTrueInstruction(
                Parameter(this[ptr + 1], instructionDescription.modes[0]),
                Parameter(this[ptr + 2], instructionDescription.modes[1])
            )
            6 -> JumpIfFalseInstruction(
                Parameter(this[ptr + 1], instructionDescription.modes[0]),
                Parameter(this[ptr + 2], instructionDescription.modes[1])
            )
            7 -> LessThanInstruction(
                Parameter(this[ptr + 1], instructionDescription.modes[0]),
                Parameter(this[ptr + 2], instructionDescription.modes[1]),
                this[ptr + 3]
            )
            8 -> EqualsInstruction(
                Parameter(this[ptr + 1], instructionDescription.modes[0]),
                Parameter(this[ptr + 2], instructionDescription.modes[1]),
                this[ptr + 3]
            )
            99 -> HaltInstruction()
            else -> throw IllegalArgumentException("${instructionDescription.opcode} at position $ptr is not a valid opcode")
        }
    }

    override fun toString(): String {
        return "$memory"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Memory

        if (memory != other.memory) return false

        return true
    }

    override fun hashCode(): Int {
        return memory.hashCode()
    }

}
