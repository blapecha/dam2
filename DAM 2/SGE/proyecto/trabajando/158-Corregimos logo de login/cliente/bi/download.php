<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['table'])) {
    $tableHTML = base64_decode($_POST['table']);

    // Set headers to download the file
    header('Content-Type: text/html');
    header('Content-Disposition: attachment; filename="table.html"');

    // Output the HTML content
    echo "<!DOCTYPE html>";
    echo "<html>";
    echo "<head><title>Downloaded Table</title></head>";
    echo "<body>";
    echo $tableHTML;
    echo "</body>";
    echo "</html>";
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['tablejson'])) {
    $tableJson = base64_decode($_POST['tablejson']);

    // Set headers to download the file
    header('Content-Type: application/json');
    header('Content-Disposition: attachment; filename="table.json"');

    // Output the HTML content
    echo $tableJson;
    exit;
}
?>

