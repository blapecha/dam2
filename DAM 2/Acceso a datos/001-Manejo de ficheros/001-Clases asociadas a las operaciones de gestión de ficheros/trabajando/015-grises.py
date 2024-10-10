import os
from PIL import Image, ImageOps ,ImageFilter

lista = os.listdir("fotos")

for archivo in lista:
    print("ok")
    imagen = Image.open(r"fotos/"+archivo)
    nueva_dim = (imagen.width // 2, imagen.height // 2)
    imagen_redimensionada = imagen.resize(nueva_dim)
    imagen_desenfocada = imagen_redimensionada.filter(ImageFilter.GaussianBlur(5))
    imagen_desenfocada.save('fotosredimensionada/'+archivo)
    imagen_desenfocada.close()

    
