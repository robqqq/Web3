let _r = 0;

document.addEventListener("DOMContentLoaded", function () {
    drawPlot(_r);
    const canvas = document.querySelector('canvas')
    canvas.addEventListener('click', function (e) {
        handlePlotClick(canvas, e)
    })
});

function changeR(r){
    _r = r;
    drawPlot();
}

function drawPlot(){
    let canvas = document.getElementById("canvas"),
        ctx = canvas.getContext("2d"),
        dr = _r * 25;

    canvas.width = 300;
    canvas.height = 300;
    ctx.fillStyle = "rgba(0, 166, 255, 0.5)";
    ctx.strokeStyle = "rgb(0, 166, 255)";
    ctx.beginPath();
    ctx.moveTo(150, 150);
    ctx.lineTo(150 - dr / 2, 150);
    ctx.lineTo(150 - dr / 2, 150 - dr);
    ctx.lineTo(150, 150 - dr);
    ctx.lineTo(150 + dr, 150);
    ctx.lineTo(150 + dr / 2, 150);
    ctx.arc(150, 150, dr / 2, 0, Math.PI / 2);
    ctx.lineTo(150, 150);
    ctx.fill();
    ctx.stroke();
    drawLine(ctx, [10, 150], [290, 150]);
    drawLine(ctx, [150, 10], [150, 290]);
    drawLine(ctx, [147, 15], [150, 10]);
    drawLine(ctx, [150, 10], [153, 15]);
    drawLine(ctx, [285, 147], [290, 150]);
    drawLine(ctx, [290, 150], [285, 153]);
    drawLine(ctx, [25, 147], [25, 153]);
    drawLine(ctx, [75, 147], [75, 153]);
    drawLine(ctx, [125, 147], [125, 153]);
    drawLine(ctx, [175, 147], [175, 153]);
    drawLine(ctx, [225, 147], [225, 153]);
    drawLine(ctx, [275, 147], [275, 153]);
    drawLine(ctx, [147, 25], [153, 25]);
    drawLine(ctx, [147, 75], [153, 75]);
    drawLine(ctx, [147, 125], [153, 125]);
    drawLine(ctx, [147, 175], [153, 175]);
    drawLine(ctx, [147, 225], [153, 225]);
    drawLine(ctx, [147, 275], [153, 275]);
    ctx.strokeText("-5", 20, 163);
    ctx.strokeText("-3", 70, 163);
    ctx.strokeText("-1", 120, 163);
    ctx.strokeText("1", 173, 163);
    ctx.strokeText("3", 223, 163);
    ctx.strokeText("5", 273, 163);
    ctx.strokeText("5", 140, 28);
    ctx.strokeText("3", 140, 78);
    ctx.strokeText("1", 140, 128);
    ctx.strokeText("-1", 135, 178);
    ctx.strokeText("-3", 135, 228);
    ctx.strokeText("-5", 135, 278);
    drawPoints();
}

function drawPoints(){
    $("#result-table_data tr").each(function (){
        const point = $(this);
        console.log(point.find("td:first-child").text() + "\n" +
            point.find("td:nth-child(2)").text() + "\n" +
            point.find("td:nth-child(3)").text() + "\n" +
            point.find("td:nth-child(4)").text() === "true");
        const x = parseFloat(point.find("td:first-child").text());
        const y = parseFloat(point.find("td:nth-child(2)").text());
        const r = parseFloat(point.find("td:nth-child(3)").text());
        let res = false;
        if (point.find("td:nth-child(4)").text() === "true"){
            res = true;
        }
        if (isNaN(x) || isNaN(y) || isNaN(r)) return;
        drawPoint(x, y, r, res)
    })
}

function drawPoint(x, y, r, res){
    let canvas = document.getElementById("canvas"),
        ctx = canvas.getContext("2d");
    if (res){
        ctx.fillStyle = "#00ff00";
        ctx.strokeStyle = "#008800";
    } else {
        ctx.fillStyle = "#ff0000";
        ctx.strokeStyle = "#880000";
    }
    ctx.beginPath();
    ctx.arc(150 + x * 25, 150 - y * 25, 3, 0, 2 * Math.PI);
    ctx.fill();
    ctx.stroke();
}

function handlePlotClick(canvas, e){
    const rect = canvas.getBoundingClientRect();
    const clickX = e.clientX - rect.left;
    const clickY = e.clientY - rect.top;
    let x = (clickX - 150) / 25;
    let y = (150 - clickY) / 25;
    if (check(x, y, _r)) {
        $(".hidden-x").val(x.toString().substr(0, 12));
        $(".hidden-y").val(y.toString().substr(0, 12));
        $(".hidden-r").val(_r.toString());
        $(".hidden-button").click();
        delayDrawPoints();
    }
}

function delayDrawPoints(){
    setTimeout(() => drawPoints(), 500);
}

function drawLine(ctx, begin, end, stroke = 'black', width = 1) {
    if (stroke) {
        ctx.strokeStyle = stroke;
    }
    if (width) {
        ctx.lineWidth = width;
    }
    ctx.beginPath();
    ctx.moveTo(...begin);
    ctx.lineTo(...end);
    ctx.stroke();
}
