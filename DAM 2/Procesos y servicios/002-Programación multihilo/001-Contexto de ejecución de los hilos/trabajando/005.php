<?php
    $file = 'tareas.txt';


    $lines = file($file);                   // Leer el archivo en un array, donde cada elemento es una línea
    $numElementos = count($lines); // Contar cuántas líneas hay

    if ($numElementos > 0) {
        $indiceAleatorio = rand(0, $numElementos - 1);     // Generar un índice aleatorio entre 0 y el número de elementos - 1  
        $tarea = $lines[$indiceAleatorio];                  // Obtener la tarea aleatoria
        
        // Mostrar el índice y la tarea seleccionada
        echo "Índice aleatorio: $indiceAleatorio<br>";
        echo "Tarea aleatoria: " . $tarea . "<br>";

       
        unset($lines[$indiceAleatorio]);                     // Eliminar la tarea seleccionada del array
         
        $lines = array_values($lines);                     // Reindexar el array para evitar espacios vacíos

        file_put_contents($file, implode('', $lines));         // Guardar las líneas restantes de nuevo en el archivo

        $myfile = fopen("asignaciones.txt", "a");           // Abrir el archivo asignaciones.txt en modo de adición

        // Escribir la asignación en el archivo asignaciones.txt
        $txt = "Al usuario " . htmlspecialchars($_GET['usuario']) . " le ha tocado la tarea: " . $tarea . "\n";
        fwrite($myfile, $txt);

        fclose($myfile);                    // Cerrar el archivo asignaciones.txt
    } else {
        echo "El archivo no tiene tareas disponibles.";
    }
?>
