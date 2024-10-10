import shutil
 
origen = 'origen/documento.txt'
destino = 'destino/documento.txt'
 
shutil.copy(origen, destino)

print(f"Archivo sea copiado de '{origen}' a '{destino}' de manera correcta.")
