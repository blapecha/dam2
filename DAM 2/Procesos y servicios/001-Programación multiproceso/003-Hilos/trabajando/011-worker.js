onmessage = function(datos) {
  const randomValue = Math.floor(Math.random() * 4);  // Genera un número aleatorio entre 0 y 3
  console.log("worker arrancado, vamos a trabajar");      // Mensaje de inicio del worker

  for(let i = 0; i < datos.data.length; i += 4) {       // Recorre cada pixel de la imagen (cada pixel se representa con 4 valores: RGBA)
      let c = datos.data;                                 // Carga los datos de color del pixel actual
      let gris = Math.round((c[i] + c[i + 1] + c[i + 2]) / 3);  // Calcula el promedio de los valores RGB para obtener el valor en escala de grises
      let colorfuerte = gris * 1.5;                       // Multiplica el gris por 1.5 para aumentar la intensidad del color

      console.log("modulo " + randomValue);               // Imprime el módulo que se usará para pintar

      if (colorfuerte > 255) {                            // Asegura que el valor del color no supere el límite RGB
          colorfuerte = 255;
      }
      
      // Dependiendo del valor aleatorio, se pintará el pixel de diferentes colores
      if (randomValue == 0) {        
          datos.data[i] = gris;                           // Actualiza el componente rojo del pixel a gris
          datos.data[i + 1] = gris;                       // Actualiza el componente verde a gris
          datos.data[i + 2] = gris;                       // Actualiza el componente azul a gris
          console.log("pinto en gris");                   // Mensaje de depuración
      }
      
      if (randomValue == 1) {
          datos.data[i] = colorfuerte;                    // Actualiza el componente rojo con el color fuerte
          datos.data[i + 1] = gris;                       // Mantiene el componente verde en gris
          datos.data[i + 2] = gris;                       // Mantiene el componente azul en gris
          console.log("pinto en rojo");                   // Mensaje de depuración
      }

      if (randomValue == 2) {
          datos.data[i] = gris;                            // Mantiene el componente rojo en gris
          datos.data[i + 1] = colorfuerte;                // Actualiza el componente verde con el color fuerte
          datos.data[i + 2] = gris;                       // Mantiene el componente azul en gris
          console.log("pinto en verde");                   // Mensaje de depuración
      }

      if (randomValue == 3) {
          datos.data[i] = gris;                            // Mantiene el componente rojo en gris
          datos.data[i + 1] = gris;                        // Mantiene el componente verde en gris
          datos.data[i + 2] = colorfuerte;                // Actualiza el componente azul con el color fuerte
          console.log("pinto en azul");                    // Mensaje de depuración
      }
  }

  console.log("worker finalizado, devolvemos al hilo principal"); // Mensaje de finalización del worker
  postMessage(datos.data);                                   // Envía los datos procesados de vuelta al hilo principal
}

