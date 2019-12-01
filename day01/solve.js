const fs = require('fs')

const input = fs.readFileSync('input.txt', 'utf8');
const moduleWeights = input.split('\n').filter(w => !!w);

function getFuelRequirement(weight) {
        const fuel = Math.floor(weight / 3) - 2;
        if (fuel > 0) {
                return getFuelRequirement(fuel) + fuel;
        } else {
                return 0;
        }
}

const fuelPerModule = moduleWeights.map(getFuelRequirement)
const totalFuel = fuelPerModule.reduce((f1, f2) => f1 + f2)

console.log(totalFuel);
