/*
* Nombre: resumenapp.js
* Descripción: Muestra un resumen de la aplicación seleccionada
*/
function cargaDatos(){
    let aplicacion = localStorage.getItem("crimson_aplicacion")
    fetch("../../servidor/?o=listatablasaplicacion&aplicacion="+aplicacion)
    .then(function(result){
        return result.json()
    }).then(function(datos){
        console.log(datos)
        
       document.getElementById("numtablas").innerHTML ="Tablas en la aplicación: "+ datos.length
    })
}