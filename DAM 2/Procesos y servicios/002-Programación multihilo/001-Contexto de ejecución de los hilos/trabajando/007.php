<?php
    $file = 'tareas.txt';

    // Validar si el archivo existe y no está vacío
    if (!file_exists($file) || filesize($file) === 0) {
        echo json_encode(["error" => "No hay tareas disponibles."]);
        exit;
    }

    // Leer el archivo en un array
    $lines = file($file, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    if (!$lines) {
        echo json_encode(["error" => "Error al leer las tareas."]);
        exit;
    }

    // Obtener la primera tarea
    $tarea = array_shift($lines);

    // Guardar las tareas restantes en el archivo
    file_put_contents($file, implode("\n", $lines));

    // Validar el usuario
    if (empty($_GET['usuario'])) {
        echo json_encode(["error" => "Usuario no especificado."]);
        exit;
    }

    $usuario = htmlspecialchars($_GET['usuario'], ENT_QUOTES, 'UTF-8');

    // Registrar en asignaciones.txt
    $asignacionesFile = 'asignaciones.txt';
    $logEntry = "Al usuario $usuario le ha tocado la tarea: $tarea\n";
    file_put_contents($asignacionesFile, $logEntry, FILE_APPEND);

    // Devolver respuesta JSON
    echo json_encode(["tarea" => $tarea]);
?>
