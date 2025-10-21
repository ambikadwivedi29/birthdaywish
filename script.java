const canvas = document.getElementById("balloons");
const ctx = canvas.getContext("2d");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

let balloons = [];

function random(min, max) {
  return Math.random() * (max - min) + min;
}

class Balloon {
  constructor() {
    this.x = random(0, canvas.width);
    this.y = canvas.height + 50;
    this.radius = random(20, 40);
    this.color = hsl(${random(300, 200)}, 80%, 70%);
    this.speed = random(0.5, 1.5);
  }

  draw() {
    ctx.beginPath();
    ctx.fillStyle = this.color;
    ctx.ellipse(this.x, this.y, this.radius * 0.8, this.radius, 0, 0, Math.PI * 2);
    ctx.fill();
    ctx.beginPath();
    ctx.moveTo(this.x, this.y + this.radius);
    ctx.lineTo(this.x, this.y + this.radius + 40);
    ctx.strokeStyle = "#fff";
    ctx.stroke();
  }

  update() {
    this.y -= this.speed;
    if (this.y + this.radius < 0) {
      this.y = canvas.height + 50;
      this.x = random(0, canvas.width);
      this.color = hsl(${random(300, 200)}, 80%, 70%);
    }
    this.draw();
  }
}

for (let i = 0; i < 30; i++) {
  balloons.push(new Balloon());
}

function animate() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  balloons.forEach(balloon => balloon.update());
  requestAnimationFrame(animate);
}

animate();
