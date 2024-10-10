import json

# Defino una clase Persona con tres propiedades: nombre, edad y altura
class Persona:
    def __init__(self, nombre, edad, altura):
        self.nombre = nombre
        self.edad = edad
        self.altura = altura

# Lista de personas para guardar
personas = [
    Persona("Alice", 30, 1.70),
    Persona("Bob", 25, 1.80),
    Persona("Charlie", 35, 1.75)
]

# Serialización: Guardamos las propiedades de los objetos en un archivo JSON
with open('personas.json', 'w') as archivo:
    # Convertimos las instancias a diccionarios para poder guardarlas en JSON
    datos = [{'nombre': persona.nombre, 'edad': persona.edad, 'altura': persona.altura} for persona in personas]
    json.dump(datos, archivo)

# Lista para deserializar las instancias de Persona
personas_deserializadas = []

# Deserialización: Leemos los datos desde el archivo JSON y creamos las instancias de Persona
with open('personas.json', 'r') as archivo:
    datos = json.load(archivo)
    for elemento in datos:
        personas_deserializadas.append(Persona(elemento['nombre'], elemento['edad'], elemento['altura']))

# Compruebo que lo que he hecho funciona correctamente
for persona in personas_deserializadas:
    print(persona.nombre, persona.edad, persona.altura)
