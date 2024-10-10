import random
import math
import json

# Declaro una clase Npc con propiedades x, y y angulo
class Npc:
    def __init__(self):
        self.x = random.randint(0, 512)
        self.y = random.randint(0, 512)
        self.angulo = random.random() * math.pi * 2

# Creo una lista de 20 npcs usando comprensión de lista
npcs = [Npc() for _ in range(20)]

# Construyo la lista de diccionarios con las propiedades de cada Npc
cadena = [{"x": npc.x, "y": npc.y, "angulo": npc.angulo} for npc in npcs]

# Serializo los datos a formato JSON con sangría para mejor legibilidad
json_formatted_str = json.dumps(cadena, indent=2)
print(json_formatted_str)

# Guardo los datos en un archivo JSON usando 'with open' para mejor manejo de archivos
with open("basededatos.json", 'w') as mibasededatos:
    json.dump(cadena, mibasededatos, indent=2)
