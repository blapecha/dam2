import asyncio                                                                  # Importo la librería de peticiones asincronas
import websockets                                                               # Importo la librería de sockets pero web
import ssl                                                                      # Importo la librería de conexión segura
import json                                                                     # Importo la librería de JSON

connected_clients = set()                                                       # Creo un conjunto vacio de clientes

async def handle_client(websocket, path):                                       # Cuando un cliente se conecte
    connected_clients.add(websocket)                                            # Añado ese cliente a la lista de clientes

    try:                                                                        # Intento, por si da error
        async for message in websocket:                                         # Para cada uno de los mensajes en el socket
            for client in connected_clients:                                    # Para cada cliente conectado
                if client != websocket:                                         # Evita enviar el mensaje al cliente que lo envió
                    await client.send(message)                                  # Envía el mensaje a los otros clientes
    except Exception as e:                                                      # En el caso de que falle algo
        print(f"Error: {e}")                                                    # Lanzame la naturaleza del error
    finally:                                                                    # Y después de todo
        connected_clients.remove(websocket)                                     # Quita el cliente que se ha desconectado de la cola

ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)                           # Estoy obligado a usar SSL en la conexión de websockets                   
ssl_context.load_cert_chain(
    "www.jotauve.es_ssl_certificate.cer",
    "www.jotauve.es_private_key.key"
    )                                                                           # Le introduzco los certificados de protocolo seguro

start_server = websockets.serve(
    handle_client,
    "jotauve.es",
    3000,
    ssl=ssl_context
    )                                                                           # Arranco el servidor

asyncio.get_event_loop().run_until_complete(start_server)                       # Arranco el asíncrono
asyncio.get_event_loop().run_forever()                                          # Ejecuto en bucle para siempre
