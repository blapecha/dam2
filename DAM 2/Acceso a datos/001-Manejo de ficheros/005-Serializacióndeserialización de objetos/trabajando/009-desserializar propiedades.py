import random

# Declaro una clase Objeto con tres propiedades: a, b y c
class Objeto:
    def __init__(self, a=0, b=0, c=0.0):
        self.a = a
        self.b = b
        self.c = c

# Creo una lista de 10 objetos con valores aleatorios
objetos = [Objeto(random.randint(1, 100), random.randint(1, 100), random.uniform(0, 1)) for _ in range(10)]

# Serializo las propiedades de los objetos a una cadena y la guardo en un archivo
with open("datos.txt", 'w') as archivo:
    for obj in objetos:
        archivo.write(f"{obj.a},{obj.b},{obj.c}\n")

# Deserializo las propiedades desde el archivo y creo nuevas instancias de Objeto
objetos_deserializados = []
with open("datos.txt", 'r') as archivo:
    for linea in archivo:
        a, b, c = linea.strip().split(',')
        objetos_deserializados.append(Objeto(int(a), int(b), float(c)))

# Imprimo las propiedades de los objetos deserializados
for obj in objetos_deserializados:
    print(f"a: {obj.a}, b: {obj.b}, c: {obj.c}")

