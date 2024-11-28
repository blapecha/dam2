import asyncio
import websockets
import ssl
import json

connected_clients = set()

async def handle_client(websocket, path):
    connected_clients.add(websocket)

    try:
        async for message in websocket:
            messages = []
            for client in connected_clients:
                messages.append(message)
            if messages:
                await websocket.send(json.dumps(messages))
    except Exception as e:
        print(f"Error: {e}")
    finally:
        connected_clients.remove(websocket)

ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
ssl_context.load_cert_chain("www.jotauve.es_ssl_certificate.cer", "www.jotauve.es_private_key.key")

start_server = websockets.serve(handle_client, "jotauve.es", 3000, ssl=ssl_context)

asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()
