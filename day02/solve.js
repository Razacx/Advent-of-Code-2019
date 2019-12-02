const program = [1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,6,19,1,9,19,23,1,6,23,27,1,10,27,31,1,5,31,35,2,6,35,39,1,5,39,43,1,5,43,47,2,47,6,51,1,51,5,55,1,13,55,59,2,9,59,63,1,5,63,67,2,67,9,71,1,5,71,75,2,10,75,79,1,6,79,83,1,13,83,87,1,10,87,91,1,91,5,95,2,95,10,99,2,9,99,103,1,103,6,107,1,107,10,111,2,111,10,115,1,115,6,119,2,119,9,123,1,123,6,127,2,127,10,131,1,131,6,135,2,6,135,139,1,139,5,143,1,9,143,147,1,13,147,151,1,2,151,155,1,10,155,0,99,2,14,0,0];

function run(program) {
	for(let i = 0; i < program.length; i+=4) {
		const opcode = program[i];
		const inVal1 = program[program[i+1]]
		const inVal2 = program[program[i+2]]
		const outIdx = program[i+3]

		switch (opcode) {
			case 1:
				program[outIdx] = inVal1 + inVal2;
				break;
			case 2:
				program[outIdx] = inVal1 * inVal2;
				break;
			case 99:
				return program;
			default:
				throw Error("Invalid opcode: " + opcode);
		}
	}

	return program;
}

const p1Copy = program.slice()
p1Copy[1] = 12
p1Copy[2] = 2
console.log(run(p1Copy)[0]);

// We're lucky that this is easily brute forced
// A non-naive way to do this would probably be running the program in reverse... 
for (let i = 0; i < 1e4; i++) {
	for (let j = 0; j < 1e4; j++) {
		const pCopy = program.slice()
		pCopy[1] = i;
		pCopy[2] = j;
		const completed = run(pCopy)
		if(completed[0] == 19690720) {
			console.log(i + "|" + j)
			process.exit()
		}
	}
}
