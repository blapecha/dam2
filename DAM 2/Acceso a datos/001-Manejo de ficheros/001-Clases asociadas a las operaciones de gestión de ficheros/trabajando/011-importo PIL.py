##import os           # módulo para trabajar con archivos y directorios 
##import PIL.Image   #módulo Pillow para manipular imágenes
##
##
##lista = os.listdir("fotos")
##
##for archivo in lista:
##    print(archivo)
##    imagen = PIL.Image.open('fotos/'+archivo)
##    datosexif = imagen._getexif()
##    # Definir el nuevo tamaño para las imágenes
##   new_size = (200, 200)
##    print(datosexif)


####   REDIMENSIONO LAS IMAGENES #####

import os                  # módulo para trabajar con archivos y directorios 
from PIL import Image          # módulo Pillow para manipular imágenes

# Crear la carpeta de salida si no existe
os.makedirs('fotosredimensionadas', exist_ok=True)

# Listar archivos en la carpeta 'fotos'
lista = os.listdir("fotos")

# Definir el nuevo tamaño para las imágenes
new_size = (200, 200)

for archivo in lista:
    try:
        # Abrir la imagen
        imagen = Image.open('fotos/' + archivo)

        
        # Redimensionar la imagen
        imagen_resized = imagen.resize(new_size)

        # Guardar la imagen redimensionada en la carpeta 'fotosredimensionadas'
        imagen_resized.save(f'fotosredimensionadas/{archivo}')
        print(f"{archivo} redimensionada y guardada.")

    except Exception as e:
        print(f"No se pudo procesar {archivo}: {e}")
