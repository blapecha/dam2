<?php
$mysqli = new mysqli("localhost", "crimson", "crimson", "crimson");

if ($mysqli->connect_error) {
    die("Error de conexión: " . $mysqli->connect_error);
}

// Obtener todas las tablas de la base de datos origen
$result = $mysqli->query("SHOW TABLES");
if (!$result) {
    die("Error obteniendo tablas: " . $mysqli->error);
}

$timestamp = date("Ymd_His"); // Formato: AñoMesDía_HoraMinutoSegundo
$destino = new mysqli("localhost", "usuario", "contraseña", "db_destino");

if ($destino->connect_error) {
    die("Error de conexión a destino: " . $destino->connect_error);
}

while ($row = $result->fetch_array()) {
    $tabla_original = $row[0];
    $tabla_nueva = $tabla_original . "_" . $timestamp;

    echo "Copiando tabla: $tabla_original a $tabla_nueva...\n";

    // Crear la tabla en la base de datos destino con el timestamp
    $query = "CREATE TABLE db_destino.$tabla_nueva AS SELECT * FROM db_origen.$tabla_original";
    if ($destino->query($query)) {
        echo "Tabla $tabla_nueva creada correctamente.\n";
    } else {
        echo "Error copiando $tabla_original: " . $destino->error . "\n";
    }
}

// Cerrar conexiones
$mysqli->close();
$destino->close();

echo "Proceso completado.";
?>
