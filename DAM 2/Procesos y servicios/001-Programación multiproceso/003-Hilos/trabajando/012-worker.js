onmessage = function(datos){
    console.log("worker arrancado, vamos a trabajar")
    for(let i = 0;i<datos.data.length;i+=4){              // Recorro cada pixel
        let c = datos.data                                  // Cargo los datos de ese pixel

      
        
         //UMBRAL 
        if(datos.data[i] < 100){
            datos.data[i] = 0;                              
            datos.data[i+1] = 0;                             
            datos.data[i+2] = 0;                             
        }else{
            datos.data[i] = 255;                            
            datos.data[i+1] = 255;                            
            datos.data[i+2] = 255;                          
        }
      }
      
      
      //console.log(datos.data)
      console.log("worker finalizado, devolvemos al hilo principal")
     postMessage(datos.data)
}
