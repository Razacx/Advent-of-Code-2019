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

const fuelPerModule = moduleWeights.map(getFuelRequirement);
const fuelPerModuleRecursive = moduleWeights.map(getFuelRequirementRecursive);

console.log("Fuel requirement without recursion: " + fuelPerModule.reduce((f1, f2) => f1 + f2));
console.log("Total fuel requirement: " + fuelPerModuleRecursive.reduce((f1, f2) => f1 + f2));
