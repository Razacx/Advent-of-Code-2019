const fs = require('fs')

const input = fs.readFileSync('input.txt', 'utf8');
const moduleWeights = input.split('\n').filter(w => !!w).map(w => parseInt(w.trim()));

function getFuelRequirement(weight) {
        return Math.floor(weight / 3) - 2;
}

function getFuelRequirementRecursive(weight) {
        const fuel = getFuelRequirement(weight);
        if (fuel > 0) {
                return getFuelRequirementRecursive(fuel) + fuel;
        } else {
                return 0;
        }
}

const fuelPerModuleWithoutRecursion = moduleWeights.map(getFuelRequirement);
const fuelPerModule = moduleWeights.map(getFuelRequirementRecursive);
const totalFuel = fuelPerModule.reduce((f1, f2) => f1 + f2);

console.log("Fuel requirement without recursion: " + fuelPerModuleWithoutRecursion.reduce((w1, w2) => w1 + w2));
console.log("Total fuel requirement: " + totalFuel);
