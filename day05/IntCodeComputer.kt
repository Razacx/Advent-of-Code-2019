package day5

open class IntCodeComputer(val memory: Memory, ptr: Int = 0) {

    fun run(): IntCodeComputer {
        val instruction = memory.getInstructionAt(memory.ptr)

        val newMemory = instruction.applyTo(memory)
        val jumped = newMemory.ptr != memory.ptr
        val newComputer =
            if (jumped) IntCodeComputer(newMemory)
            else IntCodeComputer(newMemory.withPtr(memory.ptr + instruction.length()))

        return if (instruction is HaltInstruction) {
            newComputer
        } else {
            newComputer.run()
        }
    }

    override fun toString(): String {
        return "IntCodeComputer(memory=$memory)"
    }

}
