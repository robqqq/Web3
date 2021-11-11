function check(x, y, r){
    return checkX(x) && checkY(y) && checkR(r)
}

function checkX(x){
    if (x === "") {
        alert("Enter the X value")
        return false
    }
    if (!isFinite(x)) {
        alert("X must be a number")
        return false;
    }
    if (x <= -5 || x >= 5) {
        alert("X value must be in range (-5; 5)")
        return false;
    }
    return true;
}

function checkY(y){
    if (y === "") {
        alert("Enter the Y value")
        return false
    }
    if (!isFinite(y)) {
        alert("Y must be a number")
        return false;
    }
    if (y <= -3 || y >= 3) {
        alert("Y value must be in range (-3; 3)")
        return false;
    }
    return true;
}

function checkR(r){
    if (r === 0) {
        alert("Select the R value")
        return false
    }
    if (r < 1 || r > 5) {
        alert("R value must be in {1, 2, 3, 4, 5}")
        return false;
    }
    return true;
}
