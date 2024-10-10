onmessage = function(datos){
    console.log("worker arrancado, vamos a trabajar")
    for(let i = 0;i<datos.data.length;i+=4){              // Recorro cada pixel
        let c = datos.data                                  // Cargo los datos de ese pixel
        let gris = Math.round((c[i] + c[i+1] + c[i+2])/3)   // Saco el promedio
        let rojo=gris*1.5;
        if (rojo>255){                                      // le subo el valor a rojo
          rojo=255
        }
        datos.data[i] = rojo;                               // hago que la imagen sea mas roja
        datos.data[i+1] = gris;                             // actualizo el color verde para que sea gris
        datos.data[i+2] = gris; 
        debugger                            // actualizo el color azul para que sea gris
      }
      //console.log(datos.data)
      console.log("worker finalizado, devolvemos al hilo principal")
     postMessage(datos.data)
}
