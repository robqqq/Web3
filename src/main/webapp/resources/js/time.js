document.addEventListener("DOMContentLoaded",function () {
    let time = document.getElementById("time");
    let date = document.getElementById("date");

    let day = new Date();

    time.innerHTML = day.toLocaleTimeString();
    date.innerHTML = day.toLocaleDateString();

    setInterval(() => {
        day = new Date();
        time.innerHTML = day.toLocaleTimeString();
        date.innerHTML = day.toLocaleDateString();
    }, 8000)
});


