############### CLIENTE

import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

host = 'localhost'
port = 9999
client_socket.connect((host, port))

client_socket.send("Hola, soy el cliente2".encode('utf-8'))  
mensaje = client_socket.recv(1024).decode('utf-8')
print(f"He recibido el mensaje: {mensaje}")

client_socket.close()
