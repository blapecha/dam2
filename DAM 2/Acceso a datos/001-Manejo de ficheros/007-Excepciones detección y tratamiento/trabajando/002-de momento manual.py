class Cliente:
    def __init__(self):
        self.nombre = None
        self.apellidos = None
        self.emails = {"personal": [], "profesional": []}

class Vehiculo:
    def __init__(self):
        self.modelo = None
        self.precio = None
        self.peso = None
        self.itv = None
        self.dimensiones = {"x": None, "y": None, "z": None}

class Concesionario:
    def __init__(self):
        self.nombre = None
        self.emails = {"personal": [], "profesional": []}
        self.localidad = None

# Inicialización de las listas
clientes = []
concesionarios = []

# Creación y adición de un objeto Cliente
clientes.append(Cliente())
clientes[-1].nombre = "Blanca"
clientes[-1].apellidos = "Perez Chavarria"
clientes[-1].emails['profesional'].append("info@educa.com")
clientes[-1].emails['profesional'].append("info@educa.com")
clientes[-1].emails['personal'].append("edukka2@gmail.com")

# Creación y adición de un objeto Concesionario
concesionarios.append(Concesionario())
concesionarios[-1].nombre = "Ford"
concesionarios[-1].emails['profesional'].append("ford@todocoches.com")

# Imprimir los emails
print(clientes[-1].emails)
print(concesionarios[-1].emails)

        


