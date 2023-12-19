function Messi(){
   let titolo = document.getElementById("h");
   titolo.innerHTML = "Leo Messi";
   let img = document.getElementById("img");
   img.src = "image/messi.jpg";
   let list = document.getElementById("list");
   let data = ["Palloni d'oro: 7","Champions: 4","Reti totali: 832","Coppa du mundo: 1"]
   for(let i = 0 ; i < data.length; i++){
      let li = document.createElement("li");
      li.innerHTML = data[i];
      list.appendChild(li);
   }
}

function Ronaldo(){
   let titolo = document.getElementById("h");
   titolo.innerHTML = "Cristiano Ronaldo";
   let img = document.getElementById("img");
   img.src = "../image/ronaldo.jpg";
   let list = document.getElementById("list");
   let data = ["Palloni d'oro: 5","Champions: 5","Reti totali: 869","Europeo: 1"]
   for(let i = 0 ; i < data.length; i++){
      let li = document.createElement("li");
      li.innerHTML = data[i];
      list.appendChild(li);
   }
}