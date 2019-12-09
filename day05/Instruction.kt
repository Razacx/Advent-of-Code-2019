package day5

interface Instruction {
    fun applyTo(memory: Memory): Memory
    fun length(): Int

}

class AddInstruction(
    private val input1: Parameter,
    private val input2: Parameter,
    private val storeLocation: Int
) : Instruction {

    override fun applyTo(memory: Memory): Memory {
        val resultValue = memory[input1] + memory[input2]
        return memory.withElementAtIndex(resultValue, storeLocation)
    }

    override fun length(): Int = 4

}

class MultiplyInstruction(
    private val input1: Parameter,
    private val input2: Parameter,
    private val storeLocation: Int
) : Instruction {

    override fun applyTo(memory: Memory): Memory {
        val resultValue = memory[input1] * memory[input2]
        return memory.withElementAtIndex(resultValue, storeLocation)
    }

    override fun length(): Int = 4

}

class HaltInstruction : Instruction {

    override fun applyTo(memory: Memory): Memory {
        // Does nothing
        return memory
    }

    override fun length(): Int = 1

}

class StoreInstruction(private val storeLocation: Int) : Instruction {

    override fun applyTo(memory: Memory): Memory {
        print("Requesting user input: ")
        val input = readLine()!!.toInt()
        return memory.withElementAtIndex(input, storeLocation)
    }

    override fun length(): Int = 2

}

class PrintInstruction(private val input: Parameter) : Instruction {

    override fun applyTo(memory: Memory): Memory {
        println(memory[input])
        return memory
    }

    override fun length(): Int = 2

}

class JumpIfTrueInstruction(private val input1: Parameter, private val input2: Parameter) : Instruction {

    override fun applyTo(memory: Memory): Memory {
        return if (memory[input1] != 0) {
            memory.withPtr(memory[input2])
        } else {
            memory
        }
    }

    override fun length(): Int = 3

}

class JumpIfFalseInstruction(private val input1: Parameter, private val input2: Parameter) : Instruction {

    override fun applyTo(memory: Memory): Memory {
        return if (memory[input1] == 0) {
            memory.withPtr(memory[input2])
        } else {
            memory
        }
    }

    override fun length(): Int = 3

}

class LessThanInstruction(
    private val input1: Parameter,
    private val input2: Parameter,
    private val storeLocation: Int
) : Instruction {

    override fun applyTo(memory: Memory): Memory {
        val result = if (memory[input1] < memory[input2]) 1 else 0
        return memory.withElementAtIndex(result, storeLocation)
    }

    override fun length(): Int = 4

}

class EqualsInstruction(
    private val input1: Parameter,
    private val input2: Parameter,
    private val storeLocation: Int
) : Instruction {

    override fun applyTo(memory: Memory): Memory {
        val result = if (memory[input1] == memory[input2]) 1 else 0
        return memory.withElementAtIndex(result, storeLocation)
    }

    override fun length(): Int = 4

}
