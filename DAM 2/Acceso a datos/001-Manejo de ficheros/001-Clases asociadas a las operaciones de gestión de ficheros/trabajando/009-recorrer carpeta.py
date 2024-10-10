#import os
# Esta función obtiene una lista de todos
#los elementos (archivos y carpetas) en el directorio "fotos".

#lista = os.listdir("fotos")

#print(lista)




from pathlib import Path

# Crea un objeto Path para el directorio "fotos" y lo carga

directorio = Path("fotos")

# Listar todos los archivos en el directorio

lista = [archivo.name for archivo in directorio.iterdir() if archivo.is_file()]

# directorio.iterdir(): Este método devuelve  todos los elementos en el directorio.
#archivo.is_file(): Este método comprueba que es un archivo (no una carpeta).
print(lista)
