import shutil
 
origen = 'origen/documento.txt'
destino = 'destino/documento.txt'
 
shutil.move(origen, destino)

print(f"Archivo sea movido de '{origen}' a '{destino}' de manera correcta.")
