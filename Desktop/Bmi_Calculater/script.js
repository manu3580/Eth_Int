function calculateBMI() {
    let height = document.getElementById("height").value;
    let weight = document.getElementById("weight").value;

    if (height === "" || weight === "") {
        alert("Please enter both values");
        return;
    }

    
    height = height / 100;

    let bmi = weight / (height * height);
    bmi = bmi.toFixed(2);

    let resultText = "";

    if (bmi < 18.5) {
        resultText = "Underweight 😟";
    } else if (bmi >= 18.5 && bmi < 24.9) {
        resultText = "Normal weight 😊";
    } else if (bmi >= 25 && bmi < 29.9) {
        resultText = "Overweight 🤐";
    } else {
        resultText = "Obese 😟";
    }

    document.getElementById("result").value = bmi + " (" + resultText + ")";
}