import os
import PIL.Image

lista = os.listdir("fotos")

for archivo in lista:
    print(archivo)
    imagen = PIL.Image.open('fotos/'+archivo)
    if imagen is None:
       print("La variable 'imagen' no tiene un valor válido.")
    else:
       print("La variable 'imagen' tiene un valor válido.")

    datosexif = imagen._getexif()
    if datosexif is None:
       print("No puedo extraer la metainformacion de la imagen.")
    else:
       print("Puedo extraer la metainformacion de la imagen.")

    

    





    

